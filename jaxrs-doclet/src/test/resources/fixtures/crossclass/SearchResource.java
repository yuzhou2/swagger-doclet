package fixtures.crossclass;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * The SearchResource represents a resource for finding data
 * @version $Id$
 * @author conor.roche
 */
@Path("/v1/object")
public interface SearchResource {

	/**
	 * This is the default adherence requirement
	 */
	public static final double DEFAULT_ADHERENCE = 1.0;
	/**
	 * This is the default angle allowed between a search and trip line
	 */
	public static final double DEFAULT_MAX_BEARING_DIFF = 30.0;
	/**
	 * This is the default minimum length a trip line must be compared to the search line
	 */
	public static final double DEFAULT_MIN_LENGTH_PERCENT = 75.0;
	/**
	 * This is the max percent the total detour is allowed to be compared to the length
	 * of the drivers trip, the total detour is the detour between the drivers origin and the riders origin
	 * + the detour between the drivers destination and the riders destination
	 */
	public static final double DEFAULT_MAX_DETOUR_DRIVER_LENGTH_PERCENT = 100.0;
	/**
	 * This is the max percent the total detour is allowed to be compared to the length
	 * of the riders trip, the total detour is the detour between the drivers origin and the riders origin
	 * + the detour between the drivers destination and the riders destination
	 */
	public static final double DEFAULT_MAX_DETOUR_RIDER_LENGTH_PERCENT = 100.0;
	/**
	 * This is the max percent origin detour as a percentage of the drivers trip that is allowed
	 * for the trip to be considered a match e.g. driver will only detour 15%
	 * of their trip distance, if this value is less than the DEFAULT_ORIGIN_DETOUR_DISTANCE_MIN
	 * then DEFAULT_ORIGIN_DETOUR_DISTANCE_MIN is used
	 */
	public static final double DEFAULT_ORIGIN_DETOUR_DISTANCE_PERCENT = 15.0;
	/**
	 * This is the max percent destination detour as a percentage of the drivers trip that is allowed
	 * for the trip to be considered a match e.g. driver will only detour 15%
	 * of their trip distance, if this value is less than the DEFAULT_DESTINATION_DETOUR_DISTANCE_MIN
	 * then DEFAULT_DESTINATION_DETOUR_DISTANCE_MIN is used
	 */
	public static final double DEFAULT_DESTINATION_DETOUR_DISTANCE_PERCENT = 15.0;
	/**
	 * This is the minimum detour distance in meters used for the origin detour
	 */
	public static final double DEFAULT_ORIGIN_DETOUR_DISTANCE_MIN = 3000.0;
	/**
	 * This is the minimum detour distance in meters used for the destination detour
	 */
	public static final double DEFAULT_DESTINATION_DETOUR_DISTANCE_MIN = 3000.0;
	/**
	 * This is the default number of minutes before the pickup/arrival time
	 * e.g. if a search is for a trip arriving at 7am then trips arriving after 6 am would be considered
	 * e.g. 1 hours before would be included
	 */
	public static final int DEFAULT_PICKUP_MINUTES_MIN_OFFSET = 60;
	/**
	 * This is the default number of minutes after the pickup/arrival time
	 * e.g. if a search is for a trip arriving at 7am then trips arriving before 8 am would be considered
	 * e.g. 1 hours after would be included
	 */
	public static final int DEFAULT_PICKUP_MINUTES_MAX_OFFSET = 60;
	/**
	 * This is the default distance in meters used to pad the bounding box
	 * around the origin and destination of the search, the bounding box
	 * is built by taking the MBR around the trip origin and destination
	 * and then moving this distance at 45 degree angle at the ne and sw corner
	 */
	public static final double DEFAULT_SEARCH_BOX_PADDING_DISTANCE = 10000.0;

	/**
	 * @endpointName Trip Search
	 * @parentEndpointName Discovery
	 * @category Actions
	 * @authentication Not Required
	 * @description This retrieves a list of trips according to the parameters that were supplied
	 * @successCode 200|If the trip details were retrieved ok
	 * @errorCode 400|missing_regions|If missing both an origin and destination.
	 * @errorCode 400|missing_regions|If missing an origin or destination and the sort by method required it
	 * @param originLon the longitude of the origin
	 * @param originLat the latitude of the origin
	 * @param destinationLon the longitude of the destination (null if destination should be ignored)
	 * @param destinationLat the latitude of the destination (null if destination should be ignored)
	 * @param currentLon Optional current location used for contextualising addresses
	 * @param currentLat Optional current location used for contextualising addresses
	 * @param tripType the trip type we want to search for (RIDE, DRIVE, RIDE_OR_DRIVE)
	 * @param departureMinStart This will find trips whose departure minutes is >= this value, use instead of departureTimeStart and departureTimeEnd
	 * @param departureMinEnd This will find trips whose departure minutes is <= this value, use instead of departureTimeStart and departureTimeEnd
	 * @param departureTimeStart This is the start value (millisecs from the epoch) of the time range constraining when trips must depart
	 * @param departureTimeEnd This is the end value (millisecs from the epoch) of the time range constraining when trips must depart
	 * @param pickupTimeStart This is the start value (millisecs from the epoch) of the time range constraining when trips must arrive at the search origin,
	 *            for example if this is now then only trips who arrive at the search origin now or later will be included.
	 *            Optional, if -1 then no start range will be applied
	 * @param pickupTimeEnd This is the end value (millisecs from the epoch) of the time range constraining when trips must arrive at the search origin,
	 *            for example if this is now+3 hours then only trips who arrive at the search origin before now+3 hours will be included
	 *            Optional, if -1 then no end range will be applied
	 * @param onlineSince Only return trips belonging to users that have been online since the specified (millisecs from the epoch). -1 to disable.
	 * @param originLabel A user-provided label for the origin (e.g. an address). Can be null.
	 * @param destinationLabel A user-provided label for the destination (e.g. an address). Can be null.
	 * @param userId The id of the calling user (can be used if logged in to indicate the calling user such that their ride data is excluded from the
	 *            results).
	 * @param maxBearingDiff The is the maximum difference in the bearing angle between the search and trip lines in degrees
	 * @param minLengthPercent The minimum length the matched trip must be as a percent of the search distance
	 * @param maxDetourDriverLengthPercent The max length that the total detour can be compared to the drivers distance
	 * @param maxDetourRiderLengthPercent The max length that the total detour can be compared to the riders distance
	 * @param originDetourDistancePercent The percent of the trip length to use as the max origin detour distance
	 * @param destinationDetourDistancePercent The percent of the trip length to use as the max destination detour distance
	 * @param originDetourDistanceMin The minimum origin detour distance in meters, the max detour distance is the max of this value and the trip length *
	 *            detour distance percent /100
	 * @param destinationDetourDistanceMin The minimum destination detour distance in meters, the max detour distance is the max of this value and the trip
	 *            length * detour distance percent /100
	 * @param searchBoxPaddingDistance This is the maximum padding distance in meters used for the bounding box around the search origin and destination
	 * @param originRadius The radius of the origin bbox in meters, only used when no destination was passed in. If you enter both an origin and destination
	 *            then the searchBoxPaddingDistance is used.
	 * @param destinationRadius The radius of the destination bbox in meters, only used when no origin was passed in. If you enter both an origin and
	 *            destination then the searchBoxPaddingDistance is used.
	 * @param gender the gender of the trip owners returned. Valid options are MALE and FEMALE. Leave blank for either gender to be accepted.
	 * @param adherence Trips that have an adherence set must have an adherence greater than or equal to this, default is 1, adherence of 1 mean the owner
	 *            of the trip has been online in the last 36 weeks
	 * @param lightObjects if true does not perform any reverse-geocoding of the trip origin and
	 *            destination and does not estimate prices for the trips.
	 * @param logSearch Whether the search should be logged default is true
	 * @param pageNum Which page to return, 1-indexed. For example, page=2 with max=50 will get users 51-100.
	 * @param pageSize Number of results returned for each page
	 * @param sortBy How to sort the results options are (START_TIME, PICKUP_TIME, ORIGIN_DISTANCE, TOTAL_DETOUR_DISTANCE, START_TIME_ORIGIN_DISTANCE,
	 *            PICKUP_TIME_ORIGIN_DISTANCE, BEST_MATCH, NONE)
	 *            default is PICKUP_TIME_ORIGIN_DISTANCE
	 * @param userFields A comma-separated list of fields that should be included with the user object. <br>
	 *            Clients <b>SHOULD</b> ask for the minimum amount of data they require.<br>
	 *            If this parameter is not included then the default is used.<br>
	 *            If this is passed in with an empty value as in ?userFields= then this will return just the user id and a flag indicating whether the user
	 *            account has been deleted. <br>
	 *            The other supported values are:<br>
	 *            FULL_PUBLIC, FULL, NAME, ALIAS, REGISTRATION_TIME, EMAIL,PHOTO_URL, HOME_CITY, COUNTRY, WEBPAGE, <br>
	 *            BIO, LAST_KNOWN_LOCATION, LAST_KNOWN_LOCATION_ADDRESS, IS_CURRENTLY_RIDESHARING, PHONE_NUMBER, <br>
	 *            LAST_TRIP_SEARCH, RATING, IS_FAVOURITE, VALIDATIONS_EMAIL, VALIDATIONS_PHONE, VALIDATIONS_NUM_POSITIVE_REVIEWS,<br>
	 *            VALIDATIONS_CONNECTED_FACEBOOK, VALIDATIONS_AFFILIATES, LAST_SEEN_TIMESTAMP, LAST_LOGIN_TIMESTAMP, GENDER, <br>
	 *            CREDIT_BALANCES, LOCALE, TEST_ACCOUNT_STATUS, LAST_CHANGED_TIMESTAMP_BASIC, ACHIEVEMENTS, DISTANCE<br>
	 *            NOTE: The calling user can see all of their own details but will only see a subset of the public fields of another user.
	 * @return An XML or JSON representation of the trips that were found.
	 */
	@GET
	@Path("/trip/searchTrips")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public SearchResults searchTrips(
			@QueryParam("originLon") Double originLon,
			@QueryParam("originLat") Double originLat,
			@QueryParam("destinationLon") Double destinationLon,
			@QueryParam("destinationLat") Double destinationLat,
			@QueryParam("currentLon") Double currentLon,
			@QueryParam("currentLat") Double currentLat,
			@QueryParam("tripType") @DefaultValue("RIDE_OR_DRIVE") TripType tripType,
			@QueryParam("departureMinStart") @DefaultValue("-1") int departureMinStart,
			@QueryParam("departureMinEnd") @DefaultValue("-1") int departureMinEnd,
			@QueryParam("departureTimeStart") @DefaultValue("-1") long departureTimeStart,
			@QueryParam("departureTimeEnd") @DefaultValue("-1") long departureTimeEnd,
			@QueryParam("pickupTimeStart") @DefaultValue("-1") long pickupTimeStart,
			@QueryParam("pickupTimeEnd") @DefaultValue("-1") long pickupTimeEnd,
			@QueryParam("onlineSince") @DefaultValue("-1") long onlineSince,
			@QueryParam("originLabel") String originLabel,
			@QueryParam("destinationLabel") String destinationLabel,
			@QueryParam("userId") @DefaultValue("-1") long userId,
			@QueryParam("originRadius") @DefaultValue("10000") int originRadius,
			@QueryParam("destinationRadius") @DefaultValue("10000") int destinationRadius,
			@QueryParam("searchBoxPaddingDistance") @DefaultValue("" + DEFAULT_SEARCH_BOX_PADDING_DISTANCE) double searchBoxPaddingDistance,
			@QueryParam("maxBearingDiff") @DefaultValue("" + DEFAULT_MAX_BEARING_DIFF) double maxBearingDiff,
			@QueryParam("minLengthPercent") @DefaultValue("" + DEFAULT_MIN_LENGTH_PERCENT) double minLengthPercent,
			@QueryParam("maxDetourDriverLengthPercent") @DefaultValue("" + DEFAULT_MAX_DETOUR_DRIVER_LENGTH_PERCENT) double maxDetourDriverLengthPercent,
			@QueryParam("maxDetourRiderLengthPercent") @DefaultValue("" + DEFAULT_MAX_DETOUR_RIDER_LENGTH_PERCENT) double maxDetourRiderLengthPercent,
			@QueryParam("originDetourDistancePercent") @DefaultValue("" + DEFAULT_ORIGIN_DETOUR_DISTANCE_PERCENT) double originDetourDistancePercent,
			@QueryParam("destinationDetourDistancePercent") @DefaultValue("" + DEFAULT_DESTINATION_DETOUR_DISTANCE_PERCENT) double destinationDetourDistancePercent,
			@QueryParam("originDetourDistanceMin") @DefaultValue("" + DEFAULT_ORIGIN_DETOUR_DISTANCE_MIN) double originDetourDistanceMin,
			@QueryParam("destinationDetourDistanceMin") @DefaultValue("" + DEFAULT_DESTINATION_DETOUR_DISTANCE_MIN) double destinationDetourDistanceMin,
			@QueryParam("gender") Gender gender, @QueryParam("adherence") @DefaultValue("" + DEFAULT_ADHERENCE) double adherence,
			@QueryParam("lightObjects") boolean lightObjects, @QueryParam("logSearch") @DefaultValue("true") boolean logSearch,
			@QueryParam("sortBy") @DefaultValue("START_TIME_ORIGIN_DISTANCE") MatchSortBy sortBy, @QueryParam("pageNum") @DefaultValue("1") Integer pageNum,
			@QueryParam("pageSize") @DefaultValue("20") Integer pageSize,
			@QueryParam("userFields") @DefaultValue("ALIAS, PHOTO_URL, LAST_SEEN_TIMESTAMP, PHONE_NUMBER") String userFields);

}

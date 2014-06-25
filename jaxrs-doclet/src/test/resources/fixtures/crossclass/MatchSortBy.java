/*
 * Copyright © 2013 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.crossclass;

import javax.xml.bind.annotation.XmlEnum;

/**
 * The MatchSortBy represents options to order trip matches by
 * @version $Id: MatchSortBy.java 116255 2014-04-29 07:53:55Z conor.roche $
 * @author conorroche
 */
@XmlEnum
public enum MatchSortBy {

	/**
	 * This orders by the trip start time
	 */
	START_TIME,
	/**
	 * This orders by the pickup time
	 */
	PICKUP_TIME,
	/**
	 * This orders by the distance from the search origin and trip origin
	 */
	ORIGIN_DISTANCE,
	/**
	 * This orders by the total detour at each end from the search to the trip
	 */
	TOTAL_DETOUR_DISTANCE,
	/**
	 * This orders by the start time, and for start times that are equal in minutes it subsequently orders
	 * by distance
	 */
	START_TIME_ORIGIN_DISTANCE,
	/**
	 * This orders by the pickup time, and for pickup times that are equal in minutes it subsequently orders
	 * by distance
	 */
	PICKUP_TIME_ORIGIN_DISTANCE,
	/**
	 * This orders by the best match score for example could be the total detour distance with the main weighting followed by
	 * a smaller weighting to the difference between pickup time and desired pickup time
	 */
	BEST_MATCH,
	/**
	 * Indicates that sorting has been turned off
	 */
	NONE,
	/**
	 * This uses random ordering of trips
	 */
	RANDOM;

}

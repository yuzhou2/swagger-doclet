/*
 * Copyright © 2011 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.crossclass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Trips represents a list of trips in the system.
 * @version $Id: SearchResults.java 113459 2014-03-25 15:18:24Z conor.roche $
 * @author martingillin
 */
@XmlRootElement(name = "searchResults")
public class SearchResults implements Serializable {

	private static final long serialVersionUID = 1L;

	private long searchId = -1;

	/** paginator */
	private Paginator paginator;

	private List<TripResult> tripResults;

	/**
	 * This creates a SearchResults object with empty results
	 */
	public SearchResults() {
		super();
		this.tripResults = new ArrayList<TripResult>();
		this.paginator = Paginator.DEFAULT;
	}

	/**
	 * This creates SearchResults object with empty results
	 * @param searchId The search id for the search these results are for
	 */
	public SearchResults(long searchId) {
		super();
		this.searchId = searchId;
		this.tripResults = new ArrayList<TripResult>();
		this.paginator = Paginator.DEFAULT;
	}

	/**
	 * This creates SearchResults with the given trip list and paginator
	 * @param searchId The search id for the search these results are for
	 * @param tripResults The trip list
	 * @param paginator The paginator
	 */
	public SearchResults(long searchId, List<TripResult> tripResults, Paginator paginator) {
		super();
		this.searchId = searchId;
		this.tripResults = tripResults;
		this.paginator = paginator;
	}

	/**
	 * This is the unique identifier for this particular search
	 * @return the unique identifier for this particular search
	 */
	public long getSearchId() {
		return this.searchId;
	}

	/**
	 * This sets the searchId
	 * @param searchId the searchId to set
	 */
	public void setSearchId(long searchId) {
		this.searchId = searchId;
	}

	/**
	 * This gets the tripResults
	 * @return the tripResults
	 */
	@XmlElement(name = "trips")
	@JsonProperty("trips")
	public List<TripResult> getTripResults() {
		return this.tripResults;
	}

	/**
	 * This sets the tripResults
	 * @param tripResults the tripResults to set
	 */
	public void setTripResults(List<TripResult> tripResults) {
		this.tripResults = tripResults;
	}

	/**
	 * This contains paging info for current request
	 * @return the paginator
	 */
	@XmlElement(required = true)
	public Paginator getPaginator() {
		return this.paginator;
	}

	/**
	 * This sets the paginator
	 * @param paginator the paginator to set
	 */
	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchResults [searchId=" + this.searchId + ", paginator=" + this.paginator + ", tripResults=" + this.tripResults + "]";
	}

}
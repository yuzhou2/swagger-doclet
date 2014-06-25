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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Paginator represents paging information.
 * @version $Id: Paginator.java 114470 2014-04-04 10:34:47Z martin.gerner $
 * @author yiqing
 */
@XmlRootElement(name = "paging")
public class Paginator implements Serializable {

	private static final long serialVersionUID = 100;

	/** number of items on each page */
	private int pageSize;
	/** page number */
	private int pageNum;
	/** total number of pages */
	private int totalPages;
	/** total results - possibly restricted to an upper limit */
	private long totalResults;

	/**
	 * raw total results - this is the total number of matches that could be found in the db, unfiltered
	 */
	private long rawTotalResults;

	private int size;

	private static final int DEFAULT_SIZE = 20;

	/** empty paginator */
	public static final Paginator DEFAULT = new Paginator(DEFAULT_SIZE, 1, 0);

	/** paginator */
	public Paginator() {
		super();
	}

	/**
	 * This creates a Paginator
	 * @param pageSize the maximum page size, according to the request
	 * @param pageNum the page number, according to the request
	 * @param totalResults the total number of results available across all pages
	 */
	public Paginator(int pageSize, int pageNum, long totalResults) {
		super();
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.totalResults = totalResults;
		this.rawTotalResults = totalResults;
		updatePagingInfo(totalResults);
	}

	/**
	 * This is the number of items displayed in each page
	 * @return the size
	 */
	@XmlAttribute(required = true)
	public int getSize() {
		return this.size;
	}

	/**
	 * This sets the size
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;

		if (size < 0) {
			this.pageSize = DEFAULT_SIZE;
		}
	}

	/**
	 * This is the page number
	 * @return the page number
	 */
	@XmlAttribute(required = true)
	public int getNumber() {
		return this.pageNum;
	}

	/**
	 * This sets the number
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.pageNum = number;
	}

	/**
	 * This is the total number of available pages
	 * @return the total number of available pages
	 */
	@XmlAttribute(required = true)
	public int getTotalPages() {
		return this.totalPages;
	}

	/**
	 * This sets the totalPages
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * The total number of results that are available for pagination. This may be restricted; for the total number of matching results in the Carma system, see
	 * rawTotalResults.
	 * @return The total number of results that are available for pagination. This may be restricted; for the total number of matching results in the Carma
	 *         system, see rawTotalResults.
	 */
	@XmlAttribute(required = true)
	public long getTotalResults() {
		return this.totalResults;
	}

	/**
	 * This sets the totalResults
	 * @param totalResults the totalResults to set
	 */
	public void setTotalResults(long totalResults) {
		this.totalResults = totalResults;
	}

	/**
	 * This gets the total number of raw results; this shows the number of Carma results that matched the API query. This may be higher than the number of
	 * results that are actually available for pagination - for the available number, see totalResults.
	 * @return the total number of raw results; this shows the number of Carma results that matched the API query. This may be higher than the number of
	 *         results that are actually available for pagination - for the available number, see totalResults.
	 */
	public long getRawTotalResults() {
		return this.rawTotalResults;
	}

	/**
	 * This sets the rawTotalResults
	 * @param rawTotalResults the rawTotalResults to set
	 */
	public void setRawTotalResults(long rawTotalResults) {
		this.rawTotalResults = rawTotalResults;
	}

	private void updatePagingInfo(long totalResults) {
		if (totalResults < 0) {
			return;
		}

		// minimum value of totalPages should be 1
		this.totalPages = (int) Math.ceil((double) totalResults / (double) this.pageSize);
		this.totalPages = Math.max(1, this.totalPages);

		// calculate the size of the current page
		if (this.pageNum < 0 || this.pageNum > this.totalPages) {
			this.size = 0;
		} else {
			if (this.pageNum < this.totalPages) {
				this.size = this.pageSize;
			} else {
				// pageNum == totalPages
				this.size = (int) (totalResults - (this.totalPages - 1) * this.pageSize);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Paginator [size=").append(this.pageSize).append(", number=").append(this.pageNum).append(", totalPages=").append(this.totalPages)
				.append(", totalResults=").append(this.totalResults).append("]");
		return builder.toString();
	}
}
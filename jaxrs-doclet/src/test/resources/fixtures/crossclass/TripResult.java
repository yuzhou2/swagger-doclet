/*
 * Copyright © 2014 Avego Ltd., All Rights Reserved.
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
 * The TripResult represents a trip along with its owners details which is a result of a search
 * @version $Id: TripResult.java 119801 2014-06-07 21:25:29Z conor.roche $
 * @author conorroche
 */
@XmlRootElement(name = "tripResult")
public class TripResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private double distanceToOrigin;
	private double score = -1;

	private User owner;

	/**
	 * This is the distance in meters between the search origin and the trip origin
	 * @return the distance in meters between the search origin and the trip origin
	 */
	@XmlAttribute
	public double getDistanceToOrigin() {
		return this.distanceToOrigin;
	}

	/**
	 * This sets the distance in meters between the search origin and the trip origin
	 * @param distanceToOrigin the distance in meters between the search origin and the trip origin
	 */
	public void setDistanceToOrigin(double distanceToOrigin) {
		this.distanceToOrigin = distanceToOrigin;
	}

	/**
	 * This is the score for this trip result, -1 if not available
	 * @return the score for this trip result, -1 if not available
	 */
	@XmlAttribute
	public double getScore() {
		return this.score;
	}

	/**
	 * This sets the score for this trip result, -1 if not available
	 * @param score the score for this trip result, -1 if not available
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * This gets the owner
	 * @return the owner
	 */
	public User getOwner() {
		return this.owner;
	}

	/**
	 * This sets the owner
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TripResult [distanceToOrigin=" + this.distanceToOrigin + ", score=" + this.score + ", owner=" + this.owner + "]";
	}

}

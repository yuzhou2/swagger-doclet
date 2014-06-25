/*
 * Copyright � 2012 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.crossclass;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * The TripType represents the different types of Trip that this API supports
 * @version $Id: TripType.java 106190 2014-01-27 12:13:43Z conor.roche $
 * @author martingillin
 */
@XmlEnum
public enum TripType {

	/**
	 * This is for a Trip type of DRIVE
	 */
	@XmlEnumValue("DRIVE")
	DRIVE,

	/**
	 * This is for a Trip type of RIDE
	 */
	@XmlEnumValue("RIDE")
	RIDE,

	/**
	 * This is for a Trip type of RIDE_OR_DRIVE
	 */
	@XmlEnumValue("RIDE_OR_DRIVE")
	RIDE_OR_DRIVE,
}
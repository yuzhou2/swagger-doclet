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

import javax.xml.bind.annotation.XmlEnum;

/**
 * The Gender represents
 * @version $Id: Gender.java 113459 2014-03-25 15:18:24Z conor.roche $
 * @author martingerner
 */
@XmlEnum
public enum Gender {
	/**
	 * This is for male gender
	 */
	MALE,
	/**
	 * This is for female gender
	 */
	FEMALE,
	/**
	 * This is for unknown,unspecified, or other non male/female genders
	 */
	OTHER;

	/**
	 * Returns the gender represented by the specified string. Anything starting with an "m" returns MALE, anything starting with "f" returns FEMALE, and
	 * anything else returns OTHER.
	 * @param str
	 * @return
	 */
	public static Gender getGender(String str) {
		if (str != null && str.toLowerCase().startsWith("m")) {
			return MALE;
		}

		if (str != null && str.toLowerCase().startsWith("f")) {
			return FEMALE;
		}

		return OTHER;
	}

}

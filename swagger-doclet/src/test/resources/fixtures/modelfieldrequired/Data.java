/*
 * Copyright © 2014 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.modelfieldrequired;

/**
 * The Data represents a pojo for testing field required
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class Data {

	private String defaultField;
	/**
	 * @required
	 */
	private String requiredField;
	/**
	 * @optional
	 */
	private String optionalField;

	private String defaultGetterTag;
	private String requiredGetterTag;
	private String optionalGetterTag;

	public String getDefaultGetterTag() {
		return this.defaultGetterTag;
	}

	/**
	 * @required
	 */
	public String getRequiredGetterTag() {
		return this.requiredGetterTag;
	}

	/**
	 * @optional
	 */
	public String getOptionalGetterTag() {
		return this.optionalGetterTag;
	}

	/**
	 * @optional
	 */
	public String getOptionalMethodTag() {
		return null;
	}

	/**
	 * @required
	 */
	public String getRequiredMethodTag() {
		return null;
	}

	public String getDefaultMethodTag() {
		return null;
	}

}

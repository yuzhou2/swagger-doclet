/*
 * Copyright © 2014 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.producesconsumes;

/**
 * The Data represents a simple pojo for testing produces/consumes
 * @version $Id$
 * @author conor.roche
 */
public class Data {

	private String value;

	/**
	 * This creates a Input
	 */
	public Data() {
	}

	/**
	 * This creates a Data
	 * @param value
	 */
	public Data(String value) {
		super();
		this.value = value;
	}

	@SuppressWarnings("javadoc")
	public String getValue() {
		return this.value;
	}

}

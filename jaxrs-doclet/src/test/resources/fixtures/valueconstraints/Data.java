/*
 * Copyright © 2014 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.valueconstraints;

/**
 * The Data represents a simple pojo for testing produces/consumes
 * @version $Id$
 * @author conor.roche
 */
public class Data {

	private EnumValue value;

	/**
	 * @min 1
	 */
	private int minFieldTag;
	/**
	 * @max 10
	 */
	private int maxFieldTag;
	/**
	 * @min 2
	 * @max 8
	 */
	private int minMaxFieldTag;

	private int minGetterTag;
	private int maxGetterTag;
	private int minMaxGetterTag;

	/**
	 * This creates a Input
	 */
	public Data() {
	}

	/**
	 * This creates a Data
	 * @param value
	 * @param minFieldTag
	 * @param maxFieldTag
	 * @param minMaxFieldTag
	 * @param minGetterTag
	 * @param maxGetterTag
	 * @param minMaxGetterTag
	 */
	public Data(EnumValue value, int minFieldTag, int maxFieldTag, int minMaxFieldTag, int minGetterTag, int maxGetterTag, int minMaxGetterTag) {
		super();
		this.value = value;
		this.minFieldTag = minFieldTag;
		this.maxFieldTag = maxFieldTag;
		this.minMaxFieldTag = minMaxFieldTag;
		this.minGetterTag = minGetterTag;
		this.maxGetterTag = maxGetterTag;
		this.minMaxGetterTag = minMaxGetterTag;
	}

	@SuppressWarnings("javadoc")
	public EnumValue getValue() {
		return this.value;
	}

	@SuppressWarnings("javadoc")
	public int getMinFieldTag() {
		return this.minFieldTag;
	}

	@SuppressWarnings("javadoc")
	public int getMaxFieldTag() {
		return this.maxFieldTag;
	}

	@SuppressWarnings("javadoc")
	public int getMinMaxFieldTag() {
		return this.minMaxFieldTag;
	}

	/**
	 * @min 1
	 */
	public int getMinGetterTag() {
		return this.minGetterTag;
	}

	/**
	 * @max 10
	 */
	public int getMaxGetterTag() {
		return this.maxGetterTag;
	}

	/**
	 * @min 2
	 * @max 8
	 */
	public int getMinMaxGetterTag() {
		return this.minMaxGetterTag;
	}

}

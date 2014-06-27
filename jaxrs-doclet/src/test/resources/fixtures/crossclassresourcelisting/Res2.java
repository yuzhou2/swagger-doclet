/*
 * Copyright © 2014 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.crossclassresourcelisting;

import javax.ws.rs.GET;

@SuppressWarnings("javadoc")
public class Res2 {

	/**
	 * @resourcePath c
	 * @priority 3
	 * @resourceDescription C
	 */
	@GET
	public void getC() {
		// noop
	}

	/**
	 * @parentEndpoint b
	 * @resourceDescription B
	 */
	@GET
	public void getB() {
		// noop
	}

}

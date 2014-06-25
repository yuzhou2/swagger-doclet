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

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * The ValueContraintsResource represents a resource for testing value contrainst; enum and min/max values of params and properties.
 * @version $Id$
 * @author conor.roche
 */
@Path("/valueconstraints")
public class ValueContraintsResource {

	@POST
	public Data postData(Data data, @QueryParam(value = "enumValue") EnumValue enumValue) {
		return data;
	}
}

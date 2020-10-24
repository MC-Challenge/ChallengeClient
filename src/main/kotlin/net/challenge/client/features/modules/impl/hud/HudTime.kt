/*
 * Challenge Client
 * https://github.com/MC-Challenge/ChallengeClient/

 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.challenge.client.features.modules.impl.hud

import net.challenge.client.features.modules.SimpleHudModule
import net.challenge.client.features.modules.annotations.ModuleInfo
import java.text.SimpleDateFormat
import java.util.*

@ModuleInfo(name = "Time")
object HudTime : SimpleHudModule() {

    override fun getValue(): String {
        val date = Date()
        val formatter = SimpleDateFormat("HH:mm:ss");
        return formatter.format(date)

    }

    override fun getDisplayName(): String {
        return "Time";
    }
}
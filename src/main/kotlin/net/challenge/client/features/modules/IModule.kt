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

package net.challenge.client.features.modules

import me.zero.alpine.listener.Listenable
import net.challenge.client.utils.IMC
import net.challenge.client.value.IValueHandler

/**
 * A function of the client
 */
interface IModule : Listenable, IValueHandler, IMC {

    /**
     * Name of the module
     */
    val name: String

    /**
     * Description of the module
     */
    val description: String

    /**
     * Will be executed when the module is activated
     */
    fun onEnable()

    /**
     * Will be executed when the module is deactivated
     */
    fun onDisable()

    /**
     * Toggle the module
     */
    fun toggle() {
        setEnabled(!isEnabled())
    }

    /**
     * Set the enabled state
     * @param enabled Set state to this value
     */
    fun setEnabled(enabled: Boolean)

    /**
     * Is the module enabled
     */
    fun isEnabled(): Boolean
}
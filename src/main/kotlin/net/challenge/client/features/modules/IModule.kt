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

/**
 * A function of the client
 */
interface IModule : Listenable {

    /**
     * Name of the module
     */
    var name: String

    /**
     * Description of the module
     */
    var description: String

    /**
     * Is the module enabled
     */
    var enabled: Boolean


    /**
     * Will be executed when the module is activated
     */
    fun onEnable();

    /**
     * Will be executed when the module is deactivated
     */
    fun onDisable();

    /**
     * Toggle the module
     */
    fun toggle()
}
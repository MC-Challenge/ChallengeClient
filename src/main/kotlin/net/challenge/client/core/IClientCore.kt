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

package net.challenge.client.core

import me.zero.alpine.bus.EventBus

import net.challenge.client.core.info.ClientInfo
import net.challenge.client.core.info.IClientInfo
import net.challenge.client.utils.IMC
import org.apache.logging.log4j.Logger

interface IClientCore : IMC {

    /**
     * Info of the client
     */
    val info: IClientInfo

    /**
     * Simple logger for logging
     */
    val logger: Logger

    /**
     * Event bus for calling events
     */
    val eventBus: EventBus

    /**
     * We executed before the client starts.
     *
     * @return If the return value is false, the start process is aborted.
     */
    fun onPreStart(): Boolean

    /**
     * This will be executed as soon as the client is successfully started.
     */
    fun onPostStart()

    /**
     * Once the client is turned off, this method is finally executed.
     */
    fun onShutdown()
}
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
import me.zero.alpine.bus.ExtendEventManager
import net.challenge.client.core.info.ClientInfo
import net.challenge.client.core.info.IClientInfo
import net.challenge.client.features.cosmetics.registry.CosmeticRegistry
import net.challenge.client.features.cosmetics.registry.ICosmeticRegistry
import net.challenge.client.features.modules.registry.IModuleRegistry
import net.challenge.client.features.modules.registry.ModuleRegistry
import net.challenge.client.ui.font.FontHandler
import net.challenge.client.value.registry.IValueRegistry
import net.challenge.client.value.registry.ValueRegistry
import net.norisk.core.features.command.CommandRegistry
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object ClientCore : IClientCore {

    override val info: IClientInfo = ClientInfo()

    override val logger: Logger = LogManager.getLogger(ClientCore.javaClass)

    override val eventBus: EventBus = ExtendEventManager()

    val commandRegistry: CommandRegistry = CommandRegistry()

    val cosmeticRegistry: ICosmeticRegistry = CosmeticRegistry()

    val valueRegistry: IValueRegistry = ValueRegistry()

    val moduleRegistry: IModuleRegistry = ModuleRegistry()

    override fun onPreStart(): Boolean {
        logger.debug("PRE-Start")
        return true
    }

    override fun onPostStart() {
        logger.debug("Post-Start")
        logger.info(info.toString())

        FontHandler.load()
        cosmeticRegistry.load()
        moduleRegistry.load()
    }

    override fun onShutdown() {
        logger.info("Shutdown ...")
    }
}
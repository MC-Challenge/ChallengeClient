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
import net.challenge.client.features.commands.handler.CmdHandler
import net.challenge.client.features.commands.handler.ICommandHandler
import net.challenge.client.features.commands.impl.CmdHelp
import net.challenge.client.features.commands.impl.CmdToggle
import net.challenge.client.features.commands.impl.CmdToggleCosmetic
import net.challenge.client.features.cosmetics.registry.CosmeticRegistry
import net.challenge.client.features.cosmetics.registry.ICosmeticRegistry
import net.challenge.client.features.modules.impl.challenge.ReachChallenge
import net.challenge.client.features.modules.impl.hud.*
import net.challenge.client.features.modules.registry.IModuleRegistry
import net.challenge.client.features.modules.registry.ModuleRegistry
import net.challenge.client.ui.font.FontHandler
import net.challenge.client.ui.hud.customHud.GuiCustomHud
import net.challenge.client.ui.hud.customHud.renderer.HudRenderer
import net.challenge.client.ui.hud.customHud.renderer.IHudRenderer
import net.challenge.client.ui.screen.WidgetScreen
import net.challenge.configu.config.IConfig
import net.challenge.configu.config.JsonConfig
import net.minecraft.util.ChatComponentText
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File

//TODO: Fix [CustomHUD] instance!!! @NILS
object ClientCore : IClientCore {

    override val info: IClientInfo = ClientInfo()

    override val logger: Logger = LogManager.getLogger(ClientCore.javaClass)

    override val eventBus: EventBus = ExtendEventManager()

    val commandHandler: ICommandHandler = CmdHandler(
            CmdHelp, CmdToggle, CmdToggleCosmetic
    )

    val cosmeticRegistry: ICosmeticRegistry = CosmeticRegistry()

    val hudRenderer: IHudRenderer = HudRenderer

    val moduleRegistry: IModuleRegistry = ModuleRegistry()

    lateinit var customHud: GuiCustomHud

    lateinit var config: IConfig


    override fun onPreStart(): Boolean {
        logger.debug("PRE-Start")
        return true
    }

    override fun onPostStart() {
        logger.debug("Post-Start")
        logger.info(info.toString())

        customHud = GuiCustomHud()
        config = JsonConfig(File(mc.mcDataDir, "challengeClient/config"), true)

        FontHandler.load()
        cosmeticRegistry.load()
        initModules()
        commandRegistry.init()
        moduleRegistry.load()

        config.load()
    }

    private fun initModules() {
        moduleRegistry.registerModules(
                HudDirection,
                HudKeystrokes,
                HudXYZ,
                ReachChallenge,
                HudReach,
                HudTime
        )

        moduleRegistry.load()
    }

    override fun onShutdown() {
        logger.info("Shutdown ...")

        config.save()
    }

    /**
     * Send a message in the chat.
     *
     * @param message Message to send.
     */
    fun sendChatMessage(message: String) {
        mc.thePlayer.addChatMessage(ChatComponentText("§a" + info.name + " §8> §7 $message"))
    }
}
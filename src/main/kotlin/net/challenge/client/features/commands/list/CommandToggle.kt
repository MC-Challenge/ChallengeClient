/*
 * Challenge Client
 * https://github.com/MC-Challenge/ChallengeClient/
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.challenge.client.features.commands.list

import net.challenge.client.core.ClientCore.info
import net.challenge.client.core.ClientCore.moduleRegistry
import net.challenge.client.features.commands.AbstractCommand
import net.challenge.client.features.modules.Module
import net.minecraft.client.Minecraft
import net.minecraft.util.ChatComponentText
import net.norisk.core.features.command.annotations.CommandInfo

@CommandInfo(name = "toggle", description = "Toggle an Module :)", syntax = "Use §a'Toggle <Module>'§7, to toggle a Module.", aliases = "t")
class CommandToggle : AbstractCommand() {
    override fun run(arguments: Array<String?>) {
        if (arguments.isEmpty()) {
            sendSyntax()
            return
        }
        val module = moduleRegistry.getModule(arguments[0]!!) as Module?
        if (module != null) {
            module.setEnabled(!module.isEnabled())
            if (module.isEnabled()) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText("§a" + info.name + " §8> §7The Module is now §aenabled§7."))
            } else {
                Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText("§a" + info.name + " §8> §7The Module is now §cdisabled§7."))
            }
        }
    }
}

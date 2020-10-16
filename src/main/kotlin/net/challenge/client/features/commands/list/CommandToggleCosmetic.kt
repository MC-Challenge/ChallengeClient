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

import net.challenge.client.core.ClientCore.cosmeticRegistry
import net.challenge.client.core.ClientCore.info
import net.challenge.client.features.commands.AbstractCommand
import net.minecraft.client.Minecraft
import net.minecraft.util.ChatComponentText
import net.challenge.client.features.commands.annotations.CommandInfo

@CommandInfo(name = "toggleCosmetic", description = "Toggle an Cosmetic :)", syntax = "§7Use §a'ToggleC <Cosmetic>'§7, to toggle a Cosmetic.", aliases = "tc, toggleC")
class CommandToggleCosmetic : AbstractCommand() {
    override fun run(arguments: Array<String?>) {
        if (arguments.isEmpty()) {
            sendSyntax()
            return
        }

        val module = cosmeticRegistry.getCosmetic(arguments[0]!!)

        module.enabled = !module.enabled
        if (module.enabled) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText("§a" + info.name + " §8> §7The Cosmetic §a'" + module.name + "' §7is now §aenabled§7."))
        } else {
            Minecraft.getMinecraft().thePlayer.addChatMessage(ChatComponentText("§a" + info.name + " §8> §7The Cosmetic §a'" + module.name + "' §7is now §cdisabled§7."))
        }
    }
}
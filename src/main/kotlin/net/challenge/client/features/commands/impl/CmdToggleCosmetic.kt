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

package net.challenge.client.features.commands.impl

import net.challenge.client.core.ClientCore
import net.challenge.client.features.commands.Command
import net.challenge.client.features.commands.CommandInfo
import net.minecraft.util.ChatComponentText

@CommandInfo(name = "toggleCosmetic", description = "Toggle an Cosmetic :)", syntax = "Use §a'ToggleC <Cosmetic>'§7, to toggle a Cosmetic.", aliases = ["tc", "toggleC"])
object CmdToggleCosmetic : Command() {

    override fun run(arguments: Array<String>) {
        if (arguments.isEmpty()) {
            sendSyntax()
            return
        }

        val cosmetic = ClientCore.cosmeticRegistry.getCosmetic(arguments[0]) ?: let {
            sendSyntax()
            return
        }

        cosmetic.toggle()

        val stateText = if (cosmetic.enabled) "§aenabled" else "§cdisabled"

        sendMessage("The Cosmetic §a'" + cosmetic.name+ "' §7is now $stateText§7.")
    }
}
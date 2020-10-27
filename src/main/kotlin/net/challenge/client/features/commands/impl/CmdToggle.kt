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

@CommandInfo(name = "toggle", description = "Toggle an Module :)", syntax = "Use §a'Toggle <Module>'§7, to toggle a Module.", aliases = [ "t" ])
object CmdToggle : Command() {

    override fun run(arguments: Array<String>) {
        if (arguments.isEmpty()) {
            sendSyntax()
            return
        }

        val module = ClientCore.moduleRegistry.getModule(arguments[0]) ?: let {
            sendSyntax()
            return
        }

        module.toggle()

        val stateText = if (module.isEnabled()) "§aenabled" else "§cdisabled"

        sendMessage("The Module is now $stateText§7.")
    }
}
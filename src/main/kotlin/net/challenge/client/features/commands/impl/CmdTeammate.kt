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

import net.challenge.client.features.commands.Command
import net.challenge.client.features.commands.CommandInfo
import net.challenge.client.features.teammate.ITeammateHandler

/**
 * With this command the player can manage his teammates.
 */
@CommandInfo(name = "teammate", description = "Handel teammates", syntax = "Use §a'teammate <add/remove> <name>'§7.", aliases = [])
class CmdTeammate(

        /**
         * Handler to manage teammates.
         */
        private val teammateHandler: ITeammateHandler

) : Command() {

    override fun run(arguments: Array<String>) {
        if (arguments.isEmpty() || arguments.size > 2) {
            sendSyntax()
            return
        }

        val arg1 = arguments[0]

        if (arguments.size == 1) {

            // teammate clear
            if (arg1.equals("clear", ignoreCase = true)) {
                teammateHandler.clear()
                sendMessage("Clear all teammates.")
                return
            }

            // teammate list
            if (arg1.equals("list", ignoreCase = true)) {
                sendMessage("--- List of all teammates ---")

                teammateHandler.getPlayers().forEach { sendMessage(it) }
                return
            }

            sendSyntax()
            return
        }

        val playerName = arguments[1]

        // teammate add <NAME>
        if (arg1.equals("add", ignoreCase = true)) {
            teammateHandler.add(playerName)
            sendMessage("Add the teammate ${playerName}.")
            return
        }

        // teammate remove <NAME>
        if (arg1.equals("remove", ignoreCase = true)) {
            teammateHandler.remove(playerName)
            sendMessage("Remove the teammate ${playerName}.")
            return
        }

        sendSyntax()
    }
}
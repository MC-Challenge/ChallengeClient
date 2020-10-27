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
import net.challenge.client.features.commands.ICommand

@CommandInfo(name = "help", description = "Help the player to use the commands", syntax = "Use §a'help '§7.", aliases = [ "h" ])
object CmdHelp : Command() {

    override fun run(arguments: Array<String>) {
        sendMessage("--- Help ---")
        ClientCore.commandHandler.commands.forEach(ICommand::sendSyntax)
    }
}
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

package net.challenge.client.features.commands.handler

import net.challenge.client.core.ClientCore
import net.challenge.client.features.commands.ICommand
import net.challenge.client.utils.IMC
import net.minecraft.util.ChatComponentText

/**
 * Basic implementation of a [ICommandHandler].
 */
class CmdHandler(

        /**
         * Commands to registered.
         */
        vararg command: ICommand

) : ICommandHandler, IMC {

    override val commands: Collection<ICommand> = listOf(*command)

    override fun onInput(message: String): Boolean {
        if (message.length < 2) return false
        if (!message.startsWith(".")) return false

        val components = message
                .substring(1)
                .split(" ").toTypedArray()

        val name = components[0];

        val command: ICommand = commands.firstOrNull { it.isValidName(name) } ?: let {
            ClientCore.sendChatMessage("This command does not exist. For more information /help")
            return true
        }

        val arguments = components.copyOfRange(1, components.size)
        command.run(arguments)

        return true
    }
}
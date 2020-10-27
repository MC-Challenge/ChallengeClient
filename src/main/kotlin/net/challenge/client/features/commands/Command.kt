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

package net.challenge.client.features.commands

import net.challenge.client.core.ClientCore
import net.challenge.client.utils.IMC
import net.minecraft.util.ChatComponentText

/**
 * Basic implementation of [ICommand].
 * You must add a [CommandInfo] annotation to the command.
 */
abstract class Command : ICommand, IMC {

    /**
     * Name of the command.
     */
    protected val name: String

    /**
     * Description of the command.
     */
    protected val description: String

    /**
     * Syntax of the command.
     */
    protected val syntax: String

    /**
     * Alternative names of the command.
     */
    protected val aliases: Collection<String>

    /**
     * If you want to use this constructor
     */
    init {
        val info: CommandInfo = javaClass.getAnnotation(CommandInfo::class.java)

        name = info.name
        description = info.description
        syntax = info.syntax
        aliases = info.aliases.asList()
    }

    override fun isValidName(name: String): Boolean {
        if (this.name == name) return true

        return aliases.firstOrNull { it == name } != null
    }

    override fun sendSyntax() {
        if (syntax.isEmpty()) return

        sendMessage(syntax)
    }

    override fun sendMessage(message: String) {
        ClientCore.sendChatMessage(message)
    }
}

/**
 * Annotation for the [Command] to set the meta-information.
 *
 */
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class CommandInfo(val name: String, val description: String = "", val syntax: String = "", val aliases: Array<String> = [])
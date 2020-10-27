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

import net.challenge.client.features.commands.ICommand


interface ICommandHandler {

    /**
     * Collection of all registered commands.
     */
    val commands: Collection<ICommand>

    /**
     * This method, searches after an command, and when it founds an command, it runs it.
     *
     * @param message Input text.
     *
     * @return Was the input valid.
     */
    fun onInput(message: String): Boolean
}
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

package net.challenge.client.features.teammate

/**
 * This handler manages the team members. The handler is used,
 * for example, to determine which other players participate in
 * the challenge. Modules can thus refer to these players.
 */
interface ITeammateHandler {

    /**
     * Delete all teammates.
     */
    fun clear()

    /**
     * Add a teammate by playerName.
     *
     * @param playerName Name of the player to add.
     */
    fun add(playerName: String)

    /**
     * Remove a teammate by playerName.
     *
     * @param playerName Name of the player to remove.
     */
    fun remove(playerName: String)

    /**
     * Get all teammate-names.
     *
     * @return Collection of all names.
     */
    fun getPlayers(): Collection<String>
}
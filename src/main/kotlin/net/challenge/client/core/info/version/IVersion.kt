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

package net.challenge.client.core.info.version

interface IVersion {

    /**
     * Tests if the passed version is higher.
     *
     * @param version The version that is tested to see if it is higher
     * @return Is the version higher
     */
    fun isVersionHigher(version: Version): Boolean {
        return version.toInteger() > toInteger()
    }

    /**
     * @return The version formatted as Integer
     */
    fun toInteger(): Int;
}
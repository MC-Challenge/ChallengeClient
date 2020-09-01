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

package net.challenge.client.value.registry

import net.challenge.client.value.Value
import net.challenge.client.value.IValueHandler

interface IValueRegistry {


    /**
     * # Register Handler
     * This method, registers an handler
     */
    fun registerValueHandler(valueHandler: IValueHandler)

    /**
     * # Getting Values, from an Handler
     * This method, fetches the values, from an handler
     * @return all values of the handler, that was typed in
     */
    fun getAllValuesFrom(valueHandler: IValueHandler): List<Value<*>>?

}
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

import net.challenge.client.value.IValueHandler
import net.challenge.client.value.Value
import net.challenge.client.value.registry.scanner.ValueScanner

/**
 * Default implementation of [IValueRegistry]
 */
class ValueRegistry : IValueRegistry {

    /**
     * Are the values
     */
    private var values: HashMap<IValueHandler, List<Value<*>>> = HashMap()


    override fun registerValueHandler(valueHandler: IValueHandler) {
        val findValues = ValueScanner.findValues(valueHandler)

        if (findValues.isEmpty()) return

        this.values[valueHandler] = findValues
    }

    override fun getAllValuesFrom(valueHandler: IValueHandler): List<Value<*>>? {
        for ((key, value) in values.entries) {
            if (key == valueHandler) return value
        }
        return null
    }

}
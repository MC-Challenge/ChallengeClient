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

import com.google.common.collect.Lists
import net.challenge.client.value.Value
import net.challenge.client.value.ValueHandler

/**
 * default implementation of [IValueRegistry]
 */
class ValueRegistry : IValueRegistry {

    /**
     * Are the values
     */
    private var values: HashMap<ValueHandler, List<Value<*>>> = HashMap()

    override fun registerValueHandler(valueHandler: ValueHandler) {
        val values: MutableList<Value<*>> = Lists.newArrayList<Value<*>>()
        for (field in valueHandler.javaClass.declaredFields) {
            try {
                field.isAccessible = true
                val obj = field[valueHandler]
                if (obj is Value<*>) {
                    values.add(obj)
                }
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
        this.values.put(valueHandler, values)
    }

    override fun getAllValuesFrom(valueHandler: ValueHandler): List<Value<*>>? {
        for ((key, value) in values.entries) {
            if (key == valueHandler) return value
        }
        return null
    }

}
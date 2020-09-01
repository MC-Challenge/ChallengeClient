/*
 *
 *  * Challenge Client
 *  * https://github.com/MC-Challenge/ChallengeClient/
 *
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package net.challenge.client.value.registry.scanner

import net.challenge.client.value.IValueHandler
import net.challenge.client.value.VTag
import net.challenge.client.value.Value
import java.lang.reflect.Field
import java.util.*

/**
 * # ValueScanner
 * Using the [ValueScanner] you can find all
 * values in a [IValueHandler] and return as a [List].
 */
object ValueScanner {

    /**
     * The method searches for values in the handler and the superclasses of the handler.
     *
     * @param valueHandler Handler to be searched for values
     */
    fun findValues(valueHandler: IValueHandler): List<Value<*>> {
        val values = ArrayList<Value<*>>()
        var currentClass: Class<*>? = valueHandler.javaClass

        do {
            if (currentClass!!.name == "java.lang.Object") break

            values.addAll(findValues(valueHandler, currentClass))

            currentClass = currentClass.superclass
        } while (currentClass != null)

        return values
    }

    /**
     * The method searches for values in the class of the handler.
     *
     * @param valueHandler Handler Object from the class
     * @param clazz Class to search for values
     *
     * @return List of all founded values
     */
    private fun findValues(valueHandler: IValueHandler, clazz: Class<*>): List<Value<*>> {
        return clazz.declaredFields
                .toList()
                .filter { hasTag(it) }
                .map { field -> fieldToValue(valueHandler, field) }

    }

    /**
     * Get from the [IValueHandler] the [Value]
     *
     * @param handler The handler in which the value is
     * @param field The field from the value
     *
     * @return The Value from the field
     */
    private fun fieldToValue(handler: IValueHandler, field: Field): Value<*> {
        val obj = getField(handler, field) ?: throw RuntimeException("Value is Null")

        if (obj !is Value<*>)
            throw RuntimeException("The field is not a Value")

        obj.setTagInfo(getTag(field)!!)

        return obj
    }

    /**
     * Gets the [Value] of a field from an Object
     *
     * @param obj Object that field belongs to
     * @param field  Field that is being retrieved
     *
     * @return The value of the field
     */
    private fun getField(obj: Any, field: Field): Any? {
        var value: Any? = null

        try {
            val accessible = field.isAccessible
            field.isAccessible = true
            value = field[obj]
            field.isAccessible = accessible
        } catch (ignored: NullPointerException) {
        } catch (ignored: IllegalAccessException) {
        }

        return value
    }

    /**
     * Returns whether or not the specified field
     * has a [VTag] annotation or not.
     *
     * @param field Field being checked
     * @return If the field has a [VTag] annotation
     */
    private fun hasTag(field: Field): Boolean {
        return getTag(field) != null
    }

    /**
     * Gets the class of the value annotation belonging
     * to a field, null if there is none
     *
     * @param field Field being checked
     * @return The [VTag] annotation of the field
     */
    private fun getTag(field: Field): VTag? {
        return field.getAnnotation(VTag::class.java)
    }
}
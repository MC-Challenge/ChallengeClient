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

package net.challenge.client.value

/**
 * TODO Doc
 */
abstract class Value<T>(var value: T) {

    /**
     * Name of the value
     */
    var name: String = "No-Name"

    /**
     * Description of the value
     */
    var description: String = "No-Description"

    /**
     * TODO Doc
     */
    fun setObjectObject(new: Any) {
        this.value = (new as T?)!!
    }

    /**
     * Set info from the tag to the value
     *
     * @param tag Use this tag to update
     */
    fun setTagInfo(tag: VTag) {
        name = tag.name
        description = tag.description
    }
}

/**
 * TODO Doc
 */
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class VTag(val name: String, val description: String = "No-Description")
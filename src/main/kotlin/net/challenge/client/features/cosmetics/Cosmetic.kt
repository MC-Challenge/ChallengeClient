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

package net.challenge.client.features.cosmetics

import net.challenge.client.features.cosmetics.annotations.CosmeticInfo


abstract class Cosmetic : ICosmetic {

    override var name: String = javaClass.getAnnotation(CosmeticInfo::class.java).name

    override var description: String = javaClass.getAnnotation(CosmeticInfo::class.java).description

    override var enabled: Boolean = true

    override fun toggle() {
        enabled = !enabled
    }

    override fun interpolate(yaw1: Float, yaw2: Float, percent: Float): Float {
        var rotation = (yaw1 + (yaw2 - yaw1) * percent) % 360.0f
        if (rotation < 0.0f) {
            rotation += 360.0f
        }
        return rotation
    }

}
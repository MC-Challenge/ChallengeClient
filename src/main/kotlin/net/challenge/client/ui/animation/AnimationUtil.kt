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

package net.challenge.client.ui.animation

import net.minecraft.util.MathHelper

class AnimationUtil {

    companion object {
        var deltaTime: Float = 0.0f
        var lastFrame: Float = 0.0f

        fun slide(current: Double, min: Double, max: Double, speed: Double, sliding: Boolean): Double {
            speed.times(deltaTime * 0.2)
            return MathHelper.clamp_double(if (sliding) if (current < max) current + (max - current) * speed else current else if (current > min) current - (current - min) * speed else current, min, max)
        }
    }

}
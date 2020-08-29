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

package net.challenge.client.ui.position

import net.challenge.client.utils.IScaledResolutionHelper
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.ScaledResolution
import kotlin.math.roundToInt

/**
 * TODO Doc
 */
class ScaledPosition(private var x: Double, private var y: Double) : IPosition, IScaledResolutionHelper {

    constructor(x: Int, y: Int) {
        setAbsolute(x, y)
    }

    override fun getAbsoluteX(): Int {
        return (x * getScaledWidthDouble()).roundToInt()
    }

    override fun getAbsoluteY(): Int {
        return (y * getScaledHeightDouble()).roundToInt()
    }

    override fun getRelativeX(): Double {
        return x
    }

    override fun getRelativeY(): Double {
        return y
    }

    override fun setAbsolute(x: Int, y: Int) {
        val sr = ScaledResolution(Minecraft.getMinecraft())

        this.x = x.toDouble() / sr.scaledWidth_double
        this.y = y.toDouble() / sr.scaledHeight_double
    }

    override fun setRelative(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    companion object {

        fun fromRelativePosition(x: Double, y: Double): ScaledPosition {
            return ScaledPosition(x, y)
        }

        fun fromAbsolutePosition(x: Int, y: Int): ScaledPosition {
            return ScaledPosition(x, y)
        }
    }
}
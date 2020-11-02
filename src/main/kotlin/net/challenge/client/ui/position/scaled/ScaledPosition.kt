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

package net.challenge.client.ui.position.scaled

import net.challenge.client.ui.position.IPosition
import org.lwjgl.opengl.Display
import kotlin.math.roundToInt

/**
 * The position is calculated according to the current screen size.
 * Relative means in this case that you can specify the position in percent.
 * Screen-Width: 500px
 * Relative-X: 0.5
 * Absolute X = 250px
 */
open class ScaledPosition(x: Number, y: Number) : IPosition {

    /**
     * X-Position in percent of the display-width.
     */
    private var x: Double = 0.5

    /**
     * Y-Position in percent of the display-height.
     */
    private var y: Double = 0.5

    init {
        set(x, y)
    }

    override fun getX(): Double {
        return x * getDisplayWidth()
    }

    override fun getY(): Double {
        return y * getDisplayHeight()
    }

    override fun setX(x: Number) {
        this.x = x.toDouble() / getDisplayWidth()
    }

    override fun setY(y: Number) {
        this.y = y.toDouble() / getDisplayHeight()
    }

    protected open fun getDisplayWidth(): Double {
        return Display.getWidth().toDouble()
    }

    protected open fun getDisplayHeight(): Double {
        return Display.getHeight().toDouble()
    }
}
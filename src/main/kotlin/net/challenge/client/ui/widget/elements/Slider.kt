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

package net.challenge.client.ui.widget.elements

import net.challenge.client.ui.adapter.input.mouse.IGuiMouseClickAndMove
import net.challenge.client.ui.adapter.input.mouse.IGuiMouseRelease
import net.challenge.client.ui.widget.SelectableWidget
import net.minecraft.util.MathHelper
import kotlin.math.round

/**
 *
 */
class Slider(val name: String) : SelectableWidget<Slider>(), IGuiMouseRelease, IGuiMouseClickAndMove {

    /**
     * Maximum value from the [value]
     */
    var maximum: Double = 10.0

    /**
     * Minimum value from the [value]
     */
    var minimum: Double = 0.0

    /**
     * Decimal-Places of the [value]
     */
    private var decimalPlaces: Int = 0

    /**
     * Is the slider currently dragging
     */
    var dragging: Boolean = false

    /**
     * Should the slider display the value as percent
     */
    var isPercent: Boolean = false

    var value = 0.0
        set(value) {
            field = if (decimalPlaces == 0)
                value.toInt().toDouble()
            else {
                val multiplier = 10 * decimalPlaces
                round(value * multiplier) / multiplier
            }

            if (field > maximum) field = maximum
            if (field < minimum) field = minimum

            // Call all Select-Listener
            selectListeners.forEach { it.invoke(this) }
        }


    override fun mouseClick(mouseX: Int, mouseY: Int, mouseButton: Int): Boolean {
        if (isHover(mouseX, mouseY)) {
            if (mouseButton == 0) {
                dragging = true
            }
        }

        return super.mouseClick(mouseX, mouseY, mouseButton)
    }

    override fun mouseRelease(mouseX: Int, mouseY: Int, mouseButton: Int) {
        dragging = false
    }

    override fun mouseClickAndMove(mouseX: Int, mouseY: Int, clickedMouseButton: Int, timeSinceLastClick: Long) {
        if (!isHover(mouseX, mouseY) || clickedMouseButton != 0) return

        val diff = maximum - minimum
        value = minimum + MathHelper.clamp_double(((mouseX.toDouble() - position.getAbsoluteX().toDouble()) / width.toDouble()), 0.0, 1.0) * diff
    }

    /**
     * Get the [value] as percent
     */
    fun getAsPercent(): Double {
        return (value - minimum) / (maximum - minimum)
    }

    /**
     * Set the maximum of the [value]
     *
     * @param maximum Maximum of the [value]
     * @return this
     */
    fun setMaximum(maximum: Double): Slider {
        this.maximum = maximum

        return this
    }

    /**
     * Set the maximum of the [value]
     *
     * @param minimum Minimum of the [value]
     * @return this
     */
    fun setMinimum(minimum: Double): Slider {
        this.minimum = minimum

        return this
    }

    /**
     * Set the Decimal-Places of the [value]
     *
     * @param decimalPlaces Value to set
     * @return this
     */
    fun setDecimalPlaces(decimalPlaces: Int): Slider {
        this.decimalPlaces = decimalPlaces

        return this
    }

    /**
     * Set the value
     *
     * @param value Value to set
     * @return this
     */
    fun setValue(value: Double): Slider {
        this.value = value

        return this
    }

    /**
     * Display the value as percent.
     * This also sets the [minimum] to 0 and the [maximum] to 100
     *
     * @return this
     */
    fun asPercent(): Slider {
        isPercent = true
        minimum = 0.0
        maximum = 100.0

        return this
    }

    /**
     * Set the [decimalPlaces] to 0
     */
    fun toInteger() {
        decimalPlaces = 0
    }

    /**
     * Set the minimum and maximum of the [value]
     *
     * @param minimum Minimum of the value
     * @param maximum Maximum of the value
     * @return this
     */
    fun setDistance(minimum: Double, maximum: Double) : Slider {
        this.minimum = minimum
        this.maximum = maximum

        return this
    }
}
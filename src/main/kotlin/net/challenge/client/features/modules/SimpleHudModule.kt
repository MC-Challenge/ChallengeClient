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

package net.challenge.client.features.modules

import net.challenge.client.ui.font.FontHandler
import net.challenge.configu.value.VTag
import net.challenge.configu.value.impl.VBool
import net.challenge.configu.value.impl.VColor
import java.awt.Color

/**
 * This hud module always consists of a prefix and a value.
 * This prefix and value is always displayed in this format
 * "Prefix: Value".
 */
abstract class SimpleHudModule : HudModule() {

    /**
     * Option, if the text should drop an shadow
     */
    @VTag(name = "Shadow", description = "Should the text have a shadow")
    protected val hasShadow = VBool(false)


    /**
     * Option, if the text should have a prefix
     */
    @VTag(name = "Prefix", description = "Should have a Prefix")
    protected val hasPrefix = VBool(false)

    /**
     * Color Red
     */
    /*
    @VTag(name = "Color", description = "Color")
    protected val color = VColor(Color.BLUE)
    */


    /**
     * Get the value of the module
     *
     * @return The Value of the module.
     */
    protected abstract fun getValue(): String

    /**
     * Get the Display-Name of the module
     *
     * @return The Display-Name of the module
     */
    protected abstract fun getDisplayName(): String


    override fun drawElement(mouseX: Int, mouseY: Int, partialTicks: Float) {
        val font = FontHandler.mcFontRenderer
        var text = getDisplayName() + ": " + getValue()

        if (!hasPrefix.value)
            text = getValue()

        if (hasShadow.value) font.drawStringWithShadow(text, position.getAbsoluteX().toFloat(), position.getAbsoluteY().toFloat(), Color.WHITE.rgb)
        else font.drawString(text, position.getAbsoluteX(), position.getAbsoluteY(), Color.WHITE.rgb)
    }

    override fun getElementWidth(): Int {
        var text = getDisplayName() + ": " + getValue()

        if (!hasPrefix.value)
            text = getValue()
        return FontHandler.mcFontRenderer.getStringWidth(text)
    }

    override fun getElementHeight(): Int {
        return FontHandler.mcFontRenderer.getFontHeight(getDisplayName() + ":" + getValue())
    }
}
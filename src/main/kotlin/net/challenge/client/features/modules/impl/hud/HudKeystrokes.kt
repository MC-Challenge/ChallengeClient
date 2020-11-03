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

package net.challenge.client.features.modules.impl.hud

import net.challenge.client.features.modules.HudModule
import net.challenge.client.features.modules.ModuleCategory
import net.challenge.client.features.modules.annotations.ModuleInfo
import net.challenge.client.ui.font.FontHandler
import net.challenge.client.ui.font.IFontRenderer
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import org.lwjgl.input.Keyboard
import java.awt.Color
import java.lang.reflect.Field

@ModuleInfo(name = "Keystrokes", category = ModuleCategory.HUD)
object HudKeystrokes : HudModule() {
    override fun getElementWidth(): Int {
        return 85
    }

    override fun getElementHeight(): Int {
        return 55
    }

    override fun drawElement(mouseX: Int, mouseY: Int, partialTicks: Float) {

        val buttonWidth = 27
        val buttonHeight = 27
        var start = 0

        val keyForwardColor: Int = if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindForward.keyCode)) Color(143, 207, 209).rgb else Integer.MIN_VALUE
        val keyBackwardsColor: Int = if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindBack.keyCode)) Color(223, 94, 136).rgb else Integer.MIN_VALUE
        val keyLeftColor: Int = if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindLeft.keyCode)) Color(246, 171, 108).rgb else Integer.MIN_VALUE
        val keyRightColor: Int = if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindRight.keyCode)) Color(246, 239, 166).rgb else Integer.MIN_VALUE

        val forwardXPos = (position.getX() + (start + buttonWidth + 2)).toInt()
        val forwardYPos = (position.getY() + getElementHeight() / 2).toInt()

        val font: IFontRenderer = FontHandler.mcFontRenderer

        Gui.drawRect(forwardXPos, forwardYPos - buttonHeight, forwardXPos + buttonWidth, forwardYPos, keyForwardColor)
        font.drawString(Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindForward.keyCode).toString(), (forwardXPos + 1 + buttonWidth / 2) - font.getStringWidth(Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindForward.keyCode).toString()) / 2, forwardYPos - buttonHeight / 2 - font.getFontHeight() / 2, -1);

        for (x in 0..2) {

            val keyColor: Int = when (x) {
                0 -> keyLeftColor
                1 -> keyBackwardsColor
                else -> keyRightColor
            }

            val character: String = when (x) {
                0 -> Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindLeft.keyCode).toString()
                1 -> Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindBack.keyCode).toString()
                else -> Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindRight.keyCode).toString()
            }

            val keyStartX = (position.getX() + start).toInt()
            val keyStartY = (position.getY() + getElementHeight() / 2).toInt()

            Gui.drawRect(keyStartX, keyStartY + 2, keyStartX + buttonWidth, keyStartY + 2 + buttonHeight, keyColor)
            font.drawString(character, (keyStartX + 1 + buttonWidth / 2) - font.getStringWidth(character) / 2, keyStartY + 2 + buttonHeight / 2 - font.getFontHeight() / 2, -1);

            start += (buttonWidth + 2)
        }
    }

    fun getField(clazz: Class<*>, name: String): Field {
        return clazz.getField(name)
    }

}
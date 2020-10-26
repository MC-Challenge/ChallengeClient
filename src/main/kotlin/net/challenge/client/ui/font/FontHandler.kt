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

package net.challenge.client.ui.font

import net.challenge.client.ui.font.fancy.GLFont
import net.challenge.client.ui.font.fancy.Glyph
import net.challenge.client.utils.IMC
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.FontRenderer
import net.minecraft.client.resources.IReloadableResourceManager
import net.minecraft.util.ResourceLocation
import java.awt.Font


//TODO: FIX
/**
 * Handle font rendering
 */
object FontHandler : IMC {

    /**
     * Renderer to render font with a mc look
     */
    lateinit var mcFontRenderer: IFontRenderer


    fun load() {
        mcFontRenderer = MCFontRenderer(registerFontRenderer(ResourceLocation("textures/font/ascii.png")))
    }

    /**
     * Create and register a [FontRenderer]
     * @param resourceLocation Resource from the ascii font image
     * @return Created [FontRenderer]
     */
    private fun registerFontRenderer(resourceLocation: ResourceLocation): FontRenderer {
        val gameSettings = mc.gameSettings

        val fontRenderer = FontRenderer(gameSettings, resourceLocation, mc.renderEngine, false)

        if (gameSettings.language != null) {
            fontRenderer.unicodeFlag = mc.isUnicode
            fontRenderer.bidiFlag = mc.languageManager.isCurrentLanguageBidirectional
        }

        (mc.resourceManager as IReloadableResourceManager).registerReloadListener(fontRenderer)

        return fontRenderer
    }

    /**
     * Gets the font file, and creates a new font
     * @param resourceLocation is the file location
     * @param size is the font size, from the font that's being created
     * @return the created font
     */
    private fun getFont(resourceLocation: ResourceLocation, size: Int): GLFont {
        var font: Font
        val stream = Minecraft.getMinecraft().resourceManager.getResource(resourceLocation).inputStream
        font = Font.createFont(0, stream)
        font = font.deriveFont(Font.PLAIN, size.toFloat())
        val chars = CharArray(256)
        for (i in chars.indices) {
            chars[i] = i.toChar()
        }
        val regularPage = Glyph(font, true, false)
        regularPage.generate(chars)
        regularPage.setupTexture()
        return GLFont(regularPage, null, null, null)
    }

    /**
     * more simple way, to get a font
     * @see getFont
     */
    fun getFancyFontRenderer(fileName: String, size: Int): GLFont {
        return getFont(ResourceLocation("challenge/fonts/" + fileName + if (fileName.endsWith(".ttf")) "" else ".ttf"), size)
    }

}
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

import net.challenge.client.utils.IMC
import net.minecraft.client.gui.FontRenderer
import net.minecraft.client.resources.IReloadableResourceManager
import net.minecraft.util.ResourceLocation


/**
 * Handle font rendering
 */
object FontHandler : IMC {

    /**
     * Renderer to render font with a mc look
     */
    lateinit var mcFontRenderer: IFontRenderer

    /**
     * Renderer to render font with a fancy look
     */
    lateinit var fancyFontRenderer: IFontRenderer

    fun load() {
        mcFontRenderer = MCFontRenderer(registerFontRenderer(ResourceLocation("textures/font/ascii.png")))
        fancyFontRenderer = MCFontRenderer(registerFontRenderer(ResourceLocation("challenge/font/ascii.png")))
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
}
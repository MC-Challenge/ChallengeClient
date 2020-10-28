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

package net.challenge.client.ui.widget.renderer

import net.challenge.client.ui.widget.IGuiWidget
import net.challenge.client.ui.widget.elements.Button
import net.challenge.client.ui.widget.elements.Checkbox
import net.challenge.client.ui.widget.elements.Rect
import net.challenge.client.ui.widget.elements.Slider
import net.challenge.client.ui.widget.elements.renderer.default.DButtonRenderer
import net.challenge.client.ui.widget.elements.renderer.default.DCheckboxRenderer
import net.challenge.client.ui.widget.elements.renderer.default.DSliderRenderer
import net.challenge.client.ui.widget.elements.renderer.polygons.RectRenderer
import net.challenge.client.ui.widget.elements.renderer.settings.CategoryButtonRenderer
import net.challenge.client.ui.widget.elements.renderer.settings.ModuleButtonRenderer
import net.challenge.client.ui.widget.elements.settings.CategoryButton
import net.challenge.client.ui.widget.elements.settings.ModuleButton

/**
 * TODO Doc
 */
object WidgetRenderers {

    /**
     * TODO Doc
     */
    private val renderers = mutableMapOf<Class<*>, IWidgetRenderer<*>>()


    init {
        // Set's the Default Renderers
        setRenderer(Button::class.java, DButtonRenderer())
        setRenderer(Slider::class.java, DSliderRenderer())
        setRenderer(Checkbox::class.java, DCheckboxRenderer())

        // Set's the Polygon Renderers
        setRenderer(Rect::class.java, RectRenderer())

        // Set's the Setting Renderers
        setRenderer(ModuleButton::class.java, ModuleButtonRenderer())
        setRenderer(CategoryButton::class.java, CategoryButtonRenderer())
    }


    /**
     * TODO Doc
     */
    fun <W : IGuiWidget<*>> setRenderer(clazz: Class<W>, renderer: IWidgetRenderer<W>) {
        this.renderers[clazz] = renderer
    }

    /**
     * TODO Doc
     */
    fun <C : IGuiWidget<*>> getRenderer(clazz: Class<C>): IWidgetRenderer<C>? {
        @Suppress("UNCHECKED_CAST")
        return this.renderers[clazz] as? IWidgetRenderer<C>
    }
}
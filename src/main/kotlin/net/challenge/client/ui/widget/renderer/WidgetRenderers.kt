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

import net.challenge.client.ui.widget.IWidget
import net.challenge.client.ui.widget.elements.Button
import net.challenge.client.ui.widget.elements.renderer.default.DButtonRenderer

/**
 * TODO Doc
 */
object WidgetRenderers {

    /**
     * TODO Doc
     */
    private val renderers = mutableMapOf<Class<*>, IWidgetRenderer<*>>()


    init {
        // Set Default Renderers
        setRenderer(Button::class.java, DButtonRenderer())
    }


    /**
     * TODO Doc
     */
    fun <W : IWidget> setRenderer(clazz: Class<W>, renderer: IWidgetRenderer<W>) {
        this.renderers[clazz] = renderer
    }

    /**
     * TODO Doc
     */
    fun <C : IWidget> getRenderer(clazz: Class<C>): IWidgetRenderer<C>? {
        @Suppress("UNCHECKED_CAST")
        return this.renderers[clazz] as? IWidgetRenderer<C>
    }
}
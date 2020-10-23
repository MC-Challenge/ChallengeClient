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

/**
 * This is implemented to give a widget element a look.
 * These renderers must then be registered in [WidgetRenderers]
 */
interface IWidgetRenderer<W : IGuiWidget<*>> {

    /**
     * Render the widget look
     *
     * @param widget Widget that should be rendered
     * @param mouseX Mouse X-Position in pixel
     * @param mouseY Mouse Y-Position in pixel
     */
    fun render(widget: W, mouseX: Int, mouseY: Int)
}
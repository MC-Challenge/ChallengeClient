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

package net.challenge.client.ui.hud.customHud.renderer

import net.challenge.client.ui.hud.customHud.element.IHudElement

/**
 * # IHudRenderer
 *
 * TODO Doc
 */
interface IHudRenderer {

    /**
     * Collection of all enabled custom-hud-elements
     */
    var enabledElements: Collection<IHudElement>


    /**
     * Render all enabled hud-elements
     *
     * @param mouseX Mouse X position in pixels
     * @param mouseY Mouse Y position in pixels
     * @param partialTicks How much time has elapsed since the last tick, in ticks,
     * for use by display rendering routines (range: 0.0 - 1.0).
     * This field is frozen if the display is paused to eliminate jitter.
     */
    fun renderHudElements(mouseX: Int, mouseY: Int, partialTicks: Float)
}
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

package net.challenge.client.ui.hud.customHud

import net.challenge.client.ui.hud.customHud.element.IHudElement
import net.challenge.client.ui.hud.customHud.element.list.ElementDirection
import net.minecraft.client.gui.GuiScreen
import java.util.function.Consumer

class GuiCustomHud : GuiScreen() {

    /**
     * Collection of all enabled custom-hud-modules
     */
    private var enabledModules: Collection<IHudElement> = ArrayList()

    init {
        enabledModules += ElementDirection()
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {

        enabledModules.forEach(Consumer {
            run {
                it.render(mouseX, mouseY, partialTicks)
            }
        })

        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {

        enabledModules.forEach(Consumer {
            run {
                it.mouseClicked(mouseX, mouseY, mouseButton)
            }
        })

        super.mouseClicked(mouseX, mouseY, mouseButton)
    }

    override fun mouseReleased(mouseX: Int, mouseY: Int, state: Int) {

        enabledModules.forEach(Consumer {
            run {
                it.mouseReleased(mouseX, mouseY, state)
            }
        })

        super.mouseReleased(mouseX, mouseY, state)
    }

}
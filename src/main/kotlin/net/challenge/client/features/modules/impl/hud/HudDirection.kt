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

package net.challenge.client.features.modules.impl.hud

import net.challenge.client.features.modules.ModuleCategory
import net.challenge.client.features.modules.SimpleHudModule
import net.challenge.client.features.modules.annotations.ModuleInfo
import net.challenge.configu.value.VTag
import net.challenge.configu.value.impl.VBool
import net.minecraft.client.Minecraft
import net.minecraft.entity.Entity

@ModuleInfo(name = "Direction", category = ModuleCategory.HUD)
object HudDirection : SimpleHudModule() {

    /**
     * Option, if the text only has one character
     */
    @VTag(name = "One Character", description = "Should the text only have one character")
    val oneCharacter = VBool(false)

    override fun getValue(): String {
        val entity: Entity = Minecraft.getMinecraft().renderViewEntity
        val s: String = entity.horizontalFacing.toString()

        return if (oneCharacter.value) s[0].toString().toUpperCase() else s[0].toUpperCase().toString() + s.substring(1).toLowerCase()
    }

    override fun getDisplayName(): String {
        return "Direction"
    }
}
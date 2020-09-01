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

package net.challenge.client.features.cosmetics

import net.minecraft.entity.player.EntityPlayer

interface ICosmetic {

    /**
     * Name of the cosmetic
     */
    var name: String

    /**
     * Description of the cosmetic
     */
    var description: String

    /**
     * Is the cosmetic enabled
     */
    var enabled: Boolean

    /**
     * Toggles the cosmetic
     */
    fun toggle()

    /**
     * Renders the cosmetic
     */
    fun render(player: EntityPlayer, x: Double, y: Double, z: Double, partialTicks: Float)

    /**
     * [interpolate](https://de.wikipedia.org/wiki/Interpolation_(Mathematik))
     */
    fun interpolate(yaw1: Float, yaw2: Float, percent: Float): Float

}
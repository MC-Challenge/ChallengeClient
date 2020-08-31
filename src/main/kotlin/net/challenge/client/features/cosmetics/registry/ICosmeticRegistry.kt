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

package net.challenge.client.features.cosmetics.registry

import net.challenge.client.features.cosmetics.Cosmetic
import net.challenge.client.features.cosmetics.ICosmetic
import net.minecraft.entity.player.EntityPlayer

/**
 * In a CosmeticRegistry the cosmetic of the client are loaded and saved
 */
interface ICosmeticRegistry {

    /**
     * Loading cosmetic
     */
    fun load()

    /**
     * Rendering the toggled cosmetics
     */
    fun renderActivated(player: EntityPlayer, x: Double, y: Double, z: Double, partialTicks: Float)

    /**
     * This method registers cosmetic
     *
     * @param cosmetics are the cosmetics, that will be registered
     */
    fun registerCosmetics(vararg cosmetics: Cosmetic)

    /**
     * This method, registers one module
     *
     * @param cosmetic is the cosmetic, that will be registered
     */
    fun registerCosmetic(cosmetic: Cosmetic)


    /**
     * Getting an module by cosmetic
     *
     * @return The cosmetic that was found, or null
     */
    fun getCosmetic(name: String): Cosmetic

    /**
     * Getting an cosmetic by class
     *
     * @return The cosmetic that was found, or null
     */
    fun <T : ICosmetic> getCosmetic(clazz: Class<T>): T?
}
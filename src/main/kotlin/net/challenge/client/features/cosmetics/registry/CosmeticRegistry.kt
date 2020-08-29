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

import net.challenge.client.features.cosmetics.ICosmetic
import net.minecraft.entity.player.EntityPlayer
import java.util.function.Consumer

/**
 * Default implementation of [ICosmeticRegistry]
 */
class CosmeticRegistry : ICosmeticRegistry {

    /**
     * Collection of all registered client modules
     */
    private var cosmetics: Collection<ICosmetic> = ArrayList()


    override fun load() {

    }

    override fun renderActivated(player: EntityPlayer, x: Double, y: Double, z: Double, partialTicks: Float) {
        this.cosmetics.stream().filter { cosmetic: ICosmetic? -> cosmetic!!.enabled }.forEach { cosmetic ->
            run {
                cosmetic.render(player, x, y, z, partialTicks)
            }
        }
    }

    override fun registerCosmetics(vararg cosmetics: ICosmetic) {
        cosmetics.forEach(this::registerCosmetic)
    }

    override fun registerCosmetic(cosmetic: ICosmetic) {
        cosmetics += cosmetic
    }

    override fun getCosmetic(name: String): ICosmetic {
        return cosmetics.stream().filter { cosmetic: ICosmetic -> name.equals(cosmetic.name, ignoreCase = true) }.findFirst().orElse(null)
    }

    override fun <T : ICosmetic> getCosmetic(clazz: Class<T>): T? {
        return cosmetics.stream().filter { cosmetic: ICosmetic -> cosmetic.javaClass == clazz }.findFirst().orElse(null) as T
    }
}
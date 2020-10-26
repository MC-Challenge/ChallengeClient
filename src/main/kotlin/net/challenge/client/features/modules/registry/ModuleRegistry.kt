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

package net.challenge.client.features.modules.registry

import net.challenge.client.core.ClientCore
import net.challenge.client.features.modules.IModule

/**
 * Default implementation of [IModuleRegistry]
 */
class ModuleRegistry(

        vararg module: IModule

) : IModuleRegistry {

    override var modules: Collection<IModule> = listOf(*module)

    override fun load() {
        modules.forEach {
            it.load()
            ClientCore.config.register(it)
        }
    }

    override fun getModule(name: String): IModule? {
        return modules.firstOrNull { name.equals(it.name, ignoreCase = true) }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : IModule> getModule(clazz: Class<T>): T? {
        return modules.firstOrNull { it.javaClass == clazz } as T
    }
}
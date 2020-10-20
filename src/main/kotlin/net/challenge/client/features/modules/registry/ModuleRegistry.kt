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
import net.challenge.client.features.modules.Module
import net.challenge.client.features.modules.impl.hud.HudDirection
import net.challenge.client.features.modules.impl.hud.HudKeystrokes
import net.challenge.client.features.modules.impl.hud.HudXYZ


/**
 * Default implementation of [IModuleRegistry]
 */
class ModuleRegistry : IModuleRegistry {

    override var modules: Collection<Module> = ArrayList()

    override fun load() {
        registerModules(
                HudDirection,
                HudKeystrokes,
                HudXYZ
        )

        modules.forEach {
            it.load()
            ClientCore.config.register(it)
        }
    }

    override fun registerModules(vararg modules: Module) {
        modules.forEach(this::registerModule)
    }

    override fun registerModule(module: Module) {
        modules += module
    }

    override fun getModule(name: String): IModule? {
        return modules.stream().filter { mod: IModule -> name.equals(mod.name, ignoreCase = true) }.findFirst().orElse(null)
    }

    override fun <T : IModule> getModule(clazz: Class<T>): T? {
        return modules.stream().filter { mod: IModule -> mod.javaClass == clazz }.findFirst().orElse(null) as T
    }
}
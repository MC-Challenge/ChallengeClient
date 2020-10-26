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

import net.challenge.client.features.modules.IModule

/**
 * In a ModuleRegistry the modules of the client are loaded and saved
 */
interface IModuleRegistry {

    /**
     * Collection of all registered client modules.
     */
    var modules: Collection<IModule>

    /**
     * Register modules.
     *
     * @param module Module to register.
     */
    fun registerModules(vararg module: IModule)

    /**
     * Loading modules.
     */
    fun load()

    /**
     * Getting an module by name.
     *
     * @return The module that was found, or null
     */
    fun getModule(name: String): IModule?

    /**
     * Getting an module by class.
     *
     * @return The module that was found, or null.
     */
    fun <T : IModule> getModule(clazz: Class<T>): T?
}
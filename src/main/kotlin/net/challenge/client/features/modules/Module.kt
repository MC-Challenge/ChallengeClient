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

package net.challenge.client.features.modules

import net.challenge.client.core.ClientCore
import net.challenge.client.features.modules.annotations.ModuleInfo
import net.challenge.client.value.ValueHandler

/**
 * Default implementation of [IModule]
 */
abstract class Module : IModule {

    override var enabled: Boolean = false
        set(value) {
            field = value

            if (value)
                onEnable()
            else
                onDisable()
        }


    init {
        val info: ModuleInfo = javaClass.getAnnotation(ModuleInfo::class.java)

        name = info.name
        description = info.description
    }


    override fun onEnable() {
        ClientCore.eventBus.subscribe(this)
    }

    override fun onDisable() {
        ClientCore.eventBus.unsubscribe(this)
    }

    override fun toggle() {
        enabled = !enabled
    }
}
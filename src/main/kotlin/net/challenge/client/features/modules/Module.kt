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
import net.challenge.client.value.list.BooleanValue

/**
 * Default implementation of [IModule]
 */
open class Module : IModule {

    final override var name: String = "No-Name"

    final override var description: String = "No-Description"

    private val enabled: BooleanValue = BooleanValue("enabled", false)


    init {
        val info: ModuleInfo = javaClass.getAnnotation(ModuleInfo::class.java)

        name = info.name
        description = info.description

        // TODO Only for testing
        setEnabled(true)
    }


    override fun onEnable() {
        ClientCore.eventBus.subscribe(this)
    }

    override fun onDisable() {
        ClientCore.eventBus.unsubscribe(this)
    }

    override fun setEnabled(enabled: Boolean) {
        this.enabled.value = enabled

        if (enabled)
            onEnable()
        else
            onDisable()
    }

    override fun isEnabled(): Boolean {
        return enabled.value
    }
}
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
import net.challenge.configu.container.ValueContainer
import net.challenge.configu.events.ValueChangeEvent
import net.challenge.configu.events.ValueChangeListener
import net.challenge.configu.scanner.ValueScanner
import net.challenge.configu.value.VTag
import net.challenge.configu.value.impl.VBool

/**
 * Default implementation of [IModule]
 */
open class Module : ValueContainer(ValueScanner), IModule {

    final override var name: String = "No-Name"

    final override var description: String = "No-Description"

    @VTag("Enabled", "Is the module enabled")
    private val enabled = VBool(false).setChangeListener(object : ValueChangeListener<Boolean> {

        override fun onChange(event: ValueChangeEvent<Boolean>) {
            if (event.after)
                onEnable()
            else
                onDisable()
        }
    })


    init {
        val info: ModuleInfo = javaClass.getAnnotation(ModuleInfo::class.java)

        name = info.name
        description = info.description

        setEnabled(enabled.value)
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

    override fun getFileName(): String {
        return name
    }
}
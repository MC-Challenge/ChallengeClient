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

package net.challenge.client.ui.widget

import net.challenge.client.ui.position.IPosition
import net.challenge.client.ui.position.ScaledPosition

/**
 * Default implementation of [IWidget]
 */
class Widget : IWidget {

    override var position: IPosition = ScaledPosition(0, 0)

    override var width: Int = 0

    override var height: Int = 0

    override var visible: Boolean = true
}
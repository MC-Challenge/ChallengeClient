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

/**
 * TODO Doc
 */
interface IWidget {

    /**
     * Position of the widget
     */
    var position: IPosition

    /**
     * Width of the widget
     */
    var width: Int

    /**
     * Height of the widget
     */
    var height: Int

    /**
     * If the widget is not visible no implemented input methods are executed and the widget is not rendered anymore
     */
    var visible: Boolean
}
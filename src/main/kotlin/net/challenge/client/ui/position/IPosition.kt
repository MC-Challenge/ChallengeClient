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

package net.challenge.client.ui.position

/**
 * The position can be specified relative to the screen size.
 * Relative means in this case that you can specify the position in percent.
 * Screen-Width: 500px
 * Relative-X: 0.5
 * Absolute X = 250px
 */
interface IPosition {

    /**
     * Get the absolute X-Position in pixels
     */
    fun getAbsoluteX(): Int

    /**
     * Get the absolute Y-Position in pixels
     */
    fun getAbsoluteY(): Int

    /**
     * Get the relative X-Position to the screen width
     */
    fun getRelativeX(): Double

    /**
     * Get the relative Y-Position to the screen height
     */
    fun getRelativeY(): Double

    /**
     * Set the position absolute to the screen
     *
     * @param x Absolute X-Position in pixels
     * @param y Absolute Y-Position in pixels
     */
    fun setAbsolute(x: Int, y: Int)

    /**
     * Set the position relative to the screen
     *
     * @param x Relative X-Position
     * @param y Relative Y-Position
     */
    fun setRelative(x: Double, y: Double)
}
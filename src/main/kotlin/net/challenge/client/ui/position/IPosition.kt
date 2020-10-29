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
 * A 2D-Position with X and Y-Position.
 */
interface IPosition {

    /**
     * Get the X-Position.
     *
     * @return Y-Position.
     */
    fun getX(): Double

    /**
     * Get the Y-Position.
     *
     * @return Y-Position.
     */
    fun getY(): Double

    /**
     * Set the X-Position.
     *
     * @param x Set this as X-Position
     */
    fun setX(x: Number)

    /**
     * Set the Y-Position.
     *
     * @param y Set this as Y-Position
     */
    fun setY(y: Number)

    /**
     * Set the X and Y-Position.
     *
     * @param x Set this as X-Position
     * @param y Set this as Y-Position
     */
    fun set(x: Number, y: Number) {
        setX(x)
        setY(y)
    }
}
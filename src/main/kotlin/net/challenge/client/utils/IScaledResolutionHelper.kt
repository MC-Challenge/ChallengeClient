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

package net.challenge.client.utils

import net.minecraft.client.gui.ScaledResolution

/**
 * TODO Doc
 */
interface IScaledResolutionHelper : IMC {

    fun getScaledResolution() : ScaledResolution {
        return ScaledResolution(mc)
    }

    fun getScaledWidth(): Int {
        return getScaledResolution().scaledWidth
    }

    fun getScaledHeight(): Int {
        return getScaledResolution().scaledHeight
    }

    fun getScaledWidthDouble(): Double {
        return getScaledResolution().scaledWidth_double
    }

    fun getScaledHeightDouble(): Double {
        return getScaledResolution().scaledHeight_double
    }

    fun getScaleFactor() : Int {
        return getScaledResolution().scaleFactor
    }
}
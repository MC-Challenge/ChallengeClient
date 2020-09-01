/*
 * Challenge Client
 * https://github.com/MC-Challenge/ChallengeClient/
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.challenge.client.features.cosmetics.list

import me.zero.alpine.listener.Listenable
import net.challenge.client.features.cosmetics.Cosmetic
import net.challenge.client.features.cosmetics.annotations.CosmeticInfo
import net.minecraft.client.Minecraft
import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelRenderer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11
import kotlin.math.cos
import kotlin.math.sin

@CosmeticInfo(name = "Wing", description = "wings :)")
class CosmeticWing : Cosmetic() {

    override fun render(player: EntityPlayer, x: Double, y: Double, z: Double, partialTicks: Float) {
        val wingModel = model as ModelWing
        val scale = 0.7f
        val rotate: Float = 180f + interpolate(player.prevRenderYawOffset, player.renderYawOffset, partialTicks)
        val nothing = 0.0f
        val speed = 4
        GlStateManager.pushMatrix()
        GlStateManager.resetColor()
        GlStateManager.scale(-scale, -scale, scale)
        GlStateManager.rotate(rotate, nothing, 1.0f, nothing)
        GlStateManager.translate(nothing.toDouble(), -(1.25 / scale), nothing.toDouble())
        GlStateManager.translate(nothing, nothing, 0.15f / scale)
        Minecraft.getMinecraft().textureManager.bindTexture(wingModel.location)
        if (player.isSneaking) GlStateManager.translate(0.0, 0.125 / scale, 0.0)
        for (j in 0..1) {
            GL11.glEnable(GL11.GL_CULL_FACE)
            val f11 = System.currentTimeMillis() % 1000L / 1000.0f * "$speed.1415927".toFloat() * (speed - 1)
            wingModel.wing.rotateAngleX = Math.toRadians(-80.0).toFloat() - cos(f11.toDouble()).toFloat() * 0.2f
            wingModel.wing.rotateAngleY = Math.toRadians(20.0).toFloat() + sin(f11.toDouble()).toFloat() * 0.4f
            wingModel.wing.rotateAngleZ = Math.toRadians(20.0).toFloat()
            wingModel.wingTip.rotateAngleZ = (-(sin(f11 + 2.0f.toDouble()) + 0.5)).toFloat() * 0.75f
            wingModel.wing.render(0.0625f)
            GlStateManager.scale(-1.0f, 1.0f, 1.0f)
            GL11.glCullFace(GL11.GL_FRONT)
        }
        GL11.glCullFace(GL11.GL_BACK)
        GL11.glDisable(GL11.GL_CULL_FACE)
        GL11.glPopMatrix()
    }

    private val model: ModelBase
        get() = ModelWing()

    private class ModelWing : ModelBase(), Listenable {
        val wing = ModelRenderer(this, "wing")
        val wingTip = ModelRenderer(this, "wingtip")
        val location = ResourceLocation("client/cosmetic/wings/wing.png")

        init {
            setTextureOffset("wing.bone", 0, 0)
            setTextureOffset("wing.skin", -10, 8)
            setTextureOffset("wingtip.bone", 0, 5)
            setTextureOffset("wingtip.skin", -10, 18)
            wing.setTextureSize(30, 30)
            wing.setRotationPoint(-2.0f, 0.0f, 0.0f)
            wing.addBox("bone", -10.0f, -1.0f, -1.0f, 10, 2, 2)
            wing.addBox("skin", -10.0f, 0.0f, 0.5f, 10, 0, 10)
            wingTip.setTextureSize(30, 30)
            wingTip.setRotationPoint(-10.0f, 0.0f, 0.0f)
            wingTip.addBox("bone", -10.0f, -0.5f, -0.5f, 10, 1, 1)
            wingTip.addBox("skin", -10.0f, 0.0f, 0.5f, 10, 0, 10)
            wing.addChild(wingTip)
        }
    }
}

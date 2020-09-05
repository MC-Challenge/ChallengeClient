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

package net.challenge.client.utils

import com.google.gson.JsonSyntaxException
import net.challenge.client.injection.mixins.MixinMinecraft
import net.challenge.client.injection.mixins.client.shader.MixinShaderGroup
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.shader.Framebuffer
import net.minecraft.client.shader.ShaderGroup
import net.minecraft.util.ResourceLocation
import net.minecraft.util.Timer
import java.io.IOException


object BlurUtil : IMC {

    //is the shader-config
    private val resourceLocation = ResourceLocation("challenge/shader/blur.json")

    //are the render ticks
    var renderPartialTicks: Float? = null

    //is the group, of the blur shader
    private var shaderGroup: ShaderGroup? = null

    //is the minecraft-main-framebuffer
    var framebuffer: Framebuffer? = null

    //is the last-set scale-factor from a blur
    private var lastFactor = 0

    //is the last-width of a blur
    private var lastWidth = 0

    //is the last-height of a blur
    private var lastHeight = 0

    /**
     * <h1>Loading a blur</h1>
     *
     *
     * This method, loads all.
     *
     * @see ShaderGroup
     *
     * @see net.minecraft.client.Minecraft
     *
     * @see Framebuffer
     */
    fun init() {
        try {
            //sets the shader-group, in 'init' cause of the throw from the constructor in 'ShaderGroup'
            this.shaderGroup = ShaderGroup(mc.textureManager, mc.resourceManager, mc.framebuffer, resourceLocation)

            //creates a bounded frame-buffer
            this.shaderGroup!!.createBindFramebuffers(mc.displayWidth, mc.displayHeight)

        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * # blurring the complete screen
     *
     * This method, blurs the complete screen
     *
     * **Example:** `blur(10)` blurs the complete screen (0, 0, width, height)
     *
     * @author 6AM
     * @see Framebuffer
     *
     * @see net.minecraft.client.renderer.GlStateManager
     *
     * @see ScaledResolution
     *
     * @see ShaderGroup
     *
     * @see BlurUtil
     *
     * @see net.minecraft.client.Minecraft
     *
     * @since 1.0
     */
    fun blur() {

        //is the resolution, of the screen
        val scaledResolution = ScaledResolution(mc)

        //when its not the same resolution, init the blur new
        if (this.lastFactor != scaledResolution.scaleFactor || this.lastWidth != scaledResolution.scaledWidth || this.lastHeight != scaledResolution.scaledHeight || this.framebuffer == null) {
            //init's the blur new
            this.init()
        }

        //sets the 'lastFactor'
        this.lastFactor = scaledResolution.scaleFactor

        //sets the 'lastWidth'
        this.lastWidth = scaledResolution.scaledWidth

        //sets the 'lastHeight'
        this.lastHeight = scaledResolution.scaledHeight

        //binds the framebuffer
        this.framebuffer?.bindFramebuffer(true)

        //loads the shader-group with the current-timer-renderPartialTicks
        renderPartialTicks?.let { this.shaderGroup?.loadShaderGroup(it) }

        //binds the framebuffer to minecraft
        this.mc.framebuffer.bindFramebuffer(false)

        //enables the alpha
        GlStateManager.enableAlpha()
    }

}
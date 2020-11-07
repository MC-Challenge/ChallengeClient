package net.challenge.client.utils

import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.shader.Framebuffer
import net.minecraft.client.shader.Shader
import net.minecraft.client.shader.ShaderGroup
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.ReflectionHelper
import java.lang.reflect.Field


object BlurUtil : IMC {
    //is the shader-config
    private val resourceLocation = ResourceLocation("challenge/shader/blur.json")

    //is the group, of the blur shader
    private var shaderGroup: ShaderGroup? = null

    //is the minecraft-main-framebuffer
    private var framebuffer: Framebuffer? = null

    //is the last-set scale-factor from a blur
    private var lastFactor = 0

    //is the last-width of a blur
    private var lastWidth = 0

    //is the last-height of a blur
    private var lastHeight = 0

    private var listShaders: Field? = null

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
        //sets the listShaders Field
        listShaders = ReflectionHelper.findField(ShaderGroup::class.java, "field_148031_d", "listShaders")

        //sets the shader-group, in 'init' cause of the throw from the constructor in 'ShaderGroup'
        shaderGroup = ShaderGroup(mc.textureManager, mc.resourceManager, mc.framebuffer, resourceLocation)

        //creates a bounded frame-buffer
        shaderGroup!!.createBindFramebuffers(mc.displayWidth, mc.displayHeight)

        //sets the framebuffer
        val framebufferFiled: Field = ReflectionHelper.findField(ShaderGroup::class.java, "mainFramebuffer")
        framebuffer = framebufferFiled.get(shaderGroup) as Framebuffer
    }

    /**
     * <h1>Setting the Strength</h1>
     *
     *
     * This method sets the strength, of a shader
     *
     *
     * **Example:** `setValues(10)` sets the strength for the next blur (10) Special cases:
     *  * when the blur is very high, it can hit the performance
     *
     * @param strength is the new strength
     * @see net.minecraft.client.shader.Shader
     *
     * @see net.minecraft.client.shader.ShaderManager
     */
    private fun setValues(strength: Float) {

        //starts an loop (4 loops)
        for (i in 0..2) {
            listShaders?.let {
                val current: List<Shader> = listShaders!!.get(shaderGroup) as List<Shader>

                //sets the strength of the shadersVe
                current[i].shaderManager.getShaderUniform("Radius").set(strength)
            }
        }
    }

    /**
     * <h1>Blurring the complete screen</h1>
     *
     *
     * This method, blurs the complete screen
     *
     *
     * **Example:** `blur(10)` blurs the complete screen (0, 0, width, height), with a concrete blur strength (10) Special cases:
     *  * when the blur is very high, it can hit the performance
     *
     * @param blurStrength is the strength of the new blur
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
    fun blur(blurStrength: Float, renderTicks: Float) {

        //is the resolution, of the screen
        val scaledResolution = ScaledResolution(mc)

        //when its not the same resolution, init the blur new
        if (lastFactor != scaledResolution.scaleFactor || lastWidth != scaledResolution.scaledWidth || lastHeight != scaledResolution.scaledHeight || framebuffer == null) {
            //init's the blur new
            init()
        }

        //sets the 'lastFactor'
        lastFactor = scaledResolution.scaleFactor

        //sets the 'lastWidth'
        lastWidth = scaledResolution.scaledWidth

        //sets the 'lastHeight'
        lastHeight = scaledResolution.scaledHeight

        //sets the blur-strength
        setValues(blurStrength)

        //binds the framebuffer
        framebuffer!!.bindFramebuffer(true)

        //loads the shader-group with the current-timer-renderPartialTicks
        shaderGroup!!.loadShaderGroup(renderTicks)

        //binds the framebuffer to minecraft
        this.mc.framebuffer.bindFramebuffer(false)

        //enables the alpha
        GlStateManager.enableAlpha()
    }
}
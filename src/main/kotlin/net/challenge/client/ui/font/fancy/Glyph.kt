package net.challenge.client.ui.font.fancy

import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.texture.DynamicTexture
import org.lwjgl.opengl.GL11
import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.font.FontRenderContext
import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage
import java.util.*
import kotlin.math.ceil
import kotlin.math.sqrt

class Glyph(var font: Font, val isAntiAliasingEnabled: Boolean, val isFractionalMetricsEnabled: Boolean) {
    private var imgSize = 0
    var maxFontHeight = -1
        private set
    private val glyphCharacterMap = HashMap<Char, Glyph1>()
    private var bufferedImage: BufferedImage? = null
    private var loadedTexture: DynamicTexture? = null
    fun generate(chars: CharArray) {
        var maxWidth = -1.0
        var maxHeight = -1.0
        var currentCharHeight = 0
        var posX = 0
        var posY = 1
        val affineTransform = AffineTransform()
        val fontRenderContext = FontRenderContext(affineTransform, isAntiAliasingEnabled, isFractionalMetricsEnabled)
        for (ch in chars) {
            val bounds = font.getStringBounds(ch.toString(), fontRenderContext)
            if (maxWidth < bounds.width) maxWidth = bounds.width
            if (maxHeight < bounds.height) maxHeight = bounds.height
        }
        maxWidth += 2.0
        maxHeight += 2.0
        imgSize = ceil(ceil(sqrt(maxWidth * maxWidth * chars.size) / maxWidth).coerceAtLeast(ceil(sqrt(maxHeight * maxHeight * chars.size) / maxHeight)) * maxWidth.coerceAtLeast(maxHeight)).toInt() + 1
        bufferedImage = BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_ARGB)
        val g = bufferedImage!!.graphics as Graphics2D
        g.font = font
        g.color = Color(255, 255, 255, 0)
        g.fillRect(0, 0, imgSize, imgSize)
        g.color = Color.white
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, if (isFractionalMetricsEnabled) RenderingHints.VALUE_FRACTIONALMETRICS_ON else RenderingHints.VALUE_FRACTIONALMETRICS_OFF)
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, if (isAntiAliasingEnabled) RenderingHints.VALUE_ANTIALIAS_OFF else RenderingHints.VALUE_ANTIALIAS_ON)
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, if (isAntiAliasingEnabled) RenderingHints.VALUE_TEXT_ANTIALIAS_ON else RenderingHints.VALUE_TEXT_ANTIALIAS_OFF)
        val fontMetrics = g.fontMetrics
        for (ch in chars) {
            val glyph1 = Glyph1()
            val bounds = fontMetrics.getStringBounds(ch.toString(), g)
            glyph1.width = bounds.bounds.width + 8
            glyph1.height = bounds.bounds.height
            if (posX + glyph1.width >= imgSize) {
                posX = 0
                posY += currentCharHeight
                currentCharHeight = 0
            }
            glyph1.x = posX
            glyph1.y = posY
            if (glyph1.height > maxFontHeight) maxFontHeight = glyph1.height
            if (glyph1.height > currentCharHeight) currentCharHeight = glyph1.height
            g.drawString(ch.toString(), posX + 2, posY + fontMetrics.ascent)
            posX += glyph1.width
            glyphCharacterMap[ch] = glyph1
        }
    }

    fun setupTexture() {
        loadedTexture = DynamicTexture(bufferedImage)
    }

    fun bindTexture() {
        GlStateManager.bindTexture(loadedTexture!!.glTextureId)
    }

    fun unbindTexture() {
        GlStateManager.bindTexture(0)
    }

    fun drawChar(ch: Char, x: Float, y: Float): Float {
        val glyph1 = glyphCharacterMap[ch] ?: throw IllegalArgumentException("'$ch' wasn't found")
        val pageX = glyph1.x / imgSize.toFloat()
        val pageY = glyph1.y / imgSize.toFloat()
        val pageWidth = glyph1.width / imgSize.toFloat()
        val pageHeight = glyph1.height / imgSize.toFloat()
        val width = glyph1.width.toFloat()
        val height = glyph1.height.toFloat()
        GL11.glBegin(GL11.GL_TRIANGLES)
        GL11.glTexCoord2f(pageX + pageWidth, pageY)
        GL11.glVertex2f(x + width, y)
        GL11.glTexCoord2f(pageX, pageY)
        GL11.glVertex2f(x, y)
        GL11.glTexCoord2f(pageX, pageY + pageHeight)
        GL11.glVertex2f(x, y + height)
        GL11.glTexCoord2f(pageX, pageY + pageHeight)
        GL11.glVertex2f(x, y + height)
        GL11.glTexCoord2f(pageX + pageWidth, pageY + pageHeight)
        GL11.glVertex2f(x + width, y + height)
        GL11.glTexCoord2f(pageX + pageWidth, pageY)
        GL11.glVertex2f(x + width, y)
        GL11.glEnd()
        return width - 8
    }

    fun getWidth(ch: Char): Float {
        return glyphCharacterMap[ch]!!.width.toFloat()
    }

    internal class Glyph1 {
        var x = 0
        var y = 0
        var width = 0
        var height = 0
    }
}
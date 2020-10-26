package net.challenge.client.ui.font.fancy

import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import org.lwjgl.opengl.GL11
import java.util.*

class GLFont(val regularGlyph: Glyph, private val boldGlyph: Glyph?, private val italicGlyph: Glyph?, private val boldItalicGlyph: Glyph?) {
    private val colorCode = IntArray(32)
    var randomStyle = false
    var boldStyle = false
    var italicStyle = false
    var underlineStyle = false
    var strikethroughStyle = false
    private var posX = 0f
    private var posY = 0f
    private var red = 0f
    private var blue = 0f
    private var green = 0f
    private var alpha = 0f
    fun drawString(text: String?, x: Float, y: Float, color: Int, dropShadow: Boolean): Int {
        GlStateManager.enableAlpha()
        resetStyles()
        var i: Int
        if (dropShadow) {
            i = renderString(text, x + 0.625f, y + 0.625f, color, true)
            i = i.coerceAtLeast(renderString(text, x, y, color, false))
        } else {
            i = renderString(text, x, y, color, false)
        }
        GlStateManager.disableAlpha()
        return i
    }

    fun drawString(text: String?, x: Double, y: Double, color: Int, dropShadow: Boolean): Int {
        return drawString(text, x.toFloat(), y.toFloat(), color, dropShadow)
    }

    fun drawString(text: String?, x: Double, y: Double, color: Int): Int {
        return drawString(text, x.toFloat(), y.toFloat(), color, false)
    }

    private fun renderString(text: String?, x: Float, y: Float, color: Int, dropShadow: Boolean): Int {
        var color = color
        return if (text == null) {
            0
        } else {
            if (color and -67108864 == 0) {
                color = color or -16777216
            }
            if (dropShadow) {
                color = color and 16579836 shr 2 or color and -16777216
            }
            red = (color shr 16 and 255).toFloat() / 255.0f
            blue = (color shr 8 and 255).toFloat() / 255.0f
            green = (color and 255).toFloat() / 255.0f
            alpha = (color shr 24 and 255).toFloat() / 255.0f
            GlStateManager.color(red, blue, green, alpha)
            posX = x * 2.0f
            posY = y * 2.0f
            renderStringAtPos(text, dropShadow)
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)
            (posX / 4.0f).toInt()
        }
    }

    private fun renderStringAtPos(text: String, shadow: Boolean) {
        var glyph = currentGlyphPage
        GL11.glPushMatrix()
        GL11.glScaled(0.5, 0.5, 0.5)
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        GlStateManager.enableTexture2D()
        glyph.bindTexture()
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR)
        var i = 0
        while (i < text.length) {
            val c0 = text[i]
            if (c0.toInt() == 167 && i + 1 < text.length) {
                var i1 = "0123456789abcdefklmnor".indexOf(text.toLowerCase(Locale.ENGLISH)[i + 1])
                if (i1 < 16) {
                    randomStyle = false
                    boldStyle = false
                    strikethroughStyle = false
                    underlineStyle = false
                    italicStyle = false
                    if (i1 < 0) {
                        i1 = 15
                    }
                    if (shadow) {
                        i1 += 16
                    }
                    val j1 = colorCode[i1]
                    GlStateManager.color((j1 shr 16).toFloat() / 255.0f, (j1 shr 8 and 255).toFloat() / 255.0f, (j1 and 255).toFloat() / 255.0f, alpha)
                } else if (i1 == 16) {
                    randomStyle = true
                } else if (i1 == 17) {
                    boldStyle = true
                } else if (i1 == 18) {
                    strikethroughStyle = true
                } else if (i1 == 19) {
                    underlineStyle = true
                } else if (i1 == 20) {
                    italicStyle = true
                } else {
                    randomStyle = false
                    boldStyle = false
                    strikethroughStyle = false
                    underlineStyle = false
                    italicStyle = false
                    GlStateManager.color(red, blue, green, alpha)
                }
                ++i
            } else {
                glyph = currentGlyphPage
                glyph.bindTexture()
                val f = glyph.drawChar(c0, posX, posY)
                doDraw(f, glyph)
            }
            ++i
        }
        glyph.unbindTexture()
        GL11.glPopMatrix()
    }

    private fun doDraw(f: Float, glyph: Glyph) {
        if (strikethroughStyle) {
            val tessellator = Tessellator.getInstance()
            val worldrenderer = tessellator.worldRenderer
            GlStateManager.disableTexture2D()
            worldrenderer.begin(7, DefaultVertexFormats.POSITION)
            worldrenderer.pos(posX.toDouble(), posY + (glyph.maxFontHeight / 2).toDouble(), 0.0).endVertex()
            worldrenderer.pos(posX + f.toDouble(), posY + (glyph.maxFontHeight / 2).toDouble(), 0.0).endVertex()
            worldrenderer.pos(posX + f.toDouble(), posY + (glyph.maxFontHeight / 2).toFloat() - 1.0f.toDouble(), 0.0).endVertex()
            worldrenderer.pos(posX.toDouble(), posY + (glyph.maxFontHeight / 2).toFloat() - 1.0f.toDouble(), 0.0).endVertex()
            tessellator.draw()
            GlStateManager.enableTexture2D()
        }
        if (underlineStyle) {
            val tessellator1 = Tessellator.getInstance()
            val worldrenderer1 = tessellator1.worldRenderer
            GlStateManager.disableTexture2D()
            worldrenderer1.begin(7, DefaultVertexFormats.POSITION)
            val l = if (underlineStyle) -1 else 0
            worldrenderer1.pos(posX + l.toDouble(), posY + glyph.maxFontHeight.toDouble(), 0.0).endVertex()
            worldrenderer1.pos(posX + f.toDouble(), posY + glyph.maxFontHeight.toDouble(), 0.0).endVertex()
            worldrenderer1.pos(posX + f.toDouble(), posY + glyph.maxFontHeight.toFloat() - 1.0f.toDouble(), 0.0).endVertex()
            worldrenderer1.pos(posX + l.toDouble(), posY + glyph.maxFontHeight.toFloat() - 1.0f.toDouble(), 0.0).endVertex()
            tessellator1.draw()
            GlStateManager.enableTexture2D()
        }
        posX += f
    }

    private val currentGlyphPage: Glyph
        get() = if (boldStyle && italicStyle) boldItalicGlyph!! else if (boldStyle) boldGlyph!! else if (italicStyle) italicGlyph!! else regularGlyph

    private fun resetStyles() {
        randomStyle = false
        boldStyle = false
        italicStyle = false
        underlineStyle = false
        strikethroughStyle = false
    }

    val height: Int
        get() = regularGlyph.maxFontHeight / 2

    fun getWidth(text: String?): Int {
        if (text == null) {
            return 0
        }
        var width = 0
        var currentPage: Glyph
        val size = text.length
        var on = false
        var i = 0
        while (i < size) {
            var character = text[i]
            if (character == '\u00a7') on = true else if (on && character >= '0' && character <= 'r') {
                val colorIndex = "0123456789abcdefklmnor".indexOf(character)
                if (colorIndex < 16) {
                    boldStyle = false
                    italicStyle = false
                } else if (colorIndex == 17) {
                    boldStyle = true
                } else if (colorIndex == 20) {
                    italicStyle = true
                } else if (colorIndex == 21) {
                    boldStyle = false
                    italicStyle = false
                }
                i++
                on = false
            } else {
                if (on) i--
                character = text[i]
                currentPage = currentGlyphPage
                width += (currentPage.getWidth(character) - 8).toInt()
            }
            i++
        }
        return width / 2
    }

    fun trimStringToWidth(text: String, width: Int): String {
        return this.trimStringToWidth(text, width, false)
    }

    fun trimStringToWidth(text: String, maxWidth: Int, reverse: Boolean): String {
        val stringbuilder = StringBuilder()
        var on = false
        val j = if (reverse) text.length - 1 else 0
        val k = if (reverse) -1 else 1
        var width = 0
        var currentPage: Glyph
        var i = j
        while (i >= 0 && i < text.length && i < maxWidth) {
            var character = text[i]
            if (character == '\u00a7') on = true else if (on && character >= '0' && character <= 'r') {
                val colorIndex = "0123456789abcdefklmnor".indexOf(character)
                if (colorIndex < 16) {
                    boldStyle = false
                    italicStyle = false
                } else if (colorIndex == 17) {
                    boldStyle = true
                } else if (colorIndex == 20) {
                    italicStyle = true
                } else if (colorIndex == 21) {
                    boldStyle = false
                    italicStyle = false
                }
                i++
                on = false
            } else {
                if (on) i--
                character = text[i]
                currentPage = currentGlyphPage
                width += ((currentPage.getWidth(character) - 8) / 2).toInt()
            }
            if (i > width) {
                break
            }
            if (reverse) {
                stringbuilder.insert(0, character)
            } else {
                stringbuilder.append(character)
            }
            i += k
        }
        return stringbuilder.toString()
    }

    fun drawStringWithShadow(text: String?, x: Float, y: Float, color: Int): Int {
        return this.drawString(text, x, y, color, false)
    }

    fun drawStringWithShadow(text: String?, x: Double, y: Double, color: Int) {
        this.drawString(text, x.toFloat(), y.toFloat(), color, true)
    }

    fun drawCenteredString(text: String?, x: Int, y: Int, color: Int) {
        this.drawStringWithShadow(text, (x - getWidth(text) / 2).toFloat(), y.toFloat(), color)
    }

    fun drawCenteredString(text: String?, x: Double, y: Double, color: Int) {
        this.drawStringWithShadow(text, (x - getWidth(text) / 2).toFloat() - 1, y.toFloat(), color)
    }

    init {
        for (i in 0..31) {
            val j = (i shr 3 and 1) * 85
            var k = (i shr 2 and 1) * 170 + j
            var l = (i shr 1 and 1) * 170 + j
            var i1 = (i and 1) * 170 + j
            if (i == 6) {
                k += 85
            }
            if (i >= 16) {
                k /= 4
                l /= 4
                i1 /= 4
            }
            colorCode[i] = k and 255 shl 16 or (l and 255 shl 8) or (i1 and 255)
        }
    }
}
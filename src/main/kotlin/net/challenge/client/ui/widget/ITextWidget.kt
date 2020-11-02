package net.challenge.client.ui.widget

import net.challenge.client.ui.font.fancy.GLFont

interface ITextWidget<W : ITextWidget<W>> {
    var font: GLFont

    fun setFont(font: GLFont): W {
        this.font = font
        @Suppress("UNCHECKED_CAST")
        return this as W
    }
}
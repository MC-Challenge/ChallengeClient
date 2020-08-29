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
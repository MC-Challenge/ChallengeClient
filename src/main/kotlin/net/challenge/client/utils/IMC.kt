package net.challenge.client.utils

import net.minecraft.client.Minecraft

/**
 * Implementation of the instance of [Minecraft]
 */
interface IMC {

    /**
     * Instance of [Minecraft]
     */
    val mc: Minecraft
        get() = Minecraft.getMinecraft()
}
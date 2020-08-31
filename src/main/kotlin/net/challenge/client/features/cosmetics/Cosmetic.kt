package net.challenge.client.features.cosmetics

import net.challenge.client.features.cosmetics.annotations.CosmeticInfo


abstract class Cosmetic : ICosmetic {

    override var name: String = javaClass.getAnnotation(CosmeticInfo::class.java).name

    override var description: String = javaClass.getAnnotation(CosmeticInfo::class.java).description

    override var enabled: Boolean = true

    override fun toggle() {
        enabled = !enabled
    }

    override fun interpolate(yaw1: Float, yaw2: Float, percent: Float): Float {
        var rotation = (yaw1 + (yaw2 - yaw1) * percent) % 360.0f
        if (rotation < 0.0f) {
            rotation += 360.0f
        }
        return rotation
    }

}
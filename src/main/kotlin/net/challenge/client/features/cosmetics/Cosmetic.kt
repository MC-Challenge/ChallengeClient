package net.challenge.client.features.cosmetics

import net.challenge.client.features.cosmetics.annotations.CosmeticInfo


abstract class Cosmetic : ICosmetic {

    override var enabled: Boolean = false
        set(value) {
            field = value

            if (value)
                onEnable()
            else
                onDisable()
        }


    init {
        val info: CosmeticInfo = javaClass.getAnnotation(CosmeticInfo::class.java)

        name = info.name
        description = info.description
    }


    override fun toggle() {
        enabled = !enabled
    }

}
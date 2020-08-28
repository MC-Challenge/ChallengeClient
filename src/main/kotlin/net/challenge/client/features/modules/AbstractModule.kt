package net.challenge.client.features.modules

import net.challenge.client.features.modules.annotations.ModuleInfo

abstract class AbstractModule {
    private var name: String? = ""
    private var description: String? = null
    private var enabled = false

    init {
        val info: ModuleInfo = javaClass.getAnnotation(ModuleInfo::class.java)
        name = info.name
        description = info.description
    }

    open fun onEnable() {
        //Modification.EVENT_BUS.subscribe(this)
    }

    open fun onDisable() {
        //Modification.EVENT_BUS.unsubscribe(this)
    }

    open fun getName(): String? {
        return name
    }

    open fun getDescription(): String? {
        return description
    }

    open fun isEnabled(): Boolean {
        return enabled
    }

    open fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
        if (enabled) onEnable() else onDisable()
    }
}
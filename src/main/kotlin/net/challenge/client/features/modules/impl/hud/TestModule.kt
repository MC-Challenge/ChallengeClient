package net.challenge.client.features.modules.impl.hud

import net.challenge.client.features.modules.SimpleHudModule
import net.challenge.client.features.modules.annotations.ModuleInfo

/**
 * # TestModule
 *
 * A module for testing
 */
@ModuleInfo(name = "Test")
object TestModule : SimpleHudModule() {

    override fun getValue(): String {
        return "loading ..."
    }

    override fun getDisplayName(): String {
        return "TEST"
    }
}
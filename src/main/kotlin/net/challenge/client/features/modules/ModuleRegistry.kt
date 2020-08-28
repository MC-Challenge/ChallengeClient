package net.challenge.client.features.modules

import java.util.*

class ModuleRegistry {

    //are the client-modules
    private var clientModules: Collection<AbstractModule> = ArrayList()

    /**
     *
     * **Adding the modules**
     *
     * This method, init's the module-registry
     *
     */

    init {

    }

    /**
     * **Registering more Modules**
     *
     * This method, registers more modules
     * @param modules are the modules, that will be registered
     */

    @SafeVarargs
    fun registerModules(vararg modules: AbstractModule) {
        //registers the modules
        modules.forEach(this::registerModule)
    }

    /**
     * **Registering one Module**
     *
     * This method, registers one module
     * @param module is the module, that will be registered
     */

    private fun registerModule(module: AbstractModule) {
        clientModules += module
    }

    /**
     * **Getting an module by name**
     * @return the module that was found, or null
     */

    fun getModule(name: String): AbstractModule {
        return clientModules.stream().filter { mod: AbstractModule -> name.equals(mod.getName(), ignoreCase = true) }.findFirst().orElse(null)
    }

}
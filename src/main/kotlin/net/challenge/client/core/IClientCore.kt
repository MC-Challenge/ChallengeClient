package net.challenge.client.core

interface IClientCore {

    /**
     * We executed before the client starts.
     *
     * @return If the return value is false, the start process is aborted.
     */
    fun onPreStart(): Boolean

    /**
     * This will be executed as soon as the client is successfully started.
     */
    fun onPostStart()

    /**
     * Once the client is turned off, this method is finally executed.
     */
    fun onShutdown()
}
package net.challenge.client.core.info

import net.challenge.client.core.info.version.IVersion


/**
 * Basic information about the client
 */
interface IClientInfo {

    /**
     * Name of the client
     */
    val name: String

    /**
     * Installed version of the client
     */
    val version: IVersion

    /**
     * Creators of the client
     */
    val creators: Array<String>
}
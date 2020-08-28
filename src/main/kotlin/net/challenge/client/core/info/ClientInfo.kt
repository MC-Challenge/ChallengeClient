package net.challenge.client.core.info

import net.challenge.client.core.info.version.IVersion
import net.challenge.client.core.info.version.Version

/**
 * This class contains all information about the client
 */
class ClientInfo {

    /**
     * Name of the Client
     */
    val name: String = "ChallengeClient"

    /**
     * Installed version of the Client
     */
    val version: IVersion = Version(1, 0, 0)

    /**
     * Creator of the client
     */
    val creators: Array<String> = arrayOf("Madakai", "6AM")


    override fun toString(): String {
        return "### Client Info ### \n" +
                "Name: $name \n" +
                "Version: $version \n" +
                "Creator: ${creators.asList()}"
    }
}
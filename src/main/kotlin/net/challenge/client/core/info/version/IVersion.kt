package net.challenge.client.core.info.version

interface IVersion {

    /**
     * Tests if the passed version is higher.
     *
     * @param version The version that is tested to see if it is higher
     * @return Is the version higher
     */
    fun isVersionHigher(version: Version): Boolean {
        return version.toInteger() > toInteger()
    }

    /**
     * @return The version formatted as Integer
     */
    fun toInteger(): Int;
}
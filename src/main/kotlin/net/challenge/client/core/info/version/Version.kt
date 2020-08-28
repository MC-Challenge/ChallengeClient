package net.challenge.client.core.info.version

class Version(private val major: Int, private val minor: Int, private val patch: Int) : IVersion {

    override fun toInteger(): Int {
        return "$major.$minor.$patch".toInt()
    }

    /**
     * @return The version formatted as `major.minor.patch`
     */
    override fun toString(): String {
        return "$major.$minor.$patch"
    }
}
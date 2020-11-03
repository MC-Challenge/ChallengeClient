package net.challenge.client.features.modules.impl.hud

import com.google.common.io.Resources
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.challenge.client.features.modules.ModuleCategory
import net.challenge.client.features.modules.SimpleHudModule
import net.challenge.client.features.modules.annotations.ModuleInfo

@ModuleInfo("MLG Helper", "One that tells you what to do for a safe MLG.", ModuleCategory.HUD)
object HudMlgHelper : SimpleHudModule() {

    /**
     * All known mlg heights.
     */
    private var heights: Map<Int, Mlg> = hashMapOf()

    init {
        // Loading mlg-list from json
        val json = Resources.toString(Resources.getResource("assets/minecraft/challenge/mlgList.json"), Charsets.UTF_8)

        json?.let {
            heights = Gson().fromJson(it, object : TypeToken<HashMap<Int, Mlg>>() {}.type)
        }
    }

    override fun getValue(): String {
        return calcMlg()
    }

    override fun getDisplayName(): String {
        return "MLG"
    }

    /**
     * Calculate what you have to do for an MLG.
     *
     * @return Text that tells you what to do for a MLG.
     */
    private fun calcMlg(): String {
        var y = 1

        val mop = mc.renderViewEntity.rayTrace(200.0, 1.0f)

        if (mop != null) y += mop.blockPos.y

        val sum: Int = mc.thePlayer.position.y - y

        heights[sum]?.let { return it.text }

        return ""
    }

    /**
     * A list of possibilities you have to do for an MLG.
     */
    private enum class Mlg(

            /**
             * Text that tells you what to do for a MLG.
             */
            val text: String
    ) {
        RUN("Run"),
        JUMP("Jump"),
        UP_RUN("Run ^ 1"),
        UP_JUMP("Jump ^ 1"),
        SAVE("Save"),
        UP_SAVE("Save ^ 1");
    }
}
package net.challenge.client.ui.screen

import net.challenge.client.core.ClientCore
import net.challenge.client.features.modules.IModule
import net.challenge.client.features.modules.Module
import net.challenge.client.features.modules.ModuleCategory
import net.challenge.client.ui.font.FontHandler
import net.challenge.client.ui.font.fancy.GLFont
import net.challenge.client.ui.position.Position
import net.challenge.client.ui.widget.IGuiWidget
import net.challenge.client.ui.widget.elements.Checkbox
import net.challenge.client.ui.widget.elements.Slider
import net.challenge.client.ui.widget.elements.list.WidgetList
import net.challenge.client.ui.widget.elements.settings.CategoryButton
import net.challenge.client.ui.widget.elements.settings.ModuleButton
import net.challenge.client.ui.widget.utils.RenderUtils
import net.challenge.client.utils.IScaledResolutionHelper
import net.challenge.configu.container.value.IValueContainer
import net.challenge.configu.value.Value
import net.challenge.configu.value.impl.VNumber
import java.awt.Color


//TODO: create variables for the colors and replace
//TODO: clean-up code
//TODO: Fix 'toInt', 'toFloat', 'toDouble'
class SettingScreen : WidgetScreen(), IScaledResolutionHelper {

    var currentCategory: ModuleCategory = ModuleCategory.CHALLENGE
    private var currentModule: IModule? = null

    // Gets the middle from
    // the screen (x, y)
    private val middleX = getScaledWidth() / 2.0
    private val middleY = getScaledHeight() / 2.0

    // Defines a value for
    // the both length's
    private val widthLength = 190.0
    private val heightLength = 150.0

    // Defines a value,
    // for the real length
    // cause of the [widthLength]
    // or [heightLength] is not
    // the real value
    private val realWidthLength = widthLength * 2;
    private val realHeightLength = heightLength * 2

    // Gets the start from
    // the general screen
    private val startX = middleX - widthLength
    private val startY = middleY - heightLength

    // Gets the end from
    // the general screen
    private val endX = middleX + widthLength
    private val endY = middleY + heightLength

    // Defining variables
    // for each bar
    private val categoryBarLength = 110.0
    private val moduleBarLength = 105.0
    private val settingsBarLength = endX - (startX + categoryBarLength + moduleBarLength)

    // From the entry
    // 'module-bar'
    private val moduleBarStartX = startX + categoryBarLength
    private val moduleBarEndX = startX + (categoryBarLength + moduleBarLength)
    private val moduleBarTopEndY = startY + 15

    // From the entry
    // 'settings-bar'
    private val settingsBarStartX = startX + categoryBarLength + moduleBarLength

    // Defines a variable
    // for all colors
    var mainColor = Color(14, 113, 214)
    var moduleBackgroundColor = Color(22, 21, 22)
    var mainBackgroundColor = Color(35, 34, 35)

    // TODO: get this piece of garbage in an separate manager/handler
    private var smallFont: GLFont = FontHandler.getFancyFontRenderer("raleway/raleway-medium", 10)
    private var standardFont: GLFont = FontHandler.getFancyFontRenderer("raleway/raleway-medium", 16)
    private var bigFont: GLFont = FontHandler.getFancyFontRenderer("raleway/raleway-medium", 30)

    init {
        val categoryList = WidgetList()
                .setPosition(Position(startX, startY + 40))
                .setBackGroundColor(mainBackgroundColor.rgb)
                .setSize(categoryBarLength.toInt(), heightLength.toInt() * 2 - 40)

        val modulesList = WidgetList()
                .setBackGroundColor(moduleBackgroundColor.rgb)
                .setPosition(Position(moduleBarStartX, moduleBarTopEndY))
                .setSize(moduleBarLength.toInt(), realHeightLength.toInt() - 15)

        val settingsList = WidgetList()
                .setBackGroundColor(Color(35, 34, 35).rgb)
                .setPosition(Position(settingsBarStartX, startY + 25))
                .setSize(settingsBarLength.toInt(), realHeightLength.toInt() - 25)

        ModuleCategory.values().forEach { m ->
            categoryList.widgets(
                    CategoryButton(m)
                            .setColor(mainBackgroundColor)
                            .setHeight(30)
                            .onClick { _, _ ->
                                this.currentCategory = m
                                loadModules(modulesList, settingsList)
                            }
            )
        }

        loadModules(modulesList, settingsList)

        addWidgets(
                categoryList,
                modulesList,
                settingsList
        )
    }

    /**
     * fills the module list
     * @param modulesList is the list
     * @param settingsList are the settings
     */
    private fun loadModules(modulesList: WidgetList, settingsList: WidgetList) {
        modulesList.clear()
        ClientCore.moduleRegistry.modules.stream().filter {  it.category == currentCategory }.forEach { module ->
            val settingWidgets = getWSettingsFromModule(module)
            settingWidgets.forEach { it.setHeight(15) }
            modulesList.widgets(
                    ModuleButton(module)
                            .setColor(moduleBackgroundColor)
                            .setHeight(20)
                            .setCentered(false)
                            .onClick { _, i ->
                                if (i == 0) {
                                    module.setEnabled(!module.isEnabled())
                                } else {
                                    currentModule = module
                                    settingsList.clear()
                                    settingsList.widgets(*settingWidgets.toTypedArray())
                                }
                            }
            )
        }
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {

        // Using 'RenderUtil'
        // cause of the methods
        // with the float and
        // the double para-
        // meter
        RenderUtils.drawRect(startX, startY, endX, endY, mainBackgroundColor.rgb)

        // ## Category-Bar ## //

        val name = "Challenge"
        val version = "1.0"

        val bigFontWidth = bigFont.getWidth(name)
        val standardFontWidth = standardFont.getWidth(version)
        val nameLength = bigFontWidth + standardFontWidth

        val fontX = startX + (categoryBarLength / 2 - nameLength / 2)
        val fontY = startY + 14.0

        bigFont.drawStringWithShadow(name, fontX, fontY, -1)
        standardFont.drawStringWithShadow(version, fontX + bigFontWidth + 1, fontY + bigFont.height / 2, mainColor.rgb)

        // ## Category-Bar ## //

        // ## Module-Bar ## //

        RenderUtils.drawRect(moduleBarStartX, startY, moduleBarEndX, endY, moduleBackgroundColor.rgb)
        RenderUtils.drawRect(moduleBarStartX, startY, moduleBarEndX, moduleBarTopEndY, mainColor.rgb)

        val textLength = standardFont.getWidth(currentCategory.publicName)
        standardFont.drawStringWithShadow(currentCategory.publicName, moduleBarStartX + (moduleBarLength / 2 - textLength / 2), startY + (15 / 2 - standardFont.height / 2), -1)

        // ## Module-Bar ## //

        // ## Settings-Bar ## //

        if (currentModule != null) smallFont.drawStringWithShadow(currentModule!!.category.publicName + "/" + currentModule!!.name, settingsBarStartX + 3, moduleBarTopEndY - 7, Color(133, 134, 137).rgb)

        // ## Settings-Bar ## //

        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    private fun getWSettingsFromModule(module: IValueContainer): Collection<IGuiWidget<*>> {
        val result = mutableListOf<IGuiWidget<*>>()

        module.values.forEach { value ->
            getWidgetByValue(value)?.let {
                result += it
            }
        }

        return result
    }

    private fun getWidgetByValue(setting: Value<*, *>): IGuiWidget<*>? {
        val value = setting.value

        //TODO: Fix hardcode
        if (value is Boolean)
            return Checkbox(setting.name)
                    .setValue(value)
                    .onSelect {
                        setting.setObject(it.value)
                    }

        if (setting is VNumber) {

            //TODO: Fix that piece of bad shit (cannot change the slider width although the setWidth method)
            val slider = Slider(setting.name)
                    .setWidth(40)
                    .setValue(setting.value)
                    .onSelect { setting.setObject(it.value) }
                    .setDecimalPlaces(setting.decimalPlaces)

            setting.maximum?.let { slider.setMaximum(it) }
            setting.minimum?.let { slider.setMinimum(it) }

            return slider
        }

        return null
    }
}
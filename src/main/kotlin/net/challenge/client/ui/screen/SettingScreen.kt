package net.challenge.client.ui.screen

import net.challenge.client.core.ClientCore
import net.challenge.client.ui.position.ScaledPosition
import net.challenge.client.ui.widget.IGuiWidget
import net.challenge.client.ui.widget.elements.Button
import net.challenge.client.ui.widget.elements.Checkbox
import net.challenge.client.ui.widget.elements.Slider
import net.challenge.client.ui.widget.elements.list.WidgetList
import net.challenge.configu.container.ValueContainer
import net.challenge.configu.value.Value
import net.challenge.configu.value.impl.VNumber

class SettingScreen : WidgetScreen() {

    init {
        val width = 150
        val height = 15

        val modulesList = WidgetList()
                .setPosition(ScaledPosition(10, 10))
                .setSize(width, height * 10)

        val settingsList = WidgetList()
                .setPosition(ScaledPosition(10 + width + width/10, 10))
                .setSize(width, height * 10)

        ClientCore.moduleRegistry.modules.forEach { module ->
            val settingWidgets = getWSettingsFromModule(module)
            settingWidgets.forEach { it.setHeight(height) }

            modulesList.widgets(
                    Button(module.name)
                            .setHeight(height)
                            .onClick {
                                settingsList.clear()
                                settingsList.widgets(*settingWidgets.toTypedArray())
                            }
            )
        }

        addWidgets(
                modulesList,
                settingsList
        )
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    private fun getWSettingsFromModule(module: ValueContainer): Collection<IGuiWidget<*>> {
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

        if (value is Boolean)
            return Checkbox(setting.name)
                    .setValue(value)
                    .onSelect {
                        setting.setObject(it.value)
                    }

        if (setting is VNumber) {
            val slider = Slider(setting.name)
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
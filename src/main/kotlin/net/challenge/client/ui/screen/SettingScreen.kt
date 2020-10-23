package net.challenge.client.ui.screen

import net.challenge.client.ui.position.ScaledPosition
import net.challenge.client.ui.widget.elements.Button
import net.challenge.client.ui.widget.elements.Checkbox
import net.challenge.client.ui.widget.elements.Slider
import net.challenge.client.ui.widget.elements.list.WidgetList

class SettingScreen : WidgetScreen() {

    init {
        val width = 80
        val height = 15

        addWidgets(
                WidgetList()
                        .setSize(100, 100)
                        .setPadding(left = 10.0F, right = 10.0F)
                        .setPosition(ScaledPosition(30, 30))
                        .widgets(
                                Button("SelectWorld")
                                        .setSize(width, height)
                                        .onClick {
                                            mc.displayGuiScreen(TestWidgetScreen(this))
                                        },
                                Slider("Value")
                                        .setMaximum(1000.0)
                                        .setValue(1000.0)
                                        .setSize(width, height)
                                        .onSelect {
                                            println("Set value to ${it.value}")
                                        },
                                Slider("Value")
                                        .setMaximum(1000.0)
                                        .setValue(1000.0)
                                        .setSize(width, height)
                                        .onSelect {
                                            println("Set value to ${it.value}")
                                        },
                                Slider("Value")
                                        .setMaximum(1000.0)
                                        .setValue(1000.0)
                                        .setSize(width, height)
                                        .onSelect {
                                            println("Set value to ${it.value}")
                                        },
                                Slider("Value")
                                        .setMaximum(1000.0)
                                        .setValue(1000.0)
                                        .setSize(width, height)
                                        .onSelect {
                                            println("Set value to ${it.value}")
                                        },
                                Slider("Value")
                                        .setMaximum(1000.0)
                                        .setValue(1000.0)
                                        .setSize(width, height)
                                        .onSelect {
                                            println("Set value to ${it.value}")
                                        },
                                Slider("Value")
                                        .setMaximum(1000.0)
                                        .setValue(1000.0)
                                        .setSize(width, height)
                                        .onSelect {
                                            println("Set value to ${it.value}")
                                        },
                                Slider("Value")
                                        .setMaximum(1000.0)
                                        .setValue(1000.0)
                                        .setSize(width, height)
                                        .onSelect {
                                            println("Set value to ${it.value}")
                                        },
                                Slider("Value")
                                        .setMaximum(1000.0)
                                        .setValue(1000.0)
                                        .setSize(width, height)
                                        .onSelect {
                                            println("Set value to ${it.value}")
                                        },
                                Slider("Value")
                                        .setMaximum(1000.0)
                                        .setValue(1000.0)
                                        .setSize(width, height)
                                        .onSelect {
                                            println("Set value to ${it.value}")
                                        },
                                Checkbox("CheckBox")
                                        .setSize(width, height)
                                        .setValue(true)
                                        .onSelect {
                                            println("Set box to " + it.value)
                                        }
                        )
        )
    }
}
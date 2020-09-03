package net.challenge.client.features.commands.list;

import net.challenge.client.core.ClientCore;
import net.challenge.client.features.commands.AbstractCommand;
import net.challenge.client.features.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.norisk.core.features.command.annotations.CommandInfo;

@CommandInfo(name = "toggle", description = "Toggle an Module :)", syntax = "Use §a'Toggle <Module>'§7, to toggle a Module.", aliases = "t")
public class CommandToggle extends AbstractCommand {

    @Override
    public void run(String[] arguments) {

        if (arguments.length < 1) {
            this.sendSyntax();
            return;
        }

        Module module = (Module) ClientCore.INSTANCE.getModuleRegistry().getModule(arguments[0]);

        if (module != null) {
            module.setEnabled(!module.isEnabled());

            if (module.isEnabled()) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§a" + ClientCore.INSTANCE.getInfo().getName() + " §8> §7The Module is now §aenabled§7."));
            } else {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§a" + ClientCore.INSTANCE.getInfo().getName() + " §8> §7The Module is now §cdisabled§7."));
            }

        }
    }

}

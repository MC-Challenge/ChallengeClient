package net.challenge.client.features.commands.list;

import net.challenge.client.core.ClientCore;
import net.challenge.client.features.commands.AbstractCommand;
import net.challenge.client.features.cosmetics.Cosmetic;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.norisk.core.features.command.annotations.CommandInfo;

@CommandInfo(name = "toggleCosmetic", description = "Toggle an Cosmetic :)", syntax = "§7Use §a'ToggleC <Cosmetic>'§7, to toggle a Cosmetic.", aliases = "tc, toggleC")
public class CommandToggleCosmetic extends AbstractCommand {

    @Override
    public void run(String[] arguments) {

        if (arguments.length < 1) {
            this.sendSyntax();
            return;
        }

        Cosmetic module = ClientCore.INSTANCE.getCosmeticRegistry().getCosmetic(arguments[0]);

        if (module != null) {
            module.setEnabled(!module.getEnabled());

            if (module.getEnabled()) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§a" + ClientCore.INSTANCE.getInfo().getName() + " §8> §7The Cosmetic §a'" + module.getName() + "' §7is now §aenabled§7."));
            } else {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§a" + ClientCore.INSTANCE.getInfo().getName() + " §8> §7The Cosmetic §a'" + module.getName() + "' §7is now §cdisabled§7."));
            }

        }
    }

}

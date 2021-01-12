package coreplugin.coreplugin.commands;

import coreplugin.coreplugin.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class discord implements CommandExecutor {
    private String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String Discord = Core.getInstance().getConfig().getString("Discord");
        Player p = (Player) sender;
        p.sendMessage(color(Discord));
        return false;
    }
}

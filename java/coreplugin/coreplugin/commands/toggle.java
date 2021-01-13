package coreplugin.coreplugin.commands;

import coreplugin.coreplugin.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class toggle implements CommandExecutor {

    private String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
    public static boolean DiscordToggle;
    public static boolean JoinToggle;
    public static boolean LeaveToggle;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission("core.toggle")) {
            if (label.equalsIgnoreCase("toggle")){
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("discord")) {
                        DiscordToggle = !DiscordToggle;
                        if (DiscordToggle) {
                            p.sendMessage(color("&aFeature enabled."));
                        }
                        else {
                            p.sendMessage(color("&cFeature disabled."));
                        }
                    } else if (args[0].equalsIgnoreCase("join")) {
                        JoinToggle = !JoinToggle;
                        if (JoinToggle) {
                            p.sendMessage(color("&aFeature enabled."));
                        }
                        else {
                            p.sendMessage(color("&cFeature disabled."));
                        }
                    }
                    else if (args[0].equalsIgnoreCase("leave")){
                        LeaveToggle = !LeaveToggle;
                        if (LeaveToggle) {
                            p.sendMessage(color("&aFeature enabled."));
                        }
                        else {
                            p.sendMessage(color("&cFeature disabled."));
                        }
                    }
                    else {
                        String InvalidArgument = Core.getInstance().getConfig().getString("InvalidArgument");
                        p.sendMessage(color(InvalidArgument));
                    }
                }
                else {
                    String InvalidArgument = Core.getInstance().getConfig().getString("InvalidArgument");
                    p.sendMessage(color(InvalidArgument));
                }

            }

        }
        else {
            String MissingPermission = Core.getInstance().getConfig().getString("MissingPermission");
            p.sendMessage(color(MissingPermission));
        }
        return false;
    }
}

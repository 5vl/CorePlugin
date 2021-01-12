package coreplugin.coreplugin.commands;

import coreplugin.coreplugin.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class heal implements CommandExecutor {
    private String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("core.heal")) {
            Player p = (Player) sender;
            if (label.equalsIgnoreCase("heal")) {
                if (args.length > 0) {
                    try {
                        if (args.length > 1) {
                            if (args[1].equalsIgnoreCase("-s")) {
                                Player ps = Bukkit.getPlayer(args[0]);
                                ps.setHealth(20);
                                ps.setFoodLevel(20);
                                String HealOther = Core.getInstance().getConfig().getString("HealOther");
                                p.sendMessage(color(HealOther + args[0]));
                            } else {
                                String InvalidArgument = Core.getInstance().getConfig().getString("InvalidArgument");
                                p.sendMessage(color(InvalidArgument));
                            }
                        }
                        else {
                            Player ps = Bukkit.getPlayer(args[0]);
                            ps.setHealth(20);
                            ps.setFoodLevel(20);
                            String HealOther = Core.getInstance().getConfig().getString("HealOther");
                            String HealedOther = Core.getInstance().getConfig().getString("HealedOther");
                            p.sendMessage(color(HealOther + args[0]));
                            ps.sendMessage(color(HealedOther + p.getPlayerListName()));
                        }
                    }
                    catch (Exception e) {
                        String CannotFindPlayer = Core.getInstance().getConfig().getString("CannotFindPlayer");
                        p.sendMessage(color(CannotFindPlayer));
                    }
                }
                else if (sender instanceof Player) {
                    p.setHealth(20);
                    p.setFoodLevel(20);
                    String HealSelf = Core.getInstance().getConfig().getString("HealSelf");
                    p.sendMessage(color(HealSelf));
                }
                else {
                    String Console = Core.getInstance().getConfig().getString("Console");
                    p.sendMessage(color(Console));
                }
            }
            return false;
        }
        else {
            Player p = (Player) sender;
            String MissingPermission = Core.getInstance().getConfig().getString("MissingPermission");
            p.sendMessage(color(MissingPermission));
        }
        return false;
    }
}

package coreplugin.coreplugin.commands.punish;

import coreplugin.coreplugin.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class kick implements CommandExecutor {
    private String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("core.kick")) {
            Player p = (Player) sender;
            if (label.equalsIgnoreCase("kick")) {
                try {
                    String DefaultKick = Core.getInstance().getConfig().getString("DefaultKick");
                    String BroadcastKick = Core.getInstance().getConfig().getString("BroadcastKick");
                    if (args.length == 1) {
                        Player ps = Bukkit.getPlayer(args[0]);
                        ps.kickPlayer(color(DefaultKick));
                        Bukkit.broadcastMessage(color(args[0] + " " + BroadcastKick + DefaultKick));
                    }
                    else if (args.length > 1) {
                        Player ps = Bukkit.getPlayer(args[0]);
                        if (args[1].equalsIgnoreCase("-s")) {
                            if (args.length > 2) {
                                String KickReasonS = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                                ps.kickPlayer(color(KickReasonS));
                                p.sendMessage(color(args[0] + " " + BroadcastKick + KickReasonS));
                            } else {
                                ps.kickPlayer(color(DefaultKick));
                                p.sendMessage(color(args[0] + " " + BroadcastKick + DefaultKick));
                            }
                        }
                        else {
                            String KickReason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                            ps.kickPlayer(color(KickReason));
                            Bukkit.broadcastMessage(color(args[0] + " " + BroadcastKick + KickReason));
                        }
                    }
                    else {
                        String CannotFindPlayer = Core.getInstance().getConfig().getString("CannotFindPlayer");
                        p.sendMessage(color(CannotFindPlayer));
                    }
                    return false;
                }
                catch (Exception e) {
                    e.printStackTrace();
                    String CannotFindPlayer = Core.getInstance().getConfig().getString("CannotFindPlayer");
                    p.sendMessage(color(CannotFindPlayer));
                }
            }

        } else {
            Player p = (Player) sender;
            String MissingPermission = Core.getInstance().getConfig().getString("MissingPermission");
            p.sendMessage(color(MissingPermission));
        }
        return false;
    }
}
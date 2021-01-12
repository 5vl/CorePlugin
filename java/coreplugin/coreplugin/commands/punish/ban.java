package coreplugin.coreplugin.commands.punish;

import coreplugin.coreplugin.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ban implements CommandExecutor {
    private String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("core.ban")) {
            Player p = (Player) sender;
            if (label.equalsIgnoreCase("ban")) {
                try {
                    String DefaultBan = Core.getInstance().getConfig().getString("DefaultBan");
                    String BroadcastBan = Core.getInstance().getConfig().getString("BroadcastBan");
                    if (args.length == 1) {
                        Player ps = Bukkit.getPlayer(args[0]);
                        OfflinePlayer po = Bukkit.getOfflinePlayer(args[0]);
                        if (po.isOnline()) {
                            ps.kickPlayer(color(DefaultBan));
                            ps.banPlayer(color(DefaultBan));
                        }
                        else {
                            po.banPlayer(color(DefaultBan));
                        }
                        Bukkit.broadcastMessage(color(args[0] + " " + BroadcastBan + DefaultBan));
                    }
                    else if (args.length > 1) {
                        Player ps = Bukkit.getPlayer(args[0]);
                        OfflinePlayer po = (Player) Bukkit.getOfflinePlayer(args[0]);
                        if (args[1].equalsIgnoreCase("-s")) {
                            if (args.length > 2) {
                                String BanReasonS = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                                if (po.isOnline()) {
                                    ps.kickPlayer(color(BanReasonS));
                                    ps.banPlayer(color(BanReasonS));
                                }
                                else {
                                    po.banPlayer(color(BanReasonS));
                                }
                                p.sendMessage(color(args[0] + " " + BroadcastBan + BanReasonS));
                            } else {
                                if (po.isOnline()) {
                                    ps.kickPlayer(color(DefaultBan));
                                    ps.banPlayer(color(DefaultBan));
                                }
                                else {
                                    po.banPlayer(color(DefaultBan));
                                }
                                p.sendMessage(color(args[0] + " " + BroadcastBan + DefaultBan));
                            }
                        }
                        else {
                            String BanReason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                            if (po.isOnline()) {
                                ps.kickPlayer(color(BanReason));
                                ps.banPlayer(color(BanReason));
                            }
                            else {
                                po.banPlayer(color(BanReason));
                            }
                            Bukkit.broadcastMessage(color(args[0] + " " + BroadcastBan + BanReason));
                        }
                    }
                    else {
                        String CannotFindPlayer = Core.getInstance().getConfig().getString("CannotFindPlayer");
                        p.sendMessage(color(CannotFindPlayer));
                    }
                    return false;
                }
                catch (Exception e) {
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
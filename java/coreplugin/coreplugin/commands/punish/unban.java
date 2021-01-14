package coreplugin.coreplugin.commands.punish;

import coreplugin.coreplugin.Core;
import coreplugin.coreplugin.utils.chatcolors;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class unban extends chatcolors implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (sender.hasPermission("core.unban")) {
            if (label.equalsIgnoreCase("unban")) {
                try {
                    String BroadcastUnban = Core.getInstance().getConfig().getString("BroadcastUnban");
                    if (args.length == 1) {
                        OfflinePlayer po = Bukkit.getOfflinePlayer(args[0]);
                        Bukkit.getBanList(BanList.Type.NAME).pardon(po.getName());
                        Bukkit.broadcastMessage(color(args[0] + " " + BroadcastUnban));
                    }
                    else if (args.length == 2) {
                        if (args[1].equals("-s")) {
                            OfflinePlayer po = Bukkit.getOfflinePlayer(args[0]);
                            Bukkit.getBanList(BanList.Type.NAME).pardon(po.getName());
                            p.sendMessage(color(args[0] + " " + BroadcastUnban));
                        }
                        else {
                            String InvalidAgrument = Core.getInstance().getConfig().getString("InvalidArgument");
                            p.sendMessage(color(InvalidAgrument));
                        }
                    }
                    else {
                        String InvalidAgrument = Core.getInstance().getConfig().getString("InvalidArgument");
                        p.sendMessage(color(InvalidAgrument));
                    }
                }
                catch (Exception e) {
                    String CannotFindPlayer = Core.getInstance().getConfig().getString("CannotFindPlayer");
                    p.sendMessage(color(CannotFindPlayer));
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

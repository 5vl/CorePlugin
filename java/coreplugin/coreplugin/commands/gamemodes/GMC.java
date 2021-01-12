package coreplugin.coreplugin.commands.gamemodes;

import coreplugin.coreplugin.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMC implements CommandExecutor {

    private String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender.hasPermission("core.gmc")) {
            Player p = (Player) sender;
            if (s.equalsIgnoreCase("gmc")) {
                if (strings.length > 0) {
                    try {
                        if (strings.length > 1){
                            if (strings[1].equalsIgnoreCase("-s")){
                                Player ps = Bukkit.getPlayer(strings[0]);
                                ps.setGameMode(GameMode.CREATIVE);
                                String CreativeSetOther = Core.getInstance().getConfig().getString("CreativeSetOther");
                                p.sendMessage(color(CreativeSetOther + strings[0]));
                            }
                            else {
                                String InvalidArgument = Core.getInstance().getConfig().getString("InvalidArgument");
                                p.sendMessage(color(InvalidArgument));
                            }
                        }
                        else {
                            Player ps = Bukkit.getPlayer(strings[0]);
                            ps.setGameMode(GameMode.CREATIVE);
                            String CreativeSetOther = Core.getInstance().getConfig().getString("CreativeSetOther");
                            String CreativeOther = Core.getInstance().getConfig().getString("CreativeOther");
                            p.sendMessage(color(CreativeSetOther + strings[0]));
                            ps.sendMessage(color(CreativeOther + p.getPlayerListName()));
                        }
                    }
                    catch (Exception e) {
                        String CannotFindPlayer = Core.getInstance().getConfig().getString("CannotFindPlayer");
                        p.sendMessage(color(CannotFindPlayer));
                    }
                } else {
                    p.setGameMode(GameMode.CREATIVE);
                    String CreativeSelf = Core.getInstance().getConfig().getString("CreativeSelf");
                    p.sendMessage(color(CreativeSelf));
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
package coreplugin.coreplugin.commands.gamemodes;

import coreplugin.coreplugin.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMA implements CommandExecutor {

    private String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender.hasPermission("core.gma")) {
            Player p = (Player) sender;
            if (s.equalsIgnoreCase("gma")) {
                if (strings.length > 0) {
                    try {
                        if (strings.length > 1){
                            if (strings[1].equalsIgnoreCase("-s")){
                                Player ps = Bukkit.getPlayer(strings[0]);
                                ps.setGameMode(GameMode.ADVENTURE);
                                String AdventureSetOther = Core.getInstance().getConfig().getString("AdventureSetOther");
                                p.sendMessage(color(AdventureSetOther + strings[0]));
                            }
                            else {
                                String InvalidArgument = Core.getInstance().getConfig().getString("InvalidArgument");
                                p.sendMessage(color(InvalidArgument));
                            }
                        }
                        else {
                            Player ps = Bukkit.getPlayer(strings[0]);
                            ps.setGameMode(GameMode.ADVENTURE);
                            String AdventureSetOther = Core.getInstance().getConfig().getString("AdventureSetOther");
                            String AdventureOther = Core.getInstance().getConfig().getString("AdventureOther");
                            p.sendMessage(color(AdventureSetOther + strings[0]));
                            ps.sendMessage(color(AdventureOther + p.getPlayerListName()));
                        }
                    }
                    catch (Exception e) {
                        String CannotFindPlayer = Core.getInstance().getConfig().getString("CannotFindPlayer");
                        p.sendMessage(color(CannotFindPlayer));
                    }
                } else {
                    p.setGameMode(GameMode.ADVENTURE);
                    String AdventureSelf = Core.getInstance().getConfig().getString("AdventureSelf");
                    p.sendMessage(color(AdventureSelf));
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
package coreplugin.coreplugin.commands.gamemodes;

import coreplugin.coreplugin.Core;
import coreplugin.coreplugin.utils.chatcolors;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMS extends chatcolors implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender.hasPermission("core.gms")) {
            Player p = (Player) sender;
            if (s.equalsIgnoreCase("gms")) {
                if (strings.length > 0) {
                    try {
                        if (strings.length > 1){
                            if (strings[1].equalsIgnoreCase("-s")){
                                Player ps = Bukkit.getPlayer(strings[0]);
                                ps.setGameMode(GameMode.SURVIVAL);
                                String SurvivalSetOther = Core.getInstance().getConfig().getString("SurvivalSetOther");
                                p.sendMessage(color(SurvivalSetOther + strings[0]));
                            }
                            else {
                                String InvalidArgument = Core.getInstance().getConfig().getString("InvalidArgument");
                                p.sendMessage(color(InvalidArgument));
                            }
                        }
                        else {
                            Player ps = Bukkit.getPlayer(strings[0]);
                            ps.setGameMode(GameMode.SURVIVAL);
                            String SurvivalSetOther = Core.getInstance().getConfig().getString("SurvivalSetOther");
                            String SurvivalOther = Core.getInstance().getConfig().getString("SurvivalOther");
                            p.sendMessage(color(SurvivalSetOther + strings[0]));
                            ps.sendMessage(color(SurvivalOther + p.getPlayerListName()));
                        }
                    }
                    catch (Exception e) {
                        String CannotFindPlayer = Core.getInstance().getConfig().getString("CannotFindPlayer");
                        p.sendMessage(color(CannotFindPlayer));
                    }
                } else {
                    p.setGameMode(GameMode.SURVIVAL);
                    String SurvivalSelf = Core.getInstance().getConfig().getString("SurvivalSelf");
                    p.sendMessage(color(SurvivalSelf));
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
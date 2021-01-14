package coreplugin.coreplugin.commands.gamemodes;

import coreplugin.coreplugin.Core;
import coreplugin.coreplugin.utils.chatcolors;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMSP extends chatcolors implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender.hasPermission("core.gmsp")) {
            Player p = (Player) sender;
            if (s.equalsIgnoreCase("gmsp")) {
                if (strings.length > 0) {
                    try {
                        if (strings.length > 1){
                            if (strings[1].equalsIgnoreCase("-s")){
                                Player ps = Bukkit.getPlayer(strings[0]);
                                ps.setGameMode(GameMode.SPECTATOR);
                                String SetOther = Core.getInstance().getConfig().getString("SpectatorSetOther");
                                p.sendMessage(color(SetOther + strings[0]));
                            }
                            else {
                                String InvalidArgument = Core.getInstance().getConfig().getString("InvalidArgument");
                                p.sendMessage(color(InvalidArgument));
                            }
                        }
                        else {
                            Player ps = Bukkit.getPlayer(strings[0]);
                            ps.setGameMode(GameMode.SPECTATOR);
                            String SetOther = Core.getInstance().getConfig().getString("SpectatorSetOther");
                            String Other = Core.getInstance().getConfig().getString("SpectatorOther");
                            p.sendMessage(color(SetOther + strings[0]));
                            ps.sendMessage(color(Other + p.getPlayerListName()));
                        }
                    }
                    catch (Exception e) {
                        String CannotFindPlayer = Core.getInstance().getConfig().getString("CannotFindPlayer");
                        p.sendMessage(color(CannotFindPlayer));
                    }
                } else {
                    p.setGameMode(GameMode.SPECTATOR);
                    String Self = Core.getInstance().getConfig().getString("SpectatorSelf");
                    p.sendMessage(color(Self));
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
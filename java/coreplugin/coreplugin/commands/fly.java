package coreplugin.coreplugin.commands;

import coreplugin.coreplugin.Core;
import coreplugin.coreplugin.utils.chatcolors;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class fly extends chatcolors implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender.hasPermission("core.fly")) {
            Player p = (Player) sender;
            if(s.equalsIgnoreCase("fly")) {
                if(strings.length > 0) {
                    try {
                        if (strings.length > 1){
                            if (strings[1].equalsIgnoreCase("-s")){
                                Player ps = Bukkit.getPlayer(strings[0]);
                                ps.setAllowFlight(!ps.getAllowFlight());
                                if (ps.getAllowFlight()) {
                                    String EnableFlyOther = Core.getInstance().getConfig().getString("EnableFlyOther");
                                    p.sendMessage(color(EnableFlyOther + strings[0]));
                                }
                                else {
                                    String DisableFlyOther = Core.getInstance().getConfig().getString("DisableFlyOther");
                                    p.sendMessage(color(DisableFlyOther + strings[0]));
                                }
                            }
                            else {
                                String InvalidArgument = Core.getInstance().getConfig().getString("InvalidArgument");
                                p.sendMessage(color(InvalidArgument));
                            }
                        }
                        else {
                            Player ps = Bukkit.getPlayer(strings[0]);
                            ps.setAllowFlight(!ps.getAllowFlight());
                            String DisableFlyOther = Core.getInstance().getConfig().getString("DisableFlyOther");
                            String EnableFlyOther = Core.getInstance().getConfig().getString("EnableFlyOther");
                            String FlyDisabledOther = Core.getInstance().getConfig().getString("FlyDisabledOther");
                            String FlyEnabledOther = Core.getInstance().getConfig().getString("FlyEnabledOther");
                            if (ps.getAllowFlight()) {
                                p.sendMessage(color(EnableFlyOther + strings[0]));
                                ps.sendMessage(color(FlyEnabledOther + p.getPlayerListName()));
                            } else {
                                p.sendMessage(color(DisableFlyOther + strings[0]));
                                ps.sendMessage(color(FlyDisabledOther + p.getPlayerListName()));
                            }
                        }
                    } catch (Exception e) {
                        String CannotFindPlayer = Core.getInstance().getConfig().getString("CannotFindPlayer");
                        p.sendMessage(color(CannotFindPlayer));
                    }
                }
                else {
                    p.setAllowFlight(!p.getAllowFlight());
                    String DisableFlySelf = Core.getInstance().getConfig().getString("DisableFlySelf");
                    String EnableFlySelf = Core.getInstance().getConfig().getString("EnableFlySelf");
                    if (p.getAllowFlight()) {
                        p.sendMessage(color(EnableFlySelf));
                    }
                    else {
                        p.sendMessage(color(DisableFlySelf));
                    }
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

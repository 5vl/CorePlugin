package coreplugin.coreplugin.events;

import coreplugin.coreplugin.Core;
import coreplugin.coreplugin.commands.toggle;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerLeave implements Listener {
    private String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    @EventHandler
    public void OnPlayerLeave(PlayerQuitEvent event){
        boolean t;
        t = toggle.LeaveToggle;
        if (t) {
            Player p = event.getPlayer();
            String CustomLeave = Core.getInstance().getConfig().getString("CustomLeave");
            event.setQuitMessage(color(CustomLeave + " " + p.getDisplayName()));
        }
    }
}

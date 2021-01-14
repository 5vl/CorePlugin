package coreplugin.coreplugin.events;

import coreplugin.coreplugin.Core;
import coreplugin.coreplugin.utils.chatcolors;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerLeave extends chatcolors implements Listener {
    @EventHandler
    public void OnPlayerLeave(PlayerQuitEvent event){
        boolean t;
        t = Boolean.parseBoolean(Core.getInstance().getConfig().getString("CustomJoinAndLeave"));
        if (t) {
            Player p = event.getPlayer();
            String CustomLeave = Core.getInstance().getConfig().getString("CustomLeave");
            event.setQuitMessage(color(CustomLeave + " " + p.getDisplayName()));
        }
    }
}

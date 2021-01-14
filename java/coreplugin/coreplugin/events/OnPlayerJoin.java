package coreplugin.coreplugin.events;

import coreplugin.coreplugin.Core;
import coreplugin.coreplugin.utils.chatcolors;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoin extends chatcolors implements Listener {
    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {
        boolean t;
        t = Boolean.parseBoolean(Core.getInstance().getConfig().getString("CustomJoinAndLeave"));
        if (t) {
            Player p = event.getPlayer();
            String CustomJoin = Core.getInstance().getConfig().getString("CustomJoin");
            event.setJoinMessage(color(CustomJoin + " " + p.getDisplayName()));
        }
    }
}

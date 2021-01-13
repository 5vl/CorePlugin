package coreplugin.coreplugin.events;

import coreplugin.coreplugin.Core;
import coreplugin.coreplugin.commands.toggle;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoin implements Listener {
    private String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {
        boolean t;
        t = toggle.JoinToggle;
        if (t) {
            Player p = event.getPlayer();
            String CustomJoin = Core.getInstance().getConfig().getString("CustomJoin");
            event.setJoinMessage(color(CustomJoin + " " + p.getDisplayName()));
        }
    }
}

package coreplugin.coreplugin;

import coreplugin.coreplugin.commands.discord;
import coreplugin.coreplugin.commands.fly;
import coreplugin.coreplugin.commands.gamemodes.GMA;
import coreplugin.coreplugin.commands.gamemodes.GMC;
import coreplugin.coreplugin.commands.gamemodes.GMS;
import coreplugin.coreplugin.commands.gamemodes.GMSP;
import coreplugin.coreplugin.commands.heal;
import coreplugin.coreplugin.commands.punish.ban;
import coreplugin.coreplugin.commands.punish.kick;
import coreplugin.coreplugin.commands.punish.unban;
import coreplugin.coreplugin.events.OnPlayerJoin;
import coreplugin.coreplugin.events.OnPlayerLeave;
import coreplugin.coreplugin.utils.MetricsLite;
import coreplugin.coreplugin.utils.UpdateChecker;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {
    private static Core instance;
    String version = this.getDescription().getVersion();
    @Override
    public void onEnable() {
        System.out.println("Enabed Core Plugin " + version);
        instance = this;
        this.getConfig().options().copyDefaults();
        saveDefaultConfig();
        PluginManager plm = org.bukkit.Bukkit.getPluginManager();
        plm.registerEvents(new OnPlayerJoin(), this);
        plm.registerEvents(new OnPlayerLeave(), this);
        registerCMD();
        new UpdateChecker(this, 87756).getLatestVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                Logger.log(Logger.LogLevel.SUCCESS, "Core Plugin is up to date.");
            } else {
                Logger.log(Logger.LogLevel.ERROR, "Plugin is out of date, please download the new version at: https://www.spigotmc.org/resources/core-plugin.87756/ If this is a recently released version, the spigot API might not have updated.");
            }

        });
        int pluginId = 10001;
        MetricsLite metrics = new MetricsLite(this, pluginId);
    }
    public static Core getInstance() {
        return instance;
    }
    private void registerCMD() {
        getCommand("gmc").setExecutor(new GMC());
        getCommand("gms").setExecutor(new GMS());
        getCommand("gma").setExecutor(new GMA());
        getCommand("gmsp").setExecutor(new GMSP());
        getCommand("fly").setExecutor(new fly());
        getCommand("heal").setExecutor(new heal());
        getCommand("kick").setExecutor(new kick());
        getCommand("ban").setExecutor(new ban());
        getCommand("unban").setExecutor(new unban());
        getCommand("discord").setExecutor(new discord());
    }
}
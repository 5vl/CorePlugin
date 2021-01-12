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
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {
    private static Core instance;
    @Override
    public void onEnable() {
        System.out.println("Enabed Core Plugin 1.0.0");
        instance = this;
        this.getConfig().options().copyDefaults();
        saveDefaultConfig();
        PluginManager plm = org.bukkit.Bukkit.getPluginManager();
        registerCMD();
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
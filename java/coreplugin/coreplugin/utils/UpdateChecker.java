package coreplugin.coreplugin.utils;

import coreplugin.coreplugin.Core;
import org.bukkit.Bukkit;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {

    private Core plugin;
    private int resourceID;

    public UpdateChecker (Core plugin, int resourceID) {
        this.plugin = plugin;
        this.resourceID = resourceID;
    }

    public void getLatestVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceID).openStream();
            Scanner scanner = new Scanner(inputStream)) {
            if (scanner.hasNext()) {
                consumer.accept(scanner.next());
            }
        } catch (IOException exception){
                plugin.getLogger().info("Core Plugin Update Checker does not work." + exception.getMessage());
            }
        });
    }
}
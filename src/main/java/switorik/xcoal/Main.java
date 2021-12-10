package switorik.xcoal;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class Main extends JavaPlugin {

    public static Main plugin;
    public static YamlConfiguration message;

    @Override
    public void onEnable() {

        plugin = this;

        File configyml = new File(plugin.getDataFolder(), "config.yml");
        if (!configyml.exists()) {
            plugin.saveResource("config.yml", false);
        }
        reloadMessages();
        reloadPlayers();
        this.getServer().getPluginManager().registerEvents(new blockListener(), this);
        scheduledAlert sa = new scheduledAlert();

        sa.alerts();

        this.getCommand("xcoal").setExecutor(new xcoalCommand());
        Objects.requireNonNull(this.getCommand("xcoal")).setTabCompleter(new tabComplete());

        getServer().getLogger().info("xCoal has been loaded.");

    }

    @Override
    public void onDisable() {
        getServer().getLogger().info("xCoal has been unloaded.");
    }

    public void reloadMessages() {

        File messagesyml = new File(plugin.getDataFolder(), "messages.yml");
        if(messagesyml.exists()) {
            message = YamlConfiguration.loadConfiguration(messagesyml);
        } else {
            plugin.saveResource("messages.yml", false);
        }

    }

    public void reloadPlayers() {

        File configyml = new File(plugin.getDataFolder(), "players.yml");
        if (!configyml.exists()) {
            plugin.saveResource("players.yml", false);
        }

    }

}




/*
commands:
/xcoal <player> <time>
/xcoal remove <player>

config:
turn ore into: coal_ore
tracked ores: [diamond]

player data folder with player uuid and time banned

player.sendMessage(Objects.requireNonNull(message.getString("reload")));
*/
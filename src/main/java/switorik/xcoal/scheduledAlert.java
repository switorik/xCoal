package switorik.xcoal;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class scheduledAlert {

    Main plugin = Main.plugin;

    int sec = 60;

    public void alerts() {

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {

                //pull from data file, delete data
                //have players that mine diamonds in blocklistener names added to data file
                File ymlFileData = new File(plugin.getDataFolder() + File.separator + "data" + File.separator +  "data.yml");
                YamlConfiguration ymlData = new YamlConfiguration().loadConfiguration(ymlFileData);

                for(String player : ymlData.getKeys(false)) {

                    for(Player p : Bukkit.getServer().getOnlinePlayers()) {

                        if(p.hasPermission("xcoal.use")) {
                            p.sendMessage(colorize("&8[&bDiamond Tracker&8] &f" + player + " has mined " + ymlData.get(player + ".times") + " diamonds. (" + ymlData.get(player + ".lastLocation") + ")"));
                        }

                    }

                    Bukkit.getServer().getLogger().info("[Diamond Tracker] " + player + " has mined " + ymlData.get(player + ".times") + " diamonds. (" + ymlData.get(player + ".lastLocation") + ")");
                    ymlData.set(player, null);

                    try {
                        ymlData.save(ymlFileData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, 20L * sec, 20L * sec);

    }

    private String colorize(String msg) {

        return ChatColor.translateAlternateColorCodes('&', msg);

    }

}

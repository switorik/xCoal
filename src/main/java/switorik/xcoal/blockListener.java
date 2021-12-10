package switorik.xcoal;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class blockListener implements Listener {

    Main plugin = Main.plugin;
    File ymlFile = new File(plugin.getDataFolder(), "players.yml");
    File ymlFileData = new File(plugin.getDataFolder() + File.separator + "data" + File.separator +  "data.yml");

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        YamlConfiguration yml = new YamlConfiguration().loadConfiguration(ymlFile);
        YamlConfiguration ymlData = new YamlConfiguration().loadConfiguration(ymlFileData);
        List<String> uuids = yml.getStringList("players");

        if(event.getPlayer().getGameMode() == GameMode.SURVIVAL) {

            if((event.getBlock().getType().equals(Material.DIAMOND_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_DIAMOND_ORE)) && event.getBlock().getY() < 20) {

                int times = ymlData.getInt(event.getPlayer().getName() + ".times", 0);

                ymlData.set(event.getPlayer().getName() + ".times", times + 1);
                ymlData.set(event.getPlayer().getName() + ".lastLocation", event.getBlock().getLocation().getBlockX() + "," + event.getBlock().getLocation().getBlockY() + "," + event.getBlock().getLocation().getBlockZ());

                try {
                    ymlData.save(ymlFileData);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //if(uuids.contains(event.getPlayer().getUniqueId().toString())) { //if player is on the list
/*
            List<String> worlds = plugin.getConfig().getStringList("worlds");

            if(worlds.contains(event.getPlayer().getWorld().getName())) { //if player is on a checked world

                ArrayList<Material> materials = new ArrayList<Material>();
                List<String> blocks = plugin.getConfig().getStringList("blocks");

                for(String block : blocks) { //converting strings from config to a block list

                    materials.add(Material.matchMaterial(block.toUpperCase()));

                }

                if(materials.contains(event.getBlock().getType())) { //if the mined block is on the block list

                    Material drop = Material.matchMaterial(plugin.getConfig().getString("drop"));
                    event.setDropItems(false);
                    event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(drop));

                }

            }
*/
                //FROM HERE

                //if(event.getBlock().getType().equals(Material.DIAMOND_ORE)) {
                if(uuids.contains(event.getPlayer().getUniqueId().toString())) {

                    event.setDropItems(false);
                    event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.COAL));

                }

                //TO HERE replaces the commented out section that allows customize-ability

            }

        }



    }

}

package switorik.xcoal;

import net.md_5.bungee.chat.SelectorComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class xcoalCommand implements CommandExecutor {

    Main plugin = Main.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        YamlConfiguration message = Main.message;

        switch (args.length) {
            case 1: {
                if (args[0].equalsIgnoreCase("add"))
                    sender.sendMessage(Objects.requireNonNull(message.getString("error")));
                if (args[0].equalsIgnoreCase("remove"))
                    sender.sendMessage(Objects.requireNonNull(message.getString("error")));
                if (args[0].equalsIgnoreCase("reload")) reload();
            }
            case 2: {
                if (isPlayer(args[1])) {
                    if (args[0].equalsIgnoreCase("add")) addPlayer(Bukkit.getServer().getPlayer(args[1]), sender);
                    if (args[0].equalsIgnoreCase("remove")) removePlayer(Bukkit.getServer().getPlayer(args[1]), sender);
                } else {

                    sender.sendMessage(Objects.requireNonNull(message.getString("notonline")).replace("%p", args[1]));

                }

                //getofflineplayer(uuid uuid)

            }


        }
        return true;

    }

    public void addPlayer(Player player, CommandSender sender) {

        UUID uuid = player.getUniqueId();
        YamlConfiguration message = Main.message;

        File ymlFile = new File(plugin.getDataFolder(), "players.yml");
        YamlConfiguration yml = new YamlConfiguration().loadConfiguration(ymlFile);
        List<String> uuids = yml.getStringList("players");

        if (uuids.contains(uuid.toString())) {

            sender.sendMessage(Objects.requireNonNull(message.getString("onlist")).replace("%p", player.getName()));

        } else {

            uuids.add(uuid.toString());
            yml.set("players", uuids);
            sender.sendMessage(Objects.requireNonNull(message.getString("addlist")).replace("%p", player.getName()));

            try {
                yml.save(ymlFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    public void removePlayer(Player player, CommandSender sender) {
        UUID uuid = player.getUniqueId();
        YamlConfiguration message = Main.message;
        File ymlFile = new File(plugin.getDataFolder(), "players.yml");
        YamlConfiguration yml = new YamlConfiguration().loadConfiguration(ymlFile);
        List<String> uuids = yml.getStringList("players");

        if (!uuids.contains(uuid.toString())) {

            sender.sendMessage(Objects.requireNonNull(message.getString("notonlist")).replace("%p", player.getName()));


        } else {

            uuids.remove(uuid.toString());
            yml.set("players", uuids);
            sender.sendMessage(Objects.requireNonNull(message.getString("removelist")).replace("%p", player.getName()));

            try {
                yml.save(ymlFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void reload() {

    }

    public Boolean isPlayer(String string) {

        try {
            if (Bukkit.getServer().getPlayer(string) != null)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }

    }
}

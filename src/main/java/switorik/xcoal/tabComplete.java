package switorik.xcoal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class tabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        Main plugin = Main.plugin;
        List<String> output = new ArrayList<>();

        switch (args.length) {
            case 0: {
                output.add("add");
                output.add("remove");
                output.add("reload");
                return output;
            }
            case 1: {
                if ("add".startsWith(args[0]) && sender.hasPermission("xocal.use")) { output.add("add"); }
                if ("remove".startsWith(args[0]) && sender.hasPermission("xcoal.use")) { output.add("remove"); }
                if ("reload".startsWith(args[0]) && sender.hasPermission("xcoal.use")) { output.add("reload"); }
                return output;
            }
            case 2: {
                if(args[0].equals("remove") && sender.hasPermission("xcoal.use")) {

                    for(Player p : plugin.getServer().getOnlinePlayers()) {
                        if (p.getName().startsWith(args[1])) { output.add(p.getName()); }
                    }
                    return output;

                }
                if(args[0].equals("add") && sender.hasPermission("xcoal.use")) {

                    for(Player p : plugin.getServer().getOnlinePlayers()) {
                        if (p.getName().startsWith(args[1])) { output.add(p.getName()); }
                    }
                    return output;

                }

            }
        }
        return output;
    }
}

package me.ewilson.advancedMobGriefing.commands;


import me.ewilson.advancedMobGriefing.AdvancedMobGriefing;
import me.ewilson.advancedMobGriefing.utils.ConfigHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class MobGriefingCommand implements CommandExecutor {

    private final String GRIEF_CONFIG_ROOT = "can-grief";
    private final AdvancedMobGriefing PLUGIN;

    public MobGriefingCommand(AdvancedMobGriefing plugin) {
        PLUGIN = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        boolean result = false;

        if (args.length == 2) {
            this.PLUGIN.getConfig().set(ConfigHandler.createConfigKey(
                    new String[]{this.GRIEF_CONFIG_ROOT, args[0]}),
                    Boolean.parseBoolean(args[1])
            );
            this.PLUGIN.saveConfig();

            result = true;
        }

        return result;
    }
}

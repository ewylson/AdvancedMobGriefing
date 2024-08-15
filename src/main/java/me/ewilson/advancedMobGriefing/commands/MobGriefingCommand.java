package me.ewilson.advancedMobGriefing.commands;


import me.ewilson.advancedMobGriefing.AdvancedMobGriefing;
import me.ewilson.advancedMobGriefing.utils.ConfigHandler;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MobGriefingCommand implements CommandExecutor, TabExecutor {

    private final String GRIEF_CONFIG_ROOT = "can-grief";
    private final String[] AVAILABLE_ENTITY_TYPES = {
      "creeper", "fireball", "enderman"
    };

    private final AdvancedMobGriefing PLUGIN;

    public MobGriefingCommand(AdvancedMobGriefing plugin) {
        PLUGIN = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        boolean result = true;
        FileConfiguration config = this.PLUGIN.getConfig();

        if (args.length == 1)
            result = showCurrentValueInfo(args, config, commandSender);
        else if (args.length == 2)
            result = showSetValueInfo(args, config, commandSender);
        else
            result = false;

        return result;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        return switch (args.length) {
            case 1 -> Arrays.asList(this.AVAILABLE_ENTITY_TYPES);
            case 2 -> Arrays.asList("true", "false");
            default -> new ArrayList<String>();
        };
    }

    private boolean showCurrentValueInfo(String[] args, FileConfiguration config, CommandSender commandSender) {

        boolean result = true;

        String entityType = args[0];

        if (this.isValueExist(args, config)) {
            boolean currentValue = this.getValue(entityType, config);
            this.printMessage(
                    "The current value for entity \"" + entityType + "\" is " + currentValue,
                    commandSender
            );
        }
        else {
            this.printErrorMessage(
                    "The entity type \"" + entityType + "\" isn't supported or doesn't exist.",
                    commandSender
            );
            result = false;
        }

        return result;
    }

    private boolean showSetValueInfo(String[] args, FileConfiguration config, CommandSender commandSender) {

        boolean result = true;

        String entityType = args[0];

        if (this.isValueExist(args, config)) {
            this.setValue(args, config);
            this.printMessage(
                    "mobgriefing for the entity type \"" + entityType + "\" is set to " + args[1],
                    commandSender
            );
        }
        else {
            this.printErrorMessage(
                    "The entity type \"" + entityType + "\" isn't supported or doesn't exist.",
                    commandSender
            );
            result = false;
        }

        return result;
    }

    private void printMessage(String message, CommandSender commandSender) {
        if (commandSender instanceof Player player)
            player.sendMessage(message);
        return;
    }

    private void printErrorMessage(String message, CommandSender commandSender) {
        if (commandSender instanceof Player player)
            player.sendMessage(ChatColor.RED + message);
        return;
    }

    private boolean isValueExist(String[] args, FileConfiguration config) {

        boolean result = false;

        String entityType = args[0];
        String configurationPath = getEntityConfigurationPath(entityType);

        if (config.contains(configurationPath, true))
            result = true;

        return result;
    }

    private boolean getValue(String entityType, FileConfiguration config) {
        String configurationPath = getEntityConfigurationPath(entityType);
        return config.getBoolean(configurationPath);
    }

    private void setValue(String[] args, FileConfiguration config) {

        String entityType = args[0];
        String isEnabled = args[1];

        String configurationPath = getEntityConfigurationPath(entityType);

        config.set(configurationPath, Boolean.parseBoolean(isEnabled));
        this.PLUGIN.saveConfig();

        return;
    }

    private String getEntityConfigurationPath(String entityType) {
        return ConfigHandler.createConfigKey(
                new String[]{this.GRIEF_CONFIG_ROOT, entityType}
        );
    }
}

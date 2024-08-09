package me.ewilson.advancedMobGriefing;


import me.ewilson.advancedMobGriefing.commands.MobGriefingCommand;

import org.bukkit.plugin.java.JavaPlugin;


public final class AdvancedMobGriefing extends JavaPlugin {

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        this.getServer().getPluginManager().registerEvents(new GriefingListener(this), this);
        this.getCommand("mobGriefing").setExecutor(new MobGriefingCommand(this));

        return;
    }

    @Override
    public void onDisable() {
        this.saveConfig();
        return;
    }

}

package me.ewilson.advancedMobGriefing;


import me.ewilson.advancedMobGriefing.utils.ConfigHandler;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;


public class GriefingListener implements Listener {

    private final String GRIEF_CONFIG_ROOT = "can-grief";
    private final AdvancedMobGriefing PLUGIN;

    public GriefingListener(AdvancedMobGriefing plugin) {
        this.PLUGIN = plugin;
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (!canGrief(event.getEntityType()))
            event.blockList().clear();
        return;
    }

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        if (!canGrief(event.getEntityType()))
            event.setCancelled(true);
        return;
    }

    private boolean canGrief(EntityType entityType) {
        return PLUGIN.getConfig().getBoolean(ConfigHandler.createConfigKey(
                new String[]{this.GRIEF_CONFIG_ROOT, entityType.toString()}),
                true
        );
    }

}

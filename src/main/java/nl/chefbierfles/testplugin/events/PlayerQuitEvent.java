package nl.chefbierfles.testplugin.events;

import com.bergerkiller.bukkit.common.Task;
import com.bergerkiller.bukkit.common.entity.CommonEntity;
import com.google.common.eventbus.Subscribe;
import nl.chefbierfles.testplugin.TestPlugin;
import nl.chefbierfles.testplugin.modules.PvplogModule;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerQuitEvent implements Listener {

    @EventHandler
    public void onPlayerQuit(org.bukkit.event.player.PlayerQuitEvent event) {
        //PvplogModule.PlayerQuitHandler(event.getPlayer());
    }

    //: DEBUG
    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {
        PvplogModule.PlayerQuitHandler(event.getPlayer());
    }
}

package nl.chefbierfles.testplugin.events;

import nl.chefbierfles.testplugin.modules.PvplogModule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        PvplogModule.PlayerJoinHandler(event.getPlayer());
    }
}

package nl.chefbierfles.testplugin;

import nl.chefbierfles.testplugin.events.PlayerJoinEvent;
import nl.chefbierfles.testplugin.events.PlayerQuitEvent;
import nl.chefbierfles.testplugin.modules.PvplogModule;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestPlugin extends JavaPlugin {

    private static TestPlugin instance;

    public TestPlugin() {
        instance = this;
    }

    public static TestPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        //: Register events
        Bukkit.getPluginManager().registerEvents(new PlayerQuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        //: Modules
        PvplogModule.PluginDisableHandler();
    }
}

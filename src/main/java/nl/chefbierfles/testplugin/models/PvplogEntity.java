package nl.chefbierfles.testplugin.models;

import com.bergerkiller.bukkit.common.entity.CommonEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.time.LocalTime;
import java.util.UUID;

public class PvplogEntity {

    protected OfflinePlayer player;
    protected CommonEntity pvpLogEntity;
    private Location location;
    private LocalTime timeCreated;
    private LocalTime timeUntillExpire;
    private ItemStack[] savedContents;

    public PvplogEntity(OfflinePlayer player, CommonEntity pvpLogEntity, Location location, ItemStack[] savedContents) {
        this.player = player;
        this.pvpLogEntity = pvpLogEntity;
        this.location = location;
        timeCreated = LocalTime.now();
        //: WAARDE INSTELBAAR MACHEN!!! JAWOHLL!
        timeUntillExpire = timeCreated.plusSeconds(10);
        this.savedContents = savedContents;
    }

    public CommonEntity getPvpLogEntity() {
        return pvpLogEntity;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    public ItemStack[] getSavedContents() {
        return savedContents;
    }

    public void resetTimeUntillExpire() {
        this.timeUntillExpire = LocalTime.now().plusSeconds(10);
    }

    public LocalTime getTimeUntillExpire() {
        return timeUntillExpire;
    }
}

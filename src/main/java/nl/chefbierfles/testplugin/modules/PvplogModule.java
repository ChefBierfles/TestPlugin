package nl.chefbierfles.testplugin.modules;

import com.bergerkiller.bukkit.common.controller.EntityController;
import com.bergerkiller.bukkit.common.controller.EntityNetworkController;
import com.bergerkiller.bukkit.common.entity.CommonEntity;
import com.bergerkiller.bukkit.common.wrappers.DamageSource;
import com.bergerkiller.bukkit.common.wrappers.HumanHand;
import com.bergerkiller.bukkit.common.wrappers.InteractionResult;
import com.bergerkiller.bukkit.common.wrappers.MoveType;
import nl.chefbierfles.testplugin.models.PvplogEntity;
import nl.chefbierfles.testplugin.modules.Base.BaseModule;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import java.time.LocalTime;
import java.util.*;

public class PvplogModule extends BaseModule {

    private static List<PvplogEntity> pvplogEntities = new ArrayList<>();

    public static void PlayerJoinHandler(Player joinedPlayer) {

        PvplogEntity pvplogEntity = pvplogEntities.stream().filter(x -> x.getPlayer().getUniqueId() == joinedPlayer.getUniqueId()).findFirst().orElse(null);

        // Check if npc is present
        if (pvplogEntity != null) {
            RemoveNPC(pvplogEntity);
        }
    }
    // Handles when player quits
    public static void PlayerQuitHandler(Player quittedPlayer) {
        if (!isModuleEnabled) return;

        //Check if there is an enemy near (Pig in this case for debuggging purposes)
        Iterator<?> entitesNear = quittedPlayer.getNearbyEntities(32, 300 ,32).iterator();
        while(entitesNear.hasNext()) {
            if (entitesNear.next() instanceof Pig) {

                //Init a npc
                InitNPC(quittedPlayer);
                return;
            }
        }
    }
    // Handles when plugins disables (Server shutdown etc...)
    public static void PluginDisableHandler() {

        //Remove entities and data
        Iterator<PvplogEntity> iterator = pvplogEntities.iterator();
        while(iterator.hasNext()) {
            RemoveNPC(iterator.next());
        }
        pvplogEntities = new ArrayList<>();
    }
    private static void RemoveNPC(PvplogEntity pvplogEntity) {
        CommonEntity commonEntity = pvplogEntity.getPvpLogEntity();
        commonEntity.setController(null);
        commonEntity.remove();
    }
    private static void InitNPC(Player player) {

        //Save inv contents
        ItemStack[] inventoryContents = player.getInventory().getContents();

        //NPC stuff
        CommonEntity<?> entity = CommonEntity.spawn(
                EntityType.VILLAGER,
                player.getLocation(),
                new EntityController() {

             @Override
             public void onTick() {
                super.onTick();

                 PvplogEntity pvpLoggedEntity = pvplogEntities.stream().filter(x -> x.getPvpLogEntity().getUniqueId() == entity.getUniqueId()).findFirst().orElse(null);
                 // If npc is expired
                 if (pvpLoggedEntity != null) {
                     if (LocalTime.now().compareTo(pvpLoggedEntity.getTimeUntillExpire()) > 0) {
                         RemoveNPC(pvpLoggedEntity);
                     }
                 }
             }
             @Override
             public void onMove(MoveType moveType, double dx, double dy, double dz) {}
             @Override
             public InteractionResult onInteractBy(HumanEntity interacter, HumanHand hand) {
                 return null;
             }
             @Override
             public boolean onDamage(DamageSource damageSource, double damage) {

                 PvplogEntity pvpLoggedEntity = pvplogEntities.stream().filter(x -> x.getPvpLogEntity().getUniqueId() == entity.getUniqueId()).findFirst().get();

                 //Reset timer after entity is attacked
                 pvpLoggedEntity.resetTimeUntillExpire();

                 return super.onDamage(damageSource, damage);
             }
             @Override
             public void onDie() {
                 super.onDie();
                 //Pvp logged entity reference
                 PvplogEntity pvpLoggedEntity = pvplogEntities.stream().filter(x -> x.getPvpLogEntity().getUniqueId() == entity.getUniqueId()).findFirst().orElse(null);

                 //Drop pvploggers inventory
                 if (pvpLoggedEntity != null && pvpLoggedEntity.getSavedContents() != null && pvpLoggedEntity.getSavedContents().length > 0) {
                     for (ItemStack item : pvpLoggedEntity.getSavedContents()) {
                         if (item != null && item.getType() != Material.AIR) {
                             Bukkit.getWorld(pvpLoggedEntity.getPvpLogEntity().getWorld().getUID()).dropItemNaturally(entity.getLocation(), item);
                         }
                     }
                 }

                 //: Optional Kill message???

                 //Remove npc
                 RemoveNPC(pvpLoggedEntity);
             }
             }, new EntityNetworkController(){});
        entity.getEntity().setCustomName(ChatColor.RED + player.getName() + " (PvpLogged)");
        entity.getEntity().setCustomNameVisible(true);

        // Save stuff
        PvplogEntity pvpLogEntityToAdd = new PvplogEntity(player, entity, entity.getLocation(), inventoryContents);

        pvplogEntities.add(pvpLogEntityToAdd);
    }
}

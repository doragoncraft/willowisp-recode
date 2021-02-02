package me.doragoncraft.willowisp;

//import org.bukkit.World;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MobListener implements Listener {


    @EventHandler(priority = EventPriority.LOW)
    public void onEntitySpawn(CreatureSpawnEvent event)
    {

        LivingEntity entity = event.getEntity();
        EntityType entityType = entity.getType();
        World world = entity.getWorld();


        // FEATURE: charged creeper spawns
        if (entityType == EntityType.CREEPER) {
            double d = Math.random() * 1000;
            if (d < 11){
                Location location = ((Creeper) entity).getEyeLocation();
                ((Creeper) entity).setPowered(true);
                entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,70000, 2));
                entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,70000, 2));
                entity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 700000, 2));
                world.playSound(location, Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
            }
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        LivingEntity entity = event.getEntity();
        EntityType entityType = entity.getType();
        //World world = entity.getWorld();
        ItemStack stack = new ItemStack(Material.DIAMOND);

        if (entityType == EntityType.CREEPER) {
            if (((Creeper) entity).isPowered()) {
                double d = Math.random() * 100;
                if (d < 21){
                    event.getDrops().clear();
                    event.getDrops().add(stack);
                }


            }
        }



    }

}
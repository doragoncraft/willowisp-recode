package me.doragoncraft.willowisp;


import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Willowisp extends JavaPlugin {

    private final Random randomNumberGenerator = new Random();

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new MobListener(), this);

    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if ((sender instanceof Player)) {
            Player player = (Player)sender;
            World world =  player.getWorld();
            Location location =  player.getTargetBlock(null, 200).getLocation();
            if (label.equalsIgnoreCase("creeper")) {
                if(player.hasPermission("willowisp.spawn")) {
                    if (args.length == 0) {
                        player.sendMessage("creeper");
                        Creeper creeper = (Creeper)world.spawnEntity(location, EntityType.CREEPER);
                        creeper.setMaxHealth(125);
                        creeper.setHealth(125);
                        creeper.setPowered(true);
                        creeper.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,70000, 2));
                        creeper.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,70000, 2));
                        world.playSound(location, Sound.ENTITY_PLAYER_LEVELUP, 1, 10);
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;

    }

    public boolean random(int percentChance)
    {
        return randomNumberGenerator.nextInt(101) < percentChance;
    }

}
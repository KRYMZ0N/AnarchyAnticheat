package me.krymz0n.anarchyanticheat.pvp;

import me.krymz0n.anarchyanticheat.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;

public class CrystalAura implements Listener {
    private final Main plugin;
    private final HashSet<Player> players = new HashSet<>();

    public CrystalAura(Main plugin) {
        this.plugin = plugin;
    }

    /*
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent evt) {
        if (plugin.getConfig().getBoolean("LimitCrystalAura")) {
            // --This checks that the player is placing a crystal by using checking right click on crystal placeable blocks--
            if (Action.RIGHT_CLICK_BLOCK == evt.getAction()) {
                if (Material.OBSIDIAN == evt.getClickedBlock().getType() || Material.BEDROCK == evt.getClickedBlock().getType()) {
                    if (Material.END_CRYSTAL == evt.getMaterial()) {

                        ItemStack crystals = new ItemStack(Material.AIR);
                        for (ItemStack i : evt.getPlayer().getInventory()) {
                            if (i.getType() == Material.END_CRYSTAL) {
                                crystals = i; // Setting crystals variable = to the players crystals in their inv. Used for replacing used crystal later on.
                                break;
                            }
                        }
                        ItemStack finalCrystals = crystals; // ig required for lambda

                        Bukkit.getScheduler().runTask(plugin, () -> {
                            // --Iterating through entities in chunk after placed to find the crystal entity--
                            for (Entity entity : evt.getClickedBlock().getChunk().getEntities()) {
                                if (entity.getType().equals(EntityType.ENDER_CRYSTAL)) {
                                    EnderCrystal crystal = (EnderCrystal) entity;
                                    Block belowCrystal = crystal.getLocation().getBlock().getRelative(BlockFace.DOWN);

                                    if (evt.getClickedBlock() != null && evt.getClickedBlock().equals(belowCrystal)) { // Entity of crystal
                                        Player p = evt.getPlayer();
                                        if (players.contains(p)) {
                                            crystal.remove();
                                            entity.remove();
                                            finalCrystals.add(1); // Might enable a dupe, make a pr or contact me if it does

                                        } else {
                                            players.add(p); // Add player to set if it is not in there
                                            Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> players.remove(p), (long) plugin.getConfig().getInt("CrystalDelay")); // remove it after x ticks
                                        }
                                        return;
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }
    }

     */

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent evt) {
        if (evt.getEntity().getType().equals(EntityType.ENDER_CRYSTAL)) {
            if (evt.getDamager() instanceof Player) {
                Player p = (Player) evt.getDamager();

                if (players.contains(p)) {
                    evt.setCancelled(true);
                } else {
                    players.add(p);
                    Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> players.remove(p), (long) plugin.getConfig().getInt("CrystalDelay"));

                }
            }
        }
    }
}
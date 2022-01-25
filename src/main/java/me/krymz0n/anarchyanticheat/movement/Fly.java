package me.krymz0n.anarchyanticheat.movement;

import me.krymz0n.anarchyanticheat.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;

public class Fly implements Listener {
    private final Main plugin;
    HashSet<Player> check = new HashSet<>();

    public Fly(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent evt) {
        Player  p = evt.getPlayer();
        Location loc = p.getLocation();
        Location newLoc = new Location(p.getWorld(), loc.getX(), loc.getY() - 1, loc.getZ());
        double y = loc.getY() - 1; // Checking block underneath

        // --since this event gets called by movement, we add the player to the set, and when called again we remove if false--
        if (newLoc.getBlock().getType() == Material.AIR) {
            check.add(p); // add if in air
        } else {
            if (check.contains(p)) {
                check.remove(p); // remove if not in the set on the next calls.
            }
        }

        if (check.contains(p)) {
            new BukkitRunnable() {
                public void run() { // running a task with a delay to try and prevent player from ascending.
                    if (loc.getY() > y) { // if player ascends cancel
                        evt.setCancelled(true);

                    }
                }
            }.runTaskLaterAsynchronously(plugin, 20L);
        }
    }
}

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
        System.out.println("Event Called");
        Player  p = evt.getPlayer();
        Location loc = p.getLocation();
        final Location prevLoc = loc;
        Location newLoc = new Location(p.getWorld(), loc.getX(), loc.getY() - 1, loc.getZ());
        final double y = loc.getY() - 1; // Checking block underneath

        // --since this event gets called by movement, we add the player to the set, and when called again we remove if false--
        if (newLoc.getBlock().getType() == Material.AIR) {
            System.out.println("added");
            check.add(p); // add if in air
        } else {
            if (check.contains(p)) {
                System.out.println("removed");
                check.remove(p); // remove if not in the set on the next calls.
            }
        }

        if (check.contains(p)) {
            System.out.println("contains");
            new BukkitRunnable() {
                public void run() { // running a task with a delay to try and prevent player from ascending.
                    System.out.println("runnable");
                    if (loc.getY() - y >= 1.0d && !p.isJumping()) { // if player ascends cancel
                        System.out.println("should be cancelled.");
                        for(int y = prevLoc.getBlockY(); y > 0; y--) { // get current Y coordinate, go down until we hit bedrock (0)
                            if(prevLoc.subtract(0, 1, 0).getBlock().getType() == Material.AIR) continue; // if the block is air, go to next
                            p.teleport(prevLoc.add(0, 1, 0)); // teleport player to the block above, otherwise he would be stuck in the current one
                            break; // stop the loop
                        }
                        check.remove(p);

                    } else {
                        check.remove(p);
                    }
                }
            }.runTaskLater(plugin, 20L);
        }
    }
}

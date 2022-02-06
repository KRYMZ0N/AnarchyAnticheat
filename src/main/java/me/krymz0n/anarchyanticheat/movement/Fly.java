package me.krymz0n.anarchyanticheat.movement;

import me.krymz0n.anarchyanticheat.Main;
import me.krymz0n.anarchyanticheat.utils.Utils;
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
    Utils u = new Utils();

    public Fly(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent evt) {
        if (plugin.getConfig().getBoolean("PatchFlight")) {
            Player p = evt.getPlayer();

            if (!p.isGliding()) {
                Location loc = p.getLocation().subtract(0, 1, 0);
                final Location prevLoc = loc;
                //Location newLoc = new Location(p.getWorld(), loc.getX(), loc.getY(), loc.getZ());
                final Location clone = loc.clone().subtract(0, 1, 0); // Checking block underneath

                // --since this event gets called by movement, we add the player to the set, and when called again we remove if false--
                if (!(check.contains(p)) && (loc.getBlock().getType() == Material.AIR)
                        && !(loc.getBlock().isBuildable())
                        && !(loc.getBlock().isSolid())
                        && !(p.isJumping())
                        && !(u.blockRadIsBlock(loc))
                        && !(u.isLeaves(loc.getBlock().getType()))
                        && !(p.getFallDistance() > 0)
                        && !(p.isOnGround())) {
                    check.add(p); // add if in air
                } else {
                    if (check.contains(p)) {
                        check.remove(p); // remove if not in the set on the next calls.
                    }
                }

                if (check.contains(p)) {
                    new BukkitRunnable() {
                        public void run() { // running a task with a delay to try and prevent player from ascending.
                            if (p.getLocation().getBlockY() < prevLoc.getBlockY()) {
                                check.remove(p);
                            }
                            if (!(p.isJumping())
                                    // --Getting blocks within a 1 block radius around players, and checking to make sure it's a solid block--
                                    && !(u.blockRadIsBlock(loc))
                                    && !(u.isLeaves(loc.getBlock().getType()))
                                    && !(loc.getBlock().isBuildable())
                                    && !(loc.getBlock().isSolid())
                                    && !(p.getFallDistance() > 0)
                                    && !(p.getLocation().getBlockY() < prevLoc.getBlockY())
                                    && !(p.isOnGround())
                                    && (loc.subtract(0, 1, 0).getBlock().getType() == Material.AIR)
                                    && (p.getLocation().subtract(0, 5, 0).getBlock().getType() == Material.AIR)) { // if player ascends cancel

                                for (int y = prevLoc.getBlockY(); y > 0; y--) { // get current Y coordinate, go down until we hit bedrock (0)
                                    if (prevLoc.subtract(0, 1, 0).getBlock().getType() == Material.AIR)
                                        continue; // if the block is air, go to next
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
    }
}

package me.krymz0n.anarchyanticheat.movement;

import me.krymz0n.anarchyanticheat.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashSet;

public class Speed implements Listener {
    private final Main plugin;
    private final HashSet<Player> check = new HashSet<>();

    public Speed(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent evt) {
        if (plugin.getConfig().getBoolean("PatchSpeed")) {
            Player p = evt.getPlayer();
            PlayerTeleportEvent e = new PlayerTeleportEvent(p, evt.getFrom(), evt.getTo());

            if (!p.isGliding() && !e.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
                Location loc = p.getLocation();
                final Location prevLoc = loc;
                //Location newLoc = new Location(p.getWorld(), loc.getX(), loc.getY(), loc.getZ());
                final Location clone = loc.clone().subtract(0, 1, 0); // Checking block underneath

                if (!(check.contains(p))) {
                    check.add(p);
                }


                if (check.contains(p)) {
                    new BukkitRunnable() {
                        public void run() { // running a task with a delay to try and prevent player from ascending.

                            if (p.isGliding()) {
                                check.remove(p);
                                return;
                            }
                            if (p.getLocation().getBlockY() < prevLoc.getBlockY()) {
                                check.remove(p);
                                return;
                            }

                            if (p.getLocation().distance(prevLoc) > plugin.getConfig().getInt("Distance") && !(p.getLocation().getBlockY() < prevLoc.getBlockY() && !p.isGliding())) {
                                p.teleport(prevLoc);
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
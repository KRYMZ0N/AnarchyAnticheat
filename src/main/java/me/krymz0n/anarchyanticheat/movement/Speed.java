package me.krymz0n.anarchyanticheat.movement;

import me.krymz0n.anarchyanticheat.Main;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class Speed implements Listener {
    private final Main plugin;

    public Speed(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMovement(PlayerMoveEvent evt) {
        Player p = evt.getPlayer();

        Location to = evt.getTo();
        Location from = evt.getFrom();

        double distX = to.getX() - from.getX();
        double distZ = to.getZ() - from.getZ();
        double finalValue = Math.atan2(distZ, distX) * Math.hypot(distX, distZ);
        p.sendMessage(String.valueOf(finalValue));

        if (finalValue > 0.5 && !p.isJumping() && !p.isFlying() && !(p.getFallDistance() > 0)) {
            //evt.setCancelled(true);
        }
    }
}

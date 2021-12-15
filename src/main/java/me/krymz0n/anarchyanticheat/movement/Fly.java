package me.krymz0n.anarchyanticheat.movement;

import me.krymz0n.anarchyanticheat.Main;
import me.krymz0n.anarchyanticheat.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.awt.*;

public class Fly implements Listener {
    private final Main plugin;

    public Fly(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent evt) {
        Utils u = new Utils();
        Player p = evt.getPlayer();
        Location l = evt.getFrom();

        if (p.isFlying()) {
            evt.setCancelled(true);
            evt.setTo(evt.getFrom());
        }
        if (u.isInAir(evt.getPlayer())) {
            evt.setCancelled(true);
            evt.setTo(l.subtract( 0, 1, 0));
        }
    }
}

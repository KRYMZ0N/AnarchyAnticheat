package me.krymz0n.anarchyanticheat.movement;

import me.krymz0n.anarchyanticheat.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Fly implements Listener {
    private final Main plugin;

    public Fly(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent evt) {
        Player p = evt.getPlayer();

        if (!p.isJumping()) {
            if (p.isFlying()) {
                evt.setCancelled(true);
            }
        }
    }
}

package me.krymz0n.anarchyanticheat.movement;

import me.krymz0n.anarchyanticheat.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class BedrockBurrow implements Listener {
    private final Main plugin;

    public BedrockBurrow(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent evt) {
        if (!(evt.getPlayer().getLocation().getBlock().getType().equals(Material.AIR) && evt.getPlayer().getLocation().getBlock().getType().isOccluding())) {
            evt.getPlayer().damage(0.5f);
        }
    }
}

package me.krymz0n.anarchyanticheat.movement;

import me.krymz0n.anarchyanticheat.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
        if (plugin.getConfig().getBoolean("PreventBurrow")) {
            Location loc = evt.getPlayer().getLocation();
            int x = loc.getBlockX();
            int y = loc.getBlockY();
            int z = loc.getBlockZ();
            Material b = evt.getPlayer().getLocation().getWorld().getBlockAt(x, y, z).getType();

            if (!b.equals(Material.AIR)
                    && b.isOccluding()) {

                evt.getPlayer().damage((float) plugin.getConfig().getInt("BurrowDamageAmp") * 0.5);
                if (plugin.getConfig().getBoolean("TeleportBurrow")) {
                    evt.getPlayer().teleport(loc.add(0, 1, 0));
                }
            }
        }
    }
}

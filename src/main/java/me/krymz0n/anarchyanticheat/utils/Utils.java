package me.krymz0n.anarchyanticheat.utils;

import me.krymz0n.anarchyanticheat.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class Utils {
    BoolReference bf = new BoolReference(false);

    public boolean isInAir(Player p) {
        Location l = p.getLocation();

        if (l.getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.AIR)) { // gets the block under player

            Bukkit.getScheduler().runTaskLater(Main.instance, () -> {
                if (l.getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.AIR)) {
                    bf.set(true);
                }
                else bf.set(false);
            }, 20L); // amount to wait in ticks , 20 ticks = 1 second

            while (!bf.hasBeenEdited()) {
                p.sendMessage(bf.get().toString());
                return bf.get();
            }
        }
        return false;
    }
}

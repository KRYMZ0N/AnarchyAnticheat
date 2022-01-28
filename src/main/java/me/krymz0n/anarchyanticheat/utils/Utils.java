package me.krymz0n.anarchyanticheat.utils;

import me.krymz0n.anarchyanticheat.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Utils {

    public Material blockAtLoc(Location loc) {
        return loc.getBlock().getType();
    }

    public boolean isLeaves(Material mat) {
        switch (mat) {
            case ACACIA_LEAVES:
            case BIRCH_LEAVES:
            case JUNGLE_LEAVES:
            case OAK_LEAVES:
            case SPRUCE_LEAVES:
            case DARK_OAK_LEAVES:
                return true;
            default:
                return false;
        }
    }

    // --Checking block in a 1 block radius around the player--
    public boolean blockRadIsBlock(Location loc) {
//        double x = loc.getX();
//        double y = loc.getY();
//        double z = loc.getZ();

//        for (int x = (int) loc.subtract(1, 0, 0).getX(); x < loc.add(1, 0, 0).getX(); x++) { // Checking blocks through iteration to test efficiency.
//            for (int y = (int) loc.subtract(0, 1, 0).getY(); y < loc.add(0, 1, 0).getY(); y++) {
//                for (int z = (int) loc.subtract(0, 0, 1).getZ(); z < loc.add(0, 0, 1).getZ(); z++) {
//                    if (loc.getWorld().getBlockAt(x, y, z).getType() != Material.AIR) {
//                        return true;
//                    }
//                }
//            }
//
//        }

//        return false;

        return !(loc.subtract(1, 0, 1).getBlock().getType() == Material.AIR) // Could iterate, but pretty sure this is efficient.
                || !(loc.subtract(1, 0, 0).getBlock().getType() == Material.AIR)
                || !(loc.subtract(0, 0, 1).getBlock().getType() == Material.AIR)
                || !(loc.add(1, 0, 1).getBlock().getType() == Material.AIR)
                || !(loc.add(1, 0, 0).getBlock().getType() == Material.AIR)
                || !(loc.add(0, 0, 1).getBlock().getType() == Material.AIR);
    }
}

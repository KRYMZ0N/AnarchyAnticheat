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
        switch(mat) {
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
}

package me.krymz0n.anarchyanticheat;

import me.krymz0n.anarchyanticheat.command.Reload;
import me.krymz0n.anarchyanticheat.movement.BedrockBurrow;
import me.krymz0n.anarchyanticheat.movement.Fly;
import me.krymz0n.anarchyanticheat.movement.Speed;
import me.krymz0n.anarchyanticheat.pvp.CrystalAura;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(this, this);
        pm.registerEvents(new Fly(this), this);
        pm.registerEvents(new Speed(this), this);
        pm.registerEvents(new BedrockBurrow(this), this);
        pm.registerEvents(new CrystalAura(this), this);

        Objects.requireNonNull(getCommand("aac")).setExecutor(new Reload(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

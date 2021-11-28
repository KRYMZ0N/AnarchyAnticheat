package me.krymz0n.anarchyanticheat;

import me.krymz0n.anarchyanticheat.movement.Fly;
import me.krymz0n.anarchyanticheat.movement.Speed;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(this, this);
        pm.registerEvents(new Fly(this), this);
        pm.registerEvents(new Speed(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

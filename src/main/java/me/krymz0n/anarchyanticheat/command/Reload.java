package me.krymz0n.anarchyanticheat.command;

import me.krymz0n.anarchyanticheat.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Reload implements CommandExecutor {
    private final Main plugin;

    public Reload(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("aac") && args.length == 0) { // Checking label instead of argument to fix an error
            sender.sendMessage(ChatColor.BLUE + "This server is currently running AnarchyAntiCheat " + ChatColor.GRAY + "v" + ChatColor.BLUE + plugin.getDescription().getVersion());
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.isOp() || (!(sender instanceof Player))) {
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.BLUE + "AnarchyAntiCheat Config Reloaded!");
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
            }
        }

        if (args[0].equalsIgnoreCase("ver")) {
            sender.sendMessage(ChatColor.BLUE + "This server is currently running AnarchyAntiCheat " + ChatColor.GRAY + "v" + ChatColor.BLUE+ plugin.getDescription().getVersion());
            return true;
        }
        return false;
    }
}

package com.koningjoost.blockwalk;

import com.koningjoost.blockwalk.util.Logger;
import com.koningjoost.blockwalk.util.Settings;
import com.koningjoost.blockwalk.util.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Blockwalk extends JavaPlugin implements CommandExecutor {
    private static Plugin instance;
    public static Blockwalk plugin;

    public boolean blockwalkEnabled = false;

    @Override
    public void onEnable() {
        getCommand("blockwalk").setExecutor(this);
        getServer().getPluginManager().registerEvents(new PlayerMove(this), this);
        // Plugin startup logic
        this.saveDefaultConfig();
        System.out.println("Blockwalk is enabled!");

        //PLEASE REPLACE THE RESOURCE ID WITH YOUR SPIGOT RESOURCE
        if (Blockwalk.plugin.getConfig().getBoolean("Update.Enabled") == true)
            new UpdateChecker(this, 94470).getLatestVersion(version -> {
                if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                    Logger.log(Logger.LogLevel.SUCCESS,("Blockwalk is up to date!"));
                } else {
                    Logger.log(Logger.LogLevel.OUTLINE,  "*********************************************************************");
                    Logger.log(Logger.LogLevel.WARNING,("Blockwalk is outdated!"));Logger.log(Logger.LogLevel.WARNING,("Newest version: " + version));
                    Logger.log(Logger.LogLevel.WARNING,("Your version: " + Settings.VERSION));
                    Logger.log(Logger.LogLevel.WARNING,("Please Update Here: " + Settings.PLUGIN_URL));Logger.log(Logger.LogLevel.OUTLINE,  "*********************************************************************");			}
        });
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.saveConfig();
        System.out.println("Blockwalk is disabled...");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
           sender.sendMessage(ChatColor.DARK_RED + "Run this command as a player!");
           return false;
        }
        Player p = (Player) sender;
        if(p.hasPermission("blockwalk.mainperm")) {
            if (cmd.getName().equalsIgnoreCase("blockwalk")) {
                if (!blockwalkEnabled) {
                    blockwalkEnabled = true;
                    p.sendMessage(ChatColor.GREEN + "Activated!");
                } else {
                    blockwalkEnabled = false;
                    p.sendMessage(ChatColor.GREEN + "Deactivated!");
                }
            }
        }else {
            p.sendMessage(ChatColor.DARK_RED + "You dont have permission to do that!");
            }
        return false;
    }
    public static Blockwalk getPlugin() {
        return plugin;
    }

    public static Plugin getInstance() {
        return instance;
    }
}



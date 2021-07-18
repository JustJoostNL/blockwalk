package com.koningjoost.blockwalk;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Blockwalk extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getCommand("blockwalk").setExecutor(this);
        // Plugin startup logic
        System.out.println("Blockwalk is enabled!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Blockwalk is disabled...");
    }


    //command

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("blockwalk"))
            p.sendMessage(ChatColor.BLUE + "Hoi dit is een test! Als je dit ziet kun je goed programmeren!");
        return false;
    }
}

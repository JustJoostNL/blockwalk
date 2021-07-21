package com.koningjoost.blockwalk;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
    private final Blockwalk blockwalk;
    public PlayerMove(Blockwalk b) {
        blockwalk = b;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        if (blockwalk.blockwalkEnabled) {
            p.getLocation().subtract(0,1,0).getBlock().setType(Material.COBBLESTONE);
        }
    }

}


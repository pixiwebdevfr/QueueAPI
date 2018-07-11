package com.tara3208.valuxtrial.main.events;

import com.tara3208.valuxtrial.main.Queues;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

/**
 * Created by Tara3208 on 7/16/17.
 * This has been created privately.
 * Copyright applies. Breach of this is not warranted
 */
public class Disconnect implements Listener
{

    @EventHandler(priority = EventPriority.LOW)
    public void onDisc(PlayerDisconnectEvent e) {
        Queues.getInstance().getQueueManagement().removeFromAll(e.getPlayer());
    }
}

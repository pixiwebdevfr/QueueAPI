package com.tara3208.valuxtrial.main.events;

import com.tara3208.valuxtrial.main.Queues;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Tara3208 on 7/16/17.
 * This has been created privately.
 * Copyright applies. Breach of this is not warranted
 */
public class Connection implements Listener {

    @EventHandler
    public void onConnect(ServerConnectEvent e) {
        if (Queues.getInstance().getQueueManagement().hasQueue(e.getTarget())) {
            if (Queues.getInstance().getQueueManagement().getQueueByServer(e.getTarget()).getQueues().contains(e.getPlayer()))
                return;
            e.setCancelled(true);
            Queues.getInstance().getQueueManagement().getQueueByServer(e.getTarget()).addToQueue(e.getPlayer());
        }
    }

}

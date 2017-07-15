package com.tara3208.valuxtrial.api;

import com.tara3208.valuxtrial.main.Main;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tara3208 on 7/15/17.
 * This has been created privately.
 * Copyright applies. Breach of this is not warranted
 */
public class QueueManagement
{

    private Queue queues;

    public QueueManagement(ServerInfo server) {
        this.queues = new LinkedList();

        BungeeCord.getInstance().getScheduler().schedule(Main.getInstance(), () -> {

            Object firstPlayer = queues.element();
            queues.remove(firstPlayer);

            ProxiedPlayer proxiedPlayer = (ProxiedPlayer) firstPlayer;

            proxiedPlayer.connect(server);

                }
        , 2, TimeUnit.SECONDS);
    }

    public void addToQueue(ProxiedPlayer proxiedPlayer, Server server) {
        if (queues.contains(proxiedPlayer)) return;

        queues.add(proxiedPlayer);
        proxiedPlayer.sendMessage(new TextComponent(ChatColor.DARK_RED + "[Queue] " + ChatColor.GRAY + " You have been added to a queue!"));


    }

    public boolean inQueue(ProxiedPlayer proxiedPlayer) {
        return queues.contains(proxiedPlayer);
    }


    public void reboot() {
        queues.clear();
    }

}

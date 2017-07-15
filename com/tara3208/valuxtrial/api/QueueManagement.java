package com.tara3208.valuxtrial.api;

import com.tara3208.valuxtrial.main.Main;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tara3208 on 7/15/17.
 * This has been created privately.
 * Copyright applies. Breach of this is not warranted
 */
public class QueueManagement
{

    private HashMap<ProxiedPlayer, Server> queue;

    public QueueManagement() {
        this.queue = new HashMap<ProxiedPlayer, Server>();
    }

    public void addToQueue(ProxiedPlayer proxiedPlayer, Server server, int time) {
        if (queue.containsKey(proxiedPlayer)) return;

        queue.put(proxiedPlayer, server);
        proxiedPlayer.sendMessage(new TextComponent(ChatColor.DARK_RED + "[Queue] " + ChatColor.GRAY + " You have been added to a queue! Time delay: " + time + " seconds!"));

        BungeeCord.getInstance().getScheduler().schedule(Main.getInstance(), () -> {

                proxiedPlayer.connect(server.getInfo());
                proxiedPlayer.sendMessage(new TextComponent(ChatColor.DARK_RED + "[Queue] " + ChatColor.GRAY + "You have been connected to " + ChatColor.RED + server.getInfo().getName() + "(" + server.getInfo().getPlayers().size() + ")"));

                }

        , time, TimeUnit.SECONDS);

    }

    public boolean inQueue(ProxiedPlayer proxiedPlayer) {
        return queue.containsKey(proxiedPlayer);
    }

    public Server getQueuedServer(ProxiedPlayer proxiedPlayer) {
        return queue.get(proxiedPlayer);
    }

    public void reboot() {
        queue.clear();
    }

}

package com.tara3208.valuxtrial.api.types;

import com.tara3208.valuxtrial.main.Queues;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tara3208 on 7/15/17.
 * This has been created privately.
 * Copyright applies. Breach of this is not warranted
 */
public class QueueSystem
{

    private java.util.Queue queues;
    private ServerInfo serverInfo;
    private TimeUnit timeUnit;
    private int delay;
    private int players;


    public QueueSystem(ServerInfo server, TimeUnit timeUnit, int delay, int players) {
        this.queues = new LinkedList();
        this.serverInfo = server;
        this.timeUnit = timeUnit;
        this.delay = delay;
        this.players = players;



        Queues.getInstance().getProxy().getScheduler().schedule(Queues.getInstance(), new Runnable() {

            @Override
            public void run() {

                if (queues.isEmpty()) return;

                Object toMove = queues.element();

                ProxiedPlayer proxiedPlayer = (ProxiedPlayer) toMove;

                proxiedPlayer.connect(server);

                queues.remove(proxiedPlayer);

            }
        }, 0, delay, timeUnit);

        }

    public void addToQueue(ProxiedPlayer proxiedPlayer) {
        if (players >= BungeeCord.getInstance().getPlayers().size())
        {
            if (queues.contains(proxiedPlayer)) return;

            queues.add(proxiedPlayer);
            proxiedPlayer.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', Queues.getInstance().message).replaceAll("%position%", String.valueOf(getPosition(proxiedPlayer))).replaceAll("%size%", String.valueOf(getQueues().size()))));

        }
    }

    public boolean inQueue(ProxiedPlayer proxiedPlayer) {
        return queues.contains(proxiedPlayer);
    }


    public void reboot() {
        queues.clear();
    }

    public TimeUnit getTimeUnit()
    {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit)
    {
        this.timeUnit = timeUnit;
    }

    public int getDelay()
    {
        return delay;
    }

    public void setDelay(int delay)
    {
        this.delay = delay;
    }

    public Queue getQueues()
    {
        return queues;
    }

    public ServerInfo getServerInfo()
    {
        return serverInfo;
    }

    public int getPosition(ProxiedPlayer proxiedPlayer) {
        int position = 1;
        for (Object player : queues) {
            if (player.equals(proxiedPlayer)) {
                return position;
            }

            position++;
        }

        return -1;
    }

    public int getPlayers()
    {
        return players;
    }

    public void setPlayers(int players)
    {
        this.players = players;
    }
}

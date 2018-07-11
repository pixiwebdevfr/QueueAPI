package com.tara3208.valuxtrial.main;

import com.tara3208.valuxtrial.api.FileUtils;
import com.tara3208.valuxtrial.api.managers.QueueManager;
import com.tara3208.valuxtrial.api.types.QueueSystem;
import com.tara3208.valuxtrial.main.events.Connection;
import com.tara3208.valuxtrial.main.events.Disconnect;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tara3208 on 7/15/17.
 * This has been created privately.
 * Copyright applies. Breach of this is not warranted
 */
public class Queues extends Plugin
{

    private static Queues instance;
    private static QueueManager queueManager;

    public static String message = "";

    public static int size = 2;
    public static int players = 0;

    public static List servers;

    public static ChatMessageType chatMessageType;

    @Override
    public void onEnable()
    {
        instance = this;
        queueManager = new QueueManager();
        make();
        registerListeners();
        registerQueues();
    }

    @Override
    public void onDisable()
    {
        setNull();

    }

    public static Queues getInstance()
    {
        return instance;
    }

    public static QueueManager getQueueManagement()
    {
        return queueManager;
    }

    public void setNull() {
        queueManager = null;
        instance = null;
        message = null;
        size = -1;
        servers = null;
        chatMessageType = null;
    }

    private void registerListeners() {
        BungeeCord.getInstance().getPluginManager().registerListener(this, new Connection());
        BungeeCord.getInstance().getPluginManager().registerListener(this, new Disconnect());
    }

    private void registerQueues() {
        BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("Successfully added a queue for: "));

        Configuration configuration = FileUtils.getConfiguration("config.yml");

        configuration.getStringList("servers").forEach(serverName -> {
            QueueSystem hub = new QueueSystem(BungeeCord.getInstance().getServerInfo(serverName), TimeUnit.SECONDS, size, players);
            getQueueManagement().addQueue(hub);
            BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("- " + serverName));
        });
    }

    private void make()
    {
        if (!getDataFolder().exists())
        {
            getDataFolder().mkdir();
        }
        Configuration configuration = FileUtils.getConfiguration("config.yml");

        if (configuration == null)
        {
            return;
        }


        // MESSAGE
        if (configuration.get("message") == null)
        {
            configuration.set("message", "&4[Queue] &7You have been added to a queue! Position: &c#%position%/%size%");
            this.message = "&4[Queue] &7You have been added to a queue! Position: &c#%position%/%size%";
        } else
        {
            this.message = configuration.getString("message");
        }

        // Delay

        if (configuration.get("delay") == null)
        {
            configuration.set("delay", 2);
            this.size = 2;
        } else
        {
            this.size = configuration.getInt("delay");
        }

        if (configuration.get("players") == null)
        {
            configuration.set("players", 0);
            this.players = 0;
        } else
        {
            this.players = configuration.getInt("players");
        }

        // Servers
        if (configuration.get("servers") == null)
        {
            configuration.set("servers", "[]");
            this.servers = null;
        }

        if (configuration.get("messageType") == null)
        {
            configuration.set("messageType", "CHAT");
            this.chatMessageType = ChatMessageType.CHAT;
        } else {
            this.chatMessageType = ChatMessageType.valueOf(configuration.getString("messageType"));
        }

        FileUtils.save(configuration, "config.yml");
    }

}

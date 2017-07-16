package com.tara3208.valuxtrial.main;

import com.tara3208.valuxtrial.api.managers.QueueManager;
import com.tara3208.valuxtrial.api.types.QueueSystem;
import com.tara3208.valuxtrial.main.events.Connection;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.concurrent.TimeUnit;

/**
 * Created by Tara3208 on 7/15/17.
 * This has been created privately.
 * Copyright applies. Breach of this is not warranted
 */
public class Main extends Plugin
{

    private static Main instance;
    private static QueueManager queueManager;

    @Override
    public void onEnable()
    {
        instance = this;
        queueManager = new QueueManager();
        registerListeners();
        registerQueues();
    }

    @Override
    public void onDisable()
    {
        instance = null;
        queueManager = null;
    }

    public void registerListeners() {
        BungeeCord.getInstance().getPluginManager().registerListener(this, new Connection());
    }

    public void registerQueues() {
        QueueSystem hub = new QueueSystem(BungeeCord.getInstance().getServerInfo("Hub"), TimeUnit.SECONDS, 3);
        getQueueManagement().addQueue(hub);
    }

    public static Main getInstance()
    {
        return instance;
    }

    public static QueueManager getQueueManagement()
    {
        return queueManager;
    }
}

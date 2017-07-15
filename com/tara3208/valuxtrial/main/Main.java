package com.tara3208.valuxtrial.main;

import com.tara3208.valuxtrial.api.QueueManagement;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * Created by Tara3208 on 7/15/17.
 * This has been created privately.
 * Copyright applies. Breach of this is not warranted
 */
public class Main extends Plugin
{

    private static Main instance;
    private static QueueManagement queueManagement;

    @Override
    public void onEnable()
    {

        instance = this;
        queueManagement = new QueueManagement(BungeeCord.getInstance().getServerInfo("Hub"));
    }

    @Override
    public void onDisable()
    {
        instance = null;
        queueManagement = null;
    }


    public static Main getInstance()
    {
        return instance;
    }

    public static QueueManagement getQueueManagement()
    {
        return queueManagement;
    }
}

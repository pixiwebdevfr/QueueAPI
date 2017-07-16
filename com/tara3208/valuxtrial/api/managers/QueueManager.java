package com.tara3208.valuxtrial.api.managers;

import com.tara3208.valuxtrial.api.types.QueueSystem;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;

/**
 * Created by Tara3208 on 7/16/17.
 * This has been created privately.
 * Copyright applies. Breach of this is not warranted
 */
public class QueueManager
{

    private ArrayList<QueueSystem> queues;
    private ArrayList<ServerInfo> servers;

    public QueueManager()
    {
        this.queues = new ArrayList<QueueSystem>();
        this.servers = new ArrayList<ServerInfo>();
    }

    public void addQueue(QueueSystem queueSystem) {
        if (queues.contains(queueSystem)) return;

        servers.add(queueSystem.getServerInfo());
        queues.add(queueSystem);

    }

    public void removeQueue(QueueSystem queueSystem) {
        if (queues.contains(queueSystem))
        {
            servers.remove(queueSystem.getServerInfo());
            queues.remove(queueSystem);
        }
    }

    public QueueSystem getQueue(QueueSystem queueSystem) {
        for (QueueSystem queueSystem1 : queues) {
            if (queueSystem1.equals(queueSystem)) {
                return queueSystem1;
            }
        }

        return null;
    }

    public QueueSystem getQueueByServer(ServerInfo serverInfo) {
        for (QueueSystem queueSystem1 : queues) {
            if (queueSystem1.getServerInfo().equals(serverInfo)) {
                return queueSystem1;
            }
        }

        return null;
    }

    public boolean hasQueue(ServerInfo serverInfo) {
        return servers.contains(serverInfo);
    }

    public void removeFromAll(ProxiedPlayer proxiedPlayer) {
        for (QueueSystem queueSystem : queues) {
            if (queueSystem.getQueues().contains(proxiedPlayer)) {
                queueSystem.getQueues().remove(proxiedPlayer);
            }
        }
    }

    public ArrayList getQueues() {
        return queues;
    }
}

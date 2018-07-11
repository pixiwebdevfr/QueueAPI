package com.tara3208.valuxtrial.api;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Tara3208 on 7/16/17.
 * This has been created privately.
 * Copyright applies. Breach of this is not warranted
 */
public class FileUtils {

    public final static String DIRECTORY = "plugins/QueueSystem/";

    public static Configuration getConfiguration(String name) {
        final File file = getFile(name);
        if (file == null) {
            return null;
        }
        try {
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void save(Configuration configuration, String name) {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, getFile(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getFile(String name) {
        final File file = new File(DIRECTORY, name);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}

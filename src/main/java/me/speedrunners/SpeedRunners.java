package me.speedrunners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpeedRunners extends JavaPlugin {

    private Commands commands;
    private Setup setup;
    private String prefix;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        commands = new Commands(this);
        setup = new Setup(this);
        saveDefaultConfig();
        config = getConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Commands getCommands() {
        return commands;
    }

    public Setup getSetup() {
        return setup;
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public FileConfiguration getConfig() {
        return config;
    }
}

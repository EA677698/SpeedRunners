package me.speedrunners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpeedRunners extends JavaPlugin {

    private Commands commands;
    private Setup setup;
    private String prefix;
    private FileConfiguration config;
    private TeamStats run;

    @Override
    public void onEnable() {
        commands = new Commands(this);
        getCommand("speedrun").setExecutor(commands);
        setup = new Setup(this);
        getServer().getPluginManager().registerEvents(setup,this);
        saveDefaultConfig();
        config = getConfig();
        reloadConfig();
        //prefix = ChatColor.translateAlternateColorCodes('&',config.getString("prefix"));
        prefix = "["+ChatColor.AQUA+"SpeedRunners"+ChatColor.WHITE+"]";
        prefix = prefix+" ";
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

    public TeamStats getRun() {
        return run;
    }

    public void setRun(TeamStats run) {
        this.run = run;
    }
}

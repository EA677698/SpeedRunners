package me.speedrunners;

import org.bukkit.plugin.java.JavaPlugin;

public final class SpeedRunners extends JavaPlugin {

    Commands commands;
    Logic logic;
    String prefix;

    @Override
    public void onEnable() {
        commands = new Commands(this);
        logic = new Logic(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

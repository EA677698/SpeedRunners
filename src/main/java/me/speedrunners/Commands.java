package me.speedrunners;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    SpeedRunners speedRunner;

    public Commands(SpeedRunners speedRunner){
        this.speedRunner = speedRunner;
        speedRunner.getCommand("speedrunners").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(command.getName().equalsIgnoreCase("speedrun")){
                if(args.length>0){
                    if(args[0].equalsIgnoreCase("create")){
                        speedRunner.logic.organizer = (Player) sender;
                        sender.sendMessage(speedRunner.prefix+ ChatColor.LIGHT_PURPLE+"How many players would you like per team?");
                    }
                }
            }
        }
        return false;
    }

}

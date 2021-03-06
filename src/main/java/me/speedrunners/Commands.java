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
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(command.getName().equalsIgnoreCase("speedrun")){
                if(args.length>0){
                    if(args[0].equalsIgnoreCase("create")){
                        if(!speedRunner.getSetup().phaseThree) {
                            speedRunner.getSetup().organizer = (Player) sender;
                            sender.sendMessage(speedRunner.getPrefix() + ChatColor.LIGHT_PURPLE + "How many players would you like per team?");
                        } else {
                            sender.sendMessage(speedRunner.getPrefix()+ChatColor.RED+"A game has already been made!");
                        }
                    } else if(args[0].equalsIgnoreCase("start")){
                        if(speedRunner.getSetup().phaseThree){
                            if(sender.getName() == speedRunner.getSetup().getOrganizer().getName()){
                                speedRunner.setRun(new TeamStats(speedRunner, speedRunner.getSetup().getTeamOne(),speedRunner.getSetup().getTeamTwo()));
                            }
                        }
                    } else if(args[0].equalsIgnoreCase("stop")){
                        if(speedRunner.getSetup().phaseThree){
                            if(sender.getName()==speedRunner.getSetup().getOrganizer().getName()){
                                speedRunner.getRun().setStop(true);
                                speedRunner.setRun(null);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}

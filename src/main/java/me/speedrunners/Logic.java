package me.speedrunners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Logic implements Listener {

    SpeedRunners speedRunners;

    Player organizer;

    Player[] teamOne, teamTwo;

    boolean phaseOne, phaseTwo, phaseThree;

    public Logic(SpeedRunners speedRunners){
        this.speedRunners = speedRunners;
        speedRunners.getServer().getPluginManager().registerEvents(this,speedRunners);
    }

    public Player[] getTeamOne() {
        return teamOne;
    }

    public Player[] getTeamTwo() {
        return teamTwo;
    }

    @EventHandler
    public void gameSetup(AsyncPlayerChatEvent e){
        if(organizer!=null){
            if(e.getPlayer()==organizer){
                if(e.getMessage().equalsIgnoreCase("cancel")) {
                    phaseOne = false;
                    phaseTwo = false;
                    phaseThree = false;
                    organizer = null;
                } else if(!phaseOne){
                    try {
                        teamOne = new Player[Integer.parseInt(e.getMessage())];
                        teamTwo = new Player[Integer.parseInt(e.getMessage())];
                        phaseOne = true;
                        organizer.sendMessage(speedRunners.prefix+ChatColor.LIGHT_PURPLE+"Please enter the names of the players in team one.");
                    } catch (Exception ex){
                        organizer.sendMessage(speedRunners.prefix+ChatColor.RED+"The input you have provided is invalid!");
                    }
                } else if(!phaseTwo){
                    try{
                        teamOne[0] = Bukkit.getPlayer(e.getMessage().substring(0,e.getMessage().indexOf(" ")));
                        for(int i = 1; i<teamOne.length; i++){
                            teamOne[i] = Bukkit.getPlayer(e.getMessage().substring(e.getMessage().in,e.getMessage().indexOf(" ")))
                        }
                    } catch (Exception ex){
                        organizer.sendMessage(speedRunners.prefix+ChatColor.RED+"The input you have provided is invalid!");
                    }
                }
            }
        }

    }

    public Player getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Player organizer) {
        this.organizer = organizer;
    }


}

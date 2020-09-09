package me.speedrunners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Setup implements Listener {

    SpeedRunners speedRunners;

    Player organizer;

    Player[] teamOne, teamTwo;

    boolean phaseOne, phaseTwo, phaseThree;

    public Setup(SpeedRunners speedRunners){
        this.speedRunners = speedRunners;
    }

    public Player[] getTeamOne() {
        return teamOne;
    }

    public Player[] getTeamTwo() {
        return teamTwo;
    }

    @EventHandler
    public void gameSetup(AsyncPlayerChatEvent e){
        if(organizer!=null&&!phaseThree){
            if(e.getPlayer()==organizer){
                String players = e.getMessage();
                if(e.getMessage().equalsIgnoreCase("cancel")) {
                    phaseOne = false;
                    phaseTwo = false;
                    phaseThree = false;
                    organizer = null;
                    e.setCancelled(true);
                    return;
                } else if(!phaseOne){
                    try {
                        teamOne = new Player[Integer.parseInt(e.getMessage())];
                        teamTwo = new Player[Integer.parseInt(e.getMessage())];
                        phaseOne = true;
                        organizer.sendMessage(speedRunners.getPrefix()+ChatColor.LIGHT_PURPLE+"Please enter the names of the players in team one.");
                    } catch (Exception ex){
                        organizer.sendMessage(speedRunners.getPrefix()+ChatColor.RED+"The input you have provided is invalid!");
                        ex.printStackTrace(); //DEBUG
                    }
                    e.setCancelled(true);
                    return;
                } else if(!phaseTwo){
                    try{
                        for(int i = 0; i<teamOne.length; i++){
                            if(players.contains(" ")) {
                                teamOne[i] = Bukkit.getPlayer(players.substring(0, players.indexOf(" ")));
                                players = players.substring(players.indexOf(" ") + 1);
                            } else {
                                teamOne[i] = Bukkit.getPlayer(players);
                            }
                        }
                        phaseTwo = true;
                        organizer.sendMessage(speedRunners.getPrefix()+ChatColor.LIGHT_PURPLE+"Please enter the names of the players in team two.");

                    } catch (Exception ex){
                        organizer.sendMessage(speedRunners.getPrefix()+ChatColor.RED+"The input you have provided is invalid!");
                        ex.printStackTrace(); //DEBUG
                    }
                    e.setCancelled(true);
                    return;
                } else if(!phaseThree){
                    try {
                        for (int i = 0; i < teamOne.length; i++) {
                            if(players.contains(" ")) {
                                teamTwo[i] = Bukkit.getPlayer(players.substring(0, players.indexOf(" ")));
                                players = players.substring(players.indexOf(" ") + 1);
                            } else {
                                teamTwo[i] = Bukkit.getPlayer(players);
                            }
                        }
                        phaseThree = true;
                        organizer.sendMessage(speedRunners.getPrefix()+ChatColor.GREEN+"The game is ready! Type '/speedrun start' to begin the timer!");
                    } catch (Exception ex){
                        organizer.sendMessage(speedRunners.getPrefix()+ChatColor.RED+"The input you have provided is invalid!");
                        ex.printStackTrace(); //DEBUG
                    }
                    e.setCancelled(true);
                    return;
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

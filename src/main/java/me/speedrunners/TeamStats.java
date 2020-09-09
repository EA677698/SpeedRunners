package me.speedrunners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TeamStats {

    Player[] teamOneMembers,teamTwoMembers;
    private SpeedRunners speedRunner;
    private ScoreboardManager manager;
    private Scoreboard board;
    private Objective time;
    private Team teamOne,teamTwo;
    private boolean stop;
    private LocalTime startTime;

    public TeamStats(SpeedRunners speedRunner, Player[] teamOneMembers, Player[] teamTwoMembers){
        this.speedRunner = speedRunner;
        this.teamOneMembers = teamOneMembers;
        this.teamTwoMembers = teamTwoMembers;
        manager = Bukkit.getScoreboardManager();
        board = manager.getNewScoreboard();
        teamOne = board.registerNewTeam("TeamOne");
        teamTwo = board.registerNewTeam("TeamTwo");
        teamOne.setDisplayName("Team One");
        teamTwo.setDisplayName("Team Two");
        teamOne.setColor(ChatColor.AQUA);
        teamTwo.setColor(ChatColor.GREEN);
        time = board.registerNewObjective("Timer", "dummy","placeholder");
        time.setDisplaySlot(DisplaySlot.SIDEBAR);
        for(Player player : teamOneMembers){
            teamOne.addEntry(player.getName());
            player.setScoreboard(board);
        }
        for(Player player : teamTwoMembers){
            teamTwo.addEntry(player.getName());
            player.setScoreboard(board);
        }
        startTime = LocalTime.now();
        timer();
    }

    private void timer(){
        new BukkitRunnable(){
            @Override
            public void run() {
                if(!stop){
                    time.setDisplayName("Time: "+timeSinceStart());
                    for(Player player : teamOneMembers){
                        AdvancementProgress progress = player.getAdvancementProgress(Bukkit.getAdvancement(new NamespacedKey(speedRunner, "story/upgrade_tools")));
                        if(progress.isDone()){
                            player.sendMessage(ChatColor.GREEN+"Time split: "+LocalTime.now());
                        }
                    }
                    for(Player player : teamTwoMembers){
                        AdvancementProgress progress = player.getAdvancementProgress(Bukkit.getAdvancement(new NamespacedKey(speedRunner, "story/upgrade_tools")));
                        if(progress.isDone()){
                            player.sendMessage(ChatColor.GREEN+"Time split: "+LocalTime.now());
                        }
                    }
                } else {
                    for(Player player : teamOneMembers){
                        player.sendMessage("Final Time: "+timeSinceStart());
                        player.setScoreboard(null);
                    }
                    for(Player player : teamTwoMembers){
                        player.sendMessage("Final Time: "+timeSinceStart());
                        player.setScoreboard(null);
                    }
                    this.cancel();
                }
            }
        }.runTaskTimer(speedRunner,0,20);
    }

    public String timeSinceStart(){
        Duration duration = Duration.between(LocalTime.now()
                , startTime);
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}

package io.github.kenftr.clownApi.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class GetPlayerListTask {

    private final JavaPlugin plugin;
    private final List<String> playerList = new ArrayList<>();

    public GetPlayerListTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void start() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {

            playerList.clear();
            playerList.addAll(
                    Bukkit.getOnlinePlayers()
                            .stream()
                            .map(Player::getName)
                            .toList()
            );

        }, 1L, 100L);
    }

    public List<String> getPlayerList() {
        return List.copyOf(playerList);
    }
}
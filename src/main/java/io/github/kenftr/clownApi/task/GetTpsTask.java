package io.github.kenftr.clownApi.task;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class GetTpsTask {

    private final JavaPlugin plugin;

    private long lastTime = -1;
    private volatile double tps = 20.0;

    public GetTpsTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void startTask() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            long now = System.nanoTime();

            if (lastTime != -1) {
                double elapsed = (now - lastTime) / 1_000_000_000.0;
                tps = Math.min(20.0, 20.0 / elapsed);
            }

            lastTime = now;
        }, 20L, 20L);
    }

    public double getTps() {
        return tps;
    }
}
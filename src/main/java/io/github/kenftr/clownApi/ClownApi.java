package io.github.kenftr.clownApi;

import io.github.kenftr.clownApi.routers.GetPlayerList;
import io.github.kenftr.clownApi.routers.GetTps;
import io.github.kenftr.clownApi.task.GetPlayerListTask;
import io.github.kenftr.clownApi.task.GetTpsTask;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;
import static spark.Spark.*;
public final class ClownApi extends JavaPlugin implements Listener {
    private Boolean isLoad = false;
    @Override
    public void onEnable() {
        getServer().getPluginManager()
                .registerEvents(this, this);
    }

    @EventHandler
    public void onLoad(WorldLoadEvent e) {
        if (isLoad == true) return;
        isLoad = true;
        try {
            saveDefaultConfig();
            int port = getConfig().getInt("api.port");
            boolean tpsEnabled = getConfig().getBoolean("api.get_tps.enabled");
            boolean playerListEnabled = getConfig().getBoolean("api.get_player_list.enabled");


            port(port);


            GetTpsTask getTpsTask = new GetTpsTask(this);
            getTpsTask.startTask();

            GetPlayerListTask getPlayerListTask = new GetPlayerListTask(this);
            getPlayerListTask.start();

            new Thread(() -> {
                if (tpsEnabled) {
                    new GetTps(getTpsTask).register();
                }
                if (playerListEnabled) {
                    new GetPlayerList(getPlayerListTask).register();
                }
            }).start();
            System.out.println("ClownApi Start");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
        stop();
        System.out.println("ClownApi Stop");
    }
}

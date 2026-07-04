package io.github.kenftr.clownApi;

import io.github.kenftr.clownApi.routers.GetPlayerList;
import io.github.kenftr.clownApi.routers.GetTps;
import io.github.kenftr.clownApi.task.GetPlayerListTask;
import io.github.kenftr.clownApi.task.GetTpsTask;
import org.bukkit.plugin.java.JavaPlugin;
import static spark.Spark.*;
public final class ClownApi extends JavaPlugin {

    @Override
    public void onEnable() {

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
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
        stop();
        System.out.println("ClownApi Stop");
    }
}

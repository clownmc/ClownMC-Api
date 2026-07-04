package io.github.kenftr.clownApi.routers;

import com.google.gson.Gson;
import io.github.kenftr.clownApi.task.GetPlayerListTask;

import static spark.Spark.*;

public class GetPlayerList {
    private final GetPlayerListTask getPlayerListTask;
    private Gson gson = new Gson();

    public GetPlayerList(GetPlayerListTask getPlayerListTask) {
        this.getPlayerListTask = getPlayerListTask;
    }

    public void register() {
        get("/api/v1/get_player_list",(req,res) -> {
            res.type("application/json");
            return gson.toJson(
                    getPlayerListTask.getPlayerList()
            );
        });
    }



}
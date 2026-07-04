package io.github.kenftr.clownApi.routers;

import com.google.gson.Gson;
import io.github.kenftr.clownApi.task.GetTpsTask;

import java.util.Map;

import static spark.Spark.*;

public class GetTps {
    private final GetTpsTask getTpsTask;
    private final Gson gson = new Gson();

    public GetTps(GetTpsTask getTpsTask) {
        this.getTpsTask = getTpsTask;
    }

    public void register() {
        get("/api/v1/tps",(req,res) -> {

            res.type("application/json");


            return gson.toJson(Map.of(
                    "tps", getTpsTask.getTps()
            ));
        });
    }
}

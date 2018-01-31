package top.thttnt.phoenixvital.configuration;

import com.lss233.phoenix.Phoenix;
import com.lss233.phoenix.config.json.JsonConfiguration;
import com.lss233.phoenix.world.Location;
import top.thttnt.phoenixvital.PhoenixVital;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HomeConfig {

    Map<String, Map<String, Location>> homes = new HashMap<>();

    public HomeConfig() throws IOException {
        File file = Phoenix.getConfigurationManager().getConfigurationDirectory(PhoenixVital.self);
        if (file.exists()) {
            JsonConfiguration config = JsonConfiguration.load(file);
            //TODO
        }
    }

    public boolean setHome(String playerName, String homeName, Location location) {
        if (homes.containsKey(playerName)) {
            if (homes.get(playerName).containsKey(homeName)) {
                return false;
            } else {
                homes.get(playerName).put(homeName, location);
            }
        }
        homes.put(playerName, new HashMap<String, Location>());
        homes.get(playerName).put(homeName, location);
        return true;
    }
}

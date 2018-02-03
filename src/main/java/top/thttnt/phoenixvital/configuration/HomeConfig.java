package top.thttnt.phoenixvital.configuration;

import com.lss233.phoenix.Phoenix;
import com.lss233.phoenix.config.ConfigurationSection;
import com.lss233.phoenix.config.MemoryConfiguration;
import com.lss233.phoenix.config.json.JsonConfiguration;
import com.lss233.phoenix.world.Location;
import com.lss233.phoenix.world.World;
import top.thttnt.phoenixvital.PhoenixVital;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeConfig {

    private Map<String, Map<String, Location>> homes = new HashMap<>();

    HomeConfig() throws IOException {
        File file = Phoenix.getConfigurationManager().getConfigurationDirectory(PhoenixVital.self);
        file = new File(file,"home.json");
        if (file.exists()) {
            JsonConfiguration config = JsonConfiguration.load(file);
            config.getKeys().forEach(playerName -> {
                homes.put(playerName, new HashMap<>());
                config.getSection(playerName).getKeys().forEach(homeName -> {
                    ConfigurationSection homeSection = config.getSection(playerName).getSection(homeName);
                    double x = homeSection.getDouble("x");
                    double y = homeSection.getDouble("y");
                    double z = homeSection.getDouble("z");
                    String world = homeSection.getString("world");
                    List<World> worlds = Phoenix.getServer().getWorlds();
                    worlds.forEach(it -> {
                        if (it.getName().equalsIgnoreCase(world)) {
                            Location location = new Location(it, x, y, z);
                            homes.get(playerName).put(homeName, location);
                        }
                    });
                });
            });
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
        homes.put(playerName, new HashMap<>());
        homes.get(playerName).put(homeName, location);
        return true;
    }

    public Location getHome(String playerName, String homeName) {
        if (!homes.containsKey(playerName)) {
            return null;
        }
        if (!homes.get(playerName).containsKey(homeName)) {
            return null;
        }
        return homes.get(playerName).get(homeName);
    }

    public void save() throws IOException {
        JsonConfiguration config = (JsonConfiguration) JsonConfiguration.empty();
        homes.keySet().forEach(playerName -> {
            MemoryConfiguration section = MemoryConfiguration.empty();
            homes.get(playerName).keySet().forEach(homeName -> {
                Location location = homes.get(playerName).get(homeName);
                MemoryConfiguration homeSection = MemoryConfiguration.empty();
                homeSection.set("x", location.getX());
                homeSection.set("y", location.getY());
                homeSection.set("z", location.getZ());
                homeSection.set("world", location.getWorld().getName());
                section.setSection(homeName,homeSection);
            });
            config.set(playerName,section);
        });
        File file = Phoenix.getConfigurationManager().getConfigurationDirectory(PhoenixVital.self);
        file = new File(file,"home.json");
        config.save(file);
    }
}

package top.thttnt.phoenixvital.configuration;

import java.io.IOException;

public class PluginConfig {

    private HomeConfig homeConfig;

    public PluginConfig() throws IOException {
        this.homeConfig = new HomeConfig();
    }

    public HomeConfig getHomeConfig() {
        return homeConfig;
    }

    public void save() throws IOException {
        homeConfig.save();
    }
}

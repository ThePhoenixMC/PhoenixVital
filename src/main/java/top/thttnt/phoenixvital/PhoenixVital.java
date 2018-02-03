package top.thttnt.phoenixvital;

import com.lss233.phoenix.Phoenix;
import com.lss233.phoenix.logging.Logger;
import com.lss233.phoenix.module.Module;
import com.lss233.phoenix.module.PhoenixModule;
import top.thttnt.phoenixvital.command.PluginCommand;
import top.thttnt.phoenixvital.configuration.PluginConfig;

import java.io.IOException;

@PhoenixModule(modid = "phoenixvital", name = "phoenixVital", version = "beta")
public class PhoenixVital extends Module{

    public static PhoenixVital self;

    private PluginConfig pluginConfig;

    private Logger logger = Phoenix.getLogger("PhoenixVital");

    public void onInitial() {
        self = this;
        logger.info("PhoenixVital is initialed");
    }

    public void onEnable() {
        try {
            PluginCommand.registerCommand();
            this.pluginConfig = new PluginConfig();
            logger.info("PhoenixVital is loaded");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDisable() {
        try {
            pluginConfig.save();
            logger.info("PhoenixVital is disabled");
        } catch (IOException e) {
            logger.warn("PhoenixVital has some problems when saving the data");
            e.printStackTrace();
        }
    }

    public PluginConfig getPluginConfig() {
        return pluginConfig;
    }
}

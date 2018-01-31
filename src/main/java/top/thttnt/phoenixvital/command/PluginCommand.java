package top.thttnt.phoenixvital.command;

import com.lss233.phoenix.Phoenix;
import top.thttnt.phoenixvital.PhoenixVital;

public class PluginCommand {

    public static void registerCommand(){
        Phoenix.getCommandManager().registerCommand(PhoenixVital.self,new CommandSetHome());
    }

}

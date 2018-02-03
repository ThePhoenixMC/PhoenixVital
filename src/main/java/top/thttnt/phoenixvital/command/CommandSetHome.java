package top.thttnt.phoenixvital.command;

import com.lss233.phoenix.command.*;
import com.lss233.phoenix.entity.living.Player;
import top.thttnt.phoenixvital.PhoenixVital;

@PhoenixCommand(label = "sethome")
public class CommandSetHome implements Command {

    @CommandRouter(args = "<name>",permission = "phoenixvital.sethome")
    public boolean setHomeWithName(CommandSender sender, CommandContent content) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4only player can use this command");
            return true;
        }
        Player player = (Player)sender;
        String name = content.getString("name");
        boolean result = PhoenixVital.self.getPluginConfig().getHomeConfig().setHome(player.getName(),name,player.getLocation());
        if (result){
            sender.sendMessage("§aset home successfully");
        }else{
            sender.sendMessage("§2home is already exist");
        }
        return true;
    }

    @CommandRouter(permission = "phoenixvital.sethome")
    public boolean setHomeWithoutName(CommandSender sender, CommandContent content){
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4only player can use this command");
            return true;
        }
        Player player = (Player)sender;
        boolean result = PhoenixVital.self.getPluginConfig().getHomeConfig().setHome(player.getName(),"home",player.getLocation());
        if (result){
            sender.sendMessage("§aset home successfully");
        }else{
            sender.sendMessage("§2home is already exist");
        }
        return true;
    }

    public boolean onMissHandled(CommandSender sender, String label, String[] args) {
        return false;
    }
}

package top.thttnt.phoenixvital.command;

import com.lss233.phoenix.Phoenix;
import com.lss233.phoenix.builtin.commands.PhoenixCommandExecutor;
import com.lss233.phoenix.command.*;
import com.lss233.phoenix.entity.living.Player;
import com.lss233.phoenix.world.Location;
import top.thttnt.phoenixvital.PhoenixVital;

@PhoenixCommand(label = "home")
public class CommandHome implements Command{

    @CommandRouter(args = "<name>",permission = "phoenixvital.home")
    public boolean teleportWithName(CommandSender sender, CommandContent content){
        if (!(sender instanceof Player)){
            sender.sendMessage("§4only player can use this command");
            return true;
        }
        String homeName = content.getString("name");
        Player player = (Player)sender;
        Location location = PhoenixVital.self.getPluginConfig().getHomeConfig().getHome(player.getName(),homeName);
        if (location == null){
            sender.sendMessage("§4home is not exist");
        }else{
            player.teleport(location);
        }
        return true;
    }

    @CommandRouter(args = "",permission = "phoenixvital.home")
    public boolean teleportWithoutName(CommandSender sender, CommandContent content){
        if (!(sender instanceof Player)){
            sender.sendMessage("§4only player can use this command");
            return true;
        }
        Player player = (Player)sender;
        Location location = PhoenixVital.self.getPluginConfig().getHomeConfig().getHome(player.getName(),"home");
        if (location == null){
            sender.sendMessage("§4home is not exist");
        }else{
            player.teleport(location);
        }
        return true;
    }

    @Override
    public boolean onMissHandled(CommandSender sender, String label, String[] args) {
        return false;
    }
}

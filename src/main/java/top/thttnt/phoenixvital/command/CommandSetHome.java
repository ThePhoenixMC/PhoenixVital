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

    @Override
    public CommandResult onRoot(CommandSender sender, CommandContent args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4only player can use this command");
            return CommandResult.success();
        }
        Player player = (Player)sender;
        boolean result = PhoenixVital.self.getPluginConfig().getHomeConfig().setHome(player.getName(),"home",player.getLocation());
        if (result){
            sender.sendMessage("§aset home successfully");
        }else{
            sender.sendMessage("§2home is already exist");
        }
        return CommandResult.success();
    }

    @Override
    public CommandResult onMissHandled(CommandSender sender, CommandContent args) {
        return null;
    }

    @Override
    public CommandResult onInsufficientPermission(CommandSender sender, CommandContent args) {
        return null;
    }
}

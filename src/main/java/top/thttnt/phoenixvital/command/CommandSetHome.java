package top.thttnt.phoenixvital.command;

import com.lss233.phoenix.command.*;

@PhoenixCommand(label="sethome")
public class CommandSetHome implements Command {

    @CommandRouter(args = "<name>")
    public boolean setHomeWithName(CommandSender sender,String label,ArgumentsMap args){
        return true;
    }

    public boolean onMissHandled(CommandSender sender, String label, String[] args) {
        return false;
    }
}

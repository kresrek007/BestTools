/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Util;

public abstract class CommandExecutor extends Command
{
    private String perm;
    private boolean onlyPlayer;
    
    public CommandExecutor(final String name, final String description, final String usageMessage, final String permissions, final List<String> aliases, final boolean onlyPlayer) {
        super(name, description, usageMessage, aliases);
        this.onlyPlayer = false;
        this.perm = permissions;
        this.onlyPlayer = onlyPlayer;
    }
    
    public boolean execute(final CommandSender sender, final String cmd, final String[] args) {
        if (!sender.hasPermission(this.perm)) {
        	Util.sendMessage(sender, Messages.pluginPrefix + Messages.noPermissions.replace("%permission%", this.perm));
            return true;
        }
        if (this.onlyPlayer && !(sender instanceof Player)) {
            Util.sendMessage(sender, Messages.pluginPrefix + Messages.onlyPlayer);
            return true;
        }
        try {
            return this.onCommand(sender, cmd, args);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            Util.sendMessage(sender,  Messages.pluginPrefix + Messages.errorCommand);
            return true;
        }
    }
    
    public abstract boolean onCommand(final CommandSender sender, final String cmd, final String[] args);
}

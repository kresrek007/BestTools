/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.admin;

import org.bukkit.command.CommandSender;

import pl.kresrek007.tools.basic.Mute;
import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.basic.utils.MuteUtils;
import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Util;

public class UnMuteCommand extends CommandExecutor{

	public UnMuteCommand() {
		super(Config.commands_unmute_name, Config.commands_unmute_description, Config.commands_unmute_usage, Config.commands_unmute_usage, Config.commands_unmute_aliases, false);
	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length < 1) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
		User u = UserUtils.getUser(args[0]);
		Mute m = null;
		if(u == null || (m = MuteUtils.getMute(u)) == null) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.noFoundPlayer.replace(Config.variables_playerName, args[0]));
		Util.broadcast(Messages.pluginPrefix + Messages.mute_unmute.replace(Config.variables_playerName, args[0]).replace(Config.variables_adminName, sender.getName()));
		MuteUtils.removeMute(m);
        return true;
	}

}

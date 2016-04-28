/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.admin;

import org.bukkit.command.CommandSender;

import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Util;

public class WhoisCommand extends CommandExecutor{

	public WhoisCommand() {
		super(Config.commands_whois_name, Config.commands_whois_description, Config.commands_whois_usage, Config.commands_whois_usage, Config.commands_whois_aliases, false);
	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length < 1) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
		User u = UserUtils.getUser(args[0]);
		if(u == null) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.noFoundPlayer.replace(Config.variables_playerName, args[0]));
		for(String s : Messages.whois){
			Util.sendMessage(sender, s
					.replace(Config.variables_playerName, u.getNickName()
					.replace(Config.variables_ipAddres, u.getLastIP())));
		}	
		return true;
	}

}

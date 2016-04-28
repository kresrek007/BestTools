/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.users;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Util;

public  class HelpopCommand extends CommandExecutor{

	public HelpopCommand() {
		super(Config.commands_helpop_name, Config.commands_helpop_description, Config.commands_helpop_usage, Config.commands_helpop_usage, Config.commands_helpop_aliases, false);

	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length < 1) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
		String msg = Config.format_helpop.replace(Config.variables_message.replace(Config.variables_playerName, sender.getName()), StringUtils.join(args, " "));
		Util.broadcastPermission(Config.permissions_helpopSee, msg);
		return Util.sendMessage(sender, msg);
	}

}

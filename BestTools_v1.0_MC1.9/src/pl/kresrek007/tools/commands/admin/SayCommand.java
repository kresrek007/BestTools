/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.admin;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Util;

public class SayCommand extends CommandExecutor{

	public SayCommand() {
		super(Config.commands_say_name, Config.commands_say_description, Config.commands_say_usage, Config.commands_say_usage, Config.commands_say_aliases, false);

	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length == 0) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
		return Util.broadcast(Config.format_broadcast.replace(Config.variables_message, StringUtils.join(args, " ")));
	}
}

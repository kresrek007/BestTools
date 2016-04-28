/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.admin;

import org.bukkit.command.CommandSender;

import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.ChatUtils;
import pl.kresrek007.tools.utils.Util;

public class ChatCommand extends CommandExecutor{

	public ChatCommand() {
		super(Config.commands_chat_name, Config.commands_chat_description, Config.commands_chat_usage, Config.commands_chat_usage, Config.commands_chat_aliases, false);
	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length < 1) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
		if(args[0].equalsIgnoreCase("clear") || args[0].equalsIgnoreCase("cc")){
			for (int i = 0; i < 100; i++){
				Util.broadcast("");
			}
			return Util.broadcast(Messages.pluginPrefix + Messages.chatAction.replace(Config.variables_value, Messages.value_cleared).replace(Config.variables_adminName, sender.getName()));
		}
		if(args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("off")){
			ChatUtils.setEnabled(false);
			return Util.broadcast(Messages.pluginPrefix + Messages.chatAction.replace(Config.variables_value, Messages.value_disabled).replace(Config.variables_adminName, sender.getName()));
		}
		if(args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("on")){
			ChatUtils.setEnabled(true);
			return Util.broadcast(Messages.pluginPrefix + Messages.chatAction.replace(Config.variables_value, Messages.value_enabled).replace(Config.variables_adminName, sender.getName()));
		}
		return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
	}

}

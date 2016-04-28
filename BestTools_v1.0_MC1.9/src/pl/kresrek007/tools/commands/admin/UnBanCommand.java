/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.admin;

import org.bukkit.command.CommandSender;

import pl.kresrek007.tools.basic.Ban;
import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.basic.utils.BanUtils;
import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Util;

public class UnBanCommand extends CommandExecutor{

	public UnBanCommand() {
		super(Config.commands_unban_name, Config.commands_unban_description, Config.commands_unban_usage, Config.commands_unban_usage, Config.commands_unban_aliases, false);
	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length < 1) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
		Ban b = BanUtils.getBan(args[0]);
		User u = UserUtils.getUser(args[0]);
		if(b == null && u != null) b = BanUtils.getBan(u);
		if(b == null || b.isExpired()) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.noFoundPlayer.replace(Config.variables_playerName, args[0]));
		Util.broadcast(Messages.pluginPrefix + Messages.ban_unban.replace(Config.variables_playerName, args[0]).replace(Config.variables_adminName, sender.getName()));
		BanUtils.removeBan(b);
        return true;
	}

}

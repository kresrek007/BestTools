/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.users;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Util;

public  class ReplyCommand extends CommandExecutor{

	public ReplyCommand() {
		super(Config.commands_reply_name, Config.commands_reply_description, Config.commands_reply_usage, Config.commands_reply_usage, Config.commands_reply_aliases, true);
	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length < 1) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
		Player p = UserUtils.getUser((Player)sender).getLastTalkPlayer();
		if(p == null) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.replyNoPlayer);
		String format = Config.format_talk.replace(Config.variables_message, StringUtils.join(args, " "));
		UserUtils.getUser(p).setLastTalkPlayer((Player)sender);
		Util.sendMessage(p, format.replace(Config.variables_playerName, sender.getName()).replace(Config.variables_otherName, Messages.value_you));
		return Util.sendMessage(sender, format.replace(Config.variables_playerName, Messages.value_you).replace(Config.variables_otherName, p.getName()));
	}

}

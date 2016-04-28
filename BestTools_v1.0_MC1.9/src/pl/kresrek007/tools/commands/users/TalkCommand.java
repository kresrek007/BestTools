/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.users;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Util;

public  class TalkCommand extends CommandExecutor{

	public TalkCommand() {
		super(Config.commands_talk_name, Config.commands_talk_description, Config.commands_talk_usage, Config.commands_talk_usage, Config.commands_talk_aliases, true);
	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length < 2) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
		Player other = Bukkit.getPlayer(args[0]);
		if(other == null) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.noFoundPlayer.replace(Config.variables_playerName, args[0]));
		String format = Config.format_talk.replace(Config.variables_message, StringUtils.join(args, " ", 1, args.length));
		UserUtils.getUser(other).setLastTalkPlayer((Player)sender);
		UserUtils.getUser((Player)sender).setLastTalkPlayer(other);
		Util.sendMessage(other, format.replace(Config.variables_playerName, sender.getName()).replace(Config.variables_otherName, Messages.value_you));
		return Util.sendMessage(sender, format.replace(Config.variables_playerName, Messages.value_you).replace(Config.variables_otherName, other.getName()));
	}

}

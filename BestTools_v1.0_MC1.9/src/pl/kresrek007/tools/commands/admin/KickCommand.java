/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.admin;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Util;

public  class KickCommand extends CommandExecutor{

	public KickCommand() {
		super(Config.commands_kick_name, Config.commands_kick_description, Config.commands_kick_usage, Config.commands_kick_usage, Config.commands_kick_aliases, false);

	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length < 1) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
		Player p = Bukkit.getPlayer(args[0]);
		if(p == null) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.noFoundPlayer.replace(Config.variables_playerName, args[0]));
		String r = Messages.defaultReason;
		if (args.length > 1) r = StringUtils.join(args, " ", 1, args.length);
		Util.kickPlayer(p, Config.format_kick.replace(Config.variables_adminName, sender.getName()).replace(Config.variables_reason, r));
		return true;
	}

}

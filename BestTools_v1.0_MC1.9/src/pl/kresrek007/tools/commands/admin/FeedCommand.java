/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Util;

public  class FeedCommand extends CommandExecutor{

	public FeedCommand() {
		super(Config.commands_feed_name, Config.commands_feed_description, Config.commands_feed_usage, Config.commands_feed_usage, Config.commands_feed_aliases, false);

	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		Player p = (sender instanceof Player ? p = (Player)sender : null);
		if(args.length >= 1) p = Bukkit.getPlayer(args[0]);
		if(p == null) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.noFoundPlayer.replace(Config.variables_playerName, (args.length >= 1 ? args[0] : Messages.value_server)));
		p.setFoodLevel(20);
		if(!sender.equals(p)) Util.sendMessage(p, Messages.pluginPrefix + Messages.feedPlayer.replace(Config.variables_adminName, sender.getName()));
		return Util.sendMessage(sender, Messages.pluginPrefix + Messages.feedSender.replace(Config.variables_playerName, p.getName()));
	}

}

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

public  class FlyCommand extends CommandExecutor{

	public FlyCommand() {
		super(Config.commands_fly_name, Config.commands_fly_description, Config.commands_fly_usage, Config.commands_fly_usage, Config.commands_fly_aliases, false);

	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		Player p = (sender instanceof Player ? p = (Player)sender : null);
		if(args.length >= 1) p = Bukkit.getPlayer(args[0]);
		if(p == null) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.noFoundPlayer.replace(Config.variables_playerName, (args.length >= 1 ? args[0] : Messages.value_server)));
		p.setAllowFlight(!p.getAllowFlight());
		if(!sender.equals(p)) Util.sendMessage(p, Messages.pluginPrefix + Messages.flyPlayer.replace(Config.variables_adminName, sender.getName()).replace(Config.variables_value, (p.getAllowFlight() ? Messages.value_enable : Messages.value_disable)));
		return Util.sendMessage(sender, Messages.pluginPrefix + Messages.flySender.replace(Config.variables_playerName, p.getName()).replace(Config.variables_value, (p.getAllowFlight() ? Messages.value_enable : Messages.value_disable)));
	}

}

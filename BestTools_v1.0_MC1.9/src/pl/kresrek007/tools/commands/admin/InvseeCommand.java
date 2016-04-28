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

public class InvseeCommand extends CommandExecutor{

	public InvseeCommand() {
		super(Config.commands_invsee_name, Config.commands_invsee_description, Config.commands_invsee_usage, Config.commands_invsee_usage, Config.commands_invsee_aliases, true);
	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length < 1) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));	
		Player p = Bukkit.getPlayer(args[0]);
		if(p == null) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.noFoundPlayer.replace(Config.variables_playerName, (args.length >= 1 ? args[0] : Messages.value_server)));
		Player ps = (Player)sender;
		ps.openInventory(p.getInventory());
		return true;
	}

}

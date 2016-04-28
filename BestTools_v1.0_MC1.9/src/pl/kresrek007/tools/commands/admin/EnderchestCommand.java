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

public class EnderchestCommand extends CommandExecutor{

	public EnderchestCommand() {
		super(Config.commands_enderchest_name, Config.commands_enderchest_description, Config.commands_enderchest_usage, Config.commands_enderchest_usage, Config.commands_enderchest_aliases, true);
	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		Player p = (Player)sender;
		if(args.length > 0 && p.hasPermission(Config.permissions_enderchestOther)) p = Bukkit.getPlayer(args[0]);		
		if(p == null) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.noFoundPlayer.replace(Config.variables_playerName, args[0]));
		Player p2 = (Player)sender;
		p2.openInventory(p.getEnderChest());
		return true;
	}

}

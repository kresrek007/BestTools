/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.admin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Util;

public  class SetSpawnCommand extends CommandExecutor{

	public SetSpawnCommand() {
		super(Config.commands_setspawn_name, Config.commands_setspawn_description, Config.commands_setspawn_usage, Config.commands_setspawn_usage, Config.commands_setspawn_aliases, false);

	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		Player p = (Player)sender;
		p.getWorld().setSpawnLocation(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());
		return Util.sendMessage(sender, Messages.pluginPrefix + Messages.spawnSet);
	}

}

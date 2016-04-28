/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.users;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.tasks.TeleportTask;

public  class BackCommand extends CommandExecutor{

	public BackCommand() {
		super(Config.commands_back_name, Config.commands_back_description, Config.commands_back_usage, Config.commands_back_usage, Config.commands_back_aliases, true);

	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		TeleportTask.teleport((Player)sender, UserUtils.getUser((Player)sender).getLastLocation());
		return true;
	}

}

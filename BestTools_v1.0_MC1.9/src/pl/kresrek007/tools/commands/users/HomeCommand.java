/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.users;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.tasks.TeleportTask;

public  class HomeCommand extends CommandExecutor{

	public HomeCommand() {
		super(Config.commands_home_name, Config.commands_home_description, Config.commands_home_usage, Config.commands_home_usage, Config.commands_home_aliases, true);

	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		Player p = (Player)sender;
		User u = UserUtils.getUser(p);
		TeleportTask.teleport(p, u.getHomeLocation());
		return true;
	}

}

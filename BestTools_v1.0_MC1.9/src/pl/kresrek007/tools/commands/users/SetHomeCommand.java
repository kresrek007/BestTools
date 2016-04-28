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
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Util;

public  class SetHomeCommand extends CommandExecutor{

	public SetHomeCommand() {
		super(Config.commands_sethome_name, Config.commands_sethome_description, Config.commands_sethome_usage, Config.commands_sethome_usage, Config.commands_sethome_aliases, true);

	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		Player p = (Player)sender;
		User u = UserUtils.getUser(p);
		u.setHomeLocation(p.getLocation());
		return Util.sendMessage(p, Messages.pluginPrefix + Messages.homeSet);
	}

}

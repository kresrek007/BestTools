/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.users;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Util;

public  class ListCommand extends CommandExecutor{

	public ListCommand() {
		super(Config.commands_list_name, Config.commands_list_description, Config.commands_list_usage, Config.commands_list_usage, Config.commands_list_aliases, true);
	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		return Util.sendMessage(sender, Messages.pluginPrefix + Messages.playerList.replace(Config.variables_maxPlayers, Integer.toString(Bukkit.getMaxPlayers())).replace(Config.variables_onlinePlayers, Integer.toString(Bukkit.getOnlinePlayers().size())));		
	}

}

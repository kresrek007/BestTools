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
import pl.kresrek007.tools.utils.Parser;
import pl.kresrek007.tools.utils.Util;

public class HeadCommand extends CommandExecutor{

	public HeadCommand() {
		super(Config.commands_head_name, Config.commands_head_description, Config.commands_head_usage, Config.commands_head_usage, Config.commands_head_aliases, true);
	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		String owner = sender.getName();
		if(args.length > 0) owner = args[0];
		Util.giveItems((Player)sender, Parser.getHead(owner));
		return Util.sendMessage(sender, Messages.pluginPrefix + Messages.giveHead.replace(Config.variables_playerName, owner));
	}

}

/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.admin;

import java.util.Arrays;

import org.bukkit.command.CommandSender;

import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.commands.CommandManager;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Data;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Util;

public class BestToolsCommand extends CommandExecutor{

	public BestToolsCommand() {
		super("besttools", "Main command.", "/BestTools reload", "BestTools.default", Arrays.asList("tools", "reloadtools", "btreload", "bt"), false);

	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length >= 1 && args[0].equalsIgnoreCase("reload")){			
			if(!sender.hasPermission("BestTools.reload")) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.noPermissions.replace("%permission%", "BestTools.reload"));
			Data.loadConfig();
			Data.loadMessages();
			Config.load();
			Messages.load();
			CommandManager.setup();
			return Util.sendMessage(sender, Messages.pluginPrefix + Messages.reloaded);			
		}
		return Util.sendMessage(sender, "&7BestTools &8(&7" + Data.getVersion() + "&8) &7by &eKresrek007");
	}

}

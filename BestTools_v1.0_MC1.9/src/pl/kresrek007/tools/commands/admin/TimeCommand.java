/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Util;

public class TimeCommand extends CommandExecutor{

	public TimeCommand() {
		super(Config.commands_time_name, Config.commands_time_description, Config.commands_time_usage, Config.commands_time_usage, Config.commands_time_aliases, false);
	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length < 1) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
		if(args[0].equalsIgnoreCase("day")){
	    	for (World w : Bukkit.getWorlds()) {
	            w.setTime(0L);
	        }
			return Util.sendMessage(sender, Messages.pluginPrefix + Messages.timeSet.replace(Config.variables_value, Messages.value_day));
		}
		if(args[0].equalsIgnoreCase("night")){
	    	for (World w : Bukkit.getWorlds()) {
	            w.setTime(14000L);
	        }
			return Util.sendMessage(sender, Messages.pluginPrefix + Messages.timeSet.replace(Config.variables_value, Messages.value_night));
		}
		return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
	}

}

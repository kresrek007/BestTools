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

public class KillCommand extends CommandExecutor{

	public KillCommand() {
		super(Config.commands_kill_name, Config.commands_kill_description, Config.commands_kill_usage, Config.commands_kill_usage, Config.commands_kill_aliases, false);
	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length < 1) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
		Player p = Bukkit.getPlayer(args[0]);
		if(p == null) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.noFoundPlayer.replace(Config.variables_playerName, args[0]));
		p.setHealth(0);
		return Util.broadcast(Messages.pluginPrefix + Messages.kill.replace(Config.variables_playerName, p.getName()).replace(Config.variables_adminName, sender.getName()));
	}

}

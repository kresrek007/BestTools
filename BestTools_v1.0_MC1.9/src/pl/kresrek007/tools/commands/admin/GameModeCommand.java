/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Parser;
import pl.kresrek007.tools.utils.Util;

public  class GameModeCommand extends CommandExecutor{

	public GameModeCommand() {
		super(Config.commands_gamemode_name, Config.commands_gamemode_description, Config.commands_gamemode_usage, Config.commands_gamemode_usage, Config.commands_gamemode_aliases, false);

	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length < 1) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
		Player p = (sender instanceof Player ? p = (Player)sender : null);
		if(args.length >= 2) p = Bukkit.getPlayer(args[1]);
		if(p == null) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.noFoundPlayer.replace(Config.variables_playerName, (args.length >= 2 ? args[1] : Messages.value_server)));
		GameMode mode = Parser.getGameName(args[0]);
		p.setGameMode(mode);
		if(!sender.equals(p)) Util.sendMessage(p, Messages.pluginPrefix + Messages.gamemodePlayer.replace(Config.variables_adminName, sender.getName()).replace(Config.variables_value, mode.name().toLowerCase()));
		return Util.sendMessage(sender, Messages.pluginPrefix + Messages.gamemodeSender.replace(Config.variables_playerName, p.getName()).replace(Config.variables_value, mode.name().toLowerCase()));
	}

}

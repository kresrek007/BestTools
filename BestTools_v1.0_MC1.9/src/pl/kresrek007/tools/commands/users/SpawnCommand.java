/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.users;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.tasks.TeleportTask;

public  class SpawnCommand extends CommandExecutor{

	public SpawnCommand() {
		super(Config.commands_spawn_name, Config.commands_spawn_description, Config.commands_spawn_usage, Config.commands_spawn_usage, Config.commands_spawn_aliases, false);

	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		TeleportTask.teleport((Player)sender, Bukkit.getWorlds().get(0).getSpawnLocation());
		return true;
	}

}

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
import org.bukkit.potion.PotionEffect;

import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Util;

public  class HealCommand extends CommandExecutor{

	public HealCommand() {
		super(Config.commands_heal_name, Config.commands_heal_description, Config.commands_heal_usage, Config.commands_heal_usage, Config.commands_heal_aliases, false);

	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		Player p = (sender instanceof Player ? p = (Player)sender : null);
		if(args.length >= 1) p = Bukkit.getPlayer(args[0]);
		if(p == null) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.noFoundPlayer.replace(Config.variables_playerName, (args.length >= 1 ? args[0] : Messages.value_server)));
		p.setFoodLevel(20);
		p.setHealth(20);
		p.setFireTicks(0);
		for (final PotionEffect potionEffect : p.getActivePotionEffects()) p.removePotionEffect(potionEffect.getType());		
		if(!sender.equals(p)) Util.sendMessage(p, Messages.pluginPrefix + Messages.healPlayer.replace(Config.variables_adminName, sender.getName()));
		return Util.sendMessage(sender, Messages.pluginPrefix + Messages.healSender.replace(Config.variables_playerName, p.getName()));
	}

}

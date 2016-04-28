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
import org.bukkit.inventory.ItemStack;

import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Parser;
import pl.kresrek007.tools.utils.Util;

public  class GiveCommand extends CommandExecutor{

	public GiveCommand() {
		super(Config.commands_give_name, Config.commands_give_description, Config.commands_give_usage, Config.commands_give_usage, Config.commands_give_aliases, false);

	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length < 2) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
		Player p = Bukkit.getPlayer(args[0]);
		if(p == null) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.noFoundPlayer.replace(Config.variables_playerName, args[0]));
		ItemStack item = Parser.getItem(args[1]);
		if(item == null) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.itemNull);
		int num = 64;
		if(args.length >= 3 && Util.isInteger(args[2])) num = Integer.parseInt(args[2]);
		item.setAmount(num);
		Util.giveItems(p, item);
		if(!sender.equals(p)) Util.sendMessage(p, Messages.pluginPrefix + Messages.givePlayer.replace(Config.variables_adminName, sender.getName()).replace(Config.variables_amount, Integer.toString(num)).replace(Config.variables_itemName, item.getType().name().toLowerCase()));
		return Util.sendMessage(sender, Messages.pluginPrefix + Messages.giveSender.replace(Config.variables_playerName, p.getName()).replace(Config.variables_amount, Integer.toString(num)).replace(Config.variables_itemName, item.getType().name().toLowerCase()));
	}

}

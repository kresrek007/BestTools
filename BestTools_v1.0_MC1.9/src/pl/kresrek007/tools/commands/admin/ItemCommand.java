/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.admin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Parser;
import pl.kresrek007.tools.utils.Util;

public  class ItemCommand extends CommandExecutor{

	public ItemCommand() {
		super(Config.commands_item_name, Config.commands_item_description, Config.commands_item_usage, Config.commands_item_usage, Config.commands_item_aliases, true);

	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length < 1) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
		Player p = (Player)sender;
		ItemStack item = Parser.getItem(args[0]);
		if(item == null) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.itemNull);
		int num = 64;
		if(args.length >= 2 && Util.isInteger(args[1])) num = Integer.parseInt(args[1]);
		item.setAmount(num);
		Util.giveItems(p, item);
		return Util.sendMessage(sender, Messages.pluginPrefix + Messages.itemSender.replace(Config.variables_amount, Integer.toString(num)).replace(Config.variables_itemName, item.getType().name().toLowerCase()));
	}

}

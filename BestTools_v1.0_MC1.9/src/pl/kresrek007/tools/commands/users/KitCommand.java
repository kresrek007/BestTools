/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.users;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.google.common.base.Joiner;

import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.objects.Kit;
import pl.kresrek007.tools.utils.KitUtils;
import pl.kresrek007.tools.utils.Parser;
import pl.kresrek007.tools.utils.Util;

public  class KitCommand extends CommandExecutor{

	public KitCommand() {
		super(Config.commands_kit_name, Config.commands_kit_description, Config.commands_kit_usage, Config.commands_kit_usage, Config.commands_kit_aliases, true);
	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		Player p = (Player)sender;
		User u = UserUtils.getUser(p);
		if(KitUtils.getKits().size() == 0) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.kitNoFound);
		if(args.length > 0){
			for(Kit kit : KitUtils.getKits()){
				if(kit.getName().equalsIgnoreCase(args[0])){
					if(!p.hasPermission(kit.getPermissions())) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.noPermissions.replace("%permission%", kit.getPermissions()));
					if(KitUtils.getKitTime(u, kit.getName()) + kit.getCooldown()*1000 > System.currentTimeMillis()) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.kitCooldown.replace(Config.variables_date, Parser.getDate(KitUtils.getKitTime(u, kit.getName()) + kit.getCooldown()*1000)).replace(Config.variables_kitName, kit.getName()));
					for(ItemStack is : kit.getItems()){
						Util.giveItems(p, is);
					}
					KitUtils.addKit(u, kit.getName(), System.currentTimeMillis());
					return Util.sendMessage(sender, Messages.pluginPrefix + Messages.kitReceive.replace(Config.variables_kitName, kit.getName()));
				}
			}
		}
		List<String> kitsNames = new ArrayList<String>();
		for(Kit kit : KitUtils.getKits()) kitsNames.add(kit.getName());
		return Util.sendMessage(sender, Messages.pluginPrefix + Messages.kitList.replace(Config.variables_kitList, Joiner.on(Messages.kitSplit).join(kitsNames)));
	}

}

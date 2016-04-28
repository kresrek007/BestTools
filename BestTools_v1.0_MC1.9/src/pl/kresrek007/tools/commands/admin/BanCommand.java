/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.admin;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import pl.kresrek007.tools.basic.Ban;
import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.basic.Ban.BanType;
import pl.kresrek007.tools.basic.utils.BanUtils;
import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Parser;
import pl.kresrek007.tools.utils.Util;

public class BanCommand extends CommandExecutor{

	public BanCommand() {
		super(Config.commands_ban_name, Config.commands_ban_description, Config.commands_ban_usage, Config.commands_ban_usage, Config.commands_ban_aliases, false);
	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length < 2) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
		User u = UserUtils.getUser(args[0]);
		if(u == null) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.noFoundPlayer.replace(Config.variables_playerName, args[0]));
		UUID victim = u.getUniqueId();
		Ban b = BanUtils.getBan(victim);
		if(b != null){
			if(!b.isExpired()) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.ban_already);
			BanUtils.removeBan(b);
		}
		String r = Messages.defaultReason;
        if (args.length > 2) r = StringUtils.join(args, " ", 2, args.length);        
        b = BanUtils.createBan(BanType.UUID, victim, null, r, sender.getName(), (args[1].equalsIgnoreCase("0") ? 0L : Parser.parseDateDiff(args[1], true)));
		Util.broadcast(Messages.pluginPrefix + Parser.parseBanMessage(Messages.ban_broadcast_uuid, b));
        return true;
	}

}

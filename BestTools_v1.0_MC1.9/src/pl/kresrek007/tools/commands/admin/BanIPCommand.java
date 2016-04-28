/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands.admin;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import pl.kresrek007.tools.basic.Ban;
import pl.kresrek007.tools.basic.Ban.BanType;
import pl.kresrek007.tools.basic.utils.BanUtils;
import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Parser;
import pl.kresrek007.tools.utils.Util;

public class BanIPCommand extends CommandExecutor{

	public BanIPCommand() {
		super(Config.commands_banip_name, Config.commands_banip_description, Config.commands_banip_usage, Config.commands_banip_usage, Config.commands_banip_aliases, false);
	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length < 2) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
		Ban b = BanUtils.getBan(args[0]);
		if(b != null){
			if(!b.isExpired()) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.ban_already);
			BanUtils.removeBan(b);
		}
		String r = Messages.defaultReason;
        if (args.length > 2) r = StringUtils.join(args, " ", 2, args.length);        
        b = BanUtils.createBan(BanType.IP, null, args[0], r, sender.getName(), (args[1].equalsIgnoreCase("0") ? 0L : Parser.parseDateDiff(args[1], true)));
        Util.broadcast(Messages.pluginPrefix + Parser.parseBanMessage(Messages.ban_broadcast_ip, b));
        return true;
	}

}

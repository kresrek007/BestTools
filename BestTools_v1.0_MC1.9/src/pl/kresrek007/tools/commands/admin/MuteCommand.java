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

import pl.kresrek007.tools.basic.Mute;
import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.basic.utils.MuteUtils;
import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.commands.CommandExecutor;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Parser;
import pl.kresrek007.tools.utils.Util;

public class MuteCommand extends CommandExecutor{

	public MuteCommand() {
		super(Config.commands_mute_name, Config.commands_mute_description, Config.commands_mute_usage, Config.commands_mute_usage, Config.commands_mute_aliases, false);
	}

	@Override
	public boolean onCommand(CommandSender sender, String cmd, String[] args) {
		if(args.length < 2) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.defaultUsage.replace("%usage%", this.usageMessage));
		User u = UserUtils.getUser(args[0]);
		if(u == null) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.noFoundPlayer.replace(Config.variables_playerName, args[0]));
		UUID victim = u.getUniqueId();
		Mute m = MuteUtils.getMute(victim);
		if(m != null){
			if(!m.isExpired()) return Util.sendMessage(sender, Messages.pluginPrefix + Messages.mute_already);
			MuteUtils.removeMute(m);
		}
		String r = Messages.defaultReason;
        if (args.length > 2) r = StringUtils.join(args, " ", 2, args.length);        
        m = MuteUtils.createMute(victim, r, sender.getName(), (args[1].equalsIgnoreCase("0") ? 0L : Parser.parseDateDiff(args[1], true)));
        return Util.broadcast(Messages.pluginPrefix + Messages.mute_broadcast.replace(Config.variables_playerName, args[0]).replace(Config.variables_adminName, sender.getName()).replace(Config.variables_reason, r).replace(Config.variables_expireTime, (m.getExpireTime() > 0 ? Parser.getDate(m.getExpireTime()) : Messages.value_never)));
	}

}

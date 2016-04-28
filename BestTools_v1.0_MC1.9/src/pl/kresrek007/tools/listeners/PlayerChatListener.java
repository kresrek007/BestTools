/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.listeners;

import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.google.common.collect.Maps;

import pl.kresrek007.tools.basic.Mute;
import pl.kresrek007.tools.basic.utils.MuteUtils;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.ChatUtils;
import pl.kresrek007.tools.utils.Parser;
import pl.kresrek007.tools.utils.StringUtil;
import pl.kresrek007.tools.utils.Util;


public class PlayerChatListener implements Listener{
	
	public static Map<UUID, Long> cooldown = Maps.newHashMap();
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		Mute m = MuteUtils.getMute(p);
		if(m != null){
			if(!m.isExpired()){
				Util.sendMessage(p, Messages.pluginPrefix + Messages.mute_triedMessage.replace(Config.variables_adminName, m.getAdmin()).replace(Config.variables_reason, m.getReason()).replace(Config.variables_expireTime, (m.getExpireTime() > 0 ? Parser.getDate(m.getExpireTime()) : Messages.value_never)));
				e.setCancelled(true);
				return;
			}
			MuteUtils.removeMute(m);
		}
		if(!p.hasPermission(Config.permissions_chatBypass)){ 
			if(!ChatUtils.isEnabled()){
				Util.sendMessage(p, Messages.chatIsDisabled);
				return;
			}
			if(cooldown.containsKey(p.getUniqueId()) && System.currentTimeMillis() - cooldown.get(p.getUniqueId()) <= Config.cooldown_chat * 1000){
				Util.sendMessage(p, Messages.pluginPrefix + Messages.chatCooldown.replace(Config.variables_seconds, Integer.toString(Config.cooldown_chat)));
				e.setCancelled(true);
				return;
			}
			cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		}
		e.setFormat(StringUtil.colored(Config.format_chat
				.replace(Config.variables_playerName, "%1$s")
				.replace(Config.variables_message, "%2$s"))
				.replace(Config.variables_prefix, ChatUtils.getChat().getPlayerPrefix(p)));
	}
}

/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import pl.kresrek007.tools.basic.Ban;
import pl.kresrek007.tools.basic.utils.BanUtils;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Parser;

public class PlayerLoginListener implements Listener{
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e){
		Player p = e.getPlayer();
		Ban uuidBan = BanUtils.getBan(p);
		if(uuidBan != null){
			if(uuidBan.isExpired()){
				BanUtils.removeBan(uuidBan);	
				return;
			}
			e.disallow(Result.KICK_BANNED, Parser.parseBanMessage(Messages.ban_format_uuid, uuidBan));
			
			
		}
		Ban ipBan = BanUtils.getBan(e.getAddress().getHostAddress());
		if(ipBan != null){
			if(ipBan.isExpired()){
				BanUtils.removeBan(ipBan);	
				return;
			}			
			e.disallow(Result.KICK_BANNED,Parser.parseBanMessage(Messages.ban_format_ip, ipBan));
		}
	}

}

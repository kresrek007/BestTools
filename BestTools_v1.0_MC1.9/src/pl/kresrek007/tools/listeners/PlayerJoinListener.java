/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.StringUtil;
import pl.kresrek007.tools.utils.Update;
import pl.kresrek007.tools.utils.Util;

public class PlayerJoinListener implements Listener{
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		UserUtils.join(p);
		User u = UserUtils.getUser(p);
		for(String msg : Messages.welcomeMessage)
			Util.sendMessage(p,
					msg
						.replace(Config.variables_playerName, u.getNickName())
						.replace(Config.variables_ipAddres, u.getLastIP())
						.replace(Config.variables_onlinePlayers, Integer.toString(Bukkit.getOnlinePlayers().size()))
						.replace(Config.variables_maxPlayers, Integer.toString(Bukkit.getMaxPlayers())));
		Update.check(p);
		if(Messages.join != null && !Messages.join.equalsIgnoreCase("")) e.setJoinMessage(StringUtil.colored(Messages.join.replace(Config.variables_playerName, p.getName())));
	}
	
}

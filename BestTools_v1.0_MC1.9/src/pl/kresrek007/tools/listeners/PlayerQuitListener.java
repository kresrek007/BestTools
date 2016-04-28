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
import org.bukkit.event.player.PlayerQuitEvent;

import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.StringUtil;

public class PlayerQuitListener implements Listener{
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		UserUtils.quit(p);
		if(Messages.quit != null && !Messages.quit.equalsIgnoreCase("")) e.setQuitMessage(StringUtil.colored(Messages.quit.replace(Config.variables_playerName, p.getName())));
	}
}

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
import org.bukkit.event.entity.PlayerDeathEvent;

import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.utils.Parser;

public class PlayerDeathListener implements Listener{
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		Player p = e.getEntity();
		User u = UserUtils.getUser(p);
		u.setLastLocation(p.getLocation());
		if(Config.onDeath_dropSkull) p.getLocation().getWorld().dropItemNaturally(p.getLocation(), Parser.getHead(p.getName()));	
		if(Config.onDeath_lighting) p.getLocation().getWorld().strikeLightningEffect(p.getLocation());
	}

}

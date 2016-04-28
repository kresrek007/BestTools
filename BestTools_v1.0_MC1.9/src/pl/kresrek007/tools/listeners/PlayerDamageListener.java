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
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.tasks.TeleportTask;
import pl.kresrek007.tools.utils.Util;


public class PlayerDamageListener implements Listener{
	
	@EventHandler
	public void onDamage_Teleport(EntityDamageEvent e){
		if (!(e.getEntity() instanceof Player)) return;
		Player p = (Player)e.getEntity();
		if(!TeleportTask.teleports.containsKey(p.getUniqueId())) return;
		TeleportTask.teleports.remove(p.getUniqueId());
		p.removePotionEffect(PotionEffectType.CONFUSION);
		Util.sendMessage(p, Messages.pluginPrefix + Messages.teleportCanceled);
	}
	
	@EventHandler
	public void onDamage_GOD(EntityDamageEvent e){
		if (e.isCancelled())return;  
		if (!(e.getEntity() instanceof Player)) return;
		Player p = (Player)e.getEntity();
		User u = UserUtils.getUser(p);
		if(u.isGodMode()) e.setCancelled(true);
	}
	
	@EventHandler
	public void onDamage_Protection(EntityDamageByEntityEvent e){
		if (e.isCancelled())return;       
        if (e.getDamage() <= 0.0) return;
		if (!(e.getEntity() instanceof Player)) return; 
        if (e.getDamager() == null) return;        
        if (!(e.getDamager() instanceof Player)) return;
        Player p = (Player)e.getEntity();
        User u = UserUtils.getUser(p);
        Player p2 = (Player)e.getDamager();
        User u2 = UserUtils.getUser(p2);
        if(u.isProtectionMode()){
        	e.setCancelled(true);
        	Util.sendMessage(p2, Messages.pluginPrefix + Messages.protectionOther);
        	return;
        }
        if(u2.isProtectionMode()){
        	e.setCancelled(true);
        	Util.sendMessage(p, Messages.pluginPrefix + Messages.protectionAttacker);
        	return;
        }
	}

}

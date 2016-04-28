/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.tasks.TeleportTask;
import pl.kresrek007.tools.utils.Util;


public class PlayerMoveListener implements Listener{
	
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Location from = e.getFrom();
        Location to = e.getTo();
        if (from.getWorld().equals(to.getWorld()) || from.getX() != to.getX() || from.getY() != to.getY() || from.getZ() != to.getZ()) return;
		Player p = e.getPlayer();
		if(!TeleportTask.teleports.containsKey(p.getUniqueId())) return;
		TeleportTask.teleports.remove(p.getUniqueId());
		p.removePotionEffect(PotionEffectType.CONFUSION);	
		Util.sendMessage(p, Messages.pluginPrefix + Messages.teleportCanceled);
	}
}

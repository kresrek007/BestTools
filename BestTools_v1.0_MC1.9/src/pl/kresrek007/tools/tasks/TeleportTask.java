/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.tasks;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import pl.kresrek007.tools.Main;
import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.utils.Util;


public class TeleportTask {
	public static final HashMap<UUID, BukkitTask> teleports = new HashMap<UUID, BukkitTask>();
	
	public static void teleport(final Player p, final Location loc) {
		final User u = UserUtils.getUser(p);
    	if(p.hasPermission(Config.permissions_teleportNoDelay)){
    		u.setLastLocation(p.getLocation());
    		p.teleport(loc);    		
            Util.sendMessage(p, Messages.pluginPrefix + Messages.teleported);            
    		return;
    	}
    	int time = Config.cooldown_teleport;
        Util.sendMessage(p, Messages.pluginPrefix + Messages.teleportWait.replace(Config.variables_seconds, Integer.toString(time)));
        p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20 * time + 100, 5));
        if (teleports.get(p.getUniqueId()) != null) {
            teleports.remove(p.getUniqueId()).cancel();
        }
        final BukkitTask task = new BukkitRunnable() {
            public void run() {
                if (p.isOnline()) {
                    u.setLastLocation(p.getLocation());
                    p.teleport(loc);
                    p.removePotionEffect(PotionEffectType.CONFUSION);
                    Util.sendMessage(p, Messages.pluginPrefix + Messages.teleported);                         
                    teleports.remove(p.getUniqueId());
                    
                }
            }
        }.runTaskLater(Main.getInstance(), time * 20);
        teleports.put(p.getUniqueId(), task);
    }
}

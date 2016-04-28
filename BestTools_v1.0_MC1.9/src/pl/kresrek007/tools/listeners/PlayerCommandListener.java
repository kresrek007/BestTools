/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.listeners;

import org.apache.commons.lang.StringUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import pl.kresrek007.tools.data.Data;
import pl.kresrek007.tools.utils.Util;


public class PlayerCommandListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        String m;
        if(e.getMessage() == null){
        	return;
        }
        if(!(e.getMessage().startsWith("/"))){
        	return;
        }
        m = e.getMessage().substring(1);
        ConfigurationSection cs = Data.getCommands().getConfigurationSection("commands");
        for (final String command : cs.getKeys(false)) {
        	if(StringUtils.startsWithIgnoreCase(m, command) && (command.contains(" ") || m.length() == command.length() || Character.isWhitespace(m.charAt(command.length())))){
                e.setCancelled(true);
                for (final String str : cs.getStringList(command)) {
                	Util.sendMessage(p, str);
                }
        	}
        }
    }
}

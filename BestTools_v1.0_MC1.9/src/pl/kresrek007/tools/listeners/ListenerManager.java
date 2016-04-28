/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.listeners;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import pl.kresrek007.tools.Main;
import pl.kresrek007.tools.utils.LogUtil;
import pl.kresrek007.tools.utils.LogUtil.LogType;

public class ListenerManager {
	public static void setup(){
		LogUtil.print(LogType.debug, "Registering events...");
		
		Main plugin = Main.getInstance();
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerChatListener(), plugin);
		pm.registerEvents(new PlayerCommandListener(), plugin);
		pm.registerEvents(new PlayerDamageListener(), plugin);
		pm.registerEvents(new PlayerDeathListener(), plugin);
		pm.registerEvents(new PlayerJoinListener(), plugin);
		pm.registerEvents(new PlayerLoginListener(), plugin);
		pm.registerEvents(new PlayerMoveListener(), plugin);
		pm.registerEvents(new PlayerQuitListener(), plugin);
		
		LogUtil.print(LogType.debug, "Registered events!");
	}	
}

/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import pl.kresrek007.tools.data.Data;
import pl.kresrek007.tools.utils.LogUtil.LogType;

public class Update {
	
	public static void check(){
		Thread t = new Thread(){
			@Override
			public void run(){
				String l = Util.getContent("http://pastebin.com/raw/K43BKAQc");
				if(l == null || l.isEmpty()) LogUtil.print(LogType.info, "Failed to check the new version of BestTools.");
				else if(l.equalsIgnoreCase(Data.getVersion())) LogUtil.print(LogType.info, "You have a current version of BestTools.");
				else {
					LogUtil.print(LogType.info, "###");
					LogUtil.print(LogType.info, "Available is new version of BestTools");
					LogUtil.print(LogType.info, "Latest: " + l);
					LogUtil.print(LogType.info, "Current: " + Data.getVersion());
					LogUtil.print(LogType.info, "###");
				}
			}
		};
		t.start();
	}
	
	
	public static void check(final Player p){
		if(p.hasPermission("BestTools.admin")){
			Thread t = new Thread(){
				@Override
				public void run(){
					String l = Util.getContent("http://pastebin.com/raw/K43BKAQc");
					if(!l.equalsIgnoreCase(Data.getVersion())) {
						Util.sendMessage(p, "&8###", "&7Dostepna nowa wersja &7BestTools &8(&7" + l + "&8)", "&8###");
						p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_DEATH, 3.0F, 0.5F);
					}
				}
			};
			t.start();
		}
	}
	
	
}

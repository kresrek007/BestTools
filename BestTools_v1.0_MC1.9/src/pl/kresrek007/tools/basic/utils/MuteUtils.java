/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.basic.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import pl.kresrek007.tools.basic.Mute;
import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.data.Data;
import pl.kresrek007.tools.data.database.DatabaseBasic;
import pl.kresrek007.tools.data.flat.FlatMutes;
import pl.kresrek007.tools.utils.LogUtil;
import pl.kresrek007.tools.utils.LogUtil.LogType;

public class MuteUtils {
	private static List<Mute> mutes = new ArrayList<Mute>();
	
	public static List<Mute> getMutes(){
		return mutes;
	}
	
	public static Mute createMute(UUID uuid, String reason, String admin, long expireTime){
		Mute m = new Mute(uuid, reason, admin, System.currentTimeMillis(), expireTime);
		mutes.add(m);
		m.save();
		return m;
	}
	
	public static Mute getMute(Player p){
		for(Mute m : mutes){
			if(m.getUniqueId().equals(p.getUniqueId())) return m;
		}		
		return null;
	}
	
	public static Mute getMute(User u){
		for(Mute m : mutes){
			if(m.getUniqueId().equals(u.getUniqueId())) return m;
		}		
		return null;
	}
	
	public static Mute getMute(UUID uuid){
		for(Mute m : mutes){
			if(m.getUniqueId().equals(uuid)) return m;
		}		
		return null;
	}
	
	
	public static void removeMute(Mute m){
		mutes.remove(m);
		m.remove();
	}
	
	public static void setup(){
		if(Data.isFlat()) FlatMutes.loadMutes();
		if(Data.isMySQL()) DatabaseBasic.loadMutes();
		LogUtil.print(LogType.info, "Loaded " + mutes.size() + " mutes!");
	}
}

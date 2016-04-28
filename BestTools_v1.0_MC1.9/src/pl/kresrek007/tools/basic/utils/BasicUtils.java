/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.basic.utils;

import java.util.UUID;

import org.bukkit.Location;

import pl.kresrek007.tools.basic.Ban;
import pl.kresrek007.tools.basic.Mute;
import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.basic.Ban.BanType;


public class BasicUtils {
	
	public static User deserializeUser(Object[] values){
		if(values.length < 12) return null;
		User user = new User(
				(UUID) values[0],
				(String) values[1],
				(String) values[2],
				(String) values[3],
				(long) values[4],
				(long) values[5],
				(Location) values[6],
				(Location) values[7],
				(boolean) values[8],
				(boolean) values[9],
				(boolean) values[10],
				(String) values[11]
			);
		return user;
	}
	
	public static Mute deserializeMute(Object[] values){
		if(values.length < 5) return null;
		Mute mute = new Mute(
				(UUID) values[0],
				(String) values[1],
				(String) values[2],
				(long) values[3],
				(long) values[4]
			);
		return mute;
	}
	
	public static Ban deserializeBans(Object[] values){
		BanType banType = (BanType)values[0];
		UUID uuid = null;
		String ipAddres = null;
		if(banType == BanType.UUID) uuid = (UUID)values[1];
		if(banType == BanType.IP) ipAddres = (String)values[2];
		Ban ban = new Ban(
				banType,
				uuid,
				ipAddres,
				(String)values[3],
				(String)values[4],
				(Long)values[5],
				(Long)values[6]);
		return ban;
	}
}

/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.data.flat;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;

import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.basic.utils.BasicUtils;
import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.data.Data.BasicType;
import pl.kresrek007.tools.utils.Parser;

public class FlatUsers {
	public static void loadUsers(){
		for(FileConfiguration userYaml : Flat.getListYaml(BasicType.users)){
			User u = deserialize(userYaml);
			if(u != null) UserUtils.getUsers().add(u);
		}
	}
	
	public static User deserialize(FileConfiguration userYml){
		if(userYml == null) return null;		
		Object[] values = new Object[12];		
		values[0] = UUID.fromString(userYml.getString("uuid"));
		values[1] = userYml.getString("nickName");
		values[2] = userYml.getString("displayName");
		values[3] = userYml.getString("lastIP");
		values[4] = userYml.getLong("firstJoin");
		values[5] = userYml.getLong("lastJoin");
		values[6] = Parser.getLocation(userYml.getConfigurationSection("homeLocation"));
		values[7] = Parser.getLocation(userYml.getConfigurationSection("lastLocation"));
		values[8] = userYml.getBoolean("godMode");
		values[9] = userYml.getBoolean("protectionMode");
		values[10] = userYml.getBoolean("vanish");
		values[11] = userYml.getString("kits");
		return BasicUtils.deserializeUser(values);
		
	}
	
	public static void saveUser(User user){
		FileConfiguration userYaml = Flat.getYaml(BasicType.users, user.getNickName());
		userYaml.set("uuid", user.getUniqueId().toString());
		
		userYaml.set("nickName", user.getNickName());
		userYaml.set("displayName", user.getDisplayName());
		
		userYaml.set("lastIP", user.getLastIP());
		
		userYaml.set("kits", user.getKits());
		
		userYaml.set("godMode", user.isGodMode());
		userYaml.set("protectionMode", user.isProtectionMode());
		userYaml.set("vanish", user.isVanish());			
		
		userYaml.set("firstJoin", user.getFirstJoin());
		userYaml.set("lastJoin", user.getLastJoin());
		
		userYaml.set("lastLocation.world", user.getLastLocation().getWorld().getName());
		userYaml.set("lastLocation.x", user.getLastLocation().getBlockX());
		userYaml.set("lastLocation.y", user.getLastLocation().getBlockY());
		userYaml.set("lastLocation.z", user.getLastLocation().getBlockZ());
		userYaml.set("lastLocation.yaw", user.getLastLocation().getYaw());
		userYaml.set("lastLocation.pitch", user.getLastLocation().getPitch());
		
		userYaml.set("homeLocation.world", user.getHomeLocation().getWorld().getName());
		userYaml.set("homeLocation.x", user.getHomeLocation().getBlockX());
		userYaml.set("homeLocation.y", user.getHomeLocation().getBlockY());
		userYaml.set("homeLocation.z", user.getHomeLocation().getBlockZ());
		userYaml.set("homeLocation.yaw", user.getHomeLocation().getYaw());
		userYaml.set("homeLocation.pitch", user.getHomeLocation().getPitch());
		try {
			userYaml.save(Flat.getFile(BasicType.users, user.getNickName()));
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
}

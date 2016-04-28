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

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.data.Data;
import pl.kresrek007.tools.data.database.DatabaseBasic;
import pl.kresrek007.tools.data.flat.FlatUsers;
import pl.kresrek007.tools.utils.LogUtil;
import pl.kresrek007.tools.utils.LogUtil.LogType;

public class UserUtils {
	private static List<User> users = new ArrayList<User>();
	
	public static List<User> getUsers(){
		return users;
	}
	
	public static User createUser(Player p){
		if(getUser(p) != null) return getUser(p);
		final User u = new User(p);
		users.add(u);
		u.setChanged(true);
		u.save();
		return u;
	}
	
	public static User getUser(Player p){
		return getUser(p.getUniqueId());
	}
	
	public static User getUser(UUID uuid){
		for(User u : users){
			if(u.getUniqueId().equals(uuid)) return u;
		}
		return null;
	}
	
	public static User getUser(String nickName){
		for(User u : users){
			if(u.getNickName().equalsIgnoreCase(nickName)) return u;
		}
		return null;
	}
	
	public static void setup(){
		if(Data.isFlat()) FlatUsers.loadUsers();		
		if(Data.isMySQL()) DatabaseBasic.loadUsers();
		LogUtil.print(LogType.info, "Loaded " + users.size() + " players!");
	}
	
	
	
	public static void join(final Player p){
		createUser(p).setLastIP(p.getAddress().getHostName());
	}
	
	public static void quit(final Player p){
		final User u = getUser(p);
		if(u == null){
			LogUtil.print(LogType.warning, "Brak danych uzytkownika: " + p.getName());
			return;
		}
		u.setLastLocation(p.getLocation());
		u.save();
	}

	
	public static void removeUser(User u){
		users.remove(u);
		u.remove();
	}
	
	public static void saveAll(boolean onlyOnline){
		if(onlyOnline){
			for(Player p : Bukkit.getOnlinePlayers()) UserUtils.getUser(p).save();
			return;
		}
		for(User u : users) u.save();
	}
}

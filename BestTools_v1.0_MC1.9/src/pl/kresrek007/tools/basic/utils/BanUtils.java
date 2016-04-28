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

import pl.kresrek007.tools.basic.Ban;
import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.basic.Ban.BanType;
import pl.kresrek007.tools.data.Data;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.data.database.DatabaseBasic;
import pl.kresrek007.tools.data.flat.FlatBans;
import pl.kresrek007.tools.utils.LogUtil;
import pl.kresrek007.tools.utils.Parser;
import pl.kresrek007.tools.utils.Util;
import pl.kresrek007.tools.utils.LogUtil.LogType;

public class BanUtils {
	private static List<Ban> bans = new ArrayList<Ban>();
	
	public static List<Ban> getBans(){
		return bans;
	}
	
	public static Ban createBan(BanType banType, UUID uuid, String ipAddres, String reason, String admin, long expireTime){
		List<Player> players = new ArrayList<Player>();
		String msg = null;
		if(banType == BanType.IP){
			msg = Messages.ban_format_ip;
			if(getBan(ipAddres) != null) return getBan(ipAddres);
			for(Player p : Bukkit.getOnlinePlayers()){
				if(p.getAddress().getHostString().equalsIgnoreCase(ipAddres)) players.add(p);				
			}
		}
		if(banType == BanType.UUID){
			msg = Messages.ban_format_uuid;
			if(getBan(uuid) != null) return getBan(uuid);
			for(Player p : Bukkit.getOnlinePlayers()){
				if(p.getUniqueId().equals(uuid)) players.add(p);				
			}
		}
		Ban b = new Ban(banType, uuid, ipAddres, reason, admin, System.currentTimeMillis(), expireTime);
		bans.add(b);
		for(Player p : players){
			Util.kickPlayer(p, Parser.parseBanMessage(msg, b));
		}
		b.save();
		return b;
	}
	
	public static Ban getBan(Player p){
		for(Ban b : bans){
			if(b.getBanType() == BanType.UUID && b.getUniqueId().equals(p.getUniqueId())) return b;
		}		
		return null;
	}
	
	public static Ban getBan(User u){
		for(Ban b : bans){
			if(b.getBanType() == BanType.UUID && b.getUniqueId().equals(u.getUniqueId())) return b;
		}		
		return null;
	}
	
	public static Ban getBan(UUID uuid){
		for(Ban b : bans){
			if(b.getBanType() == BanType.UUID && b.getUniqueId().equals(uuid)) return b;
		}		
		return null;
	}
	
	public static Ban getBan(String ip){
		for(Ban b : bans){
			if(b.getBanType() == BanType.IP && b.getIpAddres().equalsIgnoreCase(ip)) return b;
		}		
		return null;
	}
	
	public static void removeBan(Ban b){
		bans.remove(b);
		b.remove();
	}
	
	public static void setup(){
		if(Data.isFlat()) FlatBans.loadBans();
		if(Data.isMySQL()) DatabaseBasic.loadBans();	
		LogUtil.print(LogType.info, "Loaded " + bans.size() + " bans!");
	}
	
	public static BanType getBanType(String banType){
		if(banType.equalsIgnoreCase("IP")) return BanType.IP;
		if(banType.equalsIgnoreCase("UUID")) return BanType.UUID;
		return null;
	}
	

	
}

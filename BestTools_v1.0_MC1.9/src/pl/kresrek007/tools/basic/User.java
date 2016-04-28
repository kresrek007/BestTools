/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.basic;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Data;
import pl.kresrek007.tools.data.Data.BasicType;
import pl.kresrek007.tools.data.database.DatabaseUser;
import pl.kresrek007.tools.data.flat.Flat;
import pl.kresrek007.tools.data.flat.FlatUsers;
import pl.kresrek007.tools.utils.StringUtil;

public class User {
	final UUID uuid;
	final String nickName;
	String displayName;
	String lastIP;
	long lastJoin;
	final long firstJoin;
	Location homeLocation; 
	Location lastLocation;
	boolean godMode;
	boolean protectionMode;
	boolean vanish;
	Player lastTalkPlayer;
	String kits;
	
	boolean changed;
	
	public User(Player player){
		this.uuid = player.getUniqueId();
		this.nickName = player.getName();
		this.displayName = player.getName();
		this.lastIP = player.getAddress().getHostString();
		this.firstJoin = System.currentTimeMillis();
		this.lastJoin = System.currentTimeMillis();
		this.homeLocation = player.getLocation();
		this.lastLocation = player.getLocation();
		this.godMode = false;
		this.protectionMode = true;
		this.vanish = false;
		this.lastTalkPlayer = null;
		this.kits = "default;0";
		this.changed = false;
	}
	
	public User(UUID uuid,
			String nickName,
			String displayName, 
			String lastIP,
			long lastJoin, 
			long firstJoin,
			Location homeLocation,
			Location lastLocation,
			boolean godMode,
			boolean protectionMode,
			boolean vanish,
			String kits){
		this.uuid = uuid;
		this.nickName = nickName;
		this.displayName = displayName;
		this.lastIP = lastIP;
		this.firstJoin = firstJoin;
		this.lastJoin = lastJoin;
		this.homeLocation = homeLocation;
		this.lastLocation = lastLocation;
		this.godMode = godMode;
		this.protectionMode = protectionMode;
		this.vanish = vanish;
		this.lastTalkPlayer = null;
		this.kits = kits;
		this.changed = false;
	}

	public void setDisplayName(String displayName){
		this.displayName = StringUtil.colored(displayName);
		this.changed = true;
	}
	
	public void setLastIP(String lastIP){
		this.lastIP = lastIP;
		this.changed = true;
	}
	
	public void setHomeLocation(Location homeLocation){
		this.homeLocation = homeLocation;
		this.changed = true;
	}
	
	public void setLastLocation(Location lastLocation){
		this.lastLocation = lastLocation;
		this.changed = true;
	}
	
	public void setGodMode(boolean godMode){
		this.godMode = godMode;
		this.changed = true;
	}
	
	public void setProtectionMode(boolean protectionMode){
		this.protectionMode = protectionMode;
		this.changed = true;
	}
	
	public void setVanish(boolean vanish){
		this.vanish = vanish;
		this.changed = true;
	}
	
	public void setLastTalkPlayer(Player p){
		this.lastTalkPlayer = p;
	}
	
	public void setKits(String kits){
		this.kits = kits;
		this.changed = true;
	}
	
	public void setChanged(boolean changed){
		this.changed = changed;
	}
	
	public UUID getUniqueId(){
		return uuid;
	}
	
	public String getNickName(){
		return nickName;
	}
	
	public String getDisplayName(){
		return displayName;
	}
	
	public String getLastIP(){
		return lastIP;
	}
	
	public long getLastJoin(){
		return lastJoin;
	}
	
	public long getFirstJoin(){
		return firstJoin;
	}
	
	public Location getHomeLocation(){
		return homeLocation;
	}
	
	public Location getLastLocation(){
		return lastLocation;
	}
	
	public Player getLastTalkPlayer(){
		return lastTalkPlayer;
	}
	
	public String getKits(){
		return kits;
	}
	
	public boolean isGodMode(){
		return godMode;
	}
	
	public boolean isProtectionMode(){
		if(!Config.protection_enabled) return false;
		if(protectionMode && System.currentTimeMillis() >= firstJoin + 1000*Config.protection_expired){
			protectionMode = false;
		}
		return protectionMode;
	}
	
	public boolean isVanish(){
		return vanish;
	}
	
	public boolean isChanged(){
		return changed;
	}
	
	public void remove(){
		if(Data.isFlat()) Flat.removeFile(BasicType.users, this.getNickName());		
		if(Data.isMySQL()) DatabaseUser.delete(this);
		
	}
	
	public void save(){
		if(!changed) return;
		if(Data.isFlat()) FlatUsers.saveUser(this);
		if(Data.isMySQL()) DatabaseUser.save(this);		
	}
}

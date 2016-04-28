/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.basic;

import java.util.UUID;

import pl.kresrek007.tools.data.Data;
import pl.kresrek007.tools.data.Data.BasicType;
import pl.kresrek007.tools.data.database.DatabaseMute;
import pl.kresrek007.tools.data.flat.Flat;
import pl.kresrek007.tools.data.flat.FlatMutes;

public class Mute {
	final UUID uuid;
	final String reason;
	final String admin;
	final long createTime;
	final long expireTime;
	
	public Mute(UUID uuid, String reason, String admin, long createTime, long expireTime){
		this.uuid = uuid;
		this.reason = reason;
		this.admin = admin;
		this.createTime = createTime;
		this.expireTime = expireTime;
	}
	
	public UUID getUniqueId(){
		return uuid;
	}
	
	public String getReason(){
		return reason;
	}
	
	public String getAdmin(){
		return admin;
	}
	
	public long getExpireTime(){
		return expireTime;
	}
	
	public long getCreateTime(){
		return createTime;
	}
	
	
	public boolean isExpired(){
		if(getExpireTime() <= System.currentTimeMillis() && getExpireTime() > 0) return true;
		return false;
	}
	
	public void remove(){
		if(Data.isFlat()) Flat.removeFile(BasicType.mutes, this.getUniqueId().toString());		
		if(Data.isMySQL()) DatabaseMute.delete(this);	
	}
	
	public void save(){
		if(Data.isFlat()) FlatMutes.saveMute(this);
		if(Data.isMySQL()) DatabaseMute.save(this);	
	}
	
}

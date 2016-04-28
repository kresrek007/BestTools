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
import pl.kresrek007.tools.data.database.DatabaseBans;
import pl.kresrek007.tools.data.flat.Flat;
import pl.kresrek007.tools.data.flat.FlatBans;

public class Ban {
	final BanType banType;
	final UUID uuid;
	final String ipAddres;
	final String reason;
	final String admin;
	final long createTime;
	final long expireTime;
	
	
	public Ban(BanType banType, UUID uuid, String ipAddres, String reason, String admin, long createTime, long expireTime){
		if(banType == BanType.IP) this.ipAddres = ipAddres;
		else this.ipAddres = null;
		if(banType == BanType.UUID) this.uuid = uuid;
		else this.uuid = null;
		this.banType = banType;
		this.reason = reason;
		this.admin = admin;
		this.createTime = createTime;
		this.expireTime = expireTime;
	}
	
	public enum BanType{
		IP,
		UUID,
	}
	
	public BanType getBanType(){
		return banType;
	}
	
	public UUID getUniqueId(){
		return uuid;
	}
	
	public String getIpAddres(){
		return ipAddres;
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
		if(Data.isFlat()) Flat.removeFile(BasicType.bans, FlatBans.getNameFile(this));		
		if(Data.isMySQL()) DatabaseBans.delete(this);	
	}
	
	
	public void save(){
		if(Data.isFlat()) FlatBans.saveBan(this);
		if(Data.isMySQL()) DatabaseBans.save(this);
	}
}

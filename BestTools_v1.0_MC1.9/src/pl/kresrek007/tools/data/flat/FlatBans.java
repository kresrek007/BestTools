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

import pl.kresrek007.tools.basic.Ban;
import pl.kresrek007.tools.basic.Ban.BanType;
import pl.kresrek007.tools.basic.utils.BanUtils;
import pl.kresrek007.tools.basic.utils.BasicUtils;
import pl.kresrek007.tools.data.Data.BasicType;

public class FlatBans {
	public static String getNameFile(Ban b){
		return (b.getBanType() == Ban.BanType.IP ? b.getIpAddres() : b.getUniqueId().toString());
	}
	
	public static void loadBans(){
		for(FileConfiguration banYaml : Flat.getListYaml(BasicType.bans)){
			Ban b = deserialize(banYaml);
			if(b != null) BanUtils.getBans().add(b);
		}
	}
	
	public static Ban deserialize(FileConfiguration banYaml){
		if(banYaml == null) return null;		
		Object[] values = new Object[7];		
		final BanType banType = BanUtils.getBanType(banYaml.getString("banType"));
		if(banType == null) return null;
		values[0] = banType;
		if(banType == BanType.UUID) values[1] = UUID.fromString(banYaml.getString("uuid"));
		if(banType == BanType.IP) values[2] = banYaml.getString("ipAddres");
		values[3]= banYaml.getString("reason");
		values[4] = banYaml.getString("admin");
		values[5] = banYaml.getLong("createTime");
		values[6] = banYaml.getLong("expireTime");
		return BasicUtils.deserializeBans(values);
	}
	
	public static void saveBan(Ban ban){
		FileConfiguration banYaml = Flat.getYaml(BasicType.bans, getNameFile(ban));
		banYaml.set("banType", ban.getBanType().toString());
		if(ban.getBanType() == BanType.IP) banYaml.set("ipAddres", ban.getIpAddres());
		if(ban.getBanType() == BanType.UUID) banYaml.set("uuid", ban.getUniqueId().toString());
		banYaml.set("reason", ban.getReason());
		banYaml.set("admin", ban.getAdmin());
		banYaml.set("createTime", ban.getCreateTime());
		banYaml.set("expireTime", ban.getExpireTime());
		try {
			banYaml.save(Flat.getFile(BasicType.bans, getNameFile(ban)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

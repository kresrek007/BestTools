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

import pl.kresrek007.tools.basic.Mute;
import pl.kresrek007.tools.basic.utils.BasicUtils;
import pl.kresrek007.tools.basic.utils.MuteUtils;
import pl.kresrek007.tools.data.Data.BasicType;

public class FlatMutes {
	public static void loadMutes(){
		for(FileConfiguration mutesYaml : Flat.getListYaml(BasicType.mutes)){
			Mute mute = deserialize(mutesYaml);
			if(mute != null) MuteUtils.getMutes().add(mute);
		}
	}
	
	public static Mute deserialize(FileConfiguration mutesYaml){
		if(mutesYaml == null) return null;		
		Object[] values = new Object[5];
		values[0] = UUID.fromString(mutesYaml.getString("uuid"));
		values[1] = mutesYaml.getString("reason");
		values[2] = mutesYaml.getString("admin");
		values[3] = mutesYaml.getLong("createTime");
		values[4] = mutesYaml.getLong("expireTime");
		return BasicUtils.deserializeMute(values);
	}
	
	public static void saveMute(Mute mute){
		FileConfiguration muteYaml = Flat.getYaml(BasicType.mutes, mute.getUniqueId().toString());
		muteYaml.set("uuid", mute.getUniqueId().toString());
		muteYaml.set("reason", mute.getReason());
		muteYaml.set("admin", mute.getAdmin());
		muteYaml.set("createTime", mute.getCreateTime());
		muteYaml.set("expireTime", mute.getExpireTime());
		try {
			muteYaml.save(Flat.getFile(BasicType.mutes, mute.getUniqueId().toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveMutes(){
		for(Mute m : MuteUtils.getMutes()) m.save();
	}
}

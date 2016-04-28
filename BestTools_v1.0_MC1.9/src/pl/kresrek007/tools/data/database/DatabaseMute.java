/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.data.database;

import java.sql.ResultSet;
import java.util.UUID;

import pl.kresrek007.tools.basic.Mute;
import pl.kresrek007.tools.basic.utils.BasicUtils;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.utils.LogUtil;
import pl.kresrek007.tools.utils.LogUtil.LogType;

public class DatabaseMute {
	
	public static void save(Mute m){
		String query = getInsert(m);
		if(query != null) {
			try {
				Database.sendUpdate(query);
			} catch (Exception e){
				LogUtil.print(LogType.error, "MYSQL UPDATE: " + query);
			}
		}
	}
	
	public static void delete(Mute m){
		String query = getDelete(m);
		if(query != null) {
			try {
				Database.sendUpdate(query);
			} catch (Exception e){
				LogUtil.print(LogType.error, "MYSQL UPDATE: " + query);
			}
		}
	}
	
	public static String getDelete(Mute m){
		if(m.getUniqueId() == null) return null;
		return ("DELETE FROM " + Config.mysql_table_mute + " WHERE uuid='" + m.getUniqueId() + "'");		
	}
	
	public static String getInsert(Mute m){
		if(m.getUniqueId() == null) return null;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + Config.mysql_table_mute + " (uuid, reason, admin, createTime, expireTime) VALUES (");
		sb.append("'" + m.getUniqueId().toString() + "',");
		sb.append("'" + m.getReason() + "',");
		sb.append("'" + m.getAdmin() + "',");
		sb.append("'" + m.getCreateTime() + "',");
		sb.append("'" + m.getExpireTime() + "'");
		sb.append(") ON DUPLICATE KEY UPDATE ");
		sb.append("expireTime='" + m.getExpireTime() + "',");
		sb.append("reason='" + m.getReason() + "'");
		return sb.toString();
	}
	
	public static Mute deserialize(ResultSet rs){
		if(rs == null) return null;
		try {	
			Object[] values = new Object[7];
			values[0] = UUID.fromString(rs.getString("uuid"));
			values[1] = rs.getString("reason");
			values[2] = rs.getString("admin");
			values[3] = rs.getLong("createTime");
			values[4] = rs.getLong("expireTime");
			return BasicUtils.deserializeMute(values);
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}

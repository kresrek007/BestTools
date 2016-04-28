/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.data.database;

import java.sql.ResultSet;
import java.util.UUID;

import pl.kresrek007.tools.basic.Ban;
import pl.kresrek007.tools.basic.utils.BanUtils;
import pl.kresrek007.tools.basic.utils.BasicUtils;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.utils.LogUtil;
import pl.kresrek007.tools.utils.LogUtil.LogType;

public class DatabaseBans {
	public static void save(Ban b){
		String query = getInsert(b);
		if(query != null) {
			try {
				Database.sendUpdate(query);
			} catch (Exception e){
				LogUtil.print(LogType.error, "MYSQL UPDATE: " + query);
			}
		}
	}
	
	public static String getInsert(Ban b){
		if(b.getBanType() == null) return null;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + Config.mysql_table_ban + " (banType, uuid, ipAddres, reason, admin, createTime, expireTime) VALUES (");
		sb.append("'" + b.getBanType().toString() + "',");
		sb.append("'" + b.getUniqueId().toString() + "',");
		sb.append("'" + b.getIpAddres() + "',");
		sb.append("'" + b.getReason() + "',");
		sb.append("'" + b.getAdmin() + "',");
		sb.append("'" + b.getCreateTime() + "',");
		sb.append("'" + b.getExpireTime() + "'");
		sb.append(") ON DUPLICATE KEY UPDATE ");
		sb.append("expireTime='" + b.getExpireTime() + "',");
		sb.append("reason='" + b.getReason() + "'");
		return sb.toString();
	}
	
	public static void delete(Ban b){
		String query = getDelete(b);
		if(query != null) {
			try {
				Database.sendUpdate(query);
			} catch (Exception e){
				LogUtil.print(LogType.error, "MYSQL UPDATE: " + query);
			}
		}
	}
	
	public static String getDelete(Ban b){
		if(b.getUniqueId() == null) return null;
		return ("DELETE FROM " + Config.mysql_table_ban + " WHERE uuid='" + b.getUniqueId() + "'");		
	}
	
	public static Ban deserialize(ResultSet rs){
		if(rs == null) return null;
		try {	
			Object[] values = new Object[7];
			values[0] = BanUtils.getBanType(rs.getString("banType"));
			values[1] = UUID.fromString(rs.getString("uuid"));
			values[2] = rs.getString("ipAddres");
			values[3] = rs.getString("reason");
			values[4] = rs.getString("admin");
			values[5] = rs.getLong("createTime");
			values[6] = rs.getLong("expireTime");
			return BasicUtils.deserializeBans(values);
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}

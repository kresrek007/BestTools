/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.data.database;

import java.sql.ResultSet;
import java.util.UUID;

import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.basic.utils.BasicUtils;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.utils.LogUtil;
import pl.kresrek007.tools.utils.Parser;
import pl.kresrek007.tools.utils.LogUtil.LogType;

public class DatabaseUser {
	
	public static void save(User u){
		String query = getInsert(u);
		if(query != null) {
			try {
				Database.sendUpdate(query);
			} catch (Exception e){
				LogUtil.print(LogType.error, "MYSQL UPDATE: " + query);
			}
		}
	}
	
	public static void delete(User u){
		String query = getDelete(u);
		if(query != null) {
			try {
				Database.sendUpdate(query);
			} catch (Exception e){
				LogUtil.print(LogType.error, "MYSQL UPDATE: " + query);
			}
		}
	}
	
	public static String getDelete(User u){
		if(u.getUniqueId() == null) return null;
		return ("DELETE FROM " + Config.mysql_table_user + " WHERE uuid='" + u.getUniqueId() + "'");
		
	}
	
	public static String getInsert(User u){
		if(u.getUniqueId() == null) return null;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + Config.mysql_table_user + " (uuid, nickName, displayName, lastIP, lastJoin, firstJoin, kits, homeLocation, lastLocation, godMode, protectionMode, vanish) VALUES (");
		sb.append("'" + u.getUniqueId().toString() + "',");
		sb.append("'" + u.getNickName() + "',");
		sb.append("'" + u.getDisplayName() + "',");
		sb.append("'" + u.getLastIP() + "',");
		sb.append("'" + u.getLastJoin() + "',");
		sb.append("'" + u.getFirstJoin() + "',");
		sb.append("'" + u.getKits() + "',");
		sb.append("'" + Parser.parseLocation(u.getHomeLocation()) + "',");
		sb.append("'" + Parser.parseLocation(u.getLastLocation()) + "',");
		sb.append("'" + (u.isGodMode() ? "1" : "0") + "',");
		sb.append("'" + (u.isProtectionMode() ? "1" : "0") + "',");
		sb.append("'" + (u.isVanish() ? "1" : "0") + "'");
		sb.append(") ON DUPLICATE KEY UPDATE ");
		sb.append("nickName='" + u.getNickName() + "',");
		sb.append("displayName='" + u.getDisplayName() + "',");
		sb.append("lastIP='" + u.getLastIP() + "',");
		sb.append("lastJoin='" + u.getLastJoin() + "',");
		sb.append("kits='" + u.getKits() + "',");
		sb.append("homeLocation='" + Parser.parseLocation(u.getHomeLocation()) + "',");
		sb.append("lastLocation='" + Parser.parseLocation(u.getLastLocation()) + "',");
		sb.append("godMode='" + (u.isGodMode() ? "1" : "0") + "',");
		sb.append("protectionMode='" + (u.isProtectionMode() ? "1" : "0") + "',");
		sb.append("vanish='" + (u.isVanish() ? "1" : "0") + "'");
		return sb.toString();
	}
	
	public static User deserialize(ResultSet rs){
		if(rs == null) return null;
		try {	
			Object[] values = new Object[12];
			values[0] = UUID.fromString(rs.getString("uuid"));
			values[1] = rs.getString("nickName");
			values[2] = rs.getString("displayName");
			values[3] = rs.getString("lastIP");
			values[4] = rs.getLong("lastJoin");
			values[5] = rs.getLong("firstJoin");
			values[6] = Parser.getLocation(rs.getString("homeLocation"));
			values[7] = Parser.getLocation(rs.getString("lastLocation"));
			values[8] = (rs.getInt("godMode") == 1 ? true : false);
			values[9] = (rs.getInt("protectionMode") == 1 ? true : false);
			values[10] = (rs.getInt("vanish") == 1 ? true : false);
			values[11] = rs.getString("kits");
			return BasicUtils.deserializeUser(values);
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}

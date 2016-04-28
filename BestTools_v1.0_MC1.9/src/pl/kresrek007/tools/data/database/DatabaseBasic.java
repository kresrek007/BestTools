/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.data.database;

import java.sql.ResultSet;

import pl.kresrek007.tools.basic.Ban;
import pl.kresrek007.tools.basic.Mute;
import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.basic.utils.BanUtils;
import pl.kresrek007.tools.basic.utils.MuteUtils;
import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.data.Config;


public class DatabaseBasic {
	public static void createTable(){
		createUsersTable();
		createBansTable();
		createMutesTable();
	}
	
	public static void createUsersTable() {
		StringBuilder sb = new StringBuilder();
		sb.append("create table if not exists " + Config.mysql_table_user + "(");
		sb.append("id int(10) not null auto_increment,");
		sb.append("uuid varchar(36) not null,");
		sb.append("nickName varchar(36) not null,");
		sb.append("displayName varchar(100) not null,");
		sb.append("lastIP varchar(50) not null,");
		sb.append("lastJoin bigint(13) not null default '0',");
		sb.append("firstJoin bigint(13) not null default '0',");
		sb.append("kits varchar(999) not null,");
		sb.append("homeLocation varchar(999) not null,");
		sb.append("lastLocation varchar(999) not null,");
		sb.append("godMode int(1) NOT NULL,");
		sb.append("protectionMode int(1) NOT NULL,");
		sb.append("vanish int(1) NOT NULL,");
		sb.append("primary key (uuid),");
		sb.append("unique key `id` (`id`));");
		Database.executeUpdate(sb.toString());
	}
	
	public static void createBansTable() {
		StringBuilder sb = new StringBuilder();
		sb.append("create table if not exists " + Config.mysql_table_ban + "(");
		sb.append("id int(10) not null auto_increment,");
		sb.append("banType varchar(10) not null,");
		sb.append("uuid varchar(36),");
		sb.append("ipAddres varchar(50),");
		sb.append("reason varchar(999) not null,");
		sb.append("admin varchar(50) not null,");
		sb.append("createTime bigint(13) not null default '0',");
		sb.append("expireTime bigint(13) not null default '0',");
		sb.append("primary key (id),");
		sb.append("unique key `id` (`id`));");
		Database.executeUpdate(sb.toString());
	}
	
	public static void createMutesTable() {
		StringBuilder sb = new StringBuilder();
		sb.append("create table if not exists " + Config.mysql_table_mute + "(");
		sb.append("id int(10) not null auto_increment,");
		sb.append("uuid varchar(36),");
		sb.append("reason varchar(999) not null,");
		sb.append("admin varchar(50) not null,");
		sb.append("createTime bigint(13) not null default '0',");
		sb.append("expireTime bigint(13) not null default '0',");
		sb.append("primary key (id),");
		sb.append("unique key `id` (`id`));");
		Database.executeUpdate(sb.toString());
	}
	
	public static void loadUsers(){
		ResultSet users = Database.executeQuery("SELECT * FROM " + Config.mysql_table_user);
		try {
			while(users.next()){
				User u = DatabaseUser.deserialize(users);
				if(u != null) UserUtils.getUsers().add(u);
			}
		} catch (Exception e){
			e.printStackTrace();
		}		
	}
	
	public static void loadBans(){
		ResultSet bans = Database.executeQuery("SELECT * FROM " + Config.mysql_table_ban);
		try {
			while(bans.next()){
				Ban b = DatabaseBans.deserialize(bans);
				if(b != null) BanUtils.getBans().add(b);
			}
		} catch (Exception e){
			e.printStackTrace();
		}		
	}
	
	public static void loadMutes(){
		ResultSet mutes = Database.executeQuery("SELECT * FROM " + Config.mysql_table_mute);
		try {
			while(mutes.next()){
				Mute m = DatabaseMute.deserialize(mutes);
				if(m != null) MuteUtils.getMutes().add(m);
			}
		} catch (Exception e){
			e.printStackTrace();
		}		
	}
}

/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.data.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

import pl.kresrek007.tools.Main;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.utils.LogUtil;
import pl.kresrek007.tools.utils.LogUtil.LogType;


public class Database {
	
    private static Connection connection;
   
    
    public static Connection getConnection() {
        try {        	
        	if(checkConnection()) return connection;
			Class.forName("com.mysql.jdbc.Driver");
	        connection = DriverManager.getConnection("jdbc:mysql://" + Config.mysql_server_host + ":" + Config.mysql_server_port + "/" + Config.mysql_server_database, Config.mysql_server_user, Config.mysql_server_password);
	        return connection;
        }
	    catch (Exception e) {
	    	LogUtil.print(LogType.warning, "Nie udalo polaczyc sie z MySQL, plugin uzywa lokalny zapis plikow!");
	    	Config.dataType = "flat";
	    }
		return null;         
    }
    
	public static boolean checkConnection(){
		try {
			return connection != null && !connection.isClosed();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return connection != null;
	}
	
	public static ResultSet executeQuery(final String query) {
		try {
			Statement s = getConnection().createStatement();
			ResultSet result = s.executeQuery(query);
			return result;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;		
	}
	
	public static int executeUpdate(final String query) {
		try {
			if(getConnection() == null) return 0;
			Statement s = getConnection().createStatement();
			if(s == null) return 0;
			return s.executeUpdate(query);
		} catch (Exception e){
			e.printStackTrace();
		}
		return 0;		
	}
	
	public static void sendUpdate(final String query) {
		if(Main.disabling()){
			executeUpdate(query);
			return;
		}
		Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(),
				new Runnable() {
					public void run() {
						executeUpdate(query);

					}
				});

		}
}

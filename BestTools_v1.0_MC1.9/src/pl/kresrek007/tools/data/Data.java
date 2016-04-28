/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.data;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import pl.kresrek007.tools.Main;
import pl.kresrek007.tools.utils.LogUtil;
import pl.kresrek007.tools.utils.LogUtil.LogType;

public class Data {
	public enum BasicType { users, bans, mutes };
	
	
	private static File messagesFile = new File(Main.getInstance().getDataFolder(), "messages.yml");
	private static FileConfiguration messages;
	private static File configFile = new File(Main.getInstance().getDataFolder(), "config.yml");
	private static FileConfiguration config;
	private static File kitFile = new File(Main.getInstance().getDataFolder(), "kits.yml");
	private static FileConfiguration kit;
	private static File commandFile = new File(Main.getInstance().getDataFolder(), "commands.yml");
	private static FileConfiguration command;
	
	
	private static String version = "1.0 Beta";
	
	private static String configVersion = "1.0";
	private static String messagesVersion = "1.0";

	public static String getVersion(){
		return version;
	}
	
	public static String getConfigVersion(){
		return configVersion;
	}
	
	public static String getMessagesVersion(){
		return messagesVersion;
	}
	
	
	public static File getMessagesFile(){
		if(messagesFile == null) loadMessages();
		return messagesFile;
	}
	
	public static FileConfiguration getMessages(){
		if(messages == null) loadMessages();
		return messages;
	}
	
	
	public static void loadMessages(){
		if(!messagesFile.exists()){			
			Main.getInstance().saveResource("messages.yml", true);
			LogUtil.print(LogType.debug, "Create File: messages.yml");		
		}
		messages = YamlConfiguration.loadConfiguration(messagesFile);
		if(messages != null){
			LogUtil.print(LogType.debug, "Loaded: messages.yml");
			return;
		}
		LogUtil.print(LogType.error, "messages.yml is null :<");
	}
	
	public static File getCommandsFile(){
		if(commandFile == null) loadCommands();
		return commandFile;
	}
	
	public static FileConfiguration getCommands(){
		if(command == null) loadCommands();
		return command;
	}
	
	public static void loadCommands(){
		if(!commandFile.exists()){			
			Main.getInstance().saveResource("commands.yml", true);
			LogUtil.print(LogType.debug, "Create File: commands.yml");		
		}
		command = YamlConfiguration.loadConfiguration(commandFile);
		if(command != null){
			LogUtil.print(LogType.debug, "Loaded: commands.yml");
			return;
		}
		LogUtil.print(LogType.error, "commands.yml is null :<");
	}
	
	public static File getKitsFile(){
		if(kitFile == null) loadKits();
		return kitFile;
	}
	
	public static FileConfiguration getKits(){
		if(kit == null) loadKits();
		return kit;
	}
	
	public static void loadKits(){
		if(!kitFile.exists()){			
			Main.getInstance().saveResource("kits.yml", true);
			LogUtil.print(LogType.debug, "Create File: kits.yml");		
		}
		kit = YamlConfiguration.loadConfiguration(kitFile);
		if(kit != null){
			LogUtil.print(LogType.debug, "Loaded: kits.yml");
			return;
		}
		LogUtil.print(LogType.error, "kits.yml is null :<");
	}
	
	public static File getConfigFile(){
		if(configFile == null) loadConfig();
		return configFile;
	}
	
	public static FileConfiguration getConfig(){
		if(config == null) loadConfig();
		return config;
	}
	
	public static void configFileRefresh(){
		configFile =  new File(Main.getInstance().getDataFolder(), "config.yml");
	}

	public static void messagesFileRefresh(){
		messagesFile =  new File(Main.getInstance().getDataFolder(), "messages.yml");
	}
	
	public static void loadConfig(){
		if(!configFile.exists()){			
			Main.getInstance().saveResource("config.yml", true);
			LogUtil.print(LogType.debug, "Create File: config.yml");		
		}
		config = YamlConfiguration.loadConfiguration(configFile);
		if(config != null){
			LogUtil.print(LogType.debug, "Loaded: config.yml");
			return;
		}
		LogUtil.print(LogType.error, "config.yml is null :<");
	}
	
	public static DataType getType(){
		if(Config.dataType.equalsIgnoreCase("mysql")) return DataType.MYSQL;
		return DataType.FLAT;		
	}
	
	public static boolean isFlat(){
		if(getType() == DataType.FLAT) return true;
		return false;
	}
	
	public static boolean isMySQL(){
		if(getType() == DataType.MYSQL) return true;
		return false;
	}
	
	public enum DataType{
		FLAT,
		MYSQL,
	}
}

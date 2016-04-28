/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.data.flat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import pl.kresrek007.tools.Main;
import pl.kresrek007.tools.data.Data.BasicType;

public class Flat {
	private static File dataFolder = Main.getInstance().getDataFolder();
	
	public static File getFolder(BasicType basicType) {
		File folder = new File(dataFolder, basicType.name());
		if (!folder.exists()) {
			folder.mkdirs();
		}
		return folder;
	 }
	
	public static FileConfiguration getYaml(BasicType basicType, String fileName){
		return YamlConfiguration.loadConfiguration(getFile(basicType, fileName));
	}
	
	public static boolean exists(BasicType basicType, String fileName){
		File userFile = new File(getFolder(basicType), fileName + ".yml");
		return userFile.exists();
	}
	
	
	public static File getFile(BasicType basicType, String fileName){
		File userFile = new File(getFolder(basicType), fileName + ".yml");
		createFile(userFile);
		return userFile;
	}

	
	public static List<FileConfiguration> getListYaml(BasicType basicType){
		List<FileConfiguration> list = new ArrayList<FileConfiguration>();
		for (final File file : getFolder(basicType).listFiles()){
			list.add(YamlConfiguration.loadConfiguration(file));
		}
		return list;
	}
	
	public static boolean createFile(final File file){
		if (!file.exists()) {
            try {
            	file.createNewFile();
            	return true;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
		return false;
	}
	
	public static boolean removeFile(BasicType basicType, String fileName){
		if(!exists(basicType, fileName)) return false;
		File userFile = new File(getFolder(basicType), fileName + ".yml");
		userFile.delete();
		return true;
	}
	
}

/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class StringUtil {
	public static boolean isList(Field f) {
    	return List.class.isAssignableFrom(f.getType());
    }
	
	public static String colored(String msg){
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public static List<String> colored(List<String> msg){
		List<String> list = new ArrayList<String>();
		for(String s : msg){
			list.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		return list;
	}
}

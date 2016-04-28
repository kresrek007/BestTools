/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.utils;

import pl.kresrek007.tools.Main;
import pl.kresrek007.tools.data.Config;


public class LogUtil {
	
	public static void print(LogType type, String message){
		if(type == LogType.debug && !Config.debug) return;
		System.out.println("[" + Main.getInstance().getDescription().getName() + " / " + type.toString().toUpperCase() + "] " + message);
	}
	
	public enum LogType{
		debug,
		warning,
		info,
		error,		
	}
}

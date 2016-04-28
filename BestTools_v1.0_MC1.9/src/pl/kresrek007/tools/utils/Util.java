/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.utils;

import io.netty.handler.timeout.TimeoutException;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.utils.LogUtil.LogType;


public class Util {
	
	public static boolean isInteger(final String string) {
		return Pattern.matches("-?[0-9]+", string.subSequence(0, string.length()));
	}
	
	public static boolean sendMessage(CommandSender sender, String... message){
		if(sender == null) return false;
		if(message.length == 0) return false;
		for(String s : message){
			sender.sendMessage(StringUtil.colored(s));
		}
		return true;
	}

	public static boolean broadcast(String... message){
		if(message.length == 0) return false;
		for(String s : message){
			Bukkit.broadcastMessage(StringUtil.colored(s));
		}
		return true;
	}
	
	public static boolean broadcastPermission(String permission, String... message){
		if(message.length == 0) return false;
		for(String s : message){
			for(Player p : Bukkit.getOnlinePlayers()) if(p.hasPermission("op")) Util.sendMessage(p, s);
		}
		return true;
	}
	
	public static boolean kickPlayer(Player p, String reason){
		if(p == null) return false;
		if(p.hasPermission(Config.permissions_kickBypass)) return false;
		p.kickPlayer(StringUtil.colored(reason));
		return true;
	}
	
    public static void giveItems(final Player p, final ItemStack... items) {
        final Inventory i = (Inventory)p.getInventory();
        final HashMap<Integer, ItemStack> notStored = (HashMap<Integer, ItemStack>)i.addItem(items);
        for (final Map.Entry<Integer, ItemStack> e : notStored.entrySet()) {
            p.getWorld().dropItemNaturally(p.getLocation(), (ItemStack)e.getValue());
        }
        p.updateInventory();
    }
    
	public static String getContent(String s){
		String body = null;
		try {
			URL url = new URL(s);
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
			body = toString(in, encoding);
		} catch (TimeoutException e){
			LogUtil.print(LogType.info, e.getMessage());
		} catch (Exception e){
			LogUtil.print(LogType.warning, e.getMessage());
		}
		return body;
	}
	
	public static String toString(InputStream in, String encoding) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[8192];
		int len = 0;
		while ((len = in.read(buf)) != -1)
		    baos.write(buf, 0, len);
		return new String(baos.toByteArray(), encoding);
	}
}

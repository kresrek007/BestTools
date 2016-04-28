/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.data.Data;
import pl.kresrek007.tools.objects.Kit;

public class KitUtils {
	private static List<Kit> kit = new ArrayList<Kit>();
	
	static ConfigurationSection kitCfg = Data.getKits().getConfigurationSection("kits");
	
	public static List<Kit> getKits(){
		return kit;
	}
	
	public static void addKit(Kit b) {
		kit.add(b);
	}
	public static Kit getKit(String name){
		for(Kit sm : kit) {
			if(sm.getName().equalsIgnoreCase(name)) {
				return sm;
			}
		}
		return null;
	}
	
	
	public static void addKit(User u, String name, long time){
		String kitFirst = u.getKits();
		String[] kitSecond = kitFirst.split("@");
		for(String kitThird : kitSecond){
			if(kitThird.contains(name)){
				u.setKits(u.getKits().replace("@" +kitThird, ""));
			}
		}
		String kitAdd = null;
		if(u.getKits().length() != 0) kitAdd = "@";
		kitAdd = kitAdd + name + ";" + time;
		u.setKits(u.getKits() + kitAdd);
	}
	
	public static long getKitTime(User u,String name){
		String kitFirst = u.getKits();
		String[] kitSecond = kitFirst.split("@");
		for(String kitThird : kitSecond){
			if(kitThird.contains(name)){
				String[] kitFourth = kitThird.split(";");
				return Long.parseLong(kitFourth[1]);
				
			}
		}
		return 0;
	}
	
	public static void setup(){
		getKits().clear();
		for(String csName : kitCfg.getKeys(false)){
			ConfigurationSection cs = kitCfg.getConfigurationSection(csName);
			String name = cs.getName();
			int cooldown = cs.getInt("cooldown");
			String permission = cs.getString("permission");
			List<ItemStack> items = new ArrayList<ItemStack>();
			ConfigurationSection csItemName = cs.getConfigurationSection("items");
			for (String csI : csItemName.getKeys(false)){
				ConfigurationSection csItem = csItemName.getConfigurationSection(csI);
				items.add(Parser.getItemStack(csItem));
			}
			Kit kit = new Kit(name);
			kit.setCooldown(cooldown);
			kit.setPermissions(permission);
			kit.setItems(items);
			addKit(kit);
		}
	}
}

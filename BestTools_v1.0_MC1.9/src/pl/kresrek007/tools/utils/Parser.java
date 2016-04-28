/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import pl.kresrek007.tools.basic.Ban;
import pl.kresrek007.tools.basic.User;
import pl.kresrek007.tools.basic.Ban.BanType;
import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Messages;

public class Parser {
	
	public static Location getLocation(String s){
		String[] locString = s.split(";");
		if(locString.length < 6) return null;
		String sWorld = locString[0];
		double x = Double.parseDouble(locString[1]);
		double y = Double.parseDouble(locString[2]);
		double z = Double.parseDouble(locString[3]);
		float yaw = Float.parseFloat(locString[4]);
		float pitch = Float.parseFloat(locString[5]);
		return new Location(Bukkit.getWorld(sWorld), x, y, z, yaw, pitch);
	}
	
	public static Location getLocation(ConfigurationSection cs){
		String sWorld = cs.getString("world");
		double x = cs.getDouble("x");
		double y = cs.getDouble("y");
		double z = cs.getDouble("z");
		float yaw = cs.getInt("world");
		float pitch = cs.getInt("world");
		return new Location(Bukkit.getWorld(sWorld), x, y, z, yaw, pitch);
	}
	
	public static ItemStack getHead(String owner){
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
		SkullMeta meta = (SkullMeta) item.getItemMeta(); 
		meta.setOwner(owner);
		item.setItemMeta(meta);
		return item;
	}
	
	public static GameMode getGameName(String gm) {
		switch(gm) {
		case "0": return GameMode.SURVIVAL;
		case "1": return GameMode.CREATIVE;
		case "2": return GameMode.ADVENTURE;
		case "s": return GameMode.SURVIVAL;
		case "sv": return GameMode.SURVIVAL;
		case "surv": return GameMode.SURVIVAL;
		case "survival": return GameMode.SURVIVAL;
		case "c": return GameMode.CREATIVE;
		case "creative": return GameMode.CREATIVE;
		case "a": return GameMode.ADVENTURE;
		case "adv": return GameMode.ADVENTURE;
		case "adventure": return GameMode.ADVENTURE;
		default: return GameMode.SURVIVAL;
		}
	}
	
	public static ItemStack getItemStack(final Material m, final short data, final int amount, final String name, final List<String> lore, final HashMap<Enchantment, Integer> enchants) {
        ItemStack item = null;
        int a = 64;
        if (amount >= 1) {
            a = amount;
        }
        if (data > 0) {
            item = new ItemStack(m, a);
        }
        else {
            item = new ItemStack(m, a, data);
        }
        if (enchants != null) {
            item.addUnsafeEnchantments(enchants);
        }
        ItemMeta meta = item.getItemMeta();
        if(name != null) meta.setDisplayName(StringUtil.colored(name));
        if(lore != null) meta.setLore(StringUtil.colored(lore));
        item.setItemMeta(meta);
        return item;
    }
    
	public static ItemStack getItemStack(Material m, short data, int i) {
		ItemStack item = null;
        int a = 64;
        if (i >= 1) {
            a = i;
        }
        if (data <= 0) {
            item = new ItemStack(m, a);
        }
        else {
            item = new ItemStack(m, a, data);
        }
        return item;
	}
	
    public static Material getMaterial(final String id) {
        if (Util.isInteger(id)) {
            return Material.getMaterial(Integer.parseInt(id));
        }
        Material[] values;
        for (int length = (values = Material.values()).length, i = 0; i < length; ++i) {
            final Material m = values[i];
            if (m.name().replace("_", "").equalsIgnoreCase(id)) {
                return m;
            }
        }
        return null;
    }
	
    public static ItemStack getItemStack(ConfigurationSection cs){
		int id = 1;
		if(cs.isInt("material.id")){
			 id = cs.getInt("material.id");
		}
		int data = 0;
		if(cs.isInt("material.data")){
			data = cs.getInt("material.data");
		}
		int number = 1;
		if(cs.isInt("material.number")){
			number = cs.getInt("material.number");
		}
		boolean nameB = false;
		String name = null;
		if(cs.isString("display.name")){
			nameB = true;
			name = cs.getString("display.name");		
		}
		boolean loreB = false;
		List<String> lore = null;
		if(cs.isList("display.lore")){
			loreB = true;
			lore = cs.getStringList("display.lore");
		}
		boolean enchantB = false;
		List<String> enchantment = null;
		if(cs.isList("enchantments")){
			enchantB = true;
			enchantment = cs.getStringList("enchantments");
		}
		ItemStack itemStack = new ItemStack(id, number, (short)data);
		if(nameB || loreB){
			ItemMeta itemMeta = itemStack.getItemMeta();
			if(nameB){
				itemMeta.setDisplayName(StringUtil.colored(name));
			}
			if(loreB){
				List<String> loress = new ArrayList<String>();
				for(String loress2 : lore){
					loress.add(StringUtil.colored(loress2));
				}
				itemMeta.setLore(loress);
			}
			itemStack.setItemMeta(itemMeta);
		}
		if(enchantB){
			for(String enchant : enchantment){
				String[] ench = enchant.split(";");
				if(!(ench.length < 2)){
					itemStack.addUnsafeEnchantment(Enchantment.getById(Integer.parseInt(ench[0])), Integer.parseInt(ench[1]));
				}
				
			}
		}
		

		return itemStack;
	}
    
	public static ItemStack getItem(String i){
		ItemStack item = null;
		final String[] split = i.split(":");
		final Material m = getMaterial(split[0]);
		if(m == null) return null;
		final short data = (short)((split.length > 1) ? Short.parseShort(split[1]) : 0);
		item = getItemStack(m, data, 1);
		return item;
	}
	
	public static String getDate(final long time) {
        return new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss").format(new Date(time));
    }
	
	
	public static String parseLocation(Location loc){
		StringBuilder sb = new StringBuilder();
		sb.append(loc.getWorld().getName() + ";");
		sb.append(loc.getBlockX() + ";");
		sb.append(loc.getBlockY() + ";");
		sb.append(loc.getBlockZ() + ";");
		sb.append(loc.getYaw() + ";");
		sb.append(loc.getPitch());
		return sb.toString();
	}
	
	public static String parseBanMessage(String message, Ban b){
		String s = message
		.replace(Config.variables_nextLine, "\n")
		.replace(Config.variables_reason, b.getReason())
		.replace(Config.variables_adminName, b.getAdmin())
		.replace(Config.variables_expireTime, (b.getExpireTime() > 0 ? getDate(b.getExpireTime()) : Messages.value_never));
		if(b.getBanType() == BanType.IP) s = String.valueOf(s).replace(Config.variables_ipAddres, b.getIpAddres());		
		if(b.getBanType() == BanType.UUID){
			s = String.valueOf(s).replace(Config.variables_uuid, b.getUniqueId().toString());
			User u = UserUtils.getUser(b.getUniqueId());
			if(u != null) s = String.valueOf(s).replace(Config.variables_playerName, u.getDisplayName());
		}
		return StringUtil.colored(s);
	}
	
	//METODA NIE NASZEGO AUTORSTWA
	public static long parseDateDiff(final String time, final boolean future) {
        try {
            final Pattern timePattern = Pattern.compile("(?:([0-9]+)\\s*y[a-z]*[,\\s]*)?(?:([0-9]+)\\s*mo[a-z]*[,\\s]*)?(?:([0-9]+)\\s*w[a-z]*[,\\s]*)?(?:([0-9]+)\\s*d[a-z]*[,\\s]*)?(?:([0-9]+)\\s*h[a-z]*[,\\s]*)?(?:([0-9]+)\\s*m[a-z]*[,\\s]*)?(?:([0-9]+)\\s*(?:s[a-z]*)?)?", 2);
            final Matcher m = timePattern.matcher(time);
            int years = 0;
            int months = 0;
            int weeks = 0;
            int days = 0;
            int hours = 0;
            int minutes = 0;
            int seconds = 0;
            boolean found = false;
            while (m.find()) {
                if (m.group() != null && !m.group().isEmpty()) {
                    for (int i = 0; i < m.groupCount(); ++i) {
                        if (m.group(i) != null && !m.group(i).isEmpty()) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        continue;
                    }
                    if (m.group(1) != null && !m.group(1).isEmpty()) {
                        years = Integer.parseInt(m.group(1));
                    }
                    if (m.group(2) != null && !m.group(2).isEmpty()) {
                        months = Integer.parseInt(m.group(2));
                    }
                    if (m.group(3) != null && !m.group(3).isEmpty()) {
                        weeks = Integer.parseInt(m.group(3));
                    }
                    if (m.group(4) != null && !m.group(4).isEmpty()) {
                        days = Integer.parseInt(m.group(4));
                    }
                    if (m.group(5) != null && !m.group(5).isEmpty()) {
                        hours = Integer.parseInt(m.group(5));
                    }
                    if (m.group(6) != null && !m.group(6).isEmpty()) {
                        minutes = Integer.parseInt(m.group(6));
                    }
                    if (m.group(7) == null) {
                        break;
                    }
                    if (m.group(7).isEmpty()) {
                        break;
                    }
                    seconds = Integer.parseInt(m.group(7));
                    break;
                }
            }
            if (!found) {
                return -1L;
            }
            final Calendar c = new GregorianCalendar();
            if (years > 0) {
                c.add(1, years * (future ? 1 : -1));
            }
            if (months > 0) {
                c.add(2, months * (future ? 1 : -1));
            }
            if (weeks > 0) {
                c.add(3, weeks * (future ? 1 : -1));
            }
            if (days > 0) {
                c.add(5, days * (future ? 1 : -1));
            }
            if (hours > 0) {
                c.add(11, hours * (future ? 1 : -1));
            }
            if (minutes > 0) {
                c.add(12, minutes * (future ? 1 : -1));
            }
            if (seconds > 0) {
                c.add(13, seconds * (future ? 1 : -1));
            }
            final Calendar max = new GregorianCalendar();
            max.add(1, 10);
            if (c.after(max)) {
                return max.getTimeInMillis();
            }
            return c.getTimeInMillis();
        }
        catch (Exception e) {
            return -1L;
        }
    }
}


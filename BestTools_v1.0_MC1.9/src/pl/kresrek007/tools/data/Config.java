/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.data;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import pl.kresrek007.tools.Main;
import pl.kresrek007.tools.utils.LogUtil;
import pl.kresrek007.tools.utils.StringUtil;
import pl.kresrek007.tools.utils.LogUtil.LogType;

public class Config {
	public static boolean debug = false;
	
	public static String dataType = "flat";	
	
	public static String mysql_server_host = "localhost";
	public static String mysql_server_user = "root";
	public static String mysql_server_database = "data";
	public static String mysql_server_password = "pass";
	public static int mysql_server_port = 3306;
	
	public static String mysql_table_user = "besttools_user";
	public static String mysql_table_mute = "besttools_mute";
	public static String mysql_table_ban = "besttools_ban";
	
	public static String variables_nextLine = "%nextline%";
	public static String variables_adminName = "%adminName%";
	public static String variables_playerName = "%playerName%";
	public static String variables_otherName = "%otherName%";
	public static String variables_uuid = "%uuid%";
	public static String variables_ipAddres = "%ipAddres%";
	public static String variables_expireTime = "%expireTime%";
	public static String variables_reason = "%reason%";
	public static String variables_message = "%message%";
	public static String variables_value = "%value%";
	public static String variables_amount = "%amount%";
	public static String variables_itemName = "%itemName%";
	public static String variables_seconds = "%seconds%";
	public static String variables_prefix = "%prefix%";
	public static String variables_kitList = "%kitList%";
	public static String variables_kitName = "%kitName%";
	public static String variables_date = "%date%";
	public static String variables_onlinePlayers = "%onlinePlayers%";
	public static String variables_maxPlayers = "%maxPlayers%";
	
	public static String permissions_chatBypass = "besttools.chat.bypass";
	public static String permissions_kickBypass = "besttools.kick.bypass";
	public static String permissions_helpopSee = "besttools.helpop.see";
	public static String permissions_teleportNoDelay = "besttools.helpop.see";
	public static String permissions_enderchestOther = "besttools.enderchest.other";
	
	public static int cooldown_teleport = 5;
	public static int cooldown_chat = 10;
	
	public static boolean autoMessage_enabled = true;
	public static int autoMessage_every = 120;
	public static String autoMessage_prefix = "&8[&cBestTools BOT&8] &7";
	public static List<String> autoMessage_messages = Arrays.asList("Pierwsza wiadomosc..", "Druga wiadomosc..");
	
	public static String format_broadcast = "&eBest&6Tools &8> &e%message%";
	public static String format_helpop = "&8[&4%playerName%&8] &c%message%";
	public static String format_kick = "You kicked by %adminName%, reason: %reason%";
	public static String format_talk = "[%playerName% -> %otherName%] %message%";
	public static String format_chat = "%prefix%%playerName% &8> &f%message%";
	
	public static boolean onDeath_dropSkull = true;
	public static boolean onDeath_lighting = true;
	
	public static boolean protection_enabled = true;
	public static int protection_expired = 420;
	
	
	public static String commands_ban_name = "ban";
	public static String commands_ban_description = "Ban command!";
	public static String commands_ban_usage = "/ban <player> <time (0 = perm)> [reason]";
	public static String commands_ban_permissions = "besttools.ban";
	public static List<String> commands_ban_aliases = new ArrayList<>();
	
	public static String commands_banip_name = "banip";
	public static String commands_banip_description = "banip command!";
	public static String commands_banip_usage = "/banip <player> <time (0 = perm)> [reason]";
	public static String commands_banip_permissions = "besttools.banip";
	public static List<String> commands_banip_aliases = new ArrayList<>();
	
	public static String commands_unban_name = "unban";
	public static String commands_unban_description = "unban command!";
	public static String commands_unban_usage = "/unban <player/ip>";
	public static String commands_unban_permissions = "besttools.unban";
	public static List<String> commands_unban_aliases = Arrays.asList("pardon");
	
	public static String commands_mute_name = "mute";
	public static String commands_mute_description = "mute command!";
	public static String commands_mute_usage = "/mute <player>";
	public static String commands_mute_permissions = "besttools.mute";
	public static List<String> commands_mute_aliases = new ArrayList<>();
	
	public static String commands_unmute_name = "unmute";
	public static String commands_unmute_description = "unmute command!";
	public static String commands_unmute_usage = "/unmute <player>";
	public static String commands_unmute_permissions = "besttools.unmute";
	public static List<String> commands_unmute_aliases = new ArrayList<>();
	
	public static String commands_chat_name = "chat";
	public static String commands_chat_description = "chat command!";
	public static String commands_chat_usage = "/chat <clear/disable/enable>";
	public static String commands_chat_permissions = "besttools.chat";
	public static List<String> commands_chat_aliases = new ArrayList<>();
	
	public static String commands_clear_name = "clear";
	public static String commands_clear_description = "clear command!";
	public static String commands_clear_usage = "/clear [player]";
	public static String commands_clear_permissions = "besttools.clear";
	public static List<String> commands_clear_aliases = new ArrayList<>();
	
	public static String commands_say_name = "say";
	public static String commands_say_description = "say command!";
	public static String commands_say_usage = "/say <message>";
	public static String commands_say_permissions = "besttools.say";
	public static List<String> commands_say_aliases = Arrays.asList("broadcast");
	
	public static String commands_enderchest_name = "enderchest";
	public static String commands_enderchest_description = "enderchest command!";
	public static String commands_enderchest_usage = "/enderchest [player]";
	public static String commands_enderchest_permissions = "besttools.enderchest";
	public static List<String> commands_enderchest_aliases = Arrays.asList("echest");
	
	public static String commands_feed_name = "feed";
	public static String commands_feed_description = "feed command!";
	public static String commands_feed_usage = "/feed [player]";
	public static String commands_feed_permissions = "besttools.feed";
	public static List<String> commands_feed_aliases = new ArrayList<>();
	
	public static String commands_fly_name = "fly";
	public static String commands_fly_description = "fly command!";
	public static String commands_fly_usage = "/fly [player]";
	public static String commands_fly_permissions = "besttools.fly";
	public static List<String> commands_fly_aliases = new ArrayList<>();
	
	public static String commands_back_name = "back";
	public static String commands_back_description = "back command!";
	public static String commands_back_usage = "/back";
	public static String commands_back_permissions = "besttools.back";
	public static List<String> commands_back_aliases = new ArrayList<>();
	
	public static String commands_gamemode_name = "gamemode";
	public static String commands_gamemode_description = "gamemode command!";
	public static String commands_gamemode_usage = "/gamemode <mode> [player]";
	public static String commands_gamemode_permissions = "besttools.gamemode";
	public static List<String> commands_gamemode_aliases = Arrays.asList("gm");
	
	public static String commands_give_name = "give";
	public static String commands_give_description = "give command!";
	public static String commands_give_usage = "/give <player> <id:data> [num]";
	public static String commands_give_permissions = "besttools.give";
	public static List<String> commands_give_aliases = new ArrayList<>();	
	
	public static String commands_god_name = "god";
	public static String commands_god_description = "god command!";
	public static String commands_god_usage = "/god [player]";
	public static String commands_god_permissions = "besttools.god";
	public static List<String> commands_god_aliases = new ArrayList<>();
	
	public static String commands_head_name = "head";
	public static String commands_head_description = "head command!";
	public static String commands_head_usage = "/head <player>";
	public static String commands_head_permissions = "besttools.head";
	public static List<String> commands_head_aliases = new ArrayList<>();
	
	public static String commands_heal_name = "heal";
	public static String commands_heal_description = "heal command!";
	public static String commands_heal_usage = "/heal [player]";
	public static String commands_heal_permissions = "besttools.heal";
	public static List<String> commands_heal_aliases = new ArrayList<>();


	public static String commands_helpop_name = "helpop";
	public static String commands_helpop_description = "helpop command!";
	public static String commands_helpop_usage = "/helpop <message>";
	public static String commands_helpop_permissions = "besttools.helpop";
	public static List<String> commands_helpop_aliases = new ArrayList<>();
	
	public static String commands_home_name = "home";
	public static String commands_home_description = "home command!";
	public static String commands_home_usage = "/home";
	public static String commands_home_permissions = "besttools.home";
	public static List<String> commands_home_aliases = new ArrayList<>();
	
	public static String commands_kit_name = "kit";
	public static String commands_kit_description = "kit command!";
	public static String commands_kit_usage = "/kit <name>";
	public static String commands_kit_permissions = "besttools.kit";
	public static List<String> commands_kit_aliases = new ArrayList<>();
	
	public static String commands_sethome_name = "sethome";
	public static String commands_sethome_description = "sethome command!";
	public static String commands_sethome_usage = "/sethome";
	public static String commands_sethome_permissions = "besttools.sethome";
	public static List<String> commands_sethome_aliases = new ArrayList<>();
	
	public static String commands_invsee_name = "invsee";
	public static String commands_invsee_description = "invsee command!";
	public static String commands_invsee_usage = "/invsee <player>";
	public static String commands_invsee_permissions = "besttools.invsee";
	public static List<String> commands_invsee_aliases = new ArrayList<>();
	
	public static String commands_item_name = "item";
	public static String commands_item_description = "item command!";
	public static String commands_item_usage = "/item <arg>";
	public static String commands_item_permissions = "besttools.item";
	public static List<String> commands_item_aliases = new ArrayList<>();
	
	public static String commands_kick_name = "kick";
	public static String commands_kick_description = "kick command!";
	public static String commands_kick_usage = "/kick <player> [reason]";
	public static String commands_kick_permissions = "besttools.kick";
	public static List<String> commands_kick_aliases = new ArrayList<>();
	
	public static String commands_kill_name = "kill";
	public static String commands_kill_description = "kill command!";
	public static String commands_kill_usage = "/kill [player]";
	public static String commands_kill_permissions = "besttools.kill";
	public static List<String> commands_kill_aliases = new ArrayList<>();
	
	public static String commands_talk_name = "talk";
	public static String commands_talk_description = "talk command!";
	public static String commands_talk_usage = "/talk <player> <message>";
	public static String commands_talk_permissions = "besttools.talk";
	public static List<String> commands_talk_aliases = Arrays.asList("msg");
	
	public static String commands_reply_name = "reply";
	public static String commands_reply_description = "reply command!";
	public static String commands_reply_usage = "/reply <message>";
	public static String commands_reply_permissions = "besttools.reply";
	public static List<String> commands_reply_aliases = Arrays.asList("r");
	
	public static String commands_list_name = "list";
	public static String commands_list_description = "list command!";
	public static String commands_list_usage = "/list";
	public static String commands_list_permissions = "besttools.list";
	public static List<String> commands_list_aliases = new ArrayList<>();
	
	public static String commands_spawn_name = "spawn";
	public static String commands_spawn_description = "spawn command!";
	public static String commands_spawn_usage = "/spawn";
	public static String commands_spawn_permissions = "besttools.spawn";
	public static List<String> commands_spawn_aliases = new ArrayList<>();
	
	public static String commands_setspawn_name = "setspawn";
	public static String commands_setspawn_description = "setspawn command!";
	public static String commands_setspawn_usage = "/setspawn";
	public static String commands_setspawn_permissions = "besttools.setspawn";
	public static List<String> commands_setspawn_aliases = new ArrayList<>();
	
	public static String commands_time_name = "time";
	public static String commands_time_description = "time command!";
	public static String commands_time_usage = "/time <day/night>";
	public static String commands_time_permissions = "besttools.time";
	public static List<String> commands_time_aliases = new ArrayList<>();
	
	public static String commands_whois_name = "whois";
	public static String commands_whois_description = "whois command!";
	public static String commands_whois_usage = "/whois";
	public static String commands_whois_permissions = "besttools.whois";
	public static List<String> commands_whois_aliases = new ArrayList<>();
	
	public static void setup(){
		Data.loadConfig();
		load();
	}
	
	
	public static void load()
	{
		try{
			final FileConfiguration data = Data.getConfig();
			for(final Field f : Config.class.getFields())
			{
				if(data.isSet(f.getName().replace("_", "."))) {
					if(StringUtil.isList(f)) f.set(null, data.getStringList(f.getName().replace("\\n", "\n").replace("_", ".")));
					else{
						f.set(null, data.get(f.getName().replace("_", ".")));
					}
				}
			}
			String version = Data.getConfig().getString("version");
			if(version != null && !Data.getConfigVersion().equalsIgnoreCase(version)){
				LogUtil.print(LogType.info, "Updating the plugin config...");
				File f = Data.getConfigFile();
				f.renameTo(new File(Main.getInstance().getDataFolder(), "config.old"));
				setup();
			}
		}catch(Exception e) {
			LogUtil.print(LogType.error, "An error occured with loading config, disabling plugin...");
			Bukkit.getPluginManager().disablePlugin(Main.getInstance());
			e.printStackTrace();
		}
	}
}

/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.data;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import pl.kresrek007.tools.Main;
import pl.kresrek007.tools.utils.LogUtil;
import pl.kresrek007.tools.utils.StringUtil;
import pl.kresrek007.tools.utils.LogUtil.LogType;

public class Messages {
	
	public static String pluginPrefix = "&eBest&6Tools &8» &e";
	
	public static String noPermissions = "&cAccess denied! &8(&c%permission%&8)";
	public static String onlyPlayer = "&cAccess denied! You are console!";
	public static String errorCommand = "&cAn error occurred while executing a command!";
	public static String defaultUsage = "&cCorrect usage: &7%usage%";
	public static String chatCooldown = "&cSlow down! You're sending messages too fast! You can send one message for &7%seconds% seconds&c.";
	
	public static String join = "&8[&a+&8] &a%playerName%";
	public static String quit = "&8[&c-&8] &c%playerName%";
	
	public static List<String> welcomeMessage = Arrays.asList("&8###", "&7Nick name:&c %playerName%", "&7Addres IP:&c %ipAddres%", "&7Online: &c%onlinePlayers%&7/&c%maxPlayers%", "&7This server usign BestTools by Kresrek007", "&8###");
	
	public static String value_never = "never";
	public static String value_enabled = "enabled";
	public static String value_enable = "enable";
	public static String value_disabled = "disabled";
	public static String value_disable = "disable";
	public static String value_server = "console";
	public static String value_you = "you";
	public static String value_day = "day";
	public static String value_night = "night";
	public static String value_cleared = "cleared";
	
	public static String noFoundPlayer = "&cNo found player&7 %playerName%";
	public static String itemNull = "Dont found item :/";
	public static String defaultReason = "Default reason!";

	public static String ban_format_uuid = "You have been banned by %adminName%. %nextline%Reason: %reason%. %nextline% Expires on: %expireTime%";
	public static String ban_format_ip = "IP have been banned by %adminName%. %nextline%Reason: %reason%. %nextline% Expires on: %expireTime%";
	public static String ban_broadcast_uuid = "Player %playerName% was been banned by %adminName%. Reason: %reason% Expires: %expireTime%";
	public static String ban_broadcast_ip = "IP %ipAddres% was been banned by %adminName%. Reason: %reason% Expires: %expireTime%";
	public static String ban_already = "Ban is already!";
	public static String ban_unban = "Player %playerName% was been unbanned by %adminName%!";	
	
	public static String clearPlayer = "Your equipment has been cleaned by %adminName%.";
	public static String clearSender = "Player %playerName% equipment has been cleaned!";
	
	public static String chatAction = "Chat has been %value% by %adminName%";
	
	public static String flyPlayer = "Fly Mode has been %value% by %adminName%.";
	public static String flySender = "Player %playerName% fly Mode has been %value%";
	
	public static String feedPlayer = "Your feed lvl has been fill by %adminName%.";
	public static String feedSender = "Player %playerName% feed lvl has been fill!";
	
	public static String gamemodePlayer = "Your gamemode has been change to %value% by %adminName%.";
	public static String gamemodeSender = "Player %playerName% gamemode has been change to %value%!";
	
	public static String givePlayer = "Your recived recived (%amount%) %itemName% by %adminName%.";
	public static String giveSender = "Player %playerName% recived (%amount%) %itemName%!";
	
	public static String godPlayer = "God Mode has been %value% by %adminName%.";
	public static String godSender = "Player %playerName% god Mode has been %value%";
	
	public static String healPlayer = "You have been healted by %adminName%.";
	public static String healSender = "Player %playerName% have been healted by %value%";
	
	public static String homeSet = "Home set!";
	
	public static String mute_broadcast = "Player %playerName% was been muted by %adminName%. Reason: %reason% Expires: %expireTime%";
	public static String mute_already = "Mute is already!";
	public static String mute_unmute = "Player %playerName% was been unmuted by %adminName%!";
	public static String mute_triedMessage = "You muted by %adminName%! Reason %reason% Expires: %expireTime%";
	
	public static String itemSender = "Your recived (%amount%) %itemName%";
	
	public static String kitNoFound = "No found any kits!";
	public static String kitSplit = "&7, &c";
	public static String kitList = "Kits: &c%kitList%";
	public static String kitCooldown = "&cKit &7kitName&c you can take a &7%date%";
	public static String kitReceive = "&cYou receive &7%kitName%";
	
	public static String playerList = "&cCurrently online players &7%online%&c/&7%maxPlayers%&c.";
	
	public static String replyNoPlayer = "You do not have anyone to answer";
	
	public static String spawnSet = "Spawn set!";	
	
	public static String timeSet = "Time set to %value%";
	
	public static String giveHead = "Your recived head of %playerName%";
	
	public static String kill = "%adminName% killed by the command %playerName%";
	
	public static String reloaded = "Reloaded: config.yml & messages.yml";
	
	public static List<String> whois = Arrays.asList("&8###", "&7Nick name:&c %playerName%", "&7Addres IP:&c %ipAddres%");
	
	public static String teleported = "You teleported...";
	public static String teleportWait = "You have to wait %seconds% seconds for teleports!";
	public static String teleportCanceled = "Teleport has been canceled!";
	
	public static String chatIsDisabled = "Chat is disabled!";
	
	public static String protectionAttacker = "You cant attack because of you've protection mode!";
	public static String protectionOther = "You cant attack because of other has protection mode!";
	
	public static String version = "0.0";
	
	
	public static void setup(){
		Data.loadMessages();
		load();
	}
	
	
	public static void load()
	{
		try{
			final FileConfiguration data = Data.getMessages();
			for(final Field f : Messages.class.getFields())
			{
				if(data.isSet(f.getName().replace("_", "."))) {
					if(StringUtil.isList(f)) f.set(null, data.getStringList(f.getName().replace("\\n", "\n").replace("_", ".")));
					else{
						f.set(null, data.get(f.getName().replace("_", ".")));
					}
				}
			}
			String version = Data.getMessages().getString("version");
			if(version != null && !Data.getMessagesVersion().equalsIgnoreCase(version)){
				LogUtil.print(LogType.info, "Updating the plugin messages...");
				File f = Data.getMessagesFile();
				f.renameTo(new File(Main.getInstance().getDataFolder(), "messages.old"));
				setup();
			}
		}catch(Exception e) {
			LogUtil.print(LogType.error, "An error occured with loading messages, disabling plugin...");
			Bukkit.getPluginManager().disablePlugin(Main.getInstance());
			e.printStackTrace();
		}
	}
}

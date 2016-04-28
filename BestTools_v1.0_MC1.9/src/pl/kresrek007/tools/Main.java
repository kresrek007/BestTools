/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools;

import org.bukkit.plugin.java.JavaPlugin;

import pl.kresrek007.tools.basic.utils.BanUtils;
import pl.kresrek007.tools.basic.utils.MuteUtils;
import pl.kresrek007.tools.basic.utils.UserUtils;
import pl.kresrek007.tools.commands.CommandManager;
import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.data.Data;
import pl.kresrek007.tools.data.Messages;
import pl.kresrek007.tools.data.database.DatabaseBasic;
import pl.kresrek007.tools.listeners.ListenerManager;
import pl.kresrek007.tools.tasks.AutoMessageTask;
import pl.kresrek007.tools.utils.ChatUtils;
import pl.kresrek007.tools.utils.KitUtils;
import pl.kresrek007.tools.utils.LogUtil;
import pl.kresrek007.tools.utils.Update;
import pl.kresrek007.tools.utils.LogUtil.LogType;

public class Main extends JavaPlugin{

	private static Main instance;
	
	private static boolean disabling = false;
	
	public void onEnable(){
		instance = this;
		ListenerManager.setup();
		CommandManager.setup();
		Config.setup();
		Messages.setup();
		Data.loadKits();
		Data.loadCommands();
		if(Data.isMySQL()) DatabaseBasic.createTable();
		UserUtils.setup();
		BanUtils.setup();
		MuteUtils.setup();
		ChatUtils.setup();
		if(ChatUtils.getChat() == null) LogUtil.print(LogType.warning, "For correct operation of the plugin (chat) is required Vault!");
		KitUtils.setup();
		if(Config.autoMessage_enabled) new AutoMessageTask().runTaskTimer(Main.getInstance(), Config.autoMessage_every*20, Config.autoMessage_every*20);
		Update.check();
	}
	
	public void onDisable(){
		disabling = true;
		UserUtils.saveAll(true);
	}
	
	public static boolean disabling(){
		return disabling;
	}
	
	public static Main getInstance(){
		return instance;
	}
	
	
}

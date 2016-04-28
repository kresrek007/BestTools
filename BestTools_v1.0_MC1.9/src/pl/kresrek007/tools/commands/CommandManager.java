/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.commands;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

import pl.kresrek007.tools.Main;
import pl.kresrek007.tools.commands.admin.*;
import pl.kresrek007.tools.commands.users.*;
import pl.kresrek007.tools.utils.LogUtil;
import pl.kresrek007.tools.utils.LogUtil.LogType;

public class CommandManager {
	private static CommandMap commandMap;
	
	public static void setup(){
		if(commandMap != null) commandMap.clearCommands();
		LogUtil.print(LogType.debug, "Registering commands...");
		
		registerCommands(
				new BanCommand(),
				new BanIPCommand(),
				new ChatCommand(),
				new ClearCommand(),
				new EnderchestCommand(),
				new FeedCommand(),
				new FlyCommand(),
				new GameModeCommand(),
				new GiveCommand(),
				new GodCommand(),
				new HeadCommand(),
				new HealCommand(),
				new InvseeCommand(),
				new ItemCommand(),
				new BestToolsCommand(),
				new KickCommand(),
				new KillCommand(),
				new MuteCommand(),
				new SayCommand(),
				new SetSpawnCommand(),
				new TimeCommand(),
				new UnBanCommand(),
				new UnMuteCommand(),
				new WhoisCommand()
		);
				
		registerCommands(
				new BackCommand(),
				new HelpopCommand(),
				new HomeCommand(),
				new KitCommand(),
				new ListCommand(),
				new ReplyCommand(),
				new SetHomeCommand(),
				new SpawnCommand(),
				new TalkCommand()
		);
		
		LogUtil.print(LogType.debug, "Registered commands!");
	}
	
	
	static void registerCommands(Command... commands){
		for(Command command : commands){
			registerCommand(command);
		}
	}
	
	
	static void registerCommand(Command command){
		if(commandMap == null){			
			try {
				Field f = SimplePluginManager.class.getDeclaredField("commandMap");
				f.setAccessible(true); 
				commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
			}
			catch (Exception e) {
		        e.printStackTrace();
			}					
		}
		commandMap.register(command.getName(), Main.getInstance().getDescription().getName(), command);
	}
}

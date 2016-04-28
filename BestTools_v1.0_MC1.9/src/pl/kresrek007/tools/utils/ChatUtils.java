/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.utils;

import org.bukkit.plugin.RegisteredServiceProvider;

import pl.kresrek007.tools.Main;
import net.milkbowl.vault.chat.Chat;

public class ChatUtils {
	private static boolean enabled = true;
	
	private static Chat chat = null;
	
	public static boolean isEnabled(){
		return enabled;
	}
	
	public static void setEnabled(boolean e){
		enabled = e;
	}
		
    public static void setup()
    {
        RegisteredServiceProvider<Chat> chatProvider = Main.getInstance().getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }
    }
    
	public static Chat getChat()
	{
		return chat;
	}
}

/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.tasks;

import org.bukkit.scheduler.BukkitRunnable;

import pl.kresrek007.tools.data.Config;
import pl.kresrek007.tools.utils.Util;

public class AutoMessageTask extends BukkitRunnable{

    private int index = 0;
    
    public void run() {
        if (this.index >= Config.autoMessage_messages.size()) this.index = 0;        
        Util.broadcast(Config.autoMessage_prefix + Config.autoMessage_messages.get(this.index));        
        ++this.index;
    }
}

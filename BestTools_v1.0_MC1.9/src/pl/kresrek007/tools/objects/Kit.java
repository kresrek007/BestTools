/** 
 * 
 * AUTHOR: Kresrek007
 * VERSION: v 1.0 BETA
 * 
 */

package pl.kresrek007.tools.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public class Kit {
	private int cooldown;
	private final String name;
	private String permissions;
	private List<ItemStack> items = new ArrayList<ItemStack>();		
	
	public Kit(String name){
		this.name = name;
	}
	
	public void setCooldown(int cooldown){
		this.cooldown = cooldown;
	}
	
	public int getCooldown(){
		return cooldown;
	}
	
	public String getName(){
		return name;
	}
	
	public void setPermissions(String permissions){
		this.permissions = permissions;
	}
	
	public String getPermissions(){
		return permissions;
	}
	
	public void setItems(List<ItemStack> items){
		this.items = items;
	}
	
	public List<ItemStack> getItems(){
		return items;
	}
	
}


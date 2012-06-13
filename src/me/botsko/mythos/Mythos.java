package me.botsko.mythos;

import java.util.logging.Logger;

import me.botsko.mythos.listeners.MythosBlockBreakEvent;
import me.botsko.mythos.listeners.MythosEntityDeathEvent;
import me.botsko.mythos.listeners.MythosPlayerInteractEntityEvent;
import me.botsko.mythos.listeners.MythosPlayerInteractEvent;
import me.botsko.mythos.registry.Registry;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Mythos extends JavaPlugin {

	protected Logger log = Logger.getLogger("Minecraft");
	protected FileConfiguration config;
	protected FileConfiguration lang;
	protected Registry dr;
	
	
    /**
     * Enables the plugin and activates our player listeners
     */
	@Override
	public void onEnable(){
		
		this.log("Initializing plugin.");
		
		// Load configuration, or install if new
		MythosConfig mc = new MythosConfig( this );
		config = mc.getConfig();
		
		// Load language files
		lang = mc.getLang();
		
		// Load spell/curse/artifact registry
		this.dr = new Registry();
		
		// Assign event listeners
		getServer().getPluginManager().registerEvents(new MythosBlockBreakEvent( this ), this);
//		getServer().getPluginManager().registerEvents(new MythosPlayerPickupItemEvent( this ), this);
		getServer().getPluginManager().registerEvents(new MythosPlayerInteractEvent( this ), this);
		getServer().getPluginManager().registerEvents(new MythosPlayerInteractEntityEvent( this ), this);
		getServer().getPluginManager().registerEvents(new MythosEntityDeathEvent( this ), this);
		
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Registry getRegistry(){
		return this.dr;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public FileConfiguration getLang(){
		return this.lang;
	}
	
	
	/**
	 * 
	 * @param msg
	 * @return
	 */
	public String playerMsg(String msg){
		if(msg != null){
			return ChatColor.GOLD + "[Mythos]: " + ChatColor.WHITE + msg;
		}
		return "";
	}
	
	
	/**
	 * 
	 * @param msg
	 * @return
	 */
	public String playerError(String msg){
		if(msg != null){
			return ChatColor.GOLD + "[Mythos]: " + ChatColor.RED + msg;
		}
		return "";
	}
	
	
	/**
	 * 
	 * @param player
	 * @param msg
	 */
	public void notifyNearby( Player player, String msg ) {
        for (Player p : player.getServer().getOnlinePlayers()) {
        	if( !p.equals( player ) ){
	        	if(player.getLocation().distance( p.getLocation() ) <= getConfig().getInt("mythos.notify_radius")){
	                p.sendMessage(playerMsg(msg));
	        	}
        	}
        }
    }
	
	
	/**
	 * 
	 * @param message
	 */
	public void log(String message){
		log.info("[Mythos]: " + message);
	}
	
	
	/**
	 * 
	 * @param message
	 */
	public void debug(String message){
		if(this.getConfig().getBoolean("debug")){
			log.info("[Mythos]: " + message);
		}
	}
	
	
	/**
	 * Shutdown
	 */
	@Override
	public void onDisable(){
		this.log("Closing plugin.");
	}	
}

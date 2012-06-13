package me.botsko.mythos;

import java.util.logging.Logger;

import me.botsko.mythos.listeners.MythosBlockBreakEvent;
import me.botsko.mythos.listeners.MythosEntityDeathEvent;
import me.botsko.mythos.listeners.MythosPlayerInteractEntityEvent;
import me.botsko.mythos.listeners.MythosPlayerInteractEvent;
import me.botsko.mythos.registry.Registry;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Mythos extends JavaPlugin {

	protected Logger log = Logger.getLogger("Minecraft");
	protected FileConfiguration config;
	protected Registry dr;
	
	
    /**
     * Enables the plugin and activates our player listeners
     */
	@Override
	public void onEnable(){
		
		this.log("Initializing plugin.");
		
		// Load configuration, or install if new
		config = MythosConfig.init( this );
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
	 * @param msg
	 * @return
	 */
	public String playerMsg(String msg){
		return ChatColor.GOLD + "[Mythos]: " + ChatColor.WHITE + msg;
	}
	
	
	/**
	 * 
	 * @param msg
	 * @return
	 */
	public String playerError(String msg){
		return ChatColor.GOLD + "[Mythos]: " + ChatColor.RED + msg;
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

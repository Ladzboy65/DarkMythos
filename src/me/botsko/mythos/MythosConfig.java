package me.botsko.mythos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MythosConfig {
	
	protected Mythos plugin;
	
	
	/**
	 * 
	 * @param plugin
	 */
	public MythosConfig( Mythos plugin ) {
		this.plugin = plugin;
	}
	
	
	/**
	 * 
	 * @param plugin
	 */
	public FileConfiguration getConfig(){
		
		FileConfiguration config = plugin.getConfig();
		
		// set defaults
		config.addDefault("mythos.debug", false);
		config.addDefault("mythos.curse_chance_range", 10);
		config.addDefault("mythos.spell_chance_range", 500);
		config.addDefault("mythos.artifact_chance_range", 1000);
		config.addDefault("mythos.notify_radius", 50);
		
		// Copy defaults
		config.options().copyDefaults(true);
		
		// save the defaults/config
		plugin.saveConfig();
		
		return config;
		
	}
	
	
	/**
	 * Loads language configuration
	 * @return
	 */
	public FileConfiguration getLang(){
		
		String lang_file = "en-us"; // @todo will be a config someday
		
		// Read the base config
		FileConfiguration config = loadConfig( "languages/", lang_file );
		
		// Add defaults
		config.addDefault("spells.botanicalmaturity","Test");
		
		// copy defaults and save config
		config.options().copyDefaults(true);
		write( lang_file, config );
		
		return config;
		
	}
	
	
	/**
	 * Returns base directory for config
	 * @return
	 */
	protected File getDirectory(){
		File dir = new File(plugin.getDataFolder()+"");
		return dir;
	}
	
	
	/**
	 * Returns chosen filename with directory
	 * @return
	 */
	protected File getFilename( String filename ){
		File file = new File(getDirectory(), filename + ".yml");
		return file;
	}
	
	
	/**
	 * 
	 * @param player
	 * @return
	 */
	protected FileConfiguration loadConfig( String default_folder, String filename ){
		File file = getFilename( filename );
		if(file.exists()){
			return YamlConfiguration.loadConfiguration(file);
		} else {
			// Look for defaults in the jar
		    InputStream defConfigStream = plugin.getResource(default_folder+filename+".yml");
		    if (defConfigStream != null) {
		        return YamlConfiguration.loadConfiguration(defConfigStream);
		    }
		    return null;
		}
	}
	
	
	/**
	 * 
	 * @param config
	 */
	protected void saveConfig( String filename, FileConfiguration config ){
		File file = getFilename( filename );
		try {
			config.save(file);
		} catch (IOException e) {
			plugin.log("Could not save the configuration file to "+file);
		}
	}
	
	
	/**
	 * 
	 */
	protected void write( String filename, FileConfiguration config ){
		try {
			BufferedWriter bw = new BufferedWriter( new FileWriter( getFilename( filename ), true ) );
			saveConfig( filename, config );
			bw.flush();
			bw.close();
		} catch (IOException e){
//            log.info("[ServerNews] + '" + getDataFolder().getPath() + "/player_toggle.yml' not found.");
        }
	}
}

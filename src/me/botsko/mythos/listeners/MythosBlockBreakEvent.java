package me.botsko.mythos.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.botsko.mythos.Mythos;
import me.botsko.mythos.artifacts.ArtifactBase;
import me.botsko.mythos.registry.Registry;
import me.botsko.mythos.spells.SpellBase;


public class MythosBlockBreakEvent implements Listener {
	
	private Mythos plugin;
	private Registry dr;
	
	/**
	 * 
	 * @param plugin
	 */
	public MythosBlockBreakEvent( Mythos plugin ){
		this.plugin = plugin;
		this.dr = plugin.getRegistry();
	}

	
	/**
	 * Award the player when blocks break if the chosen spell supports it.
	 * @param event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(final BlockBreakEvent event){
		
		Block block = event.getBlock();
	
		SpellBase award = (SpellBase) dr.chooseRandom( plugin.getConfig().getInt("mythos.spell_chance_range"), dr.getSpells( block ) );
		if(award != null){
			
			// Get the block break award
			if( award.getBlockBreakAward(event) ){
			
				// Message the player
				Player player = event.getPlayer();
				player.sendMessage( plugin.playerMsg( award.getAwardMessage() ));
				
			}
		} else {
			
			// If no award was given we have the possibility of an artifact
			ArtifactBase artifact = (ArtifactBase) dr.chooseRandom( plugin.getConfig().getInt("mythos.artifact_chance_range"), dr.getArtifacts( block ) );
			if(artifact != null){
				
				// Get the block break award
				if( artifact.getBlockBreakAward(event) ){
				
					// Message the player
					Player player = event.getPlayer();
					player.sendMessage( plugin.playerMsg( artifact.getAwardMessage() ));
					
				}
			}
		}
	}
}

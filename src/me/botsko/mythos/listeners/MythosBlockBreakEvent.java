package me.botsko.mythos.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.botsko.mythos.Mythos;
import me.botsko.mythos.artifacts.ArtifactBase;
import me.botsko.mythos.artifacts.ArtifactChoice;
import me.botsko.mythos.directory.Directory;
import me.botsko.mythos.spells.SpellBase;
import me.botsko.mythos.spells.SpellChoice;


public class MythosBlockBreakEvent implements Listener {
	
	private Mythos plugin;
	private SpellChoice sc;
	private ArtifactChoice ac;
	private Directory dr;
	
	/**
	 * 
	 * @param plugin
	 */
	public MythosBlockBreakEvent( Mythos plugin ){
		this.plugin = plugin;
		this.sc = new SpellChoice( plugin );
		this.ac = new ArtifactChoice( plugin );
		this.dr = new Directory();
	}

	
	/**
	 * Award the player when blocks break if the chosen spell supports it.
	 * @param event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(final BlockBreakEvent event){
		
		Block block = event.getBlock();
	
		SpellBase award = sc.chooseRandomSpell( dr.getSpells( block ) );
		if(award != null){
			
			// Get the block break award
			if( award.getBlockBreakAward(event) ){
			
				// Message the player
				Player player = event.getPlayer();
				player.sendMessage( plugin.playerMsg( award.getAwardMessage() ));
				
			}
		} else {
			
			// If no award was given we have the possibility of an artifact
			ArtifactBase artifact = ac.chooseRandomArtifact( dr.getArtifacts( block ) );
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

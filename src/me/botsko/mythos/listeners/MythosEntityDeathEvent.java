package me.botsko.mythos.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import me.botsko.mythos.Mythos;
import me.botsko.mythos.directory.Directory;
import me.botsko.mythos.spells.SpellBase;
import me.botsko.mythos.spells.SpellChoice;


public class MythosEntityDeathEvent implements Listener {
	
	private Mythos plugin;
	private SpellChoice sc;
//	private ArtifactChoice ac;
	private Directory dr;
	
	/**
	 * 
	 * @param plugin
	 */
	public MythosEntityDeathEvent( Mythos plugin ){
		this.plugin = plugin;
		this.sc = new SpellChoice( plugin );
//		this.ac = new ArtifactChoice( plugin );
		this.dr = new Directory();
	}

	
	/**
	 * Award the player when blocks break if the chosen spell supports it.
	 * @param event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDeath(final EntityDeathEvent event) {
		
		Entity entity = event.getEntity();
		if(!(entity instanceof Player)){
			if(entity.getLastDamageCause() instanceof EntityDamageByEntityEvent){
				
				EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) entity.getLastDamageCause();
				if(entityDamageByEntityEvent.getDamager() instanceof Player){
					
					Player player = (Player)entityDamageByEntityEvent.getDamager();

					SpellBase award = sc.chooseRandomSpell( dr.getSpells( entity ) );
					if(award != null){
						
						// Get the block break award
						if( award.getEntityDeathAward(event) ){
			
							// Message the player
							player.sendMessage( plugin.playerMsg( award.getAwardMessage() ));
							
						}
					} else {
						
//						// If no award was given we have the possibility of an artifact
//						ArtifactBase artifact = ac.chooseRandomArtifact();
//						if(artifact != null){
//							
//							// Get the block break award
//							if( artifact.getEntityDeathAward(event) ){
//							
//								// Message the player
//								Player player = event.getPlayer();
//								player.sendMessage( plugin.playerMsg( artifact.getAwardMessage() ));
//								
//							}
//						}
					}
				}
			}
		}
	}
}
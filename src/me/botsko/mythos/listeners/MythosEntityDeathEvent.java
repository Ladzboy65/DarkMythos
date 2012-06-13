package me.botsko.mythos.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import me.botsko.mythos.Mythos;
import me.botsko.mythos.registry.Registry;
import me.botsko.mythos.spells.SpellBase;


public class MythosEntityDeathEvent implements Listener {
	
	private Mythos plugin;
	private Registry dr;
	
	/**
	 * 
	 * @param plugin
	 */
	public MythosEntityDeathEvent( Mythos plugin ){
		this.plugin = plugin;
		this.dr = plugin.getRegistry();
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

					SpellBase spell = (SpellBase) dr.chooseRandom( plugin.getConfig().getInt("mythos.spell_chance_range"), dr.getSpells( entity ) );
					if(spell != null){
						
						// Get the block break award
						if( spell.getEntityDeathAward(event) ){
			
							// Message the player
							player.sendMessage( plugin.playerMsg( plugin.getLang().getString("spells." + spell.getLangClassname() + ".awarded" ) ));
							
							// Notify nearby players
							String msg = plugin.getLang().getString("spells." + spell.getLangClassname() + ".nearby" ).replace("{player}", player.getName());
							plugin.notifyNearby( player, msg );
							
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

package me.botsko.mythos.listeners;

import me.botsko.mythos.Mythos;
import me.botsko.mythos.curses.CurseBase;

import me.botsko.mythos.registry.Registry;
import me.botsko.mythos.spells.SpellBase;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class MythosPlayerInteractEntityEvent implements Listener {
	
	private Mythos plugin;
	private Registry dr;
	
	/**
	 * 
	 * @param plugin
	 */
	public MythosPlayerInteractEntityEvent( Mythos plugin ){
		this.plugin = plugin;
		this.dr = plugin.getRegistry();
	}
	
	
	@EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteractEntityEvent(final PlayerInteractEntityEvent event){
		
		Player player = event.getPlayer();
		
		// Ensure they're right-clicking
		if (event.getRightClicked() != null) {
			
			// Ensure they're using a book
			if(player.getItemInHand().getType() == Material.BOOK){
				
				// Use the durability to find the award id
				SpellBase spell = dr.chooseSpell( dr.getSpells( event.getRightClicked() ), player.getItemInHand().getDurability() );
				if(spell != null){
					
					spell.setPlayer( player );
					
					// If the item is cursed, apply the curse and skip using it
					CurseBase curse = dr.chooseRandomCurse( plugin.getConfig().getInt("mythos.curse_chance_range"), dr.getCurses(), spell );
					if(curse == null){
					
						// Get the block break award
						if( spell.useSpellPlayerEntityInteract(event) ){
						
							// Message the player
							player.sendMessage( plugin.playerMsg( spell.getSpellUseMessage() ));
							
						}
					} else {
						
						curse.applyCurse(player);
						
						// Tell them about the curse
						player.sendMessage( plugin.playerMsg( curse.getMessage() ));
						
					}
				}
			}
		}
	}
}

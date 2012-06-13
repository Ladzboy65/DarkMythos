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
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class MythosPlayerInteractEvent implements Listener {
	
	private Mythos plugin;
	private Registry dr;
	
	/**
	 * 
	 * @param plugin
	 */
	public MythosPlayerInteractEvent( Mythos plugin ){
		this.plugin = plugin;
		this.dr = plugin.getRegistry();
	}
	
	
	@EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteractEvent(final PlayerInteractEvent event){
		
		Player player = event.getPlayer();
		
		// Ensure they're right-clicking
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			// Ensure they're using a book
			if(player.getItemInHand().getType() == Material.BOOK){
				
				// Use the durability to find the award id
				SpellBase spell = dr.chooseSpell( dr.getSpells( event.getClickedBlock() ), player.getItemInHand().getDurability() );
				if(spell != null){
					
					spell.setPlayer( player );
					
					// If the item is cursed, apply the curse and skip using it
					CurseBase curse = dr.chooseRandomCurse( plugin.getConfig().getInt("mythos.curse_chance_range"), dr.getCurses(), spell );
					if(curse == null){
					
						// Get the block break award
						if( spell.useSpellPlayerInteract(event) ){
						
							// Message the player
							player.sendMessage( plugin.playerMsg( plugin.getLang().getString("spells." + spell.getLangClassname() + ".used" ) ));
							
						}
					} else {
						
						curse.applyCurse(player);
						
						// Tell them about the curse
						player.sendMessage( plugin.playerMsg( plugin.getLang().getString("curses." + curse.getLangClassname() + ".applied" ) ));
						
					}
				}
			}
		}
	}
}

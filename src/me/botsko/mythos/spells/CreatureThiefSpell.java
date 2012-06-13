package me.botsko.mythos.spells;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class CreatureThiefSpell extends SpellBase implements Spell {

	
	/**
	 * 
	 * @return
	 */
	@Override
	public short getSpellId(){
		return 7;
	}
	
	
	/**
	 * Returns the weighting of the award
	 */
	@Override
	public int getWeight(){
		return 10;
	}
	
	
	/**
	 * 
	 * @param block
	 * @return
	 */
	public boolean isAwardedOn( Entity entity ){
		return true;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getAwardMessage(){
		return "You discovered a magical book: Artemis' Creature Thief";
	}
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getSpellUseMessage(){
		return "Used spell Creature Thief! Spell book consumed.";
	}
	
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	@Override
	public boolean useSpellPlayerEntityInteract(PlayerInteractEntityEvent event){
		
		Entity e = event.getRightClicked();
		
		if(e != null){
		
			// Determine egg
			ItemStack egg = new ItemStack( 383, 1 );
			egg.setDurability( e.getType().getTypeId() );
			player.getInventory().addItem(egg);
			
			// Remove mob
			e.remove();
			
			// Give egg to player
			player.updateInventory();
			MythosUtil.subtractFromHand( player );
	
			return true;
		}
		return false;
	}
}
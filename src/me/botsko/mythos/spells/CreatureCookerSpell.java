package me.botsko.mythos.spells;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class CreatureCookerSpell extends SpellBase implements Spell {

	
	/**
	 * 
	 * @return
	 */
	@Override
	public short getSpellId(){
		return 12;
	}
	
	
	/**
	 * Returns the weighting of the award
	 */
	@Override
	public int getWeight(){
		return 70;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getAwardMessage(){
		return "You discovered a magical book: Artemis' Creature Cooker";
	}
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getSpellUseMessage(){
		return "Used spell Creature Cooker! Spell book consumed.";
	}
	
	
	/**
	 * 
	 */
	@Override
	public boolean getBlockBreakAward(BlockBreakEvent event){
		
		block = event.getBlock();
		if( block.getType() == Material.GRASS || block.getType() == Material.DIRT ){
			
			// Set item
			ItemStack i = new ItemStack(Material.BOOK, 1);
			i.setDurability( getSpellId() );
			
			// Drop the item
			block.getWorld().dropItemNaturally(block.getLocation(), i);
			
			// Boom!
			MythosUtil.awardThunder( block );

			return true;
			
		}
		return false;
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
			
			int quant = 1;
		
			// Determine meat
			EntityType animal = e.getType();
			switch( animal ){
				case CHICKEN:
					player.getInventory().addItem( new ItemStack( Material.COOKED_CHICKEN, quant ) );
					break;
				case PIG:
					player.getInventory().addItem( new ItemStack( Material.PORK, quant ) );
					break;
				case COW:
				case MUSHROOM_COW:
					player.getInventory().addItem( new ItemStack( Material.COOKED_BEEF, quant ) );
					break;
			}
			
			// Remove mob
			e.remove();
			
			player.updateInventory();
			MythosUtil.subtractFromHand( player );
	
			return true;
		}
		return false;
	}
}
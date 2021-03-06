package me.botsko.mythos.spells;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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
	 * @param block
	 * @return
	 */
	public boolean isAwardedOn( Entity entity ){
		EntityType type = entity.getType();
		return (type == EntityType.PIG || type == EntityType.COW || type == EntityType.MUSHROOM_COW || type == EntityType.CHICKEN);
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
					player.getInventory().addItem( new ItemStack( Material.GRILLED_PORK, quant ) );
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
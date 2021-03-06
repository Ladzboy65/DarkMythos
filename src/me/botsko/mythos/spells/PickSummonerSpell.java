package me.botsko.mythos.spells;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PickSummonerSpell extends SpellBase implements Spell {

	
	/**
	 * 
	 * @return
	 */
	@Override
	public short getSpellId(){
		return 6;
	}
	
	
	/**
	 * Returns the weighting of the award
	 */
	@Override
	public int getWeight(){
		return 61;
	}
	
	
	/**
	 * 
	 * @param block
	 * @return
	 */
	public boolean isAwardedOn( Block block ){
		return ( block.getType() == Material.IRON_ORE || block.getType() == Material.DIAMOND_ORE || block.getType() == Material.GOLD_ORE );
	}
	
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	@Override
	public boolean useSpellPlayerInteract(PlayerInteractEvent event){
		
		Block currBlock = event.getClickedBlock();
		if( currBlock.getType() == Material.IRON_ORE ){
			player.getInventory().addItem( new ItemStack( Material.IRON_PICKAXE, 1 ) );
		}
		if( currBlock.getType() == Material.GOLD_ORE ){
			player.getInventory().addItem( new ItemStack( Material.GOLD_PICKAXE, 1 ) );
		}
		if( currBlock.getType() == Material.DIAMOND_ORE ){
			player.getInventory().addItem( new ItemStack( Material.DIAMOND_PICKAXE, 1 ) );
		}

		player.updateInventory();
		MythosUtil.subtractFromHand( player );
		return true;
	}
}
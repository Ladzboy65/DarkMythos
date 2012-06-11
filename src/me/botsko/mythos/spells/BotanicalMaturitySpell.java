package me.botsko.mythos.spells;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BotanicalMaturitySpell extends SpellBase implements Spell {

	
	/**
	 * 
	 * @return
	 */
	@Override
	public short getSpellId(){
		return 1;
	}
	
	
	/**
	 * Returns the weighting of the award
	 */
	@Override
	public int getWeight(){
		return 80;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getAwardMessage(){
		return "You discovered a magical book: Demeter's Spell of Botanical Maturity";
	}
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getSpellUseMessage(){
		return "Used spell Botanical Maturity! Spell book consumed.";
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
	public boolean useSpellPlayerInteract(PlayerInteractEvent event, Player player){
		
		Block currBlock = event.getClickedBlock();
		if(currBlock.getType() == Material.SAPLING){
			
			// Determine the tree type
			TreeType t;
			switch(currBlock.getData()){
				case 1:
					t = TreeType.REDWOOD;
					break;
				case 2:
					t = TreeType.BIRCH;
					break;
				case 3:
					t = TreeType.JUNGLE;
					break;
				default:
					t = TreeType.TREE;
			}
			
			currBlock.setType(Material.AIR);
			currBlock.getWorld().generateTree(currBlock.getLocation(), t);
			MythosUtil.subtractFromHand( player );
			return true;

		}
		if(currBlock.getType() == Material.CROPS || currBlock.getType() == Material.MELON_STEM || currBlock.getType() == Material.PUMPKIN_STEM){
			// Only if not already grown
			if(currBlock.getData() < 7){
				currBlock.setData((byte) 7);
				MythosUtil.subtractFromHand( player );
			}
		}
		return false;
	}
}
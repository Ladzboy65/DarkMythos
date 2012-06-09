package me.botsko.mythos.spells;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SkeletonSummonerSpell extends SpellBase implements Spell {

	
	/**
	 * 
	 * @return
	 */
	public short getSpellId(){
		return 10;
	}
	/**
	 * Returns the weighting of the award
	 */
	public int getWeight(){
		return 4;
}
	
	
	/**
	 * 
	 * @return
	 */
	public String getAwardMessage(){
		return "You have discovered a magical spell: Skeleton Summoner!";
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getSpellUseMessage(){
		return "Used spell Skeleton Summoner! Spell books consumed.";
	}
	
	
	/**
	 * 
	 */
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
	public boolean useSpellPlayerInteract(PlayerInteractEvent event, Player player){
	Block currBlock = event.getClickedBlock();
	World world = player.getWorld();
	world.spawn(currBlock.getLocation(), Skeleton.class);
	MythosUtil.subtractFromHand( player );
	return true;
}
	}


package me.botsko.mythos.artifacts;

import java.util.Random;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class ApollosSwordArtifact extends ArtifactBase implements Artifact {
	
	
	/**
	 * Returns the weighting of the award
	 */
	@Override
	public int getWeight(){
		return 50;
	}
	
	
	/**
	 * 
	 * @param block
	 * @return
	 */
	public boolean isAwardedOn( Block block ){
		return ( block.getType() == Material.DIAMOND_ORE );
	}
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getAwardMessage(){
		return "You've just discovered a Mighty Sword of Apollo!";
	}
	
	
	/**
	 * 
	 */
	public String getNotifyNearbyMessage( String player_name ){
		return player_name + " just discovered a Mighty Sword of Apollo!";
	}
	
	
	/**
	 * 
	 */
	@Override
	public boolean getBlockBreakAward(BlockBreakEvent event){
		
		block = event.getBlock();
		if( isAwardedOn(block) ){
			
			// Set item
			ItemStack i = new ItemStack(Material.DIAMOND_SWORD, 1);

			// Randomize durability ;)
			Random r = new Random();
			i.setDurability( (short) r.nextInt( i.getType().getMaxDurability() ) );
			
			// Drop the item
			block.getWorld().dropItemNaturally(block.getLocation(), i);
			
			// Boom!
			MythosUtil.awardThunder( block );

			return true;
			
		}
		return false;
	}
}
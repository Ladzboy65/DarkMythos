package me.botsko.mythos.artifacts;

import java.util.Random;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class ArtemisBowArtifact extends ArtifactBase implements Artifact {


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
		return ( block.getType() == Material.LAPIS_ORE );
	}


	/**
	 * 
	 */
	@Override
	public boolean getBlockBreakAward(BlockBreakEvent event){

		block = event.getBlock();
		if( isAwardedOn(block) ){

			// Set item
			ItemStack i = new ItemStack(Material.BOW, 1);
			i.addEnchantment(Enchantment.ARROW_FIRE, 1);
			ItemStack a = new ItemStack(Material.ARROW, 10);

			// Randomize durability ;)
			Random r = new Random();
			i.setDurability( (short) r.nextInt( i.getType().getMaxDurability() ) );

			// Drop the item
			block.getWorld().dropItemNaturally(block.getLocation(), i);
			block.getWorld().dropItemNaturally(block.getLocation(), a);

			// Boom!
			MythosUtil.awardThunder( block );

			return true;

		}
		return false;
	}
}
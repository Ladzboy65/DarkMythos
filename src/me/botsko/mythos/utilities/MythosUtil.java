package me.botsko.mythos.utilities;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MythosUtil {

	
	/**
	 * Causes lightning on award discovery
	 * @param block
	 */
	public static void awardThunder( Block block ){
		Location loc = block.getLocation();
		loc.setY(350D);
		block.getWorld().strikeLightning(loc);
	}
	
	
	/**
	 * 
	 * @param player
	 */
	public static void subtractFromHand(Player player){
		ItemStack in_hand = player.getInventory().getItemInHand();
		removeItem(player, in_hand);
	}
	
	
	/**
	 * 
	 * @param player
	 * @param i
	 */
	public static void removeItem( Player player, ItemStack i ){
		if(i.getAmount() == 1){
			player.getInventory().remove(i);
		} else {
			i.setAmount( i.getAmount() - 1 );
		}
		player.updateInventory();
	}
	
	
	/**
	 * Drop items at a given location.
	 *
	 * @param location The location to drop the items at
	 * @param is The items to drop
	 * @param quantity The amount of items to drop
	 */
    public static void dropItems(Location location, ItemStack is, int quantity) {
        for (int i = 0; i < quantity; i++) {
            dropItem(location, is);
        }
    }
	
	
	/**
	 * Randomly drop an item at a given location.
	 *
	 * @param location The location to drop the items at
	 * @param is The item to drop
	 * @param chance The percentage chance for the item to drop
	 */
    public static void randomDropItem(Location location, ItemStack is, double chance) {
    	Random random = new Random();
        if (random.nextInt(100) < chance) {
            dropItem(location, is);
        }
    }
	    
	    
    /**
     * Drop an item at a given location.
     *
     * @param location The location to drop the item at
     * @param itemStack The item to drop
     */
    public static void dropItem(Location location, ItemStack itemStack) {
        location.getWorld().dropItemNaturally(location, itemStack);
    }
}
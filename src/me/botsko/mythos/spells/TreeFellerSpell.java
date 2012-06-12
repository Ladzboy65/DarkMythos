package me.botsko.mythos.spells;

import java.util.ArrayList;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Tree;


/**
 * 
 * @author botskonet
 *
 */
public class TreeFellerSpell extends SpellBase {
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public short getSpellId(){
		return 11;
	}
	
	
	/**
	 * Returns the weighting of the award
	 */
	@Override
	public int getWeight(){
		return 50;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getAwardMessage(){
		return "You discovered a magical book: Demeter's Tree Feller";
	}
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getSpellUseMessage(){
		return "Used spell Tree Feller! Spell book consumed.";
	}
	
	
	/**
	 * 
	 */
	@Override
	public boolean getBlockBreakAward(BlockBreakEvent event){
		
		block = event.getBlock();
		if( block.getType() == Material.LOG || block.getType() == Material.LEAVES ){
			
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
	 * Handle the Tree Feller ability.
	 *
	 * @param event Event to modify
	 */
    public boolean useSpellPlayerInteract(PlayerInteractEvent event) {
    	
    	Block currBlock = event.getClickedBlock();
    	if(currBlock.getType() == Material.LOG){
    	
	        Player player = event.getPlayer();
	        Block firstBlock = event.getClickedBlock();
	        ArrayList<Block> toBeFelled = new ArrayList<Block>();
	
	        processTreeFelling(firstBlock, toBeFelled);
	        removeBlocks(toBeFelled, player);
	        
	        MythosUtil.subtractFromHand( player );
	        
	        return true;
    	}
    	return false;
    }
	
    
	/**
	 * Handles removing & dropping the blocks from Tree Feller.
	 *
	 * @param toBeFelled List of Blocks to be removed from the tree
	 * @param player The player using the ability
	 * @param PP The PlayerProfile of the player
	 */
    private static void removeBlocks(ArrayList<Block> toBeFelled, Player player) {

        //Prepare ItemStacks
        ItemStack item = null;
        ItemStack oak = new ItemStack(Material.LOG, 1, (short) 0, TreeSpecies.GENERIC.getData());
        ItemStack spruce = new ItemStack(Material.LOG, 1, (short) 0, TreeSpecies.REDWOOD.getData());
        ItemStack birch = new ItemStack(Material.LOG, 1, (short) 0, TreeSpecies.BIRCH.getData());
        ItemStack jungle = new ItemStack(Material.LOG, 1, (short) 0, TreeSpecies.JUNGLE.getData());

        for (Block x : toBeFelled) {
            if (x.getType() == Material.LOG) {
                Tree tree = (Tree) x.getState().getData();
                TreeSpecies species = tree.getSpecies();

                switch (species) {
                case GENERIC:
                    item = oak;
                    break;

                case REDWOOD:
                    item = spruce;
                    break;

                case BIRCH:
                    item = birch;
                    break;

                case JUNGLE:
                    item = jungle;
                    break;

                default:
                    break;
                }


                /* Remove the block */
                x.setData((byte) 0x0);
                x.setType(Material.AIR);

                /* Drop the block */
                MythosUtil.dropItem(x.getLocation(), item);
            }
            else if (x.getType() == Material.LEAVES) {
                final int SAPLING_DROP_CHANCE = 10;

                item = new ItemStack(Material.SAPLING, 1, (short) 0, (byte) (x.getData() & 3)); //Drop the right type of sapling
                MythosUtil.randomDropItem(x.getLocation(), item, SAPLING_DROP_CHANCE);

                //Remove the block
                x.setData((byte) 0);
                x.setType(Material.AIR);
            }
        }
    }
    
    
	/**
	 *    
	 * @param currentBlock
	 * @param toBeFelled
	 */
    private static void processTreeFelling(Block currentBlock, ArrayList<Block> toBeFelled) {
    	
        Material type = currentBlock.getType();
        if (type.equals(Material.LOG) || type.equals(Material.LEAVES)) {
            toBeFelled.add(currentBlock);
        }

        Block xPositive = currentBlock.getRelative(1, 0, 0);
        Block xNegative = currentBlock.getRelative(-1, 0, 0);
        Block zPositive = currentBlock.getRelative(0, 0, 1);
        Block zNegative = currentBlock.getRelative(0, 0, -1);
        Block yPositive = currentBlock.getRelative(0, 1, 0);

        
        if (!isTooAggressive(currentBlock, xPositive) && treeFellerCompatible(xPositive) && !toBeFelled.contains(xPositive)) {
            processTreeFelling(xPositive, toBeFelled);
        }

        if (!isTooAggressive(currentBlock, xNegative) && treeFellerCompatible(xNegative) && !toBeFelled.contains(xNegative)) {
            processTreeFelling(xNegative, toBeFelled);
        }

        if (!isTooAggressive(currentBlock, zPositive) && treeFellerCompatible(zPositive) && !toBeFelled.contains(zPositive)) {
            processTreeFelling(zPositive, toBeFelled);
        }

        if (!isTooAggressive(currentBlock, zNegative) && treeFellerCompatible(zNegative) && !toBeFelled.contains(zNegative)) {
            processTreeFelling(zNegative, toBeFelled);
        }
        
        if (treeFellerCompatible(yPositive)) {
            if(!toBeFelled.contains(yPositive)) {
                processTreeFelling(yPositive, toBeFelled);
            }
        }
    }
	        
	        
    /**
     * Check if Tree Feller is being too aggressive.
     *
     * @param currentBlock The current block being felled
     * @param newBlock The next block to be felled
     * @return true if Tree Feller is too aggressive, false otherwise
     */
    private static boolean isTooAggressive(Block currentBlock, Block newBlock) {
        Material currentType = currentBlock.getType();
        Material newType = newBlock.getType();

        if ((currentType.equals(Material.LEAVES) || currentType.equals(Material.AIR)) && (newType.equals(Material.LEAVES) || newType.equals(Material.AIR))) {
            return true;
        }
        else {
            return false;
        }
    }
	            

    /**
     * Checks if the block is affected by Tree Feller.
     *
     * @param block Block to check
     * @return true if the block is affected by Tree Feller, false otherwise
     */
    public static boolean treeFellerCompatible(Block block) {
        switch (block.getType()) {
        case LOG:
        case LEAVES:
        case AIR:
            return true;
        default:
            return false;
        }
    }

    
    /**
     * 
     * @param block
     * @return
     */
    public static boolean isLog (Block block){
        if (block.getType().equals(Material.LOG)) {
            return true;
        }
        return false;
    }
}

package me.botsko.mythos.spells;

import java.util.ArrayList;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;

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
	 * @param block
	 * @return
	 */
	public boolean isAwardedOn( Block block ){
		return ( block.getType() == Material.GRASS || block.getType() == Material.DIRT );
	}
	
	
	/**
	 * 
	 * @param block
	 * @return
	 */
	public boolean isUsedOn( Block block ){
		return ( block.getType() == Material.SAPLING || block.getType() == Material.CROPS || block.getType() == Material.MELON_STEM || block.getType() == Material.PUMPKIN_STEM );
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
	public String getNotifyNearbyMessage( String player_name ){
		return player_name + " discovered a magical book: Demeter's Spell of Botanical Maturity";
	}
	
	
	/**
	 * 
	 * @return
	 */
	public double getCurseAmplifier(){
		if(modifier != null){
			if(modifier.getMaterial() == Material.DIAMOND_BLOCK){
				return 0.80;
			}
		}
		return 0;
	}
	
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	@Override
	public boolean useSpellPlayerInteract(PlayerInteractEvent event){
		
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
			
			ArrayList<Block> matchingBlocks = new ArrayList<Block>();
			
			// Check for a spell modifier
			if(playerHasModifier( Material.DIAMOND_BLOCK, 1 )){

				// identify all crop blocks on same Y axis in x/z direction
				findNeighborBlocks( currBlock, matchingBlocks );
			
			} else {
				
				// Just add the one
				matchingBlocks.add(currBlock);
				
			}
			
			// grow the neighboring blocks
			if( !matchingBlocks.isEmpty() ){
				growCrops( matchingBlocks );
			}
			
			// Remove spell book
			MythosUtil.subtractFromHand( player );
			
		}
		return false;
	}
	
	
	/**
	 *    
	 * @param currBlock
	 * @param toBeFelled
	 */
    private void findNeighborBlocks( Block currBlock, ArrayList<Block> matchingBlocks ) {

        Material type = currBlock.getType();
        if(type == Material.CROPS || type == Material.MELON_STEM || type == Material.PUMPKIN_STEM){
        	
        	matchingBlocks.add(currBlock);
        	
        	Block xPositive = currBlock.getRelative(1, 0, 0);
            Block xNegative = currBlock.getRelative(-1, 0, 0);
            Block zPositive = currBlock.getRelative(0, 0, 1);
            Block zNegative = currBlock.getRelative(0, 0, -1);

        	if ( !matchingBlocks.contains(xPositive) ) {
        		findNeighborBlocks( xPositive, matchingBlocks );
        	}
        	if ( !matchingBlocks.contains(xNegative) ) {
        		findNeighborBlocks( xNegative, matchingBlocks );
        	}
        	if ( !matchingBlocks.contains(zPositive) ) {
        		findNeighborBlocks( zPositive, matchingBlocks );
        	}
        	if ( !matchingBlocks.contains(zNegative) ) {
        		findNeighborBlocks( zNegative, matchingBlocks );
        	}
        }
    }
    
    
    /**
	 * Handles removing & dropping the blocks from Tree Feller.
	 *
	 * @param toBeFelled List of Blocks to be removed from the tree
	 * @param player The player using the ability
	 * @param PP The PlayerProfile of the player
	 */
    private void growCrops( ArrayList<Block> matchingBlocks ) {

        for (Block currBlock : matchingBlocks) {
        	Material type = currBlock.getType();
            if(type == Material.CROPS || type == Material.MELON_STEM || type == Material.PUMPKIN_STEM){
              
            	// Only if not already grown
    			if(currBlock.getData() < 7){
    				currBlock.setData((byte) 7);
    			}
            }
        }
    }
}
package me.botsko.mythos.spells;

import me.botsko.mythos.MythosWeighted;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SpellBase implements MythosWeighted {
	
	
	/**
	 * Block the action happened to
	 */
	protected Block block;
	
	/**
	 * 
	 */
	protected Player player;
	
	
	/**
	 * 
	 * @param player
	 */
	public void setPlayer(Player player){
		this.player = player;
	}


	/**
	 * 
	 * @return
	 */
	public short getSpellId(){
		return 0;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public int getWeight(){
		return 1;
	}
	
	
//	/**
//	 * 
//	 * @return
//	 */
//	public int getLevel(){
//		int[] nums = {1,2,3,4,5};
//		int[] weights = {100,100,125,125,160};
//		return WeightedRandom.getWeightedRandomNumber( nums, weights );
//	}
	
	
	/**
	 * 
	 * @return
	 */
	public double getCurseAmplifier(){
		return 0;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getAwardMessage(){
		return "";
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getSpellUseMessage(){
		return "";
	}


	/**
	 * 
	 * @param event
	 */
	public boolean getBlockBreakAward(BlockBreakEvent event){
		return false;
	}
	
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	public boolean useSpellPlayerInteract(PlayerInteractEvent event){
		return false;
	}
	
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	public boolean useSpellPlayerEntityInteract(PlayerInteractEntityEvent event){
		return false;
	}
	
	
	/**
	 * 
	 * @param player
	 * @return
	 */
	public SpellModifier getSpellModifier(){
		
		for (ItemStack stack : player.getInventory().getContents()) {
			if (stack != null) {
				
				
				if(stack.getType() == Material.DIAMOND_BLOCK){
					System.out.print("Diamond block found");
					return new SpellModifier( Material.DIAMOND_BLOCK, stack.getAmount() );
				}
			}
		}
		return null;
	}
}
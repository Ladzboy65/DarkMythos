package me.botsko.mythos.spells;

import java.util.List;

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
	 */
	protected SpellModifier modifier;
	
	
	/**
	 * 
	 * @param player
	 */
	public void setPlayer(Player player){
		this.player = player;
		this.modifier = getSpellModifier();
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
	public List<MythosWeighted> getCurseChoices(){
		return null;
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
					return new SpellModifier( stack.getType(), stack.getAmount() );
				}
				if(stack.getType() == Material.GOLD_BLOCK){
					return new SpellModifier( stack.getType(), stack.getAmount() );
				}
				if(stack.getType() == Material.IRON_BLOCK){
					return new SpellModifier( stack.getType(), stack.getAmount() );
				}
			}
		}
		return null;
	}
}
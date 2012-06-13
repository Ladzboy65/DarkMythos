package me.botsko.mythos.spells;

import java.util.List;

import me.botsko.mythos.MythosWeighted;
import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
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
	protected Entity entity;
	
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
	
	
	/**
	 * 
	 */
	public boolean isAwardedOn( Block block ) {
		return false;
	}
	
	
	/**
	 * 
	 */
	public boolean isAwardedOn( Entity entity ) {
		return false;
	}
	
	
	/**
	 * 
	 * @param block
	 * @return
	 */
	public boolean isUsedOn( Block block ){
		return false;
	}
	
	
	/**
	 * 
	 * @param block
	 * @return
	 */
	public boolean isUsedOn( Entity entity ){
		return false;
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
	 * @param string 
	 * @return
	 */
	public String getNotifyNearbyMessage( String player_name ) {
		return "";
	}


	/**
	 * 
	 */
	public boolean getBlockBreakAward(BlockBreakEvent event){
		block = event.getBlock();
		return dropSpellBook();
	}
	
	
	/**
	 * 
	 */
	protected boolean dropSpellBook(){
		
		if(block != null && isAwardedOn(block) ){
		
			// Set item
			ItemStack i = new ItemStack(Material.BOOK, 1);
			i.setDurability( getSpellId() );
			
			// Drop the item
			block.getWorld().dropItemNaturally(block.getLocation(), i);
			
			// Boom!
			MythosUtil.awardThunder( block );
			
			return true;
				
		}
		if(entity != null){
			
			// Set item
			ItemStack i = new ItemStack(Material.BOOK, 1);
			i.setDurability( getSpellId() );
			
			// Drop the item
			entity.getWorld().dropItemNaturally(entity.getLocation(), i);
			
			// Boom!
//			MythosUtil.awardThunder( block );
			
			return true;
			
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	public boolean getEntityDeathAward( EntityDeathEvent event ){
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
	
	
	/**
	 * 
	 * @param material
	 * @param min_quant
	 * @return
	 */
	public boolean playerHasModifier( Material material, int min_quant ){
		
		if(modifier != null){
			if(modifier.getMaterial().equals( material )){
				if(min_quant > 0){
					return (modifier.getQuant( min_quant ) <= min_quant);
				} else {
					return true;
				}
			}
		}
		return false;
	}
}
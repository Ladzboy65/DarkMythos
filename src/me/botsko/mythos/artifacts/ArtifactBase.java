package me.botsko.mythos.artifacts;

import me.botsko.mythos.MythosWeighted;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class ArtifactBase implements MythosWeighted {
	
	
	/**
	 * Block the action happened to
	 */
	protected Block block;

	
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
	public String getLangClassname(  ){
		return this.getClass().getName().replace("me.botsko.mythos.artifacts.", "");
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
	
	
	/**
	 * 
	 */
	public String getNotifyNearbyMessage( String player_name ){
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
	 * @param player
	 */
	protected void subtractFromHand(Player player){
		
		ItemStack in_hand = player.getInventory().getItemInHand();
		if(in_hand.getAmount() == 1){
			player.getInventory().remove(in_hand);
		} else {
			in_hand.setAmount( in_hand.getAmount() - 1 );
		}
	}
}
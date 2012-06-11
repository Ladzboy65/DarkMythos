package me.botsko.mythos.curses;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KillPlayerCurse extends CurseBase {
	
	
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
	 * @return
	 */
	@Override
	public String getMessage(){
		return "Spell book held Ares' Ultimate Curse... you have sacrificed your life!";
	}
	
	
	/**
	 * 
	 * @param player
	 */
	@Override
	public void applyCurse(Player player){
		
		// If the player had a spell modifier, this curse becomes more
		// potent
		if(modifier != null){
			for (ItemStack stack : player.getInventory().getContents()) {
				if (stack != null){
					player.getInventory().remove(stack);
				}
			}
			player.updateInventory();
		} else {
			MythosUtil.subtractFromHand( player );
		}
		
		player.setHealth( 0 );
	
	}
}
package me.botsko.mythos.curses;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KillPlayerCurse extends CurseBase {
	
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
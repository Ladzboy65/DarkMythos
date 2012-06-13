package me.botsko.mythos.curses;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.entity.Player;

public class SuffocatePlayerCurse extends CurseBase {
	
	/**
	 * 
	 * @return
	 */
	@Override
	public int getWeight(){
		return 70;
	}

	
	/**
	 * 
	 * @param player
	 */
	@Override
	public void applyCurse(Player player){
        player.setRemainingAir( 0 );
        MythosUtil.subtractFromHand( player );
	}
}
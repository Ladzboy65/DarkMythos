package me.botsko.mythos.curses;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.entity.Player;

public class IgnitePlayerCurse extends CurseBase {
	
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
        player.setFireTicks(10000);
        MythosUtil.subtractFromHand( player );
		player.setHealth(0);
	}
}
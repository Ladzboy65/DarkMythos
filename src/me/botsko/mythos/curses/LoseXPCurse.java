package me.botsko.mythos.curses;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.entity.Player;

public class LoseXPCurse extends CurseBase {
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public int getWeight(){
		return 10;
	}
	
	
	/**
	 * 
	 * @param player
	 */
	@Override
	public void applyCurse(Player player){
		player.setLevel(0);
		player.setTotalExperience(0);
		MythosUtil.subtractFromHand( player );
	}
}
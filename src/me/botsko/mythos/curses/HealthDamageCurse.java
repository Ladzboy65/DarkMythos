package me.botsko.mythos.curses;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.entity.Player;

public class HealthDamageCurse extends CurseBase {

	/**
	 * 
	 * @return
	 */
	@Override
	public int getWeight(){
		return 60;
	}
	
	
	/**
	 * 
	 * @param player
	 */
	@Override
	public void applyCurse(Player player){
		player.setHealth( player.getHealth() - 15 );
		MythosUtil.subtractFromHand( player );
	}
}
package me.botsko.mythos.curses;

import org.bukkit.entity.Player;

public class SmitePlayerCurse extends CurseBase {
	
	
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
		player.getWorld().strikeLightning( player.getLocation() );
	}
}
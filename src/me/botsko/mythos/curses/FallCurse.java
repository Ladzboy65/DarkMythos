package me.botsko.mythos.curses;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FallCurse extends CurseBase {
	
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
		Location l = player.getLocation();
		l.setY( player.getWorld().getMaxHeight() );
		player.teleport( l );
		MythosUtil.subtractFromHand( player );
	}
}
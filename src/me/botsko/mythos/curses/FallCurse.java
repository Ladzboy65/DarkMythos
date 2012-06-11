package me.botsko.mythos.curses;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class FallCurse extends CurseBase {
	
	
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
		return 60;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getMessage(){
		return "Spell book was cursed... you have been shot up to the heavens!";
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
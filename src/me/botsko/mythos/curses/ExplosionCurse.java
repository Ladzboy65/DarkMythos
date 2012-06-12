package me.botsko.mythos.curses;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.entity.Player;

public class ExplosionCurse extends CurseBase {
	
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
		return "Spell book was cursed by Artago, God of Explosions!";
	}

	
	/**
	 * 
	 * @param player
	 */
	@Override
	public void applyCurse(Player player){
		player.getWorld().createExplosion(player.getLocation(), 4F);
		player.setHealth(0);
		MythosUtil.subtractFromHand( player );
	}
}
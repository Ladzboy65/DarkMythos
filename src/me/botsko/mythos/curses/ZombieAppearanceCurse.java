package me.botsko.mythos.curses;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

public class ZombieAppearanceCurse extends CurseBase {

	
	/**
	 *
	 * @return
	 */
	@Override
	public int getWeight(){
		return 40;
	}
	

	/**
	 *
	 * @return
	 */
	@Override
	public String getMessage(){
		return "Spell book was cursed! Hades Summons an Army of zombies to attack you!";
	}

	/**
	 *
	 * @param player
	 */
	@Override
	public void applyCurse(Player player){
		
		Vector v = player.getLocation().getDirection();
		for( int i = 1; i <= 3; i++ ){
			Entity e1 = player.getWorld().spawnCreature(player.getLocation(), EntityType.ZOMBIE);
			e1.setVelocity(v);
		}
		
		MythosUtil.subtractFromHand( player );

	}
}
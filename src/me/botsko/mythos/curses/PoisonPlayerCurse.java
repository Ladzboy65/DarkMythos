package me.botsko.mythos.curses;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PoisonPlayerCurse extends CurseBase {
	

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
		return "Spell book was cursed... Ares' Spell of Poison!";
	}

	
	/**
	 * 
	 * @param player
	 */
	@Override
	public void applyCurse(Player player){
		player.addPotionEffect( new PotionEffect(PotionEffectType.POISON, 200, 1) );
		player.addPotionEffect( new PotionEffect(PotionEffectType.WEAKNESS, 200, 1) );
		MythosUtil.subtractFromHand( player );
	}
}
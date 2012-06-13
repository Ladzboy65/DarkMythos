package me.botsko.mythos.spells;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;

public class FullHealSpell extends SpellBase implements Spell {
	/**
	 * 
	 * @return
	 */
	@Override
	public short getSpellId(){
		return 15;
	}


	/**
	 * Returns the weighting of the award
	 */
	@Override
	public int getWeight(){
		return 25;
	}
	
	/**
	 * 
	 * @param block
	 * @return
	 */
	public boolean isAwardedOn( Block block ){
		return ( block.getType() == Material.GRASS || block.getType() == Material.DIRT );
	}
	
	/**
	 * 
	 * @param block
	 * @return
	 */
	public boolean isUsedOn( Block block ){
		return true;
	}


	/**
	 * 
	 * @param player
	 */
	@Override
	public boolean useSpellPlayerInteract(PlayerInteractEvent event){
		player.setHealth( 20 );
		player.setFoodLevel( 20 );
		MythosUtil.subtractFromHand( player );
		return true;
	}
}

package me.botsko.mythos.spells;

import java.util.List;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class CreatureBanisherSpell extends SpellBase implements Spell {

	
	/**
	 * 
	 * @return
	 */
	@Override
	public short getSpellId(){
		return 8;
	}
	
	
	/**
	 * Returns the weighting of the award
	 */
	@Override
	public int getWeight(){
		return 55;
	}
	
	
	/**
	 * 
	 * @param block
	 * @return
	 */
	public boolean isAwardedOn( Entity entity ){
		return true;
	}
	
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	@Override
	public boolean useSpellPlayerEntityInteract(PlayerInteractEntityEvent event){
		
		Entity e = event.getRightClicked();
		
		if(e != null){
			
			// Check for a spell modifier
			if(playerHasModifier( Material.DIAMOND_BLOCK, 1 )){
				List<Entity> mobs = player.getNearbyEntities( 20, 20, 20);
				for( Entity mob : mobs){
					if(mob.getType().equals( e.getType() )){
						mob.remove();
					}
				}
			}

			// Remove mob
			e.remove();
			
			MythosUtil.subtractFromHand( player );
	
			return true;
		}
		return false;
	}
}
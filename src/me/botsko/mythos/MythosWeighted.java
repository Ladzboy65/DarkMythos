package me.botsko.mythos;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public interface MythosWeighted {
	
	
	/**
	 * A weighted item must provide a weighting.
	 * @return
	 */
	public int getWeight();
	
	
	/**
	 * 
	 * @param block
	 * @return
	 */
	public boolean isAwardedOn( Block block );
	
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public boolean isAwardedOn( Entity entity );
	
	
	/**
	 * 
	 * @param block
	 * @return
	 */
	public boolean isUsedOn( Block block );

	
	/**
	 * 
	 * @param block
	 * @return
	 */
	public boolean isUsedOn( Entity entity );

}
package me.botsko.mythos;

import org.bukkit.block.Block;

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
	 * @param block
	 * @return
	 */
	public boolean isUsedOn( Block block );

}
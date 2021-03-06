package me.botsko.mythos.curses;

import me.botsko.mythos.MythosWeighted;
import me.botsko.mythos.spells.SpellModifier;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class CurseBase implements MythosWeighted {
	
	protected SpellModifier modifier;

	
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
	 */
	public String getLangClassname(  ){
		return this.getClass().getName().replace("me.botsko.mythos.curses.", "");
	}

	
	/**
	 * 
	 */
	public boolean isAwardedOn( Block block ) {
		return false;
	}
	
	
	/**
	 * 
	 */
	public boolean isAwardedOn( Entity entity ) {
		return false;
	}
	
	
	/**
	 * 
	 * @param block
	 * @return
	 */
	public boolean isUsedOn( Block block ){
		return false;
	}
	
	
	/**
	 * 
	 * @param block
	 * @return
	 */
	public boolean isUsedOn( Entity entity ){
		return false;
	}
	
	
	/**
	 * 
	 * @param modifier
	 */
	public void playerHasSpellModifier( SpellModifier modifier ){
		this.modifier = modifier;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public SpellModifier getSpellModifier(){
		return this.modifier;
	}


	/**
	 * 
	 * @param player
	 */
	public void applyCurse(Player player){
		// does nothing by default
	}
}
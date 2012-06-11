package me.botsko.mythos.curses;

import me.botsko.mythos.MythosWeighted;
import me.botsko.mythos.spells.SpellModifier;

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
	 * @return
	 */
	public String getMessage(){
		return "";
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
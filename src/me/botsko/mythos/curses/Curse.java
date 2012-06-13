package me.botsko.mythos.curses;

import me.botsko.mythos.spells.SpellModifier;

public interface Curse {

	/**
	 * 
	 * @return
	 */
	public int getWeight();
	
	
	/**
	 * 
	 */
	public SpellModifier getSpellModifier();
	
	
	/**
	 * 
	 */
	public void applyCurse();
	
}
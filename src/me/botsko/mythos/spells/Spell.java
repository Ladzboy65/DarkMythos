package me.botsko.mythos.spells;

import java.util.List;

import me.botsko.mythos.MythosWeighted;

public interface Spell {

	/**
	 * 
	 * @return
	 */
	public short getSpellId();
	
	
	/**
	 * 
	 * @return
	 */
	public int getWeight();
	
	
	/**
	 * 
	 * @return
	 */
//	public int getLevel();
	
	
	/**
	 * 
	 * @return
	 */
	public double getCurseAmplifier();
	
	
	/**
	 * 
	 * @return
	 */
	public List<MythosWeighted> getCurseChoices();
	
	
	/**
	 * 
	 * @return
	 */
	public String getAwardMessage();
	
	
	/**
	 * 
	 * @return
	 */
	public String getSpellUseMessage();
	
	
	
}

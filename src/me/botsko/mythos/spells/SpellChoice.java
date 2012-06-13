package me.botsko.mythos.spells;

import java.util.Iterator;
import java.util.List;

import me.botsko.mythos.Mythos;
import me.botsko.mythos.MythosWeighted;
import me.botsko.mythos.utilities.WeightedRandom;


public class SpellChoice {
	
	private Mythos plugin;
	
	/**
	 * Add spells to the list.
	 * @todo I'd like them to self-register eventually
	 * 
	 */
	public SpellChoice( Mythos plugin ){
		this.plugin = plugin;
	}
	
	
	/**
	 * Chooses a random reward.
	 * @return
	 */
	public SpellBase chooseRandomSpell( List<MythosWeighted> spells ){
		// We only want to choose a weighted award
		// very rarely, so it's odds are checked first
		if(WeightedRandom.getRandomNumber( plugin.getConfig().getInt("mythos.spell_chance_range") ) == 2){
			return (SpellBase) WeightedRandom.chooseOnWeight(spells);
		}
		return null;
	}
	
	
	/**
	 * Chooses a random reward.
	 * @return
	 */
	public SpellBase chooseSpell(List<MythosWeighted> spells, int spell_id){
		Iterator<MythosWeighted> iterator = spells.iterator();
		while (iterator.hasNext()) {
			SpellBase spell = (SpellBase) iterator.next();
			if(spell.getSpellId() == spell_id){
				return spell;
			}
		}
		return null;
	}
}
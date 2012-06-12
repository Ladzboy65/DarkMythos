package me.botsko.mythos.curses;

import java.util.ArrayList;
import java.util.List;

import me.botsko.mythos.Mythos;
import me.botsko.mythos.MythosWeighted;
import me.botsko.mythos.spells.SpellBase;
import me.botsko.mythos.utilities.WeightedRandom;


public class CurseChoice {
	
	private Mythos plugin;
	
	/**
	 * Holds the curses we offer
	 */
	List<MythosWeighted> curses = new ArrayList<MythosWeighted>();
	
	
	/**
	 * Add spells to the list.
	 * @todo I'd like them to self-register eventually
	 */
	public CurseChoice( Mythos plugin ){
		
		this.plugin = plugin;
		
		curses.add(new HealthDamageCurse());
		curses.add(new PoisonPlayerCurse());
		curses.add(new KillPlayerCurse());
		curses.add(new LoseXPCurse());
		curses.add(new ExplosionCurse());
		curses.add(new FallCurse());
		curses.add(new SmitePlayerCurse());
		curses.add(new IgnitePlayerCurse());
		curses.add(new InventoryCurse());
		curses.add(new ZombieAppearanceCurse());
	}
	
	
	/**
	 * Chooses a random reward.
	 * @return
	 */
	public CurseBase chooseRandomCurse( SpellBase spell ){
		
		List<MythosWeighted> available_curses = curses;
		double curse_chance_range = plugin.getConfig().getInt("mythos.curse_chance_range");
		
		// Spell may have some influence, so we need to work that out
		if(spell != null){
			// If the spell needs to modify the curse chances, it's
			// provided here. You can drop the curse chance by 25%, etc
			double _tmp_range = spell.getCurseAmplifier();
			if(_tmp_range > 0){
				curse_chance_range = (plugin.getConfig().getDouble("mythos.curse_chance_range") * _tmp_range);
				if(curse_chance_range < 2){
					curse_chance_range = 2;
				}
			}

			// Spell may also provide a custom list of spells to choose from.
			List<MythosWeighted> spell_curses = spell.getCurseChoices();
			if(spell_curses != null){
				available_curses = spell_curses;
			}
		}

		// Choose
		if(WeightedRandom.getRandomNumber( (int) curse_chance_range ) == 1){
			CurseBase c = (CurseBase) WeightedRandom.chooseOnWeight(available_curses);
			c.playerHasSpellModifier( spell.getSpellModifier() );
			return c;
		}
		return null;
	}
}
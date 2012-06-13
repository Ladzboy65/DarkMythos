package me.botsko.mythos.directory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.botsko.mythos.MythosWeighted;
import me.botsko.mythos.artifacts.ArtifactBase;
import me.botsko.mythos.curses.CurseBase;
import me.botsko.mythos.spells.SpellBase;
import me.botsko.mythos.utilities.WeightedRandom;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;


public class DirectoryManager {
	
	/**
	 * Holds the spells we offer
	 */
	List<MythosWeighted> spells = new ArrayList<MythosWeighted>();
	
	/**
	 * Holds the curses we offer
	 */
	List<MythosWeighted> curses = new ArrayList<MythosWeighted>();
	
	/**
	 * Holds the artifacts we offer
	 */
	List<MythosWeighted> artifacts = new ArrayList<MythosWeighted>();
	
	
	/**
	 * 
	 * @param block
	 * @todo we should cache this, for each block type so we don't re-iterate 
	 * @return
	 */
	public List<MythosWeighted> getSpells( Block block ){
		List<MythosWeighted> _tmp = new ArrayList<MythosWeighted>();
		for( MythosWeighted spell : spells ){
			if( spell.isAwardedOn( block ) || spell.isUsedOn(block) ){
				_tmp.add( spell );
			}
		}
		return _tmp;
	}
	
	
	/**
	 * 
	 * @param entity
	 */
	public List<MythosWeighted> getSpells( Entity entity ){
		List<MythosWeighted> _tmp = new ArrayList<MythosWeighted>();
		for( MythosWeighted spell : spells ){
			if( spell.isAwardedOn( entity ) || spell.isUsedOn(entity) ){
				_tmp.add( spell );
			}
		}
		return _tmp;
	}
	
	
	/**
	 * 
	 * @param entity
	 */
	public List<MythosWeighted> getCurses(){
		return curses;
	}
	
	
	/**
	 * 
	 * @param block
	 * @return
	 */
	public List<MythosWeighted> getArtifacts( Block block ){
		List<MythosWeighted> _tmp = new ArrayList<MythosWeighted>();
		for( MythosWeighted artifact : artifacts ){
			if(artifact.isAwardedOn( block )){
				_tmp.add( artifact );
			}
		}
		return _tmp;
	}
	
	
	/**
	 * Chooses a random reward.
	 * @return
	 */
	public SpellBase chooseRandomSpell( int range, List<MythosWeighted> spells ){
		// We only want to choose a weighted award
		// very rarely, so it's odds are checked first
		if(WeightedRandom.getRandomNumber( range ) == 2){
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
	
	
	/**
	 * Chooses a random reward.
	 * @return
	 */
	public CurseBase chooseRandomCurse( int range, List<MythosWeighted> curses, SpellBase spell ){
		
		this.curses = curses;
		
		List<MythosWeighted> available_curses = curses;
		double curse_chance_range = range;
		
		// Spell may have some influence, so we need to work that out
		if(spell != null){
			// If the spell needs to modify the curse chances, it's
			// provided here. You can drop the curse chance by 25%, etc
			double _tmp_range = spell.getCurseAmplifier();
			if(_tmp_range > 0){
				curse_chance_range = (range * _tmp_range);
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
	
	
	/**
	 * Chooses a random reward.
	 * @return
	 */
	public ArtifactBase chooseRandomArtifact( int range, List<MythosWeighted> artifacts ){
		this.artifacts = artifacts;
		// We only want to choose a weighted artifact
		// very rarely, so it's odds are checked first
		if(WeightedRandom.getRandomNumber( range ) == 2){
			return (ArtifactBase) WeightedRandom.chooseOnWeight(artifacts);
		}
		return null;
	}
}

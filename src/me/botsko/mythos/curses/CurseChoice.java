package me.botsko.mythos.curses;

import java.util.ArrayList;
import java.util.List;

import me.botsko.mythos.Mythos;
import me.botsko.mythos.MythosWeighted;
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
		
	}
	
	
	/**
	 * Chooses a random reward.
	 * @return
	 */
	public CurseBase chooseRandomCurse( double curse_chance_range ){
		// If the spell needs to modify the curse chances, it's
		// provided here. You can drop the curse chance by 25%, etc
		if(curse_chance_range > 0){
			curse_chance_range = Math.floor(plugin.getConfig().getInt("mythos.curse_chance_range") * curse_chance_range);
			if(curse_chance_range < 2){
				curse_chance_range = 2;
			}
		}
		System.out.print("curse_chance_range = " + curse_chance_range);
		int wr = WeightedRandom.getRandomNumber( (int) curse_chance_range );
		System.out.print("wr = " + wr);
		if(WeightedRandom.getRandomNumber( (int) curse_chance_range ) == 1){
			return (CurseBase) WeightedRandom.chooseOnWeight(curses);
		}
		return null;
	}
}
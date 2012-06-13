package me.botsko.mythos.directory;

import me.botsko.mythos.artifacts.ApollosSwordArtifact;
import me.botsko.mythos.curses.ArmorDestroyerCurse;
import me.botsko.mythos.curses.ExplosionCurse;
import me.botsko.mythos.curses.FallCurse;
import me.botsko.mythos.curses.HealthDamageCurse;
import me.botsko.mythos.curses.IgnitePlayerCurse;
import me.botsko.mythos.curses.InventoryCurse;
import me.botsko.mythos.curses.KillPlayerCurse;
import me.botsko.mythos.curses.LoseXPCurse;
import me.botsko.mythos.curses.PoisonPlayerCurse;
import me.botsko.mythos.curses.SmitePlayerCurse;
import me.botsko.mythos.curses.SuffocatePlayerCurse;
import me.botsko.mythos.curses.ZombieAppearanceCurse;
import me.botsko.mythos.spells.BotanicalMaturitySpell;
import me.botsko.mythos.spells.CreatureBanisherSpell;
import me.botsko.mythos.spells.CreatureCookerSpell;
import me.botsko.mythos.spells.CreatureThiefSpell;
import me.botsko.mythos.spells.DiamondTouchSpell;
import me.botsko.mythos.spells.FeatherTouchSpell;
import me.botsko.mythos.spells.GoldenTouchSpell;
import me.botsko.mythos.spells.IronTouchSpell;
import me.botsko.mythos.spells.PickSummonerSpell;
import me.botsko.mythos.spells.SkeletonSummonerSpell;
import me.botsko.mythos.spells.TreeFellerSpell;
import me.botsko.mythos.spells.ZeusBoltSpell;


public class Directory extends DirectoryManager {
	
	
	/**
	 * 
	 * LIST YOUR SPELLS/CURSES/ARTIFACTS HERE.
	 * 
	 * No order necessary, just anywhere.
	 * 
	 * All you need to do is list your spells/curses here. As long as your isAwardedOn
	 * methods are accurate, the plugin will auto sort them, and choose
	 * them at the appropriate time.
	 * 
	 * @param block
	 */
	public Directory(){
		
		//---------------------------
		// SPELLS
		//---------------------------
		spells.add(new BotanicalMaturitySpell());
		spells.add(new SkeletonSummonerSpell());
		spells.add(new IronTouchSpell());
		spells.add(new GoldenTouchSpell());
		spells.add(new DiamondTouchSpell());
		spells.add(new FeatherTouchSpell());
		spells.add(new TreeFellerSpell());
		spells.add(new PickSummonerSpell());
		spells.add(new CreatureCookerSpell());
		spells.add(new CreatureBanisherSpell());
		spells.add(new CreatureThiefSpell());
		spells.add(new ZeusBoltSpell());
		
		
		//---------------------------
		// CURSES
		//---------------------------
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
		curses.add(new ArmorDestroyerCurse());
		curses.add(new SuffocatePlayerCurse());
		
		
		//---------------------------
		// ARTIFACTS
		//---------------------------
		artifacts.add(new ApollosSwordArtifact());
		
	}
}
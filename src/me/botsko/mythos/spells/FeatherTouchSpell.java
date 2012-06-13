package me.botsko.mythos.spells;

import java.util.ArrayList;
import java.util.List;

import me.botsko.mythos.MythosWeighted;
import me.botsko.mythos.curses.KillPlayerCurse;
import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class FeatherTouchSpell extends SpellBase implements Spell {

	
	/**
	 * 
	 * @return
	 */
	@Override
	public short getSpellId(){
		return 5;
	}
	
	
	/**
	 * Returns the weighting of the award
	 */
	@Override
	public int getWeight(){
		return 5;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public double getCurseAmplifier(){
		if(modifier != null){
			if(modifier.getMaterial() == Material.DIAMOND_BLOCK){
				return 0.20;
			}
		}
		return 0;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public List<MythosWeighted> getCurseChoices(){
		// Check for a spell modifier
		if(modifier != null){
			if(modifier.getMaterial() == Material.DIAMOND_BLOCK){
				List<MythosWeighted> curses = new ArrayList<MythosWeighted>();
				curses.add(new KillPlayerCurse());
				return curses;
			}
		}
		return null;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getAwardMessage(){
		return "You discovered a magical book: Hephaestus' Spell of Feather Touch";
	}
	
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getSpellUseMessage(){
		return "Used spell Feather Touch! Spell book consumed.";
	}
	
	
	/**
	 * 
	 */
	@Override
	public boolean getBlockBreakAward(BlockBreakEvent event){
		block = event.getBlock();
		if( block.getType() == Material.GRASS || block.getType() == Material.DIRT ){
			dropSpellBook();
			return true;
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param event
	 * @return
	 */
	@Override
	public boolean useSpellPlayerInteract(PlayerInteractEvent event){
		
		Block currBlock = event.getClickedBlock();
		
		// Set default quantity
		int quant = 1;
		
		// Check for a spell modifier
		if(playerHasModifier( Material.DIAMOND_BLOCK, 1 )){
			quant = modifier.getQuant( 5 );
		}
		
		// Copy the block at desired quantity, give to player
		ItemStack copy = new ItemStack( currBlock.getType(), quant );
		copy.setDurability(currBlock.getData());
		player.getInventory().addItem( copy );
		player.updateInventory();
		
		// Remove spell book
		MythosUtil.subtractFromHand( player );
		
		return true;
		
	}
}
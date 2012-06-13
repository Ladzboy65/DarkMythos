package me.botsko.mythos.curses;

import java.util.Random;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ArmorDestroyerCurse extends CurseBase {
	
	
	/**
	 * Block the action happened to
	 */
	protected Block block;
	Random r = new Random();
	
	/**
	 * 
	 * @return
	 */
	@Override
	public int getWeight(){
		return 60;
	}

	
	/**
	 * 
	 * @param player
	 */
	@Override
	public void applyCurse(Player player){
		
		int armorChoice = r.nextInt(8);
		if (armorChoice == 0 || armorChoice == 1)
			player.getInventory().getBoots().setAmount(0);
		if (armorChoice == 2 || armorChoice == 3)
			player.getInventory().getLeggings().setAmount(0);
		if (armorChoice == 4 || armorChoice == 5)
			player.getInventory().getChestplate().setAmount(0);
		if (armorChoice == 6 || armorChoice == 7)
			player.getInventory().getHelmet().setAmount(0);
		if (armorChoice == 8){
			player.getInventory().getHelmet().setAmount(0);
			player.getInventory().getChestplate().setAmount(0);
			player.getInventory().getLeggings().setAmount(0);
			player.getInventory().getBoots().setAmount(0);
		}
		MythosUtil.subtractFromHand( player );
	}
}
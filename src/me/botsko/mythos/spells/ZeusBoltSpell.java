package me.botsko.mythos.spells;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class ZeusBoltSpell extends SpellBase implements Spell {

	
	/**
	 * 
	 * @return
	 */
	public short getSpellId(){
		return 10;
	}
	
	
	/**
	 * Returns the weighting of the award
	 */
	public int getWeight(){
		return 4;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getAwardMessage(){
		return "You have discovered a legendary spell: Zeus' Lightning spell! Right click to unleash the wrath of the gods!";
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getSpellUseMessage(){
		return "Used Zeus' Lightning spell! Spell books consumed. Run!";
	}
	
	
	/**
	 * 
	 */
	public boolean getBlockBreakAward(BlockBreakEvent event){
		block = event.getBlock();
		if( block.getType() == Material.GOLD_ORE || block.getType() == Material.LAPIS_ORE ){
			dropSpellBook();
			return true;	
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param event
	 * @return
	 * @throws InterruptedException 
	 */
	public boolean useSpellPlayerInteract(PlayerInteractEvent event, Player player) throws InterruptedException{
		MythosUtil.subtractFromHand( player );
		Thread.sleep(2000);
		Location l = event.getClickedBlock().getLocation();
		l.getWorld().createExplosion(l, 4F);
		l.getWorld().strikeLightning(l);
		Thread.sleep(100);
		l.getWorld().dropItem(l, new ItemStack(Material.FIRE, 1)).setVelocity(new Vector(-1, 10, 1));
		l.getWorld().createExplosion(l, 4F);
		l.getWorld().strikeLightning(l);
		Thread.sleep(100);
		l.getWorld().dropItem(l, new ItemStack(Material.FIRE, 1)).setVelocity(new Vector(1, 10, 1));
		l.getWorld().createExplosion(l, 4F);
		l.getWorld().strikeLightning(l);
		Thread.sleep(100);
		l.getWorld().dropItem(l, new ItemStack(Material.FIRE, 1)).setVelocity(new Vector(1, 10, -1));
		l.getWorld().createExplosion(l, 4F);
		l.getWorld().strikeLightning(l);
		l.getWorld().dropItem(l, new ItemStack(Material.FIRE, 1)).setVelocity(new Vector(-1, 10, -1));
		Thread.sleep(10);
		l.getWorld().createExplosion(l, 4F);
		l.getWorld().strikeLightning(l);
		l.getWorld().dropItem(l, new ItemStack(Material.FIRE, 1)).setVelocity(new Vector(1, 10, 1));
		Thread.sleep(10);
		l.getWorld().dropItem(l, new ItemStack(Material.FIRE, 1)).setVelocity(new Vector(-1, 10, -1));
		return true;
	}
}


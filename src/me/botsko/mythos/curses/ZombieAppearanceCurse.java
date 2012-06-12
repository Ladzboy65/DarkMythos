package me.botsko.mythos.curses;

import me.botsko.mythos.utilities.MythosUtil;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

public class ZombieAppearanceCurse extends CurseBase {

/**
* Block the action happened to
*/
protected Block block;

/**
*
* @return
*/
@Override
public int getWeight(){
return 40;
}

/**
*
* @return
*/
@Override
public String getMessage(){
return "Spell book was cursed! Hades Summons an Army of zombies to attack you!";

}

/**
*
* @param player
*/
@Override
public void applyCurse(Player player){
Vector v = player.getLocation().getDirection();
Entity e1 = player.getWorld().spawnCreature(player.getLocation(), EntityType.ZOMBIE);
e1.setVelocity(v);
Entity e2 = player.getWorld().spawnCreature(player.getLocation(), EntityType.ZOMBIE);
e2.setVelocity(v);
Entity e3 = player.getWorld().spawnCreature(player.getLocation(), EntityType.ZOMBIE);
e3.setVelocity(v);

MythosUtil.subtractFromHand( player );

	}

}

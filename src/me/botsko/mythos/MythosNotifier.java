package me.botsko.mythos;

import me.botsko.mythos.artifacts.ArtifactBase;
import me.botsko.mythos.spells.SpellBase;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MythosNotifier {
	private Mythos plugin;

	public void foundArtifact(Player player, ArtifactBase artifact) {
        for (Player p : player.getServer().getOnlinePlayers()) {
            if (getDistance(p.getLocation(), player.getLocation()) <= plugin.getConfig().getInt("mythos.notify_radius")) 
                p.sendMessage(plugin.playerMsg(player + " just found an artifact!"));
            
        }
    }
 
	private float getDistance(Location p, Location q) {
        return (float) Math.sqrt(Math.pow(p.getBlockX() - q.getBlockX(), 2) + Math.pow(p.getBlockY() - q.getBlockY(), 2) + Math.pow(p.getBlockZ() - q.getBlockZ(), 2));
    }
	public void foundSpell(Player player, SpellBase award) {
        for (Player p : player.getServer().getOnlinePlayers()) {
            if (getDistance(p.getLocation(), player.getLocation()) <= plugin.getConfig().getInt("mythos.notify_radius")) 
                p.sendMessage(plugin.playerMsg(player + " just found a spell book!"));
		
        }
	}
}

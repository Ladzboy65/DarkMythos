package me.botsko.mythos.directory;

import java.util.ArrayList;
import java.util.List;

import me.botsko.mythos.MythosWeighted;

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
		System.out.print("Filtered Spell List Size: " + _tmp.size());
		return _tmp;
	}
	
	
	/**
	 * 
	 * @param entity
	 */
	public List<MythosWeighted> getSpells( Entity entity ){
		return null;
	}
	
	
	/**
	 * 
	 * @param entity
	 */
	public List<MythosWeighted> getCurses(){
		return null;
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
}

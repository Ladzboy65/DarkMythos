package me.botsko.mythos.spells;

import org.bukkit.Material;

public class SpellModifier {
	
	protected Material material;
	protected int quant;
	
	
	/**
	 * 
	 * @param material
	 * @param quant
	 */
	public SpellModifier( Material material, int quant ){
		this.material = material;
		this.quant = quant;
	}


	/**
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}


	/**
	 * @param material the material to set
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}


	/**
	 * @return the quant
	 */
	public int getQuant( int max_quant ) {
		if(quant > max_quant){
			quant = max_quant;
		}
		return quant;
	}


	/**
	 * @param quant the quant to set
	 */
	public void setQuant(int quant) {
		this.quant = quant;
	}
}
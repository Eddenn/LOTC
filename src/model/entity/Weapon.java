package model.entity;

import java.util.Arrays;
import java.util.List;

public enum Weapon {

	CUP(0,"Tasse à café","Une tasse à café vide..",1, 
			(List<Effect>)Arrays.asList(new Effect(EffectName.DAMAGE,0.9,1,2)) );
	
	private int id;
	private String name;
	private String desc;
	private int weigth;
	
	private List<Effect> effects;
	
	private Weapon(int id, String name, String desc, int weigth, List<Effect> effects) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.weigth = weigth;
		this.effects = effects;
	}
	
	public boolean hasEffects() {
		return this.effects == null;
	}
	
	public List<Effect> getEffects() {
		return this.effects;
	}
}

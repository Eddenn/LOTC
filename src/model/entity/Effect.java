package model.entity;

public class Effect {
		
	private EffectName effectName;
	private int hpDealMin;
	private int hpDealMax;
	private double chance;
	
	public Effect(EffectName effectName, double chance, int hpDealMin, int hpDealMax) {
		if(chance < 0 || chance > 100) {
			throw new IllegalArgumentException("0<=chance<=100 ("+chance+")");
		}
		if(hpDealMin < 0 || hpDealMax < 0 || hpDealMin > hpDealMax) {
			throw new IllegalArgumentException("0<=hpDealMin && 0<=hpDealMax && hpDealMin<=hpDealMax (min:"+hpDealMin+",max:"+hpDealMax+")");
		}
		
		this.effectName = effectName;
		this.chance = chance;
		this.hpDealMin = hpDealMin;
		this.hpDealMax = hpDealMax;
	}

	public EffectName getEffectName() {
		return effectName;
	}
	public int getHpDealMin() {
		return hpDealMin;
	}
	public int getHpDealMax() {
		return hpDealMax;
	}
	public double getChance() {
		return chance;
	}	
}

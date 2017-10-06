package model.entity;

public class FightLog {

	private boolean success;
	private FightEntity source;
	private FightEntity destination;
	private EffectName effectName;
	private double hpDeal;
	private double hpToDeal;
	private double targetArmor;
	private int dice;
	
	public FightLog(boolean success, int dice, FightEntity source, FightEntity destination, EffectName effectName, double hpDeal, double hpToDeal, double targetArmor) {
		this(success, dice, source, destination, effectName);
		this.hpDeal = hpDeal;
		this.hpToDeal = hpToDeal;
		this.targetArmor = targetArmor;
	}
	public FightLog(boolean success, int dice, FightEntity source, FightEntity destination, EffectName effectName) {
		this.success = success;
		this.dice = dice;
		this.source = source;
		this.destination = destination;
		this.effectName = effectName;
		this.hpDeal = 0;
		this.hpToDeal = 0;
		this.targetArmor = 0;
	}

	public boolean isSuccessful() {
		return success;
	}
	public FightEntity getSource() {
		return source;
	}
	public FightEntity getDestination() {
		return destination;
	}
	public EffectName getEffectName() {
		return effectName;
	}
	public double getHpDeal() {
		return hpDeal;
	}
	public double getToDeal() {
		return hpToDeal;
	}
	public double getTargetArmor() {
		return targetArmor;
	}
	public int getDice() {
		return dice;
	}
	@Override
	public String toString() {
		return "FightLog [success=" + success + ", source=" + source + ", destination=" + destination + ", effectName="
				+ effectName + ", hpDeal=" + hpDeal + ", hpToDeal=" + hpToDeal + ", targetArmor=" + targetArmor
				+ ", dice=" + dice + "]";
	}
}

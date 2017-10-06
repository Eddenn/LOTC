package model.entity;

import java.util.ArrayList;
import java.util.Random;

public class FightEntity extends Entity{

	//Attributes
	private double hp;
	
	private Weapon weapon;
	private Helmet helmet;
	private Armor armor;
	private Pants pants;
	
	private ArrayList<EffectName> status;
	
	//Constructors
	public FightEntity(String name, String pathToSprite, double hp) {
		super(name, pathToSprite);
		this.hp = hp;
		this.weapon = null;
		this.status = new ArrayList<EffectName>();
	}
	public FightEntity(String name, String pathToSprite, double hp, Weapon weapon, Helmet helmet, Armor armor, Pants pants) {
		super(name, pathToSprite);
		this.hp = hp;
		this.weapon = weapon;
		this.helmet = helmet;
		this.armor = armor;
		this.pants = pants;
		this.status = new ArrayList<EffectName>();
	}

	//Getters
	public double getHp() {
		return hp;
	}
	public double getArmor() {
		return helmet.getArmorPoints() + armor.getArmorPoints() + pants.getArmorPoints();
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public ArrayList<Effect> getStatus() {
		return (ArrayList<Effect>) status.clone();
	}
	
	//Methods
	public ArrayList<FightLog> attack(FightEntity target) {
		Random r = new Random();
		ArrayList<FightLog> logs = new ArrayList<FightLog>();
		int dice = 0;
		
		if(this.getWeapon() != null) { //Si le joueur a une arme
			for(Effect e : this.getWeapon().getEffects()) {
				dice = r.nextInt(100)+1; //Random entre 1 et 100
				if(dice <= e.getChance()) {
					if(e.getEffectName() == EffectName.DAMAGE) {
						//Lance un dé pour défini quels dégats vont etre causé à la cible
						dice = r.nextInt(e.getHpDealMax()-e.getHpDealMin()+1);
						double hpToDeal = dice + e.getHpDealMin();
						//Affecte la cible
						double hpDeal = hpToDeal - getArmor();
						target.recieveDamage(hpDeal);						
						//Ajout dans les logs
						logs.add(new FightLog(true, dice, this, target, e.getEffectName(), hpDeal, hpToDeal, target.getArmor()));
					} else if(e.getEffectName() == EffectName.STUN) {
						target.addStatus(EffectName.STUN);
					}
				} else {
					//Ajout dans les logs
					logs.add(new FightLog(false, dice, this, target, e.getEffectName()));
				}
			}
		} else { //Sinon go à la main
			dice = r.nextInt(100)+1; //Random entre 1 et 100
			if(dice <= 80) {
				//Affecte la cible
				double hpToDeal = 1;
				double hpDeal = hpToDeal - getArmor();
				target.recieveDamage(hpDeal);						
				//Ajout dans les logs
				logs.add(new FightLog(true, dice, this, target, EffectName.DAMAGE, hpDeal, hpToDeal, target.getArmor()));
			} else {
				//Ajout dans les logs
				logs.add(new FightLog(false, dice, this, target, EffectName.DAMAGE));
			}
		}
		
		return logs;
	}
	public void addStatus(EffectName stun) {
		status.add(stun);
	}
	public void removeStatus(Effect effect) {
		status.remove(effect);
	}
	public void recieveDamage(double hpDeal) {
		if(hpDeal < 0) {
			throw new IllegalArgumentException("hpDeal>0 (hpDeal="+hpDeal+")");
		}
		if(hpDeal-this.hp < 0) {
			this.hp = 0;
		} else {
			this.hp = hpDeal - this.hp;
		}
	}
}

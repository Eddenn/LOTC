package model.entity;

public enum Armor {

	TSHIRT(0, "TShirt", "TShirt desctiption ....", 1, 0);
	
	private int id;
	private String name;
	private String desc;
	private int weigth;
	
	private double armorPoints;
	
	private Armor(int id, String name, String desc, int weigth, double armorPoints) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.weigth = weigth;
		this.armorPoints = armorPoints;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDesc() {
		return desc;
	}
	public int getWeigth() {
		return weigth;
	}
	public double getArmorPoints() {
		return armorPoints;
	}	
}

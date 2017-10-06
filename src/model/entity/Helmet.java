package model.entity;

public enum Helmet {

	CAP(0, "Casquette", "Une casquette reconnaisable par son étrange Z vert.", 1, 0);
	
	private int id;
	private String name;
	private String desc;
	private int weigth;
	
	private double armorPoints;
	
	private Helmet(int id, String name, String desc, int weigth, double armorPoints) {
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

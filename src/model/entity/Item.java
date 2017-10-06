package model.entity;

public enum Item {
	
	ERROR(-1,"Erreur","N'est pas un item correct.",1);
	
	private int id;
	private String name;
	private String desc;
	private int weigth;
		
	private Item(int id, String name, String desc, int weigth) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.weigth = weigth;
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
}

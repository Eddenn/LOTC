package model.entity;

import java.util.ArrayList;

public class Player extends FightEntity{
	
	private ArrayList<Storage> inventory;
	
	public Player(String name, String pathToSprite, double hp) {
		super(name,pathToSprite,hp);
		this.inventory = new ArrayList<Storage>();
	}
	
	public boolean hasItem(Item item) {
		for(Storage s : inventory) {
			if(s.hasItem(item)) {
				return true;
			}
		}
		return false;
	}
}

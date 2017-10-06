package model.entity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe permettant de stocker des objets (cf. Item)
 * (Utilisé dans Inventory)
 * @author AlexandreH
 *
 */
public class Storage {

	private Item[] storage;
	private int size;
	private int emptySlot;
	
	public Storage(int size) {
		this.size = size;
		this.storage = new Item[size];
		this.emptySlot = 0;
	}
	
	private void calculateEmptySlot() {
		for(int i=0; i<size; i++) {
			if(storage[i] == null) {
				emptySlot = i;
				return;
			}
		}
		emptySlot = -1;
	}
	
	public boolean addItem(Item item) {
		if(item == null) {
			throw new IllegalArgumentException("item != null");
		}
		
		if(emptySlot != -1) {
			this.storage[emptySlot] = item;
			calculateEmptySlot();
			return true;
		}
		return false;
	}

	public boolean addItemAt(int i, Item item) {
		if(item == null) {
			throw new IllegalArgumentException("item != null");
		}
		
		if(storage[i] == null) {
			this.storage[i] = item;
			calculateEmptySlot();
			return true;
		}
		return false;
	}
	
	public boolean removeItem(Item item) {
		int itemToRemove = -1;
		for(int i=0; i<size; i++) {
			if(storage[i] == item) {
				itemToRemove = i;
				break;
			}
		}
		if(itemToRemove != -1) {
			storage[itemToRemove] = null;
			calculateEmptySlot();
			return true;
		}
		return false;
	}
	
	public boolean removeItemAt(int i) {
		if(storage[i] != null) {
			storage[i] = null;
			return true;
		}
		return false;
	}
	
	//Getters and tools
	public Item getItemAt(int i) {
		return this.storage[i];
	}
	public boolean hasItem(Item item) {
		return Arrays.asList(storage).contains(item);
	}
	public int getSize() {
		return this.size;
	}
	public ArrayList<Item> toArray() {
		return (ArrayList<Item>) Arrays.asList(this.storage);
	}
}

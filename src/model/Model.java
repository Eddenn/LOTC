package model;

import model.entity.Player;

public class Model {

	private Player player;
	
	public Model() {
		this.player = new Player("Joueur", "/skull.png", 100);
	}
	
}

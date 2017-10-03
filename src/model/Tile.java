package model;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Tile {
	PLAIN("/sprites/tiles/plain.png"),
	FOREST("/sprites/tiles/forest.png");
	
	private Image sprite;
	
	private Tile(String pathToSprite) {
		try {
			this.sprite = ImageIO.read(getClass().getResource(pathToSprite));
		} catch (IOException e) {e.printStackTrace();}
	}

	public Image getSprite() {
		return sprite;
	}
}

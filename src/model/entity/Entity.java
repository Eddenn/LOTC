package model.entity;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Entity {

	private String name;
	private Image sprite; 
	
	public Entity(String name, String pathToSprite) {
		this.name = name;
		try {
			this.sprite = ImageIO.read(getClass().getResource(pathToSprite));
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public String getName() {
		return name;
	}
	public Image getSprite() {
		return sprite;
	}
}

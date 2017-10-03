package model;

public class Map {

	private Tile[][] storage;
	private int sizeX;
	private int sizeY;
	
	public Map(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		storage = new Tile[sizeX][sizeY];
		for(int x=0; x < sizeX; x++) {
			for(int y=0; y < sizeY; y++) {
				storage[x][y] = Tile.PLAIN;
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		return storage[x][y];
	}
	
	public void setSprite(int x, int y, Tile c) {
		if(x >= sizeX || x < 0 || y >= sizeY || y < 0) {
			throw new IndexOutOfBoundsException("x="+x+",y="+y+",sizeX="+sizeX+",sizeY="+sizeY);
		}
		storage[x][y] = c;
	}
	
	public int getSizeX() {
		return sizeX;
	}
	public int getSizeY() {
		return sizeY;
	}
}

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Map;
import model.Model;
import model.Tile;

public class MapPanel extends JPanel implements MouseWheelListener,MouseMotionListener,MouseListener{

	private MainIhm ihm;
	private Model model;
	
	private static final int originX = -8;
	private static final int originY = -20;
	private static final int sizeXSprite = 32;
	private static final int sizeYSprite = 48;
	private static final int sizeXTile = 32;
	private static final int sizeYTile = 28;
	
	private Point playerPos;
	private Point cameraPos;
	private Dimension cameraSize;
	private Map map;
	private Image playerSprite;
	private Image hoverSprite;
	private Point tileHovered;
	private Point[][] mapCamera;
	
	public MapPanel(MainIhm ihm) {
		this.ihm = ihm;
		this.model = ihm.model;
		
		try {
			playerSprite = ImageIO.read(getClass().getResource("/sprites/tiles/cursor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			hoverSprite = ImageIO.read(getClass().getResource("/sprites/tiles/hover.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		playerPos = new Point(10,5);
		cameraPos = new Point(0,0);
		cameraSize = new Dimension(33, 19);
		mapCamera = new Point[33][40];

		map = new Map(33,40);
				
		map.setSprite(23, 0, Tile.FOREST);
		map.setSprite(23, 11, Tile.FOREST);
		
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		
		addMouseWheelListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		
		Tile tile = null;
		int viewX = 0;
		int viewY = 0;
		int paddingX = 0;
		int paddingY = 0;
		for(int x=cameraPos.x; x < cameraSize.width+cameraPos.x; x++) {
			for(int y=cameraPos.y; y < cameraSize.height+cameraPos.y; y++) {			
				viewX = x-cameraPos.x;
				viewY = y-cameraPos.y;
				if(x%2==0) {
					paddingY = -14;
				} else {
					paddingY = 0;
				}
				paddingX = -viewX*8;
				//Find the sprite
				try {
					tile = map.getTile(x, y);
				} catch(ArrayIndexOutOfBoundsException e) {
					tile = null;
				}
				//Draw the spite
				if(tile != null) {
					g.drawImage(tile.getSprite(), 
								originX+viewX*sizeXTile+paddingX, 
								originY+viewY*sizeYTile+paddingY, 
								null);
					mapCamera[viewX][viewY] = new Point(originX+viewX*sizeXTile+paddingX+sizeXSprite/2, originY+viewY*sizeYTile+paddingY+2*sizeYSprite/3);
					g.setColor(Color.RED);
					g.drawRect(mapCamera[viewX][viewY].x, mapCamera[viewX][viewY].y, 1, 1);
					g.setColor(Color.WHITE);
				}
			}
		}
		
		//Draw player
		int playerPosX = playerPos.x - cameraPos.x;
		int playerPosY = playerPos.y - cameraPos.y;
		if(playerPosX%2==0) {
			paddingY = -16;
		} else {
			paddingY = 0;
		}
		paddingX = -playerPosX*8;
		g.drawImage(playerSprite, 
					originX+playerPosX*sizeXTile+paddingX, 
					originY+playerPosY*sizeYTile+paddingY, 
					sizeXSprite, 
					sizeYSprite, 
					null);
		//Hover with the mouse
		if(tileHovered != null) {
			if(tileHovered.x%2==0) {
				paddingY = -14;
			} else {
				paddingY = 0;
			}
			paddingX = -tileHovered.x*8;
			g.drawImage(hoverSprite, 
						originX+tileHovered.x*sizeXTile+paddingX, 
						originY+tileHovered.y*sizeYTile+paddingY,
						null);
		}
	}
	
	//To move only the camera
	public void moveDownPlayer() {
		int px = playerPos.x;
		int py = playerPos.y;
		if(py+1 >= map.getSizeY()) {
			throw new IndexOutOfBoundsException("Don't fell out of the map !");
		}
		playerPos.setLocation(px, py+1);
		//moveDownCamera();
	}
	public void moveUpPlayer() {
		int px = playerPos.x;
		int py = playerPos.y;
		if(py-1 < 0) {
			throw new IndexOutOfBoundsException("Don't fell out of the map !");
		}
		playerPos.setLocation(px, py-1);
		//moveUpCamera();
	}
	public void moveRightPlayer() {
		int px = playerPos.x;
		int py = playerPos.y;
		if(px+1 >= map.getSizeX()) {
			throw new IndexOutOfBoundsException("Don't fell out of the map !");
		}
		playerPos.setLocation(px+1, py);
		//moveRightCamera();
	}
	public void moveLeftPlayer() {		
		int px = playerPos.x;
		int py = playerPos.y;
		if(px-1 < 0) {
			throw new IndexOutOfBoundsException("Don't fell out of the map !");
		}
		playerPos.setLocation(px-1, py);
		//moveLeftCamera();
	}
	
	/*===TOOLS===*/
	//To move only the camera
	public void moveDownCamera() {
		cameraPos.setLocation(cameraPos.x, cameraPos.y+1);
	}
	public void moveUpCamera() {
		cameraPos.setLocation(cameraPos.x, cameraPos.y-1);
	}
	public void moveRightCamera() {
		cameraPos.setLocation(cameraPos.x+1, cameraPos.y);
	}
	public void moveLeftCamera() {
		cameraPos.setLocation(cameraPos.x-1, cameraPos.y);
	}
	//Test if in circle (for hover)
	public boolean isInCircle(double x, double y, double circleX, double circleY, double circleR)
	{
	   double dx = x - circleX;
	   double dy = y - circleY;
	   return dx * dx + dy * dy <= circleR * circleR;
	} 
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation()==1) {
			moveDownCamera();
			repaint();
		} else {
			moveUpCamera();
			repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point tmpPts = null;
		for(int i=0; i<mapCamera.length; i++) {
			for(int j=0; j<mapCamera[0].length; j++) {
				if( mapCamera[i][j] != null && isInCircle(e.getX(), e.getY(), mapCamera[i][j].x, mapCamera[i][j].y, 15) ) {
					tmpPts = new Point();
					tmpPts.x = i;
					tmpPts.y = j;
					break;
				}
			}
		}
		
		if(tmpPts != null && map.getTile(tmpPts.x, tmpPts.y) != null) {
			tileHovered = new Point(tmpPts.x,tmpPts.y);
		} else {
			tileHovered = null;
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("---Click---");
		System.out.println("Tile hovered : [x="+tileHovered.x+",y="+tileHovered.y+"]");
		System.out.println("Camera positon :[x="+cameraPos.x+",y="+cameraPos.y+"]");
		System.out.println("Real tile : [x="+(tileHovered.x+cameraPos.x)+",y="+(tileHovered.y+cameraPos.y)+"]");
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	
}

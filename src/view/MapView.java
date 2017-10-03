package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import model.Model;

public class MapView extends JPanel{
	
	private MainIhm ihm;
	private Model model;
	private MapPanel mapPanel;
	private JPanel topMenuPanel;
	private JButton quitButton;
	private JButton invButton;
	private JLabel logoLabel;
	private Image backgroundMenuImg;

	public MapView(MainIhm ihm) {
		this.ihm = ihm;
		this.model = ihm.model;
		
		try {
			backgroundMenuImg = ImageIO.read(getClass().getResource("/mapMenuBackground1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setLayout(new BorderLayout());
		setBackground(Color.black);
		
		//Instantiate the map
		this.mapPanel = new MapPanel(ihm);
		
		//Main top menu panel
		topMenuPanel = new JPanel(new GridBagLayout()) {
			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(backgroundMenuImg, 0, 0, 785, 50, null);
				super.paintComponent(g);
			}
		};
		//topMenuPanel.setForeground(new Color(0,0,0,0));
		topMenuPanel.setBackground(new Color(0,0,0,0));
		
		ImageIcon invIcon = new ImageIcon(getClass().getResource("/buttonBackground.png"));
		Image inv = invIcon.getImage();
		Image invResized = inv.getScaledInstance(invIcon.getIconWidth()/2, invIcon.getIconHeight()/2,  java.awt.Image.SCALE_SMOOTH);
		invIcon = new ImageIcon(invResized);
		invButton = new JButton(invIcon);
		invButton.setBorderPainted(false);
		invButton.setFocusPainted(false);
		invButton.setContentAreaFilled(false);
		invButton.setBorder(null);
		
		quitButton = new JButton("Menu principal");
		ImageIcon logoIcon = new ImageIcon(getClass().getResource("/skull.png"));
		Image logo = logoIcon.getImage();
		Image logoResized = logo.getScaledInstance(logoIcon.getIconWidth()/2, logoIcon.getIconHeight()/2,  java.awt.Image.SCALE_SMOOTH);
		logoIcon = new ImageIcon(logoResized);
		logoLabel = new JLabel(logoIcon);
		
		//===PlaceComponents===//
        GridBagConstraints gbc = new GridBagConstraints();
        
        //Panel who contain left buttons
		JPanel buttonLeft = new JPanel(new FlowLayout(FlowLayout.LEADING)); {
			buttonLeft.add(invButton);
		}
		buttonLeft.setForeground(new Color(0,0,0,0));
		buttonLeft.setBackground(new Color(0,0,0,0));
		topMenuPanel.add(buttonLeft,gbc);
		
		//Adding logo
		topMenuPanel.add(logoLabel,gbc);
		
		//Panel who contain right buttons
		JPanel buttonRight = new JPanel(new FlowLayout(FlowLayout.TRAILING)); {
			buttonRight.add(quitButton);
		}
		buttonRight.setForeground(new Color(0,0,0,0));
		buttonRight.setBackground(new Color(0,0,0,0));
		topMenuPanel.add(buttonRight,gbc);
		
		//Adding all of this to main MapView
		add(topMenuPanel,BorderLayout.NORTH);
		add(mapPanel,BorderLayout.CENTER);
		
		//Controllers
		createController();
	}
	
	/*==== Controls ====*/
	private void createController() {
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(ihm.mainPanel.getLayout());
			    cl.show(ihm.mainPanel, "menu");
			    ihm.mainPanel.requestFocus();
			}
		});
		
		
		
		//Move player down with key DOWN
		Action moveDown = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapPanel.moveDownPlayer();
				repaint();
			}
		};
		getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
		getActionMap().put("moveDown", moveDown);
		//Move player up with key UP
		Action moveUp = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapPanel.moveUpPlayer();
				repaint();
			}
		};
		getInputMap().put(KeyStroke.getKeyStroke("UP"), "moveUp");
		getActionMap().put("moveUp", moveUp);
		//Move player left with key LEFT
		Action moveLeft = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapPanel.moveLeftPlayer();
				repaint();
			}
		};
		getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
		getActionMap().put("moveLeft", moveLeft);
		//Move player right with key RIGHT
		Action moveRight = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapPanel.moveRightPlayer();
				repaint();
			}
		};
		getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
		getActionMap().put("moveRight", moveRight);
	}
}

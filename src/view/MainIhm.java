package view;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Model;

public class MainIhm {
	
	protected JFrame mainFrame;
	protected JPanel mainPanel;
	
	private JPanel menuView;
	private JButton playButton;
	private JButton optionButton;
	private JButton quitButton;
	private MapView mapView;
	
	protected Model model;
	
	public MainIhm() {
		createModel();
		createView();
		placeComponent();
		createController();
	}
	

	protected void display() {
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	private void createModel() {
		model = new Model();
	}

	private void createView() {
		mainFrame = new JFrame("Legends Of Timecracks");
		mainFrame.setSize(790, 600);
		mainFrame.setResizable(false);
		
		menuView = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel p = new JPanel(new GridLayout(0,1)); {
        	p.add( new JLabel(new ImageIcon(getClass().getResource("/skull.png"))) );
        	
        	JLabel title = new JLabel("Legends Of Timecracks");
        	title.setFont(new Font("Arial", 0, 50));
        	p.add(title);
        	JPanel q = new JPanel(new FlowLayout()); {
	        	playButton = new JButton("Jouer");
				playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
				playButton.setFont(new Font("Arial", 0, 35));
				q.add(playButton);
        	}
        	p.add(q);
    		q = new JPanel(new FlowLayout()); {
    			JPanel r = new JPanel(new GridLayout(0,1,0,5)); {
            		optionButton = new JButton("Options");
            		optionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            		r.add(optionButton);
            		quitButton = new JButton("Quitter");
            		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            		r.add(quitButton);
    			}
    			q.add(r);
    		}
    		p.add(q);
        }
        menuView.add(p,gbc);
				
		mapView = new MapView(this);
        
		mainPanel = new JPanel(new CardLayout());
		mainPanel.add(menuView,"menu");
		mainPanel.add(mapView,"map");
	}

	private void placeComponent() {
		mainFrame.add(mainPanel);
	}
	
	private void createController() {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*==== Menu controls ====*/
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(mainPanel.getLayout());
			    cl.show(mainPanel, "map");
			    mapView.requestFocus();
			}
		});
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.dispose();
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] agrs) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new MainIhm().display();
			}
		});
	}
}

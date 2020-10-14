import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//creates background image for "playground"
		Image img = new ImageIcon(this.getClass().getResource("/pic2.png")).getImage();
		
		
		// here we're creating buttons, each button will have an an "action listener" and upon clicking them by mouse, it'll call on corresponding method.
		JButton B3 = new JButton("");  //creates new button
		B3.addActionListener(new ActionListener() {  //adding action listener, this looks for events regarding the button. 
			public void actionPerformed(ActionEvent e) {  
			}
		});
		B3.setBackground(Color.BLACK);  //formatting, black background, white lettering, general properties. 
		B3.setForeground(Color.WHITE);
		B3.setBounds(328, 43, 24, 23);
		frame.getContentPane().add(B3);
		
		JButton W1 = new JButton("");
		W1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		W1.setBackground(Color.WHITE);
		W1.setBounds(83, 43, 19, 23);
		frame.getContentPane().add(W1);
		
		JButton W2 = new JButton("");
		W2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		W2.setBackground(Color.WHITE);
		W2.setBounds(83, 197, 19, 23);
		frame.getContentPane().add(W2);
		
		JButton B1 = new JButton("");
		B1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		B1.setBackground(Color.BLACK);
		B1.setForeground(Color.WHITE);
		B1.setBounds(328, 197, 24, 23);
		frame.getContentPane().add(B1);
		
		JButton W3 = new JButton("");
		W3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		W3.setBackground(Color.WHITE);
		W3.setBounds(83, 119, 19, 23);
		frame.getContentPane().add(W3);
		
		JButton B2 = new JButton("");
		B2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		B2.setForeground(Color.WHITE);
		B2.setBackground(Color.BLACK);
		B2.setBounds(328, 119, 24, 23);
		frame.getContentPane().add(B2);
		
		JButton W4 = new JButton("");
		W4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		W4.setBackground(Color.WHITE);
		W4.setBounds(203, 43, 28, 23);
		frame.getContentPane().add(W4);
		
		JButton W5 = new JButton("");
		W5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		W5.setBackground(Color.WHITE);
		W5.setBounds(203, 82, 28, 23);
		frame.getContentPane().add(W5);
		
		JButton B4 = new JButton("");
		B4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		B4.setBackground(Color.BLACK);
		B4.setForeground(Color.WHITE);
		B4.setBounds(203, 152, 28, 23);
		frame.getContentPane().add(B4);
		
		JButton B5 = new JButton("");
		B5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		B5.setForeground(Color.WHITE);
		B5.setBackground(Color.BLACK);
		B5.setBounds(203, 227, 28, 23);
		frame.getContentPane().add(B5);
		
		JButton W6 = new JButton("");
		
		
		
		W6.setBackground(Color.WHITE);
		W6.setForeground(Color.BLACK);
		W6.setBounds(21, 9, 19, 23);
		frame.getContentPane().add(W6);
		
		JButton W7 = new JButton("");
		W7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				W7.setVisible(true);
			}
		});
		W7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		W7.setBackground(Color.WHITE);
		W7.setBounds(203, 0, 28, 23);
		frame.getContentPane().add(W7);
		
		JButton B6 = new JButton("");
		B6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		B6.setBackground(Color.BLACK);
		B6.setForeground(Color.WHITE);
		B6.setBounds(393, 9, 19, 23);
		frame.getContentPane().add(B6);
		
		JButton W8 = new JButton("");
		W8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		W8.setBackground(Color.WHITE);
		W8.setForeground(new Color(0, 0, 0));
		W8.setBounds(21, 227, 19, 23);
		frame.getContentPane().add(W8);
		
		JButton B7 = new JButton("");
		B7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		B7.setForeground(Color.WHITE);
		B7.setBackground(Color.BLACK);
		B7.setBounds(393, 227, 19, 23);
		frame.getContentPane().add(B7);
		
		JButton B8 = new JButton("");
		B8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		B8.setForeground(Color.WHITE);
		B8.setBackground(Color.BLACK);
		B8.setBounds(203, 197, 28, 23);
		frame.getContentPane().add(B8);
		
		JButton W9 = new JButton("");
		W9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		W9.setBackground(Color.WHITE);
		W9.setBounds(21, 119, 19, 23);
		frame.getContentPane().add(W9);
		
		JButton W10 = new JButton("");
		W10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		W10.setBackground(Color.WHITE);
		W10.setBounds(147, 82, 19, 23);
		frame.getContentPane().add(W10);
		
		JButton W11 = new JButton("");
		W11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		W11.setBackground(Color.WHITE);
		W11.setBounds(147, 119, 19, 23);
		frame.getContentPane().add(W11);
		
		JButton W12 = new JButton("");
		W12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		W12.setBackground(Color.WHITE);
		W12.setBounds(146, 152, 20, 23);
		frame.getContentPane().add(W12);
		
		JButton B9 = new JButton("");
		B9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		B9.setForeground(Color.WHITE);
		B9.setBackground(Color.DARK_GRAY);
		B9.setBounds(268, 82, 24, 23);
		frame.getContentPane().add(B9);
		
		JButton B10 = new JButton("");
		B10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		B10.setForeground(Color.WHITE);
		B10.setBackground(Color.BLACK);
		B10.setBounds(268, 119, 24, 23);
		frame.getContentPane().add(B10);
		
		JButton B11 = new JButton("");
		B11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		B11.setForeground(Color.WHITE);
		B11.setBackground(Color.BLACK);
		B11.setBounds(268, 152, 24, 23);
		frame.getContentPane().add(B11);
		
		JButton B12 = new JButton("");
		B12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		B12.setForeground(Color.WHITE);
		B12.setBackground(Color.BLACK);
		B12.setBounds(393, 119, 19, 23);
		frame.getContentPane().add(B12);  
		
		//The buttons at the top represent pieces 
		
		
		//This button clears all of the pieces 
		JButton start = new JButton("START");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				W1.setVisible(false);
				W2.setVisible(false);
				W3.setVisible(false);
				W4.setVisible(false);
				W5.setVisible(false);
				W6.setVisible(false);
				W7.setVisible(false);
				W8.setVisible(false);
				W9.setVisible(false);
				W10.setVisible(false);
				W11.setVisible(false);
				W12.setVisible(false);
				B1.setVisible(false);
				B2.setVisible(false);
				B3.setVisible(false);
				B4.setVisible(false);
				B5.setVisible(false);
				B6.setVisible(false);
				B7.setVisible(false);
				B8.setVisible(false);
				B9.setVisible(false);
				B10.setVisible(false);
				B11.setVisible(false);
				B12.setVisible(false);
	
				
			}
		});
		
		
		
	
		start.setBounds(185, 119, 73, 23);
		frame.getContentPane().add(start);
		
		W6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(W6.isVisible()==false) {
					W6.setVisible(true);
				}
				
				
			}
		});
		
		
		//Button that randomizes
		
		//Once button is clicked the pieces shows up on the board 
		JButton Random = new JButton("Random");
		Random.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int counter = 0;
				counter++;
				if(counter == 1) {
					W1.setVisible(true);
					W3.setVisible(true);
					W4.setVisible(true);
					W5.setVisible(true);
					W6.setVisible(true);
					B1.setVisible(true);
					B2.setVisible(true);
					B3.setVisible(true);
					B4.setVisible(true);
					B6.setVisible(true);
				}
				
				if(counter == 2 ) {
					W2.setVisible(true);
					W7.setVisible(true);
					W8.setVisible(true);
					W9.setVisible(true);
					W10.setVisible(true);
					W11.setVisible(true);
					B7.setVisible(true);
					B8.setVisible(true);
					B9.setVisible(true);
					B10.setVisible(true);
					B11.setVisible(true);
					B12.setVisible(true);
				}
				
			
			}
		});
		
		//-----------end of buttons-----------------
		Random.setBounds(104, 213, 89, 23);
		frame.getContentPane().add(Random);
		
		

		JLabel lblNewLabel = new JLabel("piece");
		lblNewLabel.setIcon(new ImageIcon(img));
		
		lblNewLabel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(lblNewLabel);
	}
}

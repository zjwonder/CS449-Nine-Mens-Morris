import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		
		Image img = new ImageIcon(this.getClass().getResource("/pic4.jpg")).getImage();
		
		JButton btnOne = new JButton("Piece ");
		btnOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnOne.setBounds(76, 61, 85, 21);
		frame.getContentPane().add(btnOne);
		
		JButton btnNewButton = new JButton("Piece");
		btnNewButton.setBounds(278, 61, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Piece");
		btnNewButton_1.setBounds(76, 168, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Player Won ");
		lblNewLabel_1.setBounds(184, 114, 75, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("Piece");
		btnNewButton_2.setBounds(278, 168, 85, 21);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("Mohamud");
		lblNewLabel.setIcon(new ImageIcon(img));
		
		lblNewLabel.setBounds(0, 0, 436, 263);
		frame.getContentPane().add(lblNewLabel);
	}
}

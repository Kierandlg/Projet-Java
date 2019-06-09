package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class connexionframe {

	private JFrame frame;
	private JFrame Acc;
	private JTextField textFielddbname;
	private JTextField textFieldpassword;
	JPanel container = new JPanel();
	JLabel dbnamlabel = new JLabel("db name");
	JLabel passwordlabel = new JLabel("password");
	JButton ok = new JButton("Connexion");
	JLabel titre = new JLabel("     ECE-Gestion");
	Acceuil window ;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					connexionframe window = new connexionframe();
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
	public connexionframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
	
		
		
		container.setBackground(Color.BLACK);
		frame.getContentPane().add(container, BorderLayout.CENTER);
		container.setLayout(null);
		
		textFielddbname = new JTextField();
		textFielddbname.setBounds(170, 66, 114, 20);
		container.add(textFielddbname);
		textFielddbname.setColumns(10);
		
		textFieldpassword = new JTextField();
		textFieldpassword.setBounds(170, 132, 114, 20);
		container.add(textFieldpassword);
		textFieldpassword.setColumns(10);
		dbnamlabel.setForeground(Color.WHITE);
		
	
		dbnamlabel.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 11));
		dbnamlabel.setBounds(71, 66, 72, 17);
		container.add(dbnamlabel);
		passwordlabel.setForeground(Color.WHITE);
		

		passwordlabel.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 11));
		passwordlabel.setBounds(71, 135, 89, 14);
		container.add(passwordlabel);
		
		
		ok.setBackground(Color.GRAY);
		ok.setForeground(Color.RED);
		ok.setBounds(170, 187, 104, 33);
		ok.addActionListener(new validation());
		container.add(ok);
		

		titre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titre.setForeground(Color.RED);
		titre.setBounds(139, 11, 145, 33);
		container.add(titre);
	}
class validation implements ActionListener{
    	
    	public void actionPerformed(ActionEvent e)
    	{
    		String dbname="ecole";
    		String password="";
    		
    		if(textFielddbname.getText().equals(dbname) && textFieldpassword.getText().equals(password))
    		{
    			JOptionPane.showMessageDialog(frame,"connexion réussie");
    			container.removeAll();
    			
    			window = new Acceuil();
    			
				frame.dispose();
    			
    		}
    		else
    		{
    			JOptionPane.showMessageDialog(frame,"echec de la connexion");
    		}
    	}
    }
    
}

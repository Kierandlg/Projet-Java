package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.DAO;
import Controller.DAOFactory;
import Vue.Acceuil.tablemodel;
import modele.Classe;
import modele.Discipline;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AjoutDiscipline {

	private JFrame frame;
	private JTextField textField;

	

	/**
	 * Create the application.
	 */
	public AjoutDiscipline(tablemodel mod,ArrayList<Discipline>arrayd) {
		initialize(mod,arrayd);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(tablemodel mod,ArrayList<Discipline>arrayd) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAjoutDiscipline = new JLabel("Ajout Discipline ");
		lblAjoutDiscipline.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAjoutDiscipline.setBounds(123, 11, 181, 26);
		frame.getContentPane().add(lblAjoutDiscipline);
		
		JLabel NomLabel = new JLabel("Nom");
		NomLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		NomLabel.setBounds(88, 104, 46, 14);
		frame.getContentPane().add(NomLabel);
		
		textField = new JTextField();
		textField.setBounds(186, 103, 118, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				Discipline d;
				DAO<Discipline> ddao = DAOFactory.getDisciplineDAO();
				
				if(textField.getText()!=null)
				{
				d=new Discipline(0,textField.getText());
				
				ddao.insert(d);
				
				arrayd.clear();
				arrayd.addAll(ddao.findAll());
				
				mod.addRow(new Object[] {
						d.get_nom()
				});
				JOptionPane.showMessageDialog(frame,"ajout reussie");
				frame.dispose();
				
				
				}
				
				else {
					JOptionPane.showMessageDialog(frame,"champs manquant");
				}
				
				
			}
		});
		btnAjouter.setBounds(306, 208, 89, 23);
		frame.getContentPane().add(btnAjouter);
	}

}

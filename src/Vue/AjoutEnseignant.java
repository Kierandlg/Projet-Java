package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.DAO;
import Controller.DAOFactory;
import Vue.Acceuil.tablemodel;
import modele.Discipline;
import modele.Enseignant;

public class AjoutEnseignant {

	private JFrame frame;
	private JTextField textFieldNom;
	private JTextField textField;

	ArrayList<Discipline> arraydisc = new ArrayList<Discipline>();
	DAO<Discipline> disciplinedao = DAOFactory.getDisciplineDAO();
	private JTextField textFieldPhoto;
	
	
	
	 /** Create the application.
	 */
	public AjoutEnseignant(tablemodel mod,ArrayList<Enseignant>arrayens) {
		initialize(mod,arrayens);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(tablemodel mod,ArrayList<Enseignant>arrayens) {
		
		arraydisc=disciplinedao.findAll();
		
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 597, 429);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAjoutDunEnseignant = new JLabel("Ajout d'un Enseignant");
		lblAjoutDunEnseignant.setForeground(Color.WHITE);
		lblAjoutDunEnseignant.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 28));
		lblAjoutDunEnseignant.setBounds(120, 28, 374, 35);
		frame.getContentPane().add(lblAjoutDunEnseignant);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNom.setBounds(96, 92, 46, 14);
		frame.getContentPane().add(lblNom);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(247, 89, 117, 20);
		frame.getContentPane().add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPrenom.setBounds(96, 149, 98, 20);
		frame.getContentPane().add(lblPrenom);
		
		textField = new JTextField();
		textField.setBounds(247, 146, 117, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblDiscipline = new JLabel("Discipline");
		
		lblDiscipline.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDiscipline.setBounds(96, 208, 89, 23);
		frame.getContentPane().add(lblDiscipline);
		
		JComboBox comboBox = new JComboBox();
		for(int i=0;i<arraydisc.size();i++)
		{
		comboBox.addItem(arraydisc.get(i).get_nom());
		}
		comboBox.setBounds(247, 205, 117, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Enseignant prof;
				DAO<Enseignant> ensdao = DAOFactory.getEnseignantDAO();
				
				if(textField.getText()!=null
					&& textFieldNom.getText()!=null
					&& comboBox.getSelectedItem()!=null)
				{
				prof=new Enseignant(0,textFieldNom.getText(),textField.getText(),textFieldPhoto.getText());
				prof.setDiscipline((String)comboBox.getSelectedItem());
				
				ensdao.insert(prof);
				
				arrayens.clear();
				arrayens.addAll(ensdao.findAll());
				
				
				mod.addRow(new Object[] {
						prof.get_nom(),prof.get_prenom(),prof.getDiscipline()
				});
				JOptionPane.showMessageDialog(frame,"ajout reussie");
				frame.dispose();
				InfoEnseignant infoEnseignant=new InfoEnseignant(arrayens.get(arrayens.size()-1),arrayens.size()-1,mod);
				
				}
				
				else {
					JOptionPane.showMessageDialog(frame,"champs manquant");
				}
				
			}
		});
		btnAjouter.setBounds(435, 300, 89, 23);
		frame.getContentPane().add(btnAjouter);
		
		JLabel lblUrlPhoto = new JLabel("URL photo");
		lblUrlPhoto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUrlPhoto.setBounds(96, 259, 89, 23);
		frame.getContentPane().add(lblUrlPhoto);
		
		textFieldPhoto = new JTextField();
		textFieldPhoto.setColumns(10);
		textFieldPhoto.setBounds(247, 263, 117, 20);
		frame.getContentPane().add(textFieldPhoto);
	}
}

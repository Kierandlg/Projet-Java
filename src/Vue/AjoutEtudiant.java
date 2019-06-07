package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.DAO;
import Controller.DAOFactory;
import Vue.Acceuil.tablemodel;
import modele.Classe;
import modele.Eleve;
import modele.Niveau;
import java.awt.Font;
import java.awt.Color;

public class AjoutEtudiant {

	private JFrame frame;
	private JTextField NomField;
	private JTextField PrenomField;
	
	DAO<Niveau> niveaudao=DAOFactory.getNiveauDAO();
	ArrayList<Niveau> arrayniv =niveaudao.findAll();
	
	DAO<Classe> classedao=DAOFactory.getClasseDAO();
	ArrayList<Classe> arrayclasse =classedao.findAll();

	DAO<Eleve> elevedao=DAOFactory.getEleveDAO();
	private JTextField textFieldphoto;

	/**
	 * Create the application.
	 */
	public AjoutEtudiant(tablemodel mod,ArrayList<Eleve> arrayel) {
		initialize(mod,arrayel);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(tablemodel mod,ArrayList<Eleve> arrayel) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 671, 517);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNom.setBounds(90, 106, 92, 26);
		frame.getContentPane().add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPrenom.setBounds(90, 168, 92, 26);
		frame.getContentPane().add(lblPrenom);
		
		JLabel lblNiveau = new JLabel("Niveau");
		lblNiveau.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNiveau.setBounds(90, 227, 92, 26);
		frame.getContentPane().add(lblNiveau);
		
		JLabel lblTd = new JLabel("TD");
		lblTd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTd.setBounds(90, 282, 92, 26);
		frame.getContentPane().add(lblTd);
		
		NomField = new JTextField();
		NomField.setBounds(239, 103, 186, 32);
		frame.getContentPane().add(NomField);
		NomField.setColumns(10);
		
		PrenomField = new JTextField();
		PrenomField.setBounds(239, 165, 186, 32);
		frame.getContentPane().add(PrenomField);
		PrenomField.setColumns(10);
		
		JComboBox NiveaucomboBox = new JComboBox();
		JComboBox TDcomboBox = new JComboBox();
		for(int i=0 ;i<arrayniv.size();i++)
		{
			NiveaucomboBox.addItem(arrayniv.get(i).get_nom());
			
		}
		
		NiveaucomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TDcomboBox.removeAllItems();
				for(int j=0 ;j<arrayclasse.size();j++)
				{	
					if(arrayclasse.get(j).getniveau().equals(NiveaucomboBox.getSelectedItem()))
					{
						TDcomboBox.addItem(arrayclasse.get(j).get_nom()+"-"+arrayclasse.get(j).getanneescolaire());
					}
					
				}
				
				
				
			}
		});
		NiveaucomboBox.setBounds(239, 224, 186, 32);
		frame.getContentPane().add(NiveaucomboBox);
		
		
		TDcomboBox.setBounds(239, 279, 186, 32);
		frame.getContentPane().add(TDcomboBox);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(NomField.getText()!=null 
						&& PrenomField.getText()!=null
						&& TDcomboBox.getSelectedItem()!=null
						&& NiveaucomboBox.getSelectedItem()!=null)
				{
			
				Eleve eleve=new Eleve(0,NomField.getText(),PrenomField.getText(),textFieldphoto.getText());
				eleve.setCalsse(((String)TDcomboBox.getSelectedItem()).substring(0, 3));
				eleve.setniveau((String)NiveaucomboBox.getSelectedItem());
				elevedao.insert(eleve);
				mod.addRow(new Object[] {
						eleve.get_nom(),eleve.get_prenom(),eleve.getclasse(),eleve.getniveau()
				});
				arrayel.clear();
				arrayel.addAll(elevedao.findAll());
				JOptionPane.showMessageDialog(frame,"ajout reussie");
				frame.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(frame,"champs manquant");
				}
				
				
			}
		});
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAjouter.setBounds(463, 403, 141, 35);
		frame.getContentPane().add(btnAjouter);
		
		JLabel lblAjourDunEtudiant = new JLabel("Ajo\u00FBt d'un Etudiant");
		lblAjourDunEtudiant.setForeground(Color.WHITE);
		lblAjourDunEtudiant.setBackground(Color.BLACK);
		lblAjourDunEtudiant.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 28));
		lblAjourDunEtudiant.setBounds(162, 25, 334, 56);
		frame.getContentPane().add(lblAjourDunEtudiant);
		
		JLabel lblUrlPhoto = new JLabel("URL Photo");
		lblUrlPhoto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUrlPhoto.setBounds(90, 349, 92, 26);
		frame.getContentPane().add(lblUrlPhoto);
		
		textFieldphoto = new JTextField();
		textFieldphoto.setColumns(10);
		textFieldphoto.setBounds(239, 343, 186, 32);
		frame.getContentPane().add(textFieldphoto);
	}
}

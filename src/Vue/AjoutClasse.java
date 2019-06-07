package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.Color;
import javax.swing.JTextField;

import Controller.DAO;
import Controller.DAOFactory;
import Vue.Acceuil.tablemodel;
import modele.AnneeScolaire;
import modele.Classe;
import modele.Discipline;
import modele.Enseignant;
import modele.Niveau;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AjoutClasse {

	private JFrame frame;
	private JTextField NomField;
	
	ArrayList<AnneeScolaire> arraydisc = new ArrayList<AnneeScolaire>();
	
	DAO<Niveau> niveaudao = DAOFactory.getNiveauDAO();
	
	ArrayList<Niveau> arrayniv=niveaudao.findAll();
	
	Calendar calendar=Calendar.getInstance();

	
	/**
	 * Create the application.
	 */
	public AjoutClasse(tablemodel mod,ArrayList<Classe>arraycl) {
		initialize(mod,arraycl);
		System.out.println(calendar.get(Calendar.YEAR));
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(tablemodel mod,ArrayList<Classe>arraycl) {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 479, 353);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAjoutDunTd = new JLabel("Ajout d'un TD");
		lblAjoutDunTd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAjoutDunTd.setBounds(144, 11, 147, 42);
		frame.getContentPane().add(lblAjoutDunTd);
		
		JLabel NomLabel = new JLabel("Nom");
		NomLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		NomLabel.setBounds(57, 95, 46, 14);
		frame.getContentPane().add(NomLabel);
		
		JLabel lblNiveau = new JLabel("Niveau");
		lblNiveau.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNiveau.setBounds(57, 132, 79, 14);
		frame.getContentPane().add(lblNiveau);
		
		JLabel lblNewLabel = new JLabel("Annee scolaire");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(57, 174, 117, 20);
		frame.getContentPane().add(lblNewLabel);
		
		NomField = new JTextField();
		NomField.setBounds(220, 89, 86, 20);
		frame.getContentPane().add(NomField);
		NomField.setColumns(10);
		
		JComboBox AnneecomboBox = new JComboBox();
		AnneecomboBox.setBounds(220, 177, 86, 20);
		frame.getContentPane().add(AnneecomboBox);
		int i=2000;
		while(i<(int)calendar.get(Calendar.YEAR)+10)
		{
			AnneecomboBox.addItem(i);
			if(i==(int)calendar.get(Calendar.YEAR))AnneecomboBox.setSelectedItem(i);
			i++;
		}
		
		JComboBox NiveaucomboBox = new JComboBox();
		NiveaucomboBox.setBounds(220, 132, 86, 20);
		frame.getContentPane().add(NiveaucomboBox);
		
		for(int j=0 ;j<arrayniv.size();j++)
		{
			NiveaucomboBox.addItem(arrayniv.get(j).get_nom());
			
			
		}
		
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Classe cl;
				DAO<Classe> classedao = DAOFactory.getClasseDAO();
				
				if(NomField.getText()!=null
					&& AnneecomboBox.getSelectedItem()!=null
					&& NiveaucomboBox.getSelectedItem()!=null)
				{
				cl=new Classe(0,NomField.getText());
				cl.setniveau((String)NiveaucomboBox.getSelectedItem());
				cl.setannescolaire(Integer.toString((int)AnneecomboBox.getSelectedItem()));
				
				classedao.insert(cl);
				
				arraycl.clear();
				arraycl.addAll(classedao.findAll());
				
				mod.addRow(new Object[] {
						cl.get_nom(),cl.getniveau(),cl.getanneescolaire()
				});
				JOptionPane.showMessageDialog(frame,"ajout reussie");
				frame.dispose();
				
				
				}
				
				else {
					JOptionPane.showMessageDialog(frame,"champs manquant");
				}
				
				
				
				
				
			}
		});
		btnAjouter.setBounds(337, 264, 89, 23);
		frame.getContentPane().add(btnAjouter);
		
	
	}

}

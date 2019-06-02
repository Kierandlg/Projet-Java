package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import Controller.DAO;
import Controller.DAOFactory;
import Vue.Acceuil.tablemodel;
import modele.Bulletin;
import modele.Classe;
import modele.Detailsbulletin;
import modele.Eleve;

public class InfoEleve {

	private JFrame frame;
	private JTextField NomField;
	private JTextField PrenomField;
	private JComboBox  TDField;
	private JComboBox NiveauField;
	private DefaultTableModel model= new DefaultTableModel(); 
	String[] title = {"Module", "Moyenne Eleve", "Moyenne Classe", "Appreciation"};
	Object[] rowdata = new Object[4];
	private JTextField textField;
	
	private JTable bulletin;

	DAO<Classe> classeDAO = DAOFactory.getClasseDAO();
	ArrayList<Classe> arrayclass =classeDAO.findAll();

	/**
	 * Create the application.
	 */
	public InfoEleve(Eleve e,int row,tablemodel mod) {
		initialize(e,row,mod);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Eleve e,int row,tablemodel mod) {
		
	ArrayList<Bulletin> d = e.getarraybulletin();
	ArrayList<Detailsbulletin> db1= d.get(0).get_arrayDBulletin(); 
	
	model.setColumnIdentifiers(title);
	
		for(int i=0; i<db1.size();i++)
		{
			rowdata[0]=db1.get(i).get_Discipline();
			rowdata[1]=db1.get(i).get_moyenne();
			rowdata[2]=db1.get(i).get_moyenneClasse();
			rowdata[3]=db1.get(i).get_appreciation();
			
			model.addRow(rowdata);
		}
		bulletin =new JTable(model);
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 620, 750);
		frame.getContentPane().setLayout(null);
		
		JPanel container = new JPanel();
		container.setBounds(0, 0, 594, 679);
		frame.getContentPane().add(container);
		container.setLayout(null);
		
		JLabel LabelPhoto = new JLabel("  	photo");
		LabelPhoto.setBounds(35, 52, 146, 157);
		container.add(LabelPhoto);
		LabelPhoto.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel lblEnseignant = new JLabel("Etudiant");
		lblEnseignant.setBounds(56, 5, 137, 26);
		container.add(lblEnseignant);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(218, 65, 92, 26);
		container.add(lblNom);
		
		NomField = new JTextField();
		NomField.setText(e.get_nom());
		NomField.setBounds(322, 62, 186, 32);
		container.add(NomField);
		NomField.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(202, 117, 92, 26);
		container.add(lblPrenom);
		
		PrenomField = new JTextField();
		PrenomField.setText(e.get_prenom());
		PrenomField.setColumns(10);
		PrenomField.setBounds(322, 114, 186, 32);
		container.add(PrenomField);
		
		JLabel lblTD = new JLabel("TD");
		lblTD.setBounds(202, 174, 92, 26);
		container.add(lblTD);
	
		TDField = new JComboBox<>();
		for(int i=0;i<arrayclass.size();i++)
		{
			String str;
			str=arrayclass.get(i).get_nom();
			TDField.addItem(str);		
		}
		for(int i=0;i<TDField.getItemCount();i++)	
		{
			if(TDField.getItemAt(i).equals(e.getclasse()))
				TDField.setSelectedIndex(i);
		}
		TDField.setBounds(322, 167, 186, 32);
		container.add(TDField);
		
		JLabel NiveauLabel = new JLabel("Niveau");
		NiveauLabel.setBounds(202, 221, 92, 26);
		container.add(NiveauLabel);
		
		NiveauField = new JComboBox();
		NiveauField.setModel(new DefaultComboBoxModel(new String[] {"ING1", "ING2", "ING3","ING4","ING5"}));
		for(int i=0;i<NiveauField.getItemCount();i++)	
		{
			if(NiveauField.getItemAt(i).equals(e.getniveau()))
				NiveauField.setSelectedIndex(i);
		}
		
		NiveauField.setBounds(322, 220, 186, 32);
		container.add(NiveauField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 359, 484, 184);
		container.add(scrollPane);
		
		
	
		bulletin.getColumnModel().getColumn(0).setPreferredWidth(116);
		bulletin.getColumnModel().getColumn(1).setPreferredWidth(203);
		bulletin.getColumnModel().getColumn(2).setPreferredWidth(253);
		bulletin.getColumnModel().getColumn(3).setPreferredWidth(450);
		scrollPane.setViewportView(bulletin);
		
		JLabel labelbulletin = new JLabel("Bulletin ");
		labelbulletin.setBounds(56, 312, 92, 26);
		container.add(labelbulletin);
		
		JComboBox comboTrimestre = new JComboBox();
		comboTrimestre.setModel(new DefaultComboBoxModel(new String[] {"1er ", "2eme", "3eme"}));
		comboTrimestre.setBounds(420, 309, 88, 32);
		container.add(comboTrimestre);
		
		JLabel labelTrimestre = new JLabel("Trimestre:");
		labelTrimestre.setBounds(303, 312, 96, 26);
		container.add(labelTrimestre);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DAO<Eleve> EleveDAO = DAOFactory.getEleveDAO();
				EleveDAO.delete(e);
				mod.removeRow(row);
				frame.dispose();
			}
		});
		btnSupprimer.setBounds(40, 611, 141, 35);
		container.add(btnSupprimer);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent argo) {
				DAO<Eleve> EleveDAO = DAOFactory.getEleveDAO();
				e.set_nom(NomField.getText());
				e.set_prenom(PrenomField.getText());
				e.setCalsse((String)TDField.getSelectedItem());
				e.setniveau((String)NiveauField.getSelectedItem());
				EleveDAO.update(e);
				
			}
		});
		btnEnregistrer.setBounds(399, 611, 141, 35);
		container.add(btnEnregistrer);
		
	
	}
}

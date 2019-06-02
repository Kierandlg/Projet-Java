package Vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import modele.Classe;
import modele.Discipline;
import modele.Enseignant;

public class InfoEnseignant {

	private JFrame frame;
	private JTextField NomField;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private Object[] rowdata = new Object[2];
	private DefaultTableModel model= new DefaultTableModel(); 
	String[] title = {"Classe","Niveau"};
	
	JComboBox Disciplinebox; 
	DAO<Discipline> disciplinedao=DAOFactory.getDisciplineDAO();
	ArrayList<Discipline> arraydisc = disciplinedao.findAll();
	
	

	/**
	 * Create the application.
	 */
	public InfoEnseignant(Enseignant ens,int row,tablemodel mod) {
		initialize(ens,row,mod);
		frame.setVisible(true);
		
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Enseignant ens,int row,tablemodel mod) {
		
		model.setColumnIdentifiers(title);
		for(int i=0; i<ens.getClasse().size(); i++)
		{
			rowdata[0]=ens.getClasse().get(i).get_nom(); 
			rowdata[1]=ens.getClasse().get(i).getniveau(); 
			
			
			model.addRow(rowdata);
		}
		//on les met dans le tableau
		table=new JTable(model);
		
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 620, 679);
		frame.getContentPane().setLayout(null);
		
		//container principale
		JPanel container = new JPanel();
		container.setBounds(0, 0, 594, 608);
		frame.getContentPane().add(container);
		container.setLayout(null);
		
		//photo de profil
		JLabel LabelPhoto = new JLabel("     photo");
		LabelPhoto.setBounds(35, 52, 146, 157);
		container.add(LabelPhoto);
		LabelPhoto.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel lblEnseignant = new JLabel("Enseignant");
		lblEnseignant.setBounds(56, 5, 137, 26);
		container.add(lblEnseignant);
		
		//Nom 
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(218, 65, 92, 26);
		container.add(lblNom);
		
		NomField = new JTextField();
		NomField.setText(ens.get_nom());
		NomField.setBounds(322, 62, 186, 32);
		container.add(NomField);
		NomField.setColumns(10);
		
		
		//Prenom
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(202, 117, 92, 26);
		container.add(lblPrenom);
		
		textField = new JTextField();
		textField.setText(ens.get_prenom());
		textField.setColumns(10);
		textField.setBounds(322, 114, 186, 32);
		container.add(textField);
		
		
		//ComboBow discipline
		JLabel lblDiscpiline = new JLabel("Discpiline");
		lblDiscpiline.setBounds(202, 174, 92, 26);
		container.add(lblDiscpiline);
		
		Disciplinebox = new JComboBox<>();
		for(int i=0;i<arraydisc.size();i++)
		{
			String str;
			str=arraydisc.get(i).get_nom();
			Disciplinebox.addItem(str);		
		}
		for(int i=0;i<Disciplinebox.getItemCount();i++)	
		{
			if(Disciplinebox.getItemAt(i).equals(ens.getDiscipline()))
				Disciplinebox.setSelectedIndex(i);
		}
		Disciplinebox.setBounds(322, 177, 186, 32);
		container.add(Disciplinebox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(89, 286, 408, 148);
		container.add(scrollPane);
		
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(101);
		table.getColumnModel().getColumn(1).setPreferredWidth(116);
		scrollPane.setViewportView(table);
		
		
		//Boutton suppression
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAO<Enseignant> Enseignantdao=DAOFactory.getEnseignantDAO();
				Enseignantdao.delete(ens);
				mod.removeRow(row);
				frame.dispose();
			}
		});
		btnSupprimer.setBounds(35, 540, 141, 35);
		container.add(btnSupprimer);
		
		
		// button editer bulletin 
		JButton btnEditerBulletin = new JButton("Editer Bulletin");
		btnEditerBulletin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				EditerBulletin editerBulletin=new EditerBulletin(ens);
			}
		});
		btnEditerBulletin.setBounds(218, 540, 165, 35);
		container.add(btnEditerBulletin);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAO<Enseignant> Enseignantdao=DAOFactory.getEnseignantDAO();
				ens.set_nom(NomField.getText());
				ens.set_prenom(textField .getText());
				ens.setDiscipline(textField_1.getText());
				Enseignantdao.update(ens);
			}
		});
		btnEnregistrer.setBounds(417, 540, 141, 35);
		container.add(btnEnregistrer);
		
		JButton btnAjouterClasse = new JButton("Ajouter Classe");
		btnAjouterClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AjoutClasseEns ajoutClasseEns =new AjoutClasseEns(ens);
				
				
				
			}
		});
		btnAjouterClasse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAjouterClasse.setBounds(89, 439, 141, 35);
		container.add(btnAjouterClasse);
		
		
	}
}

package Vue;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Vue.Acceuil.tablemodel;
import modele.Classe;
import modele.Eleve;

public class infoClasse {

	private JFrame frame;
	private JTable table;
	JPanel panel = new JPanel();
	JLabel NomClasse = new JLabel("New label");
	JLabel NiveauClasse = new JLabel("New label");
	JLabel AnneeScolaire = new JLabel("New label");
	Object[] data = new Object[2];
	String[] title = {"Nom","Prenom"}; 
	DefaultTableModel model = new DefaultTableModel();

	
	

	/**
	 * Create the application.
	 */
	public infoClasse(Classe A) {
		initialize(A);
		frame.setVisible(true);
	}
	
	
	
	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Classe A) {
		
		/*Remplissage des eleves appatement à la classe*/
		model.setColumnIdentifiers(title);
		for(int i=0 ;i<A.getarrayeleve().size();i++)
		{
			data[0]=A.getarrayeleve().get(i).get_nom();
			data[1]=A.getarrayeleve().get(i).get_prenom();
			
			model.addRow(data);
		}
		
		table = new JTable();
		table.setModel(model);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 510);
		frame.getContentPane().setLayout(null);
		
		
		panel.setBounds(0, 0, 674, 439);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		NomClasse.setText(A.get_nom());
		NomClasse.setBounds(45, 21, 92, 26);
		panel.add(NomClasse);
		
		NiveauClasse.setText(A.getniveau());
		NiveauClasse.setBounds(185, 21, 92, 26);
		panel.add(NiveauClasse);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(80, 100, 506, 247);
		panel.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		
		AnneeScolaire.setText(A.getanneescolaire());
		AnneeScolaire.setBounds(322, 21, 92, 26);
		panel.add(AnneeScolaire);
	}
}

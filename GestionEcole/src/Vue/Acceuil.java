package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.DAO;
import Controller.DAOFactory;
import modele.Eleve;
import modele.Enseignant;

public class Acceuil extends JFrame implements ActionListener{

	JPanel container = new JPanel();
	JLabel labelTitre = new JLabel("ECE");
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	JPanel ongletEleve = new JPanel();
	JPanel ongletEnseignant = new JPanel();
	JPanel ongletClasse = new JPanel();
	JPanel ongletNiveau = new JPanel();
	JPanel[] panneau= {ongletEleve, ongletEnseignant,ongletClasse,ongletNiveau}; 
	JScrollPane scrollPane = new JScrollPane();
	JButton Searchbutton = new JButton("Rechercher");
	DAO<Eleve> EleveDAO = DAOFactory.getEleveDAO();
	DAO<Enseignant> EnseignanteDAO = DAOFactory.getEnseignantDAO();
	protected ArrayList<Eleve> arrayeleve = new ArrayList<Eleve>(); 
	protected ArrayList<Enseignant> arrayens = new ArrayList<Enseignant>(); 
	//protected ArrayList<Eleve> searcharrayeleve = new ArrayList<Eleve>(); 
	DefaultTableModel model = new DefaultTableModel(); 
	DefaultTableModel model2 = new DefaultTableModel(); 
	Object[] rowdata = new Object[4];
	String[] title = {"Nom", "Prenom", "TD", "Niveau"};
	String[] title2 = {"Nom", "Prenom", "Discipline", "Classes"};
	String [] combodata= {};	
	JComboBox Classeens= new JComboBox(); 
	
	
	
	
	
	private JFrame frame;
	private JTable table;
	private JTextField searchField;
	private JTable tableens;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acceuil window = new Acceuil();
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
	public Acceuil() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//recupération des éleves
		arrayeleve= EleveDAO.findAll();
		
		model.setColumnIdentifiers(title);
		for(int i=0; i<arrayeleve.size(); i++)
		{
			rowdata[0]=arrayeleve.get(i).get_nom(); 
			rowdata[1]=arrayeleve.get(i).get_prenom(); 
			rowdata[2]=arrayeleve.get(i).getclasse();
			rowdata[3]=arrayeleve.get(i).getniveau(); 
			
			model.addRow(rowdata);
		}
		//on les met dans le tableau
		table=new JTable(model);
		
		//recupération étudiant
		
		arrayens=EnseignanteDAO.findAll(); getContentPane();
		model2.setColumnIdentifiers(title2);
		for(int i=0; i<arrayens.size(); i++)
		{
			rowdata[0]=arrayens.get(i).get_nom(); 
			rowdata[1]=arrayens.get(i).get_prenom(); 
			rowdata[2]=arrayens.get(i).getDiscipline();
			//rowdata[3]=arrayens.get(i).getniveau(); 
			
			model2.addRow(rowdata);
		}
		tableens = new JTable(model2);
		tableens.getColumn("Classes").setCellEditor(new DefaultCellEditor(Classeens));
		
		 
	
		
		
		frame = new JFrame();
		
		frame.setBounds(100, 100, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		container.setBackground(Color.GRAY);
		frame.getContentPane().add(container, BorderLayout.CENTER);
		container.setLayout(null);
		labelTitre.setBounds(454, 21, 67, 35);
		
	
		labelTitre.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 29));
		container.add(labelTitre);
		
		
		tabbedPane.setBounds(56, 96, 867, 565);
		container.add(tabbedPane);
		
		
		tabbedPane.addTab("Eleves", null, ongletEleve, null);
		ongletEleve.setLayout(null);
		
		
		scrollPane.setBounds(42, 21, 782, 483);
		ongletEleve.add(scrollPane);
		
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(164);
		table.getColumnModel().getColumn(1).setPreferredWidth(188);
		table.getColumnModel().getColumn(2).setPreferredWidth(87);
		table.getColumnModel().getColumn(3).setPreferredWidth(169);
		scrollPane.setViewportView(table);
		
		
		tabbedPane.addTab("Enseignant", null, ongletEnseignant, null);
		ongletEnseignant.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(70, 42, 747, 447);
		ongletEnseignant.add(scrollPane_1);
		
		
		
		tableens.getColumnModel().getColumn(0).setPreferredWidth(141);
		tableens.getColumnModel().getColumn(1).setPreferredWidth(165);
		tableens.getColumnModel().getColumn(2).setPreferredWidth(201);
		tableens.getColumnModel().getColumn(3).setPreferredWidth(153);
		scrollPane_1.setViewportView(tableens);
		
		
		Searchbutton.setBounds(134, 836, 141, 35);
		Searchbutton.addActionListener(this);
		container.add(Searchbutton);
		
		searchField = new JTextField();
		searchField.setBounds(362, 837, 431, 32);
		container.add(searchField);
		searchField.setColumns(10);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== Searchbutton)
		{	Eleve el;
			String t =searchField.getText();
			
			
			for(int j = model.getRowCount()-1;j>=0;j-- )
			{
				model.removeRow(j);
			}
			
			for(int i=0;i<arrayeleve.size(); i++)
			{System.out.println(t);
				el=arrayeleve.get(i);
				if(Integer.toString(el.get_id()).equals(t) 
						|| el.get_nom().equals(t) 
						|| el.get_prenom().equals(t) 
						|| el.getclasse().equals(t) 
						|| el.getniveau().equals(t))
				{
					
					rowdata[0]=el.get_nom(); 
					rowdata[1]=el.get_prenom(); 
					rowdata[2]=el.getclasse();
					rowdata[3]=el.getniveau(); 
					
					model.addRow(rowdata);
				}
			
			}
			table.setModel(model);
			
			
			
			
		}
	}
}
	

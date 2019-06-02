package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Controller.DAO;
import Controller.DAOFactory;
import modele.Classe;
import modele.Eleve;
import modele.Enseignant;

public class Acceuil extends JFrame implements ActionListener{

	JPanel container = new JPanel();
	JLabel labelTitre = new JLabel("ECE");
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	JPanel ongletEleve = new JPanel();
	JPanel ongletEnseignant = new JPanel();
	JPanel OngletClasse = new JPanel();
	JPanel ongletNiveau = new JPanel();
	JPanel[] panneau= {ongletEleve, ongletEnseignant, OngletClasse,ongletNiveau}; 
	JScrollPane scrollPane = new JScrollPane();
	JButton Searchbutton = new JButton("Rechercher");
	
	DAO<Eleve> EleveDAO = DAOFactory.getEleveDAO();
	DAO<Enseignant> EnseignanteDAO = DAOFactory.getEnseignantDAO();
	DAO<Classe> ClasseDAO = DAOFactory.getClasseDAO();
	
	
	protected ArrayList<Eleve> arrayeleve = new ArrayList<Eleve>(); 
	protected ArrayList<Enseignant> arrayens = new ArrayList<Enseignant>(); 
	protected ArrayList<Classe> arrayclasse = new ArrayList<Classe>(); 
	
	tablemodel model = new tablemodel(); 
	tablemodel model2 = new tablemodel(); 
	tablemodel model3 = new tablemodel(); 
	
	Object[] rowdata = new Object[4];
	
	String[] title = {"Nom", "Prenom", "TD", "Niveau"};
	String[] title2 = {"Nom", "Prenom", "Discipline", "Classes"};
	String[] title3 = {"Nom", "Niveau", "Annee Scolaire"};
	
	String [] combodata= {};	
	JComboBox Classeens= new JComboBox(); 
	
	InfoEnseignant infoEns ;
	infoClasse infoclasse ;
	InfoEleve infoEleve; 
	
	
	
	private JFrame frame;
	private JTextField searchField;
	private JTable tableens;
	private JTable tableeleve;
	private JTable tableclasse;

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
		tableeleve=new JTable(model);
		tableeleve.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableeleve.addMouseListener(new TableauListener());
		
	
		
		//recupération enseignant
		
		arrayens=EnseignanteDAO.findAll(); 
		model2.setColumnIdentifiers(title2);
		for(int i=0; i<arrayens.size(); i++)
		{
			rowdata[0]=arrayens.get(i).get_nom(); 
			rowdata[1]=arrayens.get(i).get_prenom(); 
			rowdata[2]=arrayens.get(i).getDiscipline();
			
			model2.addRow(rowdata);
		}
		tableens = new JTable(model2);
		tableens.addMouseListener(new TableauListener());
		
		//recupération des classes 
		
		arrayclasse=ClasseDAO.findAll(); 
		model3.setColumnIdentifiers(title3);
		for(int i=0; i<arrayclasse.size(); i++)
		{
			rowdata[0]=arrayclasse.get(i).get_nom(); 
			rowdata[1]=arrayclasse.get(i).getniveau();
			rowdata[2]=arrayclasse.get(i).getanneescolaire();
			
			model3.addRow(rowdata);
		}
		tableclasse = new JTable(model3);
		tableclasse.addMouseListener(new TableauListener());
		
		 
	
		
		
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
		
		
		
		tableeleve.getColumnModel().getColumn(0).setPreferredWidth(164);
		tableeleve.getColumnModel().getColumn(1).setPreferredWidth(188);
		tableeleve.getColumnModel().getColumn(2).setPreferredWidth(87);
		tableeleve.getColumnModel().getColumn(3).setPreferredWidth(169);
		scrollPane.setViewportView(tableeleve);
		
		
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
		
		
		tabbedPane.addTab("Classe", null, OngletClasse, null);
		tabbedPane.setBackgroundAt(2, Color.WHITE);
		OngletClasse.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(64, 21, 747, 447);
		OngletClasse.add(scrollPane_2);
		
		
		tableclasse.getColumnModel().getColumn(0).setPreferredWidth(131);
		tableclasse.getColumnModel().getColumn(1).setPreferredWidth(147);
		tableclasse.getColumnModel().getColumn(2).setPreferredWidth(193);
		scrollPane_2.setViewportView(tableclasse);
		
		
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
		{	
			if(ongletEleve.isVisible())
			{
				Eleve el;
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
				tableeleve.setModel(model);
			}
			
			if(ongletEnseignant.isVisible())
			{
				Enseignant en;
				String t =searchField.getText();
				
				
				for(int j = model2.getRowCount()-1;j>=0;j-- )
				{
					model2.removeRow(j);
				}
				
				for(int i=0;i<arrayens.size(); i++)
				{System.out.println(arrayens.size());
					en=arrayens.get(i);
					if(Integer.toString(en.get_id()).equals(t) 
							|| en.get_nom().equals(t) 
							|| en.get_prenom().equals(t) 
							|| en.getDiscipline().equals(t) 
							)
					{
						
						rowdata[0]=en.get_nom(); 
						rowdata[1]=en.get_prenom(); 
						rowdata[2]=en.getDiscipline();
						
						model2.addRow(rowdata);
					}
				
				}
				tableens.setModel(model2);
			}
			
		
			
			
			
			
		}
	}
	
	
	
	
	public class TableauListener implements MouseListener {
		 
		 
	    @Override
	    public void mouseClicked(java.awt.event.MouseEvent e) {
	    	
	     
	         
	    }
	 
	    @Override
	    public void mousePressed(java.awt.event.MouseEvent e) {
	    	  
	    	//Onglet Enseignant 
	    	if(tabbedPane.getSelectedComponent()==ongletEnseignant)
	    	{
	    		int NumLigne = tableens.getSelectedRow();
	        System.out.println(NumLigne);
	        infoEns = new InfoEnseignant(arrayens.get(NumLigne),NumLigne,model2);
	    	}
	    	
	    	//onglet Eleve
	    	if(tabbedPane.getSelectedComponent()==OngletClasse)
	    	{
	    		int NumLigne = tableclasse.getSelectedRow();
	        System.out.println(NumLigne);
	        infoclasse= new infoClasse(arrayclasse.get(NumLigne)); 
	    	}
	    	
	    	//onglet Eleve
	    	if(tabbedPane.getSelectedComponent()==ongletEleve)
	    	{
	    		int NumLigne = tableeleve.getSelectedRow();
	        System.out.println(NumLigne);
	       infoEleve= new InfoEleve(arrayeleve.get(NumLigne),NumLigne,model);
	    	}
	    	
	    	
	        
	    }
	 
	    @Override
	    public void mouseReleased(java.awt.event.MouseEvent e) {
	        //System.out.println("Hello World3");
	         
	    }
	 
	    @Override
	    public void mouseEntered(java.awt.event.MouseEvent e) {
	        //entrer cellule
	         
	    }
	 
	    @Override
	    public void mouseExited(java.awt.event.MouseEvent e) {
	        //sortie cellule
	         
	    }
	
	
	}
	
	class tablemodel extends DefaultTableModel{
		
		public boolean isCellEditable(int rowIndex,int columnIndex){
			return false;
		}

	}
}
	

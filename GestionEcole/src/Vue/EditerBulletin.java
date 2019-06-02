package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modele.Bulletin;
import modele.Enseignant;

public class EditerBulletin {

	private JFrame frame;
	
	
	JComboBox comboBoxClasse = new JComboBox();
	JComboBox comboBoxEtudiant = new JComboBox();
	String[] title = {"Semstre","Annee"}; 
	Object[] rowdata= new Object[2];
	DefaultTableModel model=new DefaultTableModel();
	private JTable tableBulletin;
	
	Enseignant enseignant;
	Bulletin bulletin;
	
	
	public EditerBulletin(Enseignant ens) {
		initialize(ens);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Enseignant ens) {
		
		enseignant=ens;
		frame = new JFrame();
		frame.setBounds(100, 100, 622, 611);
		frame.getContentPane().setLayout(null);
		
		JPanel container = new JPanel();
		container.setBounds(0, 0, 596, 540);
		frame.getContentPane().add(container);
		container.setLayout(null);
		
		JLabel lblClasse = new JLabel("Classe");
		lblClasse.setBounds(72, 21, 92, 26);
		container.add(lblClasse);

		
		comboBoxClasse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					comboBoxEtudiant.removeAllItems();
					int index=comboBoxClasse.getSelectedIndex();
					for(int i=0;i<ens.getClasse().get(index).getarrayeleve().size();i++)
					{
						String str1=ens.getClasse().get(index).getarrayeleve().get(i).get_nom();
						String str2=ens.getClasse().get(index).getarrayeleve().get(i).get_prenom();
						comboBoxEtudiant.addItem(str1+" "+ str2);
					}
					int comboetudiantindex=comboBoxEtudiant.getSelectedIndex();
					model.setColumnIdentifiers(title);
					
					for(int j=0;j<ens.getClasse().get(index).getarrayeleve().get(comboetudiantindex).getarraybulletin().size();j++)
					{
						rowdata[0] = j+1+" Semestre";
						rowdata[1] = "2019";
						model.addRow(rowdata);
					}
					
					
				
			}
		});
		
		for(int i=0;i<ens.getClasse().size();i++)
		{
			comboBoxClasse.addItem(ens.getClasse().get(i).get_nom() +"-"+ ens.getClasse().get(i).getniveau());
		}
		comboBoxClasse.setBounds(200, 18, 202, 32);
		container.add(comboBoxClasse);
		
		JLabel lblEtudiant = new JLabel("Etudiant");
		lblEtudiant.setBounds(72, 74, 92, 26);
		container.add(lblEtudiant);
		
		
		//mettre un listener sur le premier combo
		comboBoxEtudiant.setBounds(200, 71, 202, 32);
		container.add(comboBoxEtudiant);
		
		
		
		JLabel lblListeBulletin = new JLabel("Liste Bulletin");
		lblListeBulletin.setBounds(48, 150, 147, 26);
		container.add(lblListeBulletin);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 197, 486, 232);
		container.add(scrollPane);
		
		tableBulletin = new JTable(model);
		tableBulletin.addMouseListener(new TableauListener());
		scrollPane.setViewportView(tableBulletin);
	}
	
	
	public class TableauListener implements MouseListener {
		 
		 
	    @Override
	    public void mouseClicked(java.awt.event.MouseEvent e) {
	    	
	     
	         
	    }
	 
	    @Override
	    public void mousePressed(java.awt.event.MouseEvent e) {
	    	
	    	int NumLigne = tableBulletin.getSelectedRow();
	    	int comboetudiantindex=comboBoxEtudiant.getSelectedIndex();
	    	int index=comboBoxClasse.getSelectedIndex();
	    	bulletin=enseignant.getClasse().get(index).getarrayeleve().get(comboetudiantindex).getarraybulletin().get(NumLigne);
	    	for(int i=0; i<bulletin.get_arrayDBulletin().size();i++)
	    	{
	    		if(bulletin.get_arrayDBulletin().get(i).get_Discipline().equals(enseignant.getDiscipline()))
	    		{
	    				InfonDBulletin DtailsB = new InfonDBulletin(bulletin.get_arrayDBulletin().get(i),enseignant.getClasse().get(index).getarrayeleve().get(comboetudiantindex));
	    		}
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
	
	
	
	
}

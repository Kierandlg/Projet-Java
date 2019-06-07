package Vue;

import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.sql.rowset.FilteredRowSet;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modele.Bulletin;
import modele.Enseignant;
import java.awt.Font;

public class EditerBulletin {

	private JFrame frame;
	
	

	JComboBox comboBoxEtudiant = new JComboBox();
	String[] title = {"Semstre","Annee"}; 
	Object[] rowdata= new Object[2];
	DefaultTableModel model=new DefaultTableModel();
	private JTable tableBulletin;
	
	Enseignant enseignant;
	int indiceTD;
	Bulletin bulletin;
	ComboEtudiantListener comboEtudiantListener=new ComboEtudiantListener();
	
	public EditerBulletin(Enseignant ens,int TD) {
		initialize(ens,TD);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Enseignant ens,int TD) {
		
		indiceTD=TD;
	
		model.setColumnIdentifiers(title);
		enseignant=ens;
		frame = new JFrame();
		frame.setBounds(100, 100, 622, 611);
		frame.getContentPane().setLayout(null);
		
		JPanel container = new JPanel();
		container.setBounds(0, 0, 596, 540);
		frame.getContentPane().add(container);
		container.setLayout(null);
		
	
		
		JLabel lblEtudiant = new JLabel("Etudiant");
		lblEtudiant.setBounds(72, 74, 92, 26);
		container.add(lblEtudiant);
		
		for(int i=0; i<ens.getClasse().get(TD).getarrayeleve().size();i++)	
		{
			String str1= ens.getClasse().get(TD).getarrayeleve().get(i).get_nom();
			String str2= ens.getClasse().get(TD).getarrayeleve().get(i).get_prenom();
			comboBoxEtudiant.addItem(str1 + " "+ str2);
		}

		for(int j=0;j<ens.getClasse().get(TD).getarrayeleve().get(comboBoxEtudiant.getSelectedIndex()).getarraybulletin().size();j++)
		{
			rowdata[0] = ens.getClasse().get(TD).getarrayeleve().get(comboBoxEtudiant.getSelectedIndex()).getarraybulletin().get(j).get_trimestre() + " trimestre";
			rowdata[1] = ens.getClasse().get(TD).getniveau()+"-"+ ens.getClasse().get(TD).getanneescolaire();
			model.addRow(rowdata);
		}
		comboBoxEtudiant.setBounds(200, 71, 202, 32);
		comboBoxEtudiant.addActionListener(comboEtudiantListener);
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
		
		JLabel TDlabel = new JLabel();
		TDlabel.setText(ens.getClasse().get(TD).get_nom());
		TDlabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		TDlabel.setBounds(29, 11, 92, 26);
		container.add(TDlabel);
		
		JLabel NIveauLabel = new JLabel();
		NIveauLabel.setText(ens.getClasse().get(TD).getniveau());
		NIveauLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		NIveauLabel.setBounds(139, 11, 130, 26);
		container.add(NIveauLabel);
	}
	
	
	
	
	
	
	public class ComboEtudiantListener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e)
		{
			
		
			int comboetudiantindex=comboBoxEtudiant.getSelectedIndex();
		
			//on vide le model
			for(int j = model.getRowCount()-1;j>=0;j-- )
			{
				model.removeRow(j);
			}
			for(int j=0;j<enseignant.getClasse().get(indiceTD).getarrayeleve().get(comboetudiantindex).getarraybulletin().size();j++)
				{
					rowdata[0] = enseignant.getClasse().get(indiceTD).getarrayeleve().get(comboetudiantindex).getarraybulletin().get(j).get_trimestre() + " trimestre";
					rowdata[1] = enseignant.getClasse().get(indiceTD).getniveau()+"-"+ enseignant.getClasse().get(indiceTD).getanneescolaire();
					model.addRow(rowdata);
				}
			
		}
		}
	
	
	
	public class TableauListener implements MouseListener {
		 
		 
	    @Override
	    public void mouseClicked(java.awt.event.MouseEvent e) {
	    	
	     
	         
	    }
	 
	    @Override
	    public void mousePressed(java.awt.event.MouseEvent e) {
	    	
	    	int NumLigne = tableBulletin.getSelectedRow();
	    	int comboetudiantindex=comboBoxEtudiant.getSelectedIndex();
	    	int index=indiceTD;
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
	        
	    }
	 
	    @Override
	    public void mouseEntered(java.awt.event.MouseEvent e) {
	       
	         
	    }
	 
	    @Override
	    public void mouseExited(java.awt.event.MouseEvent e) {
	       
	         
	    }
	
	
	}
}

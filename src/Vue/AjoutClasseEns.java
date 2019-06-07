package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Controller.DAO;
import Controller.DAOFactory;
import modele.Classe;
import modele.Enseignant;

public class AjoutClasseEns {

	private JFrame frame;

	
	DAO<Classe> classedao=DAOFactory.getClasseDAO();
	ArrayList<Classe> arrayclasse= classedao.findAll(); 
	
	String[] title = {"TD","Niveau","Annee scolaire"};
	Object[] rowdata = new Object[4]; 
	tablemodel model = new tablemodel(); 
	JTable tableclasse;
	
	

	/**
	 * Create the application.
	 */
	public AjoutClasseEns(Enseignant ens,DefaultTableModel md) {
		initialize(ens,md);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Enseignant ens,DefaultTableModel md) {
		
		model.setColumnIdentifiers(title);
		for(int i=0; i<arrayclasse.size(); i++)
		{
			rowdata[0]=arrayclasse.get(i).get_nom(); 
			rowdata[1]=arrayclasse.get(i).getniveau();
			rowdata[2]=arrayclasse.get(i).getanneescolaire();
			
			model.addRow(rowdata);
		}
		tableclasse = new JTable(model);
		tableclasse.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	

	
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 611, 461);
		
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 585, 390);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 84, 484, 229);
		panel.add(scrollPane);
		
	
		scrollPane.setViewportView(tableclasse);
		
		JLabel lblAjoutDeClasse = new JLabel("Ajout de Classes");
		lblAjoutDeClasse.setBounds(21, 21, 171, 26);
		panel.add(lblAjoutDeClasse);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DAO<Enseignant> ensdao=DAOFactory.getEnseignantDAO();
				if (tableclasse.getSelectedRows()!=null) {

	                 int[] selectedrows = tableclasse.getSelectedRows();

	                 for (int i = 0; i < selectedrows.length; i++)
	                {

	                    ens.addClasse(arrayclasse.get(selectedrows[i]));
	                    md.addRow(new Object[] {
	                    		arrayclasse.get(selectedrows[i]).get_nom(),
	                    		arrayclasse.get(selectedrows[i]).getniveau()
	        				});
	                   frame.dispose();

	                }
	               ensdao.update(ens);
	            }
				
			}
		});
		btnAjouter.setBounds(51, 334, 141, 35);
		panel.add(btnAjouter);
	
	}
	
class tablemodel extends DefaultTableModel{
		
		public boolean isCellEditable(int rowIndex,int columnIndex){
			return false;
		}

	}


    	
    	
        
    }



package Vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.DAO;
import Controller.DAOFactory;
import modele.Detailsbulletin;
import modele.Eleve;
import modele.Evaluation;
import javax.swing.JTextArea;

public class InfonDBulletin {

	private JFrame frame;
	private JTable DetailsBulletintable;
	JTextArea textArea = new JTextArea();

	Object[] rowdata=new Object[3];
	String[] title= {"nom","note","appreciation"};
	DefaultTableModel model=new DefaultTableModel();
	
	DAO<Detailsbulletin> dbulletindao=DAOFactory.getDetailsbulletinDAO();
	DAO<Evaluation> evaldao=DAOFactory.getEvaluationDAO();
	
	/**
	 * Create the application.
	 */
	public InfonDBulletin(Detailsbulletin db,Eleve el) {
		initialize(db,el);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Detailsbulletin db,Eleve el) {
		
		model.setColumnIdentifiers(title);
		for(int i=0;i<db.get_eval().size();i++)
		{
			rowdata[0]=db.get_eval().get(i).get_nom();
			rowdata[1]=db.get_eval().get(i).get_note();
			rowdata[2]=db.get_eval().get(i).get_appreciation();
			
			model.addRow(rowdata);
		}
		DetailsBulletintable = new JTable(model);
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 643, 603);
	
		frame.getContentPane().setLayout(null);
		
		JPanel container = new JPanel();
		container.setBounds(0, 21, 617, 511);
		frame.getContentPane().add(container);
		container.setLayout(null);
		
		JLabel Nomlabel = new JLabel();
		Nomlabel.setText(el.get_nom());
		Nomlabel.setBounds(21, 21, 92, 26);
		container.add(Nomlabel);
		
		JLabel Prenomlabel = new JLabel();
		Prenomlabel.setText(el.get_prenom());
		Prenomlabel.setBounds(139, 21, 129, 26);
		container.add(Prenomlabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 208, 452, 209);
		container.add(scrollPane);
		
		
		scrollPane.setViewportView(DetailsBulletintable);
		
		JButton btnAjouterEvaluation = new JButton("Ajouter Evaluation");
		btnAjouterEvaluation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjoutEvaluation ajoutEvaluation= new AjoutEvaluation(model,db);
			}
		});
		btnAjouterEvaluation.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnAjouterEvaluation.setBounds(21, 438, 210, 35);
		container.add(btnAjouterEvaluation);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<DetailsBulletintable.getRowCount();i++)
				{	
					int row=i;
					db.get_eval().get(row).set_nom((String)DetailsBulletintable.getValueAt(row, 0));
					db.get_eval().get(row).set_note((String)DetailsBulletintable.getValueAt(row, 1));
					db.get_eval().get(row).set_appreciation((String)DetailsBulletintable.getValueAt(row, 2));
					evaldao.update(db.get_eval().get(row)); 
				}
				db.set_appreciation(textArea.getText());
				dbulletindao.update(db);
				frame.dispose();
		
			}
		});
		btnEnregistrer.setBounds(291, 438, 143, 35);
		container.add(btnEnregistrer);
		
		JButton btnNewButton = new JButton("Supprimer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int NumLigne=DetailsBulletintable.getSelectedRow();
				evaldao.delete(db.get_eval().get(NumLigne));
				db.get_eval().remove(NumLigne);
				model.removeRow(NumLigne);
				
				
			}
		});
		btnNewButton.setBounds(455, 438, 141, 35);
		container.add(btnNewButton);
		
		JLabel lblEvaluations = new JLabel("Evaluations");
		lblEvaluations.setBounds(63, 161, 117, 26);
		container.add(lblEvaluations);
		
		JLabel lblApprciationGnral = new JLabel("Appreciation General");
		lblApprciationGnral.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblApprciationGnral.setBounds(53, 87, 178, 35);
		container.add(lblApprciationGnral);
		
		textArea.setText(db.get_appreciation());
		textArea.setBounds(237, 95, 210, 45);
		container.add(textArea);
	}
}

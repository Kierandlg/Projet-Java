package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.DAO;
import Controller.DAOFactory;
import modele.Detailsbulletin;
import modele.Evaluation;

public class AjoutEvaluation {

	private JFrame frame;
	private JTextField textFieldNom;
	private JTextField textFieldNote;

	DAO<Evaluation> evaldao=DAOFactory.getEvaluationDAO();
	Evaluation evaluation=new Evaluation();

	/**
	 * Create the application.
	 */
	public AjoutEvaluation(DefaultTableModel model,Detailsbulletin db) {
		initialize(model,db);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(DefaultTableModel model,Detailsbulletin db) {
		frame = new JFrame();
		frame.setBounds(100, 100, 513, 397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(45, 21, 92, 26);
		frame.getContentPane().add(lblNom);
		
		JLabel lblNote = new JLabel("Note");
		lblNote.setBounds(45, 68, 92, 26);
		frame.getContentPane().add(lblNote);
		
		JLabel lblAppreciation = new JLabel("Appreciation");
		lblAppreciation.setBounds(45, 153, 133, 26);
		frame.getContentPane().add(lblAppreciation);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(223, 18, 186, 32);
		frame.getContentPane().add(textFieldNom);
		textFieldNom.setColumns(10);
		
		textFieldNote = new JTextField();
		textFieldNote.setBounds(223, 65, 186, 32);
		frame.getContentPane().add(textFieldNote);
		textFieldNote.setColumns(10);
		
		JTextArea textAreaAppreciation = new JTextArea();
		textAreaAppreciation.setBounds(223, 132, 232, 91);
		frame.getContentPane().add(textAreaAppreciation);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				evaluation.set_fkdb(db.get_id());
				evaluation.set_nom(textFieldNom.getText());
				evaluation.set_note((textFieldNote.getText()));
				evaluation.set_appreciation(textAreaAppreciation.getText());
				evaldao.insert(evaluation);
				db.addEval(evaluation);
				model.addRow(new Object[] {
					textFieldNom.getText(),
					textFieldNote.getText(),
					textAreaAppreciation.getText()
				});
				frame.dispose();
				
			}
		});
		btnEnregistrer.setBounds(45, 270, 141, 35);
		frame.getContentPane().add(btnEnregistrer);
	}
}

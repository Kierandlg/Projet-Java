package Vue;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.PortUnreachableException;
import java.net.URL;
import java.util.ArrayList;

import javax.management.Query;
import javax.swing.ImageIcon;
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
	
	JButton btnEditerBulletin = new JButton("Editer Bulletin");
	
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
		
	// pour etre sur que l'neseignant est bien à jour on le rafraichis depuis la base donnée
		
		btnEditerBulletin.setEnabled(false);
		
		model.setColumnIdentifiers(title);
		for(int i=0; i<ens.getClasse().size(); i++)
		{
			rowdata[0]=ens.getClasse().get(i).get_nom(); 
			rowdata[1]=ens.getClasse().get(i).getniveau(); 
			
			
			model.addRow(rowdata);
		}
		//on les met dans le tableau
		table=new JTable(model);
		table.addMouseListener(new TableauListener());
		
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 620, 679);
		frame.getContentPane().setLayout(null);
		
		//container principale
		JPanel container = new JPanel();
		container.setBounds(0, 0, 594, 608);
		frame.getContentPane().add(container);
		container.setLayout(null);
		
		//photo de profil
		try {
			ImageIcon icon= new ImageIcon( new URL( ens.get_photo() ) );
			icon.setImage(getScaledImage(icon.getImage(), 146, 157));
			JLabel LabelPhoto = new JLabel(icon);
		LabelPhoto.setBounds(35, 52, 146, 157);
		container.add(LabelPhoto);
		LabelPhoto.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		
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
		
		btnEditerBulletin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int indice=table.getSelectedRow();
				EditerBulletin editerBulletin=new EditerBulletin(ens,indice);
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
				ens.setDiscipline((String)Disciplinebox.getSelectedItem());
				Enseignantdao.update(ens);
				
				mod.setValueAt(ens.get_nom(), row,0);
				mod.setValueAt(ens.get_prenom(), row,1);
				mod.setValueAt(ens.getDiscipline(), row,2);
		
				
				
				
			}
		});
		btnEnregistrer.setBounds(417, 540, 141, 35);
		container.add(btnEnregistrer);
		
		JButton btnAjouterClasse = new JButton("Ajouter Classe");
		btnAjouterClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AjoutClasseEns ajoutClasseEns =new AjoutClasseEns(ens,model);
				
				
				
			}
		});
		btnAjouterClasse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAjouterClasse.setBounds(89, 439, 141, 35);
		container.add(btnAjouterClasse);
		
		
	}
	
	
	

	public class TableauListener implements MouseListener {
		 
		 
	    @Override
	    public void mouseClicked(java.awt.event.MouseEvent e) {
	    	
	     if(table.getSelectedRow()<0)
	     {
	    	 btnEditerBulletin.setEnabled(true);
	     }
	     else {
	    	 btnEditerBulletin.setEnabled(true);
	     }
	         
	    }
	 
	    @Override
	    public void mousePressed(java.awt.event.MouseEvent e) {
	    	
	    
	    
	     	
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
	
	

	//https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
	
	
	
}

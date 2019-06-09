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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import Controller.DAO;
import Controller.DAOFactory;
import modele.Classe;
import modele.Discipline;
import modele.Eleve;
import modele.Enseignant;
import modele.Niveau;
import java.awt.FlowLayout;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.factories.FormFactory;
//import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;

/* TODO
 * 
 * 
 * AJOUTER insertion de bulletin sur la page d'un eleve
 * 
 * supprimer une classe si il n y a plus d eleve dedans 
 */
public class Acceuil extends JFrame implements ActionListener {

    JPanel container = new JPanel();
    JLabel labelTitre = new JLabel("ECE");
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    JPanel ongletEleve = new JPanel();
    JPanel ongletEnseignant = new JPanel();
    JPanel OngletClasse = new JPanel();
    JPanel ongletNiveau = new JPanel();
    JPanel ongletReport = new JPanel();
    JPanel[] panneau = {ongletEleve, ongletEnseignant, OngletClasse, ongletNiveau, ongletReport};

    JScrollPane scrollPane = new JScrollPane();
    JButton Searchbutton = new JButton("Rechercher");

    DAO<Eleve> EleveDAO = DAOFactory.getEleveDAO();
    DAO<Enseignant> EnseignanteDAO = DAOFactory.getEnseignantDAO();
    DAO<Classe> ClasseDAO = DAOFactory.getClasseDAO();
    DAO<Niveau> niveauDAO = DAOFactory.getNiveauDAO();
    DAO<Discipline> disciplineDAO = DAOFactory.getDisciplineDAO();

    protected ArrayList<Eleve> arrayeleve = new ArrayList<Eleve>();
    protected ArrayList<Enseignant> arrayens = new ArrayList<Enseignant>();
    protected ArrayList<Classe> arrayclasse = new ArrayList<Classe>();
    protected ArrayList<Niveau> arrayniv = new ArrayList<Niveau>();
    protected ArrayList<Discipline> arraydisc = new ArrayList<Discipline>();

    tablemodel model = new tablemodel();
    tablemodel model2 = new tablemodel();
    tablemodel model3 = new tablemodel();
    tablemodel model4 = new tablemodel();
    tablemodel model5 = new tablemodel();

    Object[] rowdata = new Object[4];

    String[] title = {"Nom", "Prenom", "TD", "Niveau"};
    String[] title2 = {"Nom", "Prenom", "Discipline"};
    String[] title3 = {"Nom", "Niveau", "Annee Scolaire"};
    String[] title4 = {"Niveau"};
    String[] title5 = {"Discipline"};

    String[] combodata = {};
    JComboBox Classeens = new JComboBox();

    InfoEnseignant infoEns;
    infoClasse infoclasse;
    InfoEleve infoEleve;

    private JFrame frame;
    private JTextField searchField;
    private JTable tableens;
    private JTable tableeleve;
    private JTable tableclasse;
    private JTable tableniveau;
    private final JPanel panel = new JPanel();
    private final JScrollPane scrollPane_3 = new JScrollPane();
    private final JButton btnAjouterEtudiant = new JButton("Ajouter Etudiant");
    private final JButton btnAjouterEnseignant = new JButton("Ajouter Enseignant");
    private final JButton btnRefresh = new JButton("Refresh report");

    private final JButton btnAjouterClasse = new JButton("Ajouter Classe");
    private final JPanel OngletDiscipline = new JPanel();
    private JTable tableDiscipline;
    private final JButton btnAjouterDiscipline = new JButton("Ajouter Discipline ");
    private final JButton btnNewButton = new JButton("Supprimer");
    private final JButton suppclasse = new JButton("SupprimerClasse");

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
        frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        

        //recup�ration des �leves
        arrayeleve = EleveDAO.findAll();

        model.setColumnIdentifiers(title);
        for (int i = 0; i < arrayeleve.size(); i++) {
            rowdata[0] = arrayeleve.get(i).get_nom();
            rowdata[1] = arrayeleve.get(i).get_prenom();
            rowdata[2] = arrayeleve.get(i).getclasse();
            rowdata[3] = arrayeleve.get(i).getniveau();

            model.addRow(rowdata);
        }

        //recup�ration enseignant
        arrayens = EnseignanteDAO.findAll();
        model2.setColumnIdentifiers(title2);
        for (int i = 0; i < arrayens.size(); i++) {
            rowdata[0] = arrayens.get(i).get_nom();
            rowdata[1] = arrayens.get(i).get_prenom();
            rowdata[2] = arrayens.get(i).getDiscipline();

            model2.addRow(rowdata);
        }
        tableens = new JTable(model2);
        tableens.setBackground(Color.WHITE);
        tableens.setGridColor(Color.BLACK);
        tableens.addMouseListener(new TableauListener());

        //recup�ration des classes 
        arrayclasse = ClasseDAO.findAll();
        model3.setColumnIdentifiers(title3);
        for (int i = 0; i < arrayclasse.size(); i++) {
            rowdata[0] = arrayclasse.get(i).get_nom();
            rowdata[1] = arrayclasse.get(i).getniveau();
            rowdata[2] = arrayclasse.get(i).getanneescolaire();

            model3.addRow(rowdata);
        }
        tableclasse = new JTable(model3);
        tableclasse.addMouseListener(new TableauListener());

        //recuperation des niveaux
        arrayniv = niveauDAO.findAll();
        model4.setColumnIdentifiers(title4);
        for (int i = 0; i < arrayniv.size(); i++) {
            rowdata[0] = arrayniv.get(i).get_nom();

            model4.addRow(rowdata);
        }
        tableniveau = new JTable(model4);
        tableniveau.addMouseListener(new TableauListener());

        // recuperation des disciplines
        arraydisc = disciplineDAO.findAll();
        model5.setColumnIdentifiers(title5);
        for (int i = 0; i < arraydisc.size(); i++) {
            rowdata[0] = arraydisc.get(i).get_nom();

            model5.addRow(rowdata);
        }
        tableDiscipline = new JTable(model5);
        tableDiscipline.addMouseListener(new TableauListener());

        frame = new JFrame();
        frame.setResizable(false);

        frame.setBounds(100, 100, 1001, 970);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        container.setBackground(Color.BLACK);
        frame.getContentPane().add(container, BorderLayout.CENTER);
        container.setLayout(null);
        labelTitre.setForeground(Color.RED);
        labelTitre.setBounds(454, 21, 67, 35);

        labelTitre.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 29));
        container.add(labelTitre);
        tabbedPane.setBorder(null);
        tabbedPane.setBackground(Color.GRAY);
        tabbedPane.setForeground(Color.BLACK);

        tabbedPane.setBounds(56, 96, 867, 565);
        container.add(tabbedPane);
        ongletEnseignant.setBorder(null);
        ongletEnseignant.setBackground(Color.DARK_GRAY);

        tabbedPane.addTab("Enseignant", null, ongletEnseignant, null);
        ongletEnseignant.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBackground(Color.WHITE);
        scrollPane_1.setBounds(70, 42, 747, 408);
        ongletEnseignant.add(scrollPane_1);

        tableens.getColumnModel().getColumn(0).setPreferredWidth(141);
        tableens.getColumnModel().getColumn(1).setPreferredWidth(165);
        tableens.getColumnModel().getColumn(2).setPreferredWidth(201);
        scrollPane_1.setViewportView(tableens);
        btnAjouterEnseignant.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, null, null, null));
        btnAjouterEnseignant.setForeground(Color.BLACK);
        btnAjouterEnseignant.setBackground(Color.WHITE);
        btnAjouterEnseignant.setBounds(37, 475, 211, 35);
        ongletEnseignant.add(btnAjouterEnseignant);
        btnAjouterEnseignant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                AjoutEnseignant ajoutEnseignant = new AjoutEnseignant(model2, arrayens);
            }
        });
        OngletClasse.setBackground(Color.DARK_GRAY);

        tabbedPane.addTab("Classe", null, OngletClasse, null);
        OngletClasse.setLayout(null);

        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(70, 32, 728, 412);
        OngletClasse.add(scrollPane_2);

        tableclasse.getColumnModel().getColumn(0).setPreferredWidth(131);
        tableclasse.getColumnModel().getColumn(1).setPreferredWidth(147);
        tableclasse.getColumnModel().getColumn(2).setPreferredWidth(193);
        scrollPane_2.setViewportView(tableclasse);
        btnAjouterClasse.setBounds(42, 477, 165, 35);
        OngletClasse.add(btnAjouterClasse);
        suppclasse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int Num = tableclasse.getSelectedRow();
                if (arrayclasse.get(Num).getarrayeleve().isEmpty() && arrayclasse.get(Num).get_arrayens().isEmpty()) {
                    ClasseDAO.delete(arrayclasse.get(Num));
                    model3.removeRow(Num);
                }

            }
        });
        suppclasse.setEnabled(false);
        suppclasse.setBounds(243, 477, 139, 35);

        OngletClasse.add(suppclasse);
        btnAjouterClasse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AjoutClasse ajoutClasse = new AjoutClasse(model3, arrayclasse);
            }
        });
        //on les met dans le tableau
        tableeleve = new JTable(model);
        tableeleve.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableeleve.addMouseListener(new TableauListener());
        ongletEleve.setBackground(Color.DARK_GRAY);

        tabbedPane.addTab("Eleves", null, ongletEleve, null);
        ongletEleve.setLayout(null);

        scrollPane.setBounds(42, 21, 782, 425);
        ongletEleve.add(scrollPane);

        tableeleve.getColumnModel().getColumn(0).setPreferredWidth(164);
        tableeleve.getColumnModel().getColumn(1).setPreferredWidth(188);
        tableeleve.getColumnModel().getColumn(2).setPreferredWidth(87);
        tableeleve.getColumnModel().getColumn(3).setPreferredWidth(169);
        scrollPane.setViewportView(tableeleve);
        btnAjouterEtudiant.setBounds(25, 479, 185, 35);
        ongletEleve.add(btnAjouterEtudiant);
        btnAjouterEtudiant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                AjoutEtudiant ajoutEtudiant = new AjoutEtudiant(model, arrayeleve);
            }
        });
        ongletNiveau.setBackground(Color.DARK_GRAY);

        tabbedPane.addTab("Niveau", null, ongletNiveau, null);
        ongletNiveau.setLayout(null);
        scrollPane_3.setBounds(74, 43, 711, 382);
        ongletNiveau.add(scrollPane_3);

        tableniveau.getColumnModel().getColumn(0).setPreferredWidth(131);
        scrollPane_3.setViewportView(tableniveau);
        OngletDiscipline.setBackground(Color.DARK_GRAY);

        tabbedPane.addTab("Discipline", null, OngletDiscipline, null);
        OngletDiscipline.setLayout(null);

        JScrollPane scrollPaneDiscipline = new JScrollPane();
        scrollPaneDiscipline.setBounds(64, 47, 724, 351);
        OngletDiscipline.add(scrollPaneDiscipline);

        scrollPaneDiscipline.setViewportView(tableDiscipline);
        btnAjouterDiscipline.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AjoutDiscipline ajoutDiscipline = new AjoutDiscipline(model5, arraydisc);
            }
        });
        btnAjouterDiscipline.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnAjouterDiscipline.setBounds(43, 450, 152, 38);

        OngletDiscipline.add(btnAjouterDiscipline);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int Num = tableDiscipline.getSelectedRow();
                disciplineDAO.delete(arraydisc.get(Num));
                model5.removeRow(Num);
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.setBounds(205, 450, 152, 38);

        OngletDiscipline.add(btnNewButton);
        
        
        
        //report
        DefaultPieDataset chartClasse = new DefaultPieDataset();
        int tdCompteur[] = new int[11];
        DefaultCategoryDataset chartNiveau = new DefaultCategoryDataset();
        int nivCompteur[] = new int[6];
        
        ongletReport.setBackground(Color.DARK_GRAY);
        btnRefresh.setBounds(25, 479, 185, 35);
        ongletReport.add(btnRefresh);
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrayeleve = EleveDAO.findAll();
                    for(int j=0; j<tdCompteur.length; j++){
                        tdCompteur[j]=0;
                    }
                    for(int k=0; k<nivCompteur.length; k++){
                        nivCompteur[k]=0;
                    }
                for (int i = 0; i < arrayeleve.size(); i++) {
                    
                    
                    String td = arrayeleve.get(i).getclasse();

                    if (td.equals("TD1")) {
                        tdCompteur[1] += 1;
                    }
                    if (td.equals("TD2")) {
                        tdCompteur[2] += 1;
                    }
                    if (td.equals("TD3")) {
                        tdCompteur[3] += 1;
                    }
                    if (td.equals("TD4")) {
                        tdCompteur[4] += 1;
                    }
                    if (td.equals("TD5")) {
                        tdCompteur[5] += 1;
                    }
                    if (td.equals("TD6")) {
                        tdCompteur[6] += 1;
                    }
                    if (td.equals("TD7")) {
                        tdCompteur[7] += 1;
                    }
                    if (td.equals("TD8")) {
                        tdCompteur[8] += 1;
                    }
                    if (td.equals("TD9")) {
                        tdCompteur[9] += 1;
                    }
                    if (td.equals("TD10")) {
                        tdCompteur[10] += 1;
                    }
                    if (td.equals("TD11")) {
                        tdCompteur[11] += 1;
                    }

                    String niv = arrayeleve.get(i).getniveau();

                    if (niv.equals("ING1")) {
                        nivCompteur[1] += 1;
                    }
                    if (niv.equals("ING2")) {
                        nivCompteur[2] += 1;
                    }
                    if (niv.equals("ING3")) {
                        nivCompteur[3] += 1;
                    }
                    if (niv.equals("ING4")) {
                        nivCompteur[4] += 1;
                    }
                    if (niv.equals("ING5")) {
                        nivCompteur[5] += 1;
                    }

                }
                for (int ii = 1; ii < 11; ii++) {
            if (tdCompteur[ii] != 0) {
                chartClasse.setValue("TD" + ii, tdCompteur[ii]);
            }
        }
        for (int iii = 1; iii < 6; iii++) {
            chartNiveau.addValue(nivCompteur[iii], "Eleves", "ING" + iii);
        }
            }
        });

        tabbedPane.addTab("Reporting", null, ongletReport, null);

        for (int ii = 1; ii < 11; ii++) {
            if (tdCompteur[ii] != 0) {
                chartClasse.setValue("TD" + ii, tdCompteur[ii]);
            }
        }
        for (int iii = 1; iii < 6; iii++) {
            chartNiveau.addValue(nivCompteur[iii], "Eleves", "ING" + iii);
        }

        JFreeChart chart1 = ChartFactory.createPieChart(
                "Répartition des élèves",
                chartClasse,
                true, // legend?
                true, // tooltips?
                false // URLs?
        );
        pack();

        JFreeChart chart2 = ChartFactory.createBarChart(
                "Répartition globale des TD", // chart title
                "Niveau d'étude", // domain axis label
                "Nombre d'étudiant", // range axis label
                chartNiveau, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips?
                false // URLs?
        );
        pack();
        ongletReport.setLayout(null);

        JScrollPane scrollPane_4 = new JScrollPane();
        scrollPane_4.setBounds(5, 0, 857, 537);
        ongletReport.add(scrollPane_4);

        JPanel panel_1 = new JPanel();
        scrollPane_4.setViewportView(panel_1);
        panel_1.setLayout(new GridLayout(0, 1, 0, 0));

        ChartPanel frame2 = new ChartPanel(chart2);
        panel_1.add(frame2);
        frame2.setVisible(true);
        ChartPanel frame1 = new ChartPanel(chart1);
        panel_1.add(frame1);
        frame1.setVisible(true);
        Searchbutton.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, null, null, null));
        Searchbutton.setFocusable(false);
        Searchbutton.setFocusTraversalKeysEnabled(false);
        Searchbutton.setFocusPainted(false);
        Searchbutton.setForeground(Color.WHITE);
        Searchbutton.setBackground(Color.DARK_GRAY);

        Searchbutton.setBounds(134, 836, 141, 35);
        Searchbutton.addActionListener(this);
        container.add(Searchbutton);

        searchField = new JTextField();
        searchField.setBounds(362, 837, 431, 32);
        container.add(searchField);
        searchField.setColumns(10);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Searchbutton) {
            if (ongletEleve.isVisible()) {
                Eleve el;
                String t = searchField.getText();

                if (t.isEmpty()) {
                    model = new tablemodel();
                    model.setColumnIdentifiers(title);
                    for (int i = 0; i < arrayeleve.size(); i++) {
                        rowdata[0] = arrayeleve.get(i).get_nom();
                        rowdata[1] = arrayeleve.get(i).get_prenom();
                        rowdata[2] = arrayeleve.get(i).getclasse();
                        rowdata[3] = arrayeleve.get(i).getniveau();

                        model.addRow(rowdata);
                    }
                } else {
                    for (int j = model.getRowCount() - 1; j >= 0; j--) {
                        model.removeRow(j);
                    }

                    for (int i = 0; i < arrayeleve.size(); i++) {
                        System.out.println(t);
                        el = arrayeleve.get(i);
                        if (Integer.toString(el.get_id()).equals(t)
                                || el.get_nom().equals(t)
                                || el.get_prenom().equals(t)
                                || el.getclasse().equals(t)
                                || el.getniveau().equals(t)) {

                            rowdata[0] = el.get_nom();
                            rowdata[1] = el.get_prenom();
                            rowdata[2] = el.getclasse();
                            rowdata[3] = el.getniveau();

                            model.addRow(rowdata);
                        }

                    }

                }
                tableeleve.setModel(model);
            }

            if (ongletEnseignant.isVisible()) {
                Enseignant en;
                String t = searchField.getText();

                if (t.isEmpty()) {
                    model2 = new tablemodel();
                    model2.setColumnIdentifiers(title2);

                    for (int i = 0; i < arrayens.size(); i++) {
                        rowdata[0] = arrayens.get(i).get_nom();
                        rowdata[1] = arrayens.get(i).get_prenom();
                        rowdata[2] = arrayens.get(i).getDiscipline();

                        model2.addRow(rowdata);
                    }
                } else {
                    for (int j = model2.getRowCount() - 1; j >= 0; j--) {
                        model2.removeRow(j);
                    }

                    for (int i = 0; i < arrayens.size(); i++) {
                        System.out.println(arrayens.size());
                        en = arrayens.get(i);
                        if (Integer.toString(en.get_id()).equals(t)
                                || en.get_nom().equals(t)
                                || en.get_prenom().equals(t)
                                || en.getDiscipline().equals(t)) {

                            rowdata[0] = en.get_nom();
                            rowdata[1] = en.get_prenom();
                            rowdata[2] = en.getDiscipline();

                            model2.addRow(rowdata);
                        }

                    }
                }
                tableens.setModel(model2);
            }

            if (OngletClasse.isVisible()) {
                String t = searchField.getText();
                Classe classe;

                if (t.isEmpty()) {
                    model3 = new tablemodel();
                    model3.setColumnIdentifiers(title3);
                    for (int i = 0; i < arrayclasse.size(); i++) {
                        rowdata[0] = arrayclasse.get(i).get_nom();
                        rowdata[1] = arrayclasse.get(i).getniveau();
                        rowdata[2] = arrayclasse.get(i).getanneescolaire();

                        model3.addRow(rowdata);
                    }
                } else {
                    for (int j = model3.getRowCount() - 1; j >= 0; j--) {
                        model3.removeRow(j);
                    }

                    for (int i = 0; i < arrayens.size(); i++) {
                        System.out.println(arrayens.size());
                        classe = arrayclasse.get(i);
                        if (Integer.toString(classe.get_id()).equals(t)
                                || classe.get_nom().equals(t)
                                || classe.getniveau().equals(t)
                                || classe.getanneescolaire().equals(t)) {

                            rowdata[0] = classe.get_nom();
                            rowdata[1] = classe.getniveau();
                            rowdata[2] = classe.getanneescolaire();

                            model3.addRow(rowdata);
                        }

                    }
                }
                tableclasse.setModel(model3);
            }

            if (OngletDiscipline.isVisible()) {
                String t = searchField.getText();
                Discipline d;

                if (t.isEmpty()) {
                    model5 = new tablemodel();
                    model5.setColumnIdentifiers(title5);
                    for (int i = 0; i < arraydisc.size(); i++) {
                        rowdata[0] = arraydisc.get(i).get_nom();

                        model5.addRow(rowdata);
                    }
                } else {
                    for (int j = model5.getRowCount() - 1; j >= 0; j--) {
                        model5.removeRow(j);
                    }

                    for (int i = 0; i < arraydisc.size(); i++) {
                        System.out.println(arraydisc.size());
                        d = arraydisc.get(i);
                        if (Integer.toString(d.get_id()).equals(t)
                                || d.get_nom().equals(t)) {

                            rowdata[0] = d.get_nom();

                            model5.addRow(rowdata);
                        }

                    }
                }

                tableDiscipline.setModel(model5);
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
            if (tabbedPane.getSelectedComponent() == ongletEnseignant) {
                int NumLigne = tableens.getSelectedRow();
                arrayens.set(NumLigne, EnseignanteDAO.find(arrayens.get(NumLigne).get_id()));
                infoEns = new InfoEnseignant(arrayens.get(NumLigne), NumLigne, model2);
            }

            //onglet Classe
            if (tabbedPane.getSelectedComponent() == OngletClasse) {
                int NumLigne = tableclasse.getSelectedRow();
                arrayclasse.set(NumLigne, ClasseDAO.find(arrayclasse.get(NumLigne).get_id()));
                infoclasse = new infoClasse(arrayclasse.get(NumLigne));

                if (NumLigne < 0 && arrayclasse.get(NumLigne).get_arrayens().isEmpty()) {
                    suppclasse.setEnabled(false);
                } else {
                    suppclasse.setEnabled(true);
                }
            }

            //onglet Eleve
            if (tabbedPane.getSelectedComponent() == ongletEleve) {
                int NumLigne = tableeleve.getSelectedRow();
                arrayeleve.set(NumLigne, EleveDAO.find(arrayeleve.get(NumLigne).get_id()));
                infoEleve = new InfoEleve(arrayeleve.get(NumLigne), NumLigne, model, arrayeleve);
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

    class tablemodel extends DefaultTableModel {

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

    }
}

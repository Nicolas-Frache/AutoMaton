package Vue;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import Controleur.ControleurAutomate;
import Controleur.ControleurDessin;
import Controleur.ControleurEvolution;
import Controleur.ControleurReglageAffichage;
import Modele.Automate;
import Modele.Jeu;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;

public class PanPrincipale extends JPanel implements Observer{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Jeu jeu;
	
	
	/******** Affichage *********************/
	public JButton zoomPlus;
	public JButton zoomMoins;
	public JButton couleurQuadrillage;
	public JButton couleurFond;
	public JButton couleurCelluleVivante;
	public JButton couleurCelluleMorte;
	
	public JCheckBox afficherQuadrillageCheckBox;

	public JSpinner choixEpaisseurSpinner;
	public JSlider zoomSlider;

	public JTabbedPane tabbedPane;	
	
	public JSplitPane splitPane;
	
	public JPanel reglageGraphique;
	public JPanel zoomPanel;
	public JLabel zoomEtiquette;
	public JPanel zoomPartieBas;
	public JPanel zonePlusZoom;
	public JPanel zoneMoinsZoom;
	public JPanel zoneQuadrillage;
	public JPanel zoneQuadrillageBas;
	public JPanel zoneChoixEpaisseurQuadrillage;
	public JPanel zoneCouleurQuadrillage;
	public JPanel zoneCouleurFond;
	public JPanel zoneCellule;
	public JPanel zoneCellulesBas;
	public JPanel zoneCouleurCelluleVivantes;
	public JPanel zoneCouleurCelluleMortes;
	public JPanel reglageAutomate;
	
	public JLabel cellulesEtiquette;
	public JLabel couleurFondEtiquette;
	public JLabel lblQuadrillage;
	public JLabel couleurQuadrillageEtiquette;
	public JLabel choixEpaisseurEtiquette;
	public JLabel couleurCelluleVivanteEtiquette;
	public JLabel couleurCelluleMorteEtiquette;
	
	ControleurReglageAffichage contRegAff;
	/********************************************************/
	
	public VueGrille dessin;
	
	
	/******** Reglages automate *********************/
	
	public JPanel zoneReglagePerso;
	public JLabel ReglagePersoEtiquette;
	public JPanel zoneBasPerso;
	public JPanel zonePersoVivante;
	public JPanel zonePersoVivanteTitre;
	public JPanel zonePersoVivanteBox;
	public JLabel lblConditionsDeVoisinage;
	public JPanel zonePersoMort;
	public JPanel zonePersoMortTitre;
	public JLabel lblConditionsPourRester;
	public JPanel zonePersoVivanteBox_1;
	public JCheckBox[][] tabCheckBox;
	public JPanel zonePreset;
	public JLabel ReglagePresetEtiquette;
	public JPanel zoneReglagePreset;
	public JCheckBox presetCheckBox;
	public JComboBox<String> listePreset;
	
	public ControleurAutomate contAut;
	
	/********* Evolution ****************/
	
	public JPanel Evo;
	public JPanel zoneEvo;
	public JLabel evoEtiquette;
	public JPanel evoBasPan;
	public JButton boutonPlay;
	public JButton boutonPause;
	public JButton boutonOneStep;
	public JPanel zoneReglageEvo;
	public JLabel evoReglageEtiquette;
	public JPanel zoneReglageVitesse;
	public JSpinner spinnerEvoVitesse;
	public JLabel spinnerVitesseLbl;
	
	public ControleurEvolution contEvo;
	public JPanel zoneGen;
	public JLabel genLbl;
	public JLabel genNumLbl;
	
	/****************************************/
	
	
	public PanPrincipale() {	
		initGUI();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initGUI() {
		
		setSize(new Dimension(1500, 200));
		
		setBackground(Color.magenta);
		
		dessin = new VueGrille();
		dessin.setBackground(Color.CYAN);
		
		
		setLayout(new BorderLayout(0, 0));
		
		splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane);
		splitPane.setBottomComponent(dessin);
		
		
		
		/******** Affichage *********************/
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setLeftComponent(tabbedPane);
		
		reglageGraphique = new JPanel();
		reglageGraphique.setBorder(null);
		tabbedPane.addTab("Affichage", null, reglageGraphique, null);
		reglageGraphique.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		zoomPanel = new JPanel();
		zoomPanel.setBorder(UIManager.getBorder("TitledBorder.border"));
		reglageGraphique.add(zoomPanel);
		zoomPanel.setLayout(new BorderLayout(0, 0));
		
		zoomEtiquette = new JLabel("Zoom");
		zoomEtiquette.setFont(new Font("Tahoma", Font.BOLD, 14));
		zoomEtiquette.setHorizontalAlignment(SwingConstants.CENTER);
		zoomPanel.add(zoomEtiquette, BorderLayout.NORTH);
		
		zoomPartieBas = new JPanel();
		zoomPanel.add(zoomPartieBas, BorderLayout.SOUTH);
		
		zonePlusZoom = new JPanel();
		
		zoomPlus = new JButton("+");
		zoomPlus.setToolTipText("Augmente le zoom");
		zonePlusZoom.add(zoomPlus);
		zoomPlus.setFont(new Font("Source Serif Pro Black", Font.BOLD, 30));
		
		zoomSlider = new JSlider();
		zoomSlider.setValue(100);
		zoomSlider.setPaintTicks(true);
		zoomSlider.setPaintLabels(true);
		zoomSlider.setMinorTickSpacing(25);
		zoomSlider.setMajorTickSpacing(50);
		
		zoneMoinsZoom = new JPanel();
		zoneMoinsZoom.setToolTipText("");
		zoneMoinsZoom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		zoomMoins = new JButton("-");
		zoomMoins.setToolTipText("Réduire le zoom");
		zoneMoinsZoom.add(zoomMoins);
		zoomMoins.setFont(new Font("Source Serif Pro Black", Font.BOLD, 30));

		zoomPartieBas.add(zoneMoinsZoom);
		zoomPartieBas.add(zoomSlider);
		zoomPartieBas.add(zonePlusZoom);
		
		zoneQuadrillage = new JPanel();
		reglageGraphique.add(zoneQuadrillage);
		zoneQuadrillage.setBorder(UIManager.getBorder("TitledBorder.border"));
		zoneQuadrillage.setLayout(new BorderLayout(0, 0));
		
		zoneQuadrillageBas = new JPanel();
		zoneQuadrillage.add(zoneQuadrillageBas, BorderLayout.CENTER);
		zoneQuadrillageBas.setLayout(new GridLayout(2, 2, 0, 0));
		
		afficherQuadrillageCheckBox = new JCheckBox("Afficher quadrillage");
		afficherQuadrillageCheckBox.setSelected(true);
		zoneQuadrillageBas.add(afficherQuadrillageCheckBox);
		
		zoneChoixEpaisseurQuadrillage = new JPanel();
		zoneQuadrillageBas.add(zoneChoixEpaisseurQuadrillage);
		zoneChoixEpaisseurQuadrillage.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		zoneCouleurQuadrillage = new JPanel();
		zoneQuadrillageBas.add(zoneCouleurQuadrillage);
		
		couleurQuadrillageEtiquette = new JLabel("Couleur quadrillage :");
		zoneCouleurQuadrillage.add(couleurQuadrillageEtiquette);
		
		couleurQuadrillage = new JButton("");
		couleurQuadrillage.setToolTipText("Choisir une couleur");
		couleurQuadrillage.setPreferredSize(new Dimension(33, 20));
		couleurQuadrillage.setForeground(Color.BLACK);
		couleurQuadrillage.setBackground(Color.DARK_GRAY);
		zoneCouleurQuadrillage.add(couleurQuadrillage);
		
		choixEpaisseurEtiquette = new JLabel("Epaisseur : ");
		zoneChoixEpaisseurQuadrillage.add(choixEpaisseurEtiquette);
		
		choixEpaisseurSpinner = new JSpinner();
		choixEpaisseurSpinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		choixEpaisseurSpinner.setPreferredSize(new Dimension(40, 20));
		zoneChoixEpaisseurQuadrillage.add(choixEpaisseurSpinner);
		
		zoneCouleurFond = new JPanel();
		zoneQuadrillageBas.add(zoneCouleurFond);
		
		couleurFondEtiquette = new JLabel("Couleur fond :");
		zoneCouleurFond.add(couleurFondEtiquette);
		
		couleurFond = new JButton("");
		couleurFond.setToolTipText("Choisir une couleur");
		couleurFond.setPreferredSize(new Dimension(33, 20));
		couleurFond.setForeground(Color.BLACK);
		couleurFond.setBackground(Color.LIGHT_GRAY);
		zoneCouleurFond.add(couleurFond);
		
		lblQuadrillage = new JLabel("Quadrillage");
		lblQuadrillage.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuadrillage.setFont(new Font("Tahoma", Font.BOLD, 14));
		zoneQuadrillage.add(lblQuadrillage, BorderLayout.NORTH);
		
		zoneCellule = new JPanel();
		zoneCellule.setBorder(UIManager.getBorder("TitledBorder.border"));
		reglageGraphique.add(zoneCellule);
		zoneCellule.setLayout(new BorderLayout(0, 0));
		
		cellulesEtiquette = new JLabel("Cellules");
		cellulesEtiquette.setHorizontalAlignment(SwingConstants.CENTER);
		cellulesEtiquette.setFont(new Font("Tahoma", Font.BOLD, 14));
		zoneCellule.add(cellulesEtiquette, BorderLayout.NORTH);
		
		zoneCellulesBas = new JPanel();
		zoneCellule.add(zoneCellulesBas, BorderLayout.SOUTH);
		zoneCellulesBas.setLayout(new GridLayout(2, 2, 0, 0));
		
		zoneCouleurCelluleVivantes = new JPanel();
		zoneCellulesBas.add(zoneCouleurCelluleVivantes);
		
		couleurCelluleVivanteEtiquette = new JLabel("Cellules vivantes :");
		zoneCouleurCelluleVivantes.add(couleurCelluleVivanteEtiquette);
		
		couleurCelluleVivante = new JButton("");
		couleurCelluleVivante.setToolTipText("Choisir une couleur");
		couleurCelluleVivante.setPreferredSize(new Dimension(33, 20));
		couleurCelluleVivante.setForeground(Color.BLACK);
		couleurCelluleVivante.setBackground(Color.BLUE);
		zoneCouleurCelluleVivantes.add(couleurCelluleVivante);
		
		zoneCouleurCelluleMortes = new JPanel();
		zoneCellulesBas.add(zoneCouleurCelluleMortes);
		
		couleurCelluleMorteEtiquette = new JLabel("Cellules mortes :");
		zoneCouleurCelluleMortes.add(couleurCelluleMorteEtiquette);
		
		couleurCelluleMorte = new JButton("");
		couleurCelluleMorte.setToolTipText("Choisir une couleur");
		couleurCelluleMorte.setPreferredSize(new Dimension(33, 20));
		couleurCelluleMorte.setForeground(Color.BLACK);
		couleurCelluleMorte.setBackground(Color.LIGHT_GRAY);
		zoneCouleurCelluleMortes.add(couleurCelluleMorte);
		
		reglageAutomate = new JPanel();
		tabbedPane.addTab("Réglage automate", null, reglageAutomate, null);
		
		zoneReglagePerso = new JPanel();
		zoneReglagePerso.setBorder(UIManager.getBorder("TitledBorder.border"));
		reglageAutomate.add(zoneReglagePerso);
		zoneReglagePerso.setLayout(new BorderLayout(0, 0));
		
		ReglagePersoEtiquette = new JLabel("Réglages personnalisés");
		ReglagePersoEtiquette.setHorizontalAlignment(SwingConstants.CENTER);
		ReglagePersoEtiquette.setFont(new Font("Tahoma", Font.BOLD, 14));
		zoneReglagePerso.add(ReglagePersoEtiquette, BorderLayout.NORTH);
		
		zoneBasPerso = new JPanel();
		zoneBasPerso.setBorder(new EmptyBorder(10, 0, 0, 0));
		zoneReglagePerso.add(zoneBasPerso, BorderLayout.CENTER);
		zoneBasPerso.setLayout(new GridLayout(1, 2, 10, 0));
		
		zonePersoVivante = new JPanel();
		zonePersoVivante.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		zoneBasPerso.add(zonePersoVivante);
		zonePersoVivante.setLayout(new BorderLayout(0, 0));
		
		zonePersoVivanteTitre = new JPanel();
		zonePersoVivante.add(zonePersoVivanteTitre, BorderLayout.NORTH);
		zonePersoVivanteTitre.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblConditionsDeVoisinage = new JLabel("Conditions pour naissance");
		zonePersoVivanteTitre.add(lblConditionsDeVoisinage);
		
		zonePersoVivanteBox = new JPanel();
		zonePersoVivante.add(zonePersoVivanteBox);
		zonePersoVivanteBox.setLayout(new GridLayout(1, 9, 0, 0));
		
		tabCheckBox = new JCheckBox[2][9];
		for(int i=0; i<9; i++) {
			tabCheckBox[0][i] = new JCheckBox(""+i);
			zonePersoVivanteBox.add(tabCheckBox[0][i]);
		}
		
		zonePersoMort = new JPanel();
		zonePersoMort.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		zoneBasPerso.add(zonePersoMort);
		zonePersoMort.setLayout(new BorderLayout(0, 0));
		
		zonePersoMortTitre = new JPanel();
		zonePersoMort.add(zonePersoMortTitre, BorderLayout.NORTH);
		zonePersoMortTitre.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblConditionsPourRester = new JLabel("Conditions pour rester en vie");
		zonePersoMortTitre.add(lblConditionsPourRester);
		
		zonePersoVivanteBox_1 = new JPanel();
		zonePersoMort.add(zonePersoVivanteBox_1, BorderLayout.SOUTH);
		zonePersoVivanteBox_1.setLayout(new GridLayout(1, 9, 0, 0));
		
		for(int i=0; i<9; i++) {
			tabCheckBox[1][i] = new JCheckBox(""+i);
			zonePersoVivanteBox_1.add(tabCheckBox[1][i]);
		}
		
		zonePreset = new JPanel();
		zonePreset.setBorder(UIManager.getBorder("TitledBorder.border"));
		reglageAutomate.add(zonePreset);
		zonePreset.setLayout(new BorderLayout(0, 0));
		
		ReglagePresetEtiquette = new JLabel("Presets");
		ReglagePresetEtiquette.setHorizontalAlignment(SwingConstants.CENTER);
		ReglagePresetEtiquette.setFont(new Font("Tahoma", Font.BOLD, 14));
		zonePreset.add(ReglagePresetEtiquette, BorderLayout.NORTH);
		
		zoneReglagePreset = new JPanel();
		zoneReglagePreset.setBorder(new EmptyBorder(10, 0, 0, 0));
		zonePreset.add(zoneReglagePreset, BorderLayout.SOUTH);
		zoneReglagePreset.setLayout(new GridLayout(2, 1, 0, 6));
		
		presetCheckBox = new JCheckBox("Utiliser un preset");
		zoneReglagePreset.add(presetCheckBox);
		
		DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>(Automate.PRESETS_NOMS);
		listePreset = new JComboBox(comboModel);
		zoneReglagePreset.add(listePreset);
		
		Evo = new JPanel();
		Evo.setBorder(UIManager.getBorder("TitledBorder.border"));
		tabbedPane.addTab("Evolution", null, Evo, null);
		Evo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		zoneGen = new JPanel();
		zoneGen.setBorder(UIManager.getBorder("TitledBorder.border"));
		Evo.add(zoneGen);
		
		genLbl = new JLabel("Génération n°:");
		genLbl.setFont(new Font("Dialog", Font.BOLD, 13));
		zoneGen.add(genLbl);
		
		genNumLbl = new JLabel("132");
		genNumLbl.setFont(new Font("Dialog", Font.BOLD, 20));
		zoneGen.add(genNumLbl);
		
		zoneEvo = new JPanel();
		zoneEvo.setBorder(UIManager.getBorder("TitledBorder.border"));
		Evo.add(zoneEvo);
		zoneEvo.setLayout(new BorderLayout(0, 0));
		
		evoEtiquette = new JLabel("Avancer");
		evoEtiquette.setHorizontalAlignment(SwingConstants.CENTER);
		evoEtiquette.setFont(new Font("Tahoma", Font.BOLD, 14));
		zoneEvo.add(evoEtiquette, BorderLayout.NORTH);
		
		evoBasPan = new JPanel();
		zoneEvo.add(evoBasPan, BorderLayout.CENTER);
		
		boutonOneStep = new JButton("⏭");
		boutonOneStep.setToolTipText("Avancer d'une génération");
		boutonOneStep.setFont(new Font("Dialog", Font.BOLD, 30));
		evoBasPan.add(boutonOneStep);
		
		boutonPlay = new JButton("▶");
		boutonPlay.setToolTipText("Lancer l'évolution automatique");
		boutonPlay.setFont(new Font("Dialog", Font.BOLD, 30));
		evoBasPan.add(boutonPlay);
		
		boutonPause = new JButton("⏸");
		boutonPause.setToolTipText("Stopper l'évolution automatique");
		boutonPause.setFont(new Font("Dialog", Font.PLAIN, 30));
		evoBasPan.add(boutonPause);
		
		zoneReglageEvo = new JPanel();
		zoneReglageEvo.setBorder(UIManager.getBorder("TitledBorder.border"));
		Evo.add(zoneReglageEvo);
		zoneReglageEvo.setLayout(new BorderLayout(0, 0));
		
		evoReglageEtiquette = new JLabel("Réglage évolution");
		evoReglageEtiquette.setHorizontalAlignment(SwingConstants.CENTER);
		evoReglageEtiquette.setFont(new Font("Tahoma", Font.BOLD, 14));
		zoneReglageEvo.add(evoReglageEtiquette, BorderLayout.NORTH);
		
		zoneReglageVitesse = new JPanel();
		zoneReglageEvo.add(zoneReglageVitesse, BorderLayout.WEST);
		zoneReglageVitesse.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		spinnerVitesseLbl = new JLabel("Génération par seconde : ");
		zoneReglageVitesse.add(spinnerVitesseLbl);
		
		spinnerEvoVitesse = new JSpinner();
		spinnerEvoVitesse.setModel(new SpinnerNumberModel(new Double(1), new Double(0), null, new Double(0.2)));
		spinnerEvoVitesse.setPreferredSize(new Dimension(40, 20));
		zoneReglageVitesse.add(spinnerEvoVitesse);
		
		
		
		
	}

	public void donnerJeu(Jeu jeu) {
		this.jeu = jeu;
		
		zoomSlider.setValue(104 - jeu.donneesGrille.tab[0].length);
		
		contRegAff = new ControleurReglageAffichage(this, jeu);
		contAut = new ControleurAutomate(this, jeu);
		contEvo = new ControleurEvolution(jeu, this);
		
		dessin.addMouseListener(new ControleurDessin(jeu, dessin));
		dessin.addMouseWheelListener(contRegAff);
		
		zoomPlus.addActionListener(contRegAff);
		zoomMoins.addActionListener(contRegAff);
		couleurQuadrillage.addActionListener(contRegAff);
		couleurFond.addActionListener(contRegAff);
		couleurCelluleVivante.addActionListener(contRegAff);
		couleurCelluleMorte.addActionListener(contRegAff);
		afficherQuadrillageCheckBox.addActionListener(contRegAff);
		choixEpaisseurSpinner.addChangeListener(contRegAff);
		zoomSlider.addChangeListener(contRegAff);
		afficherQuadrillageCheckBox.addItemListener(contRegAff);
	
		for(int i=0; i<9; i++) {
			tabCheckBox[0][i].addActionListener(contAut);
			tabCheckBox[1][i].addActionListener(contAut);
		}
		presetCheckBox.addActionListener(contAut);
		listePreset.addActionListener(contAut);
		
		presetCheckBox.setSelected(true);
		contAut.cocherPresetCheckbox();
		
		boutonPause.addActionListener(contEvo);
		boutonPlay.addActionListener(contEvo);
		boutonOneStep.addActionListener(contEvo);
		spinnerEvoVitesse.addChangeListener(contEvo);
		
		boutonPause.setEnabled(false);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
		genNumLbl.setText(Integer.toString(jeu.donneesGrille.generation));
	}	

	
}		
		

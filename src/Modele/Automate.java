package Modele;

import Vue.PanPrincipale;

public class Automate {

	public PanPrincipale pan;
	public Jeu jeu;
	public EvolutionAuto evoAuto;                     
	
	public boolean modePreset;
	public boolean[] tabNaissance;
	public boolean[] tabSurvie;
	public double genParSeconde;
	
	public static final String[] PRESETS_NOMS = {
			"Jeu de la vie de Conway",
			"HighLife",
			"Replicator World"};
	
	public static final int[][][] PRESETS = {
			{{3},{2,3}},
			{{3,6},{2,3}},
			{{1,3,5,7},{1,3,5,7}}
		}; 
	
	
	public Automate(Jeu jeu, PanPrincipale pp) {
		this.pan = pp;
		this.jeu = jeu;
		
		evoAuto = new EvolutionAuto(jeu);
		
		tabNaissance = new boolean[9];
		tabSurvie = new boolean[9];
		modePreset = true;
		genParSeconde = 1;
	}
	
	public void changerCheckbox() {
		for(int i=0; i<9; i++) {
			tabNaissance[i] = pan.tabCheckBox[0][i].isSelected();
			tabSurvie[i] = pan.tabCheckBox[1][i].isSelected();
		}
		jeu.donneesGrille.generation = 0;
	}
	
	public void choisirPreset(int index) {
		tabNaissance = new boolean[9];
		for(int i=0; i< PRESETS[index][0].length; i++) {
			tabNaissance[PRESETS[index][0][i]] = true;  
		}
		
		tabSurvie = new boolean[9];
		for(int i=0; i< PRESETS[index][1].length; i++) {
			tabSurvie[PRESETS[index][1][i]] = true;  
		}
		jeu.donneesGrille.generation = 0;
	}
	
	public void generationSuivante() {
		
		jeu.donneesGrille.generation++;
		int nbVoisin;
		boolean[][] nvTab = new boolean[jeu.donneesGrille.tab.length][jeu.donneesGrille.tab[0].length];
		
		for(int i=0; i< jeu.donneesGrille.tab.length; i++) {
			for(int j=0; j< jeu.donneesGrille.tab[0].length; j++) {
				nbVoisin = 0;
				
				if(estAllume(i-1, j-1)) {
					nbVoisin++;
				}
				if(estAllume(i, j-1)) {
					nbVoisin++;
				}
				if(estAllume(i+1, j-1)) {
					nbVoisin++;
				}
				
				
				if(estAllume(i-1, j+1)) {
					nbVoisin++;
				}
				if(estAllume(i, j+1)) {
					nbVoisin++;
				}
				if(estAllume(i+1, j+1)) {
					nbVoisin++;
				}
				
				
				if(estAllume(i-1, j)) {
					nbVoisin++;
				}
				if(estAllume(i+1, j)) {
					nbVoisin++;
				}
				
				
				if(jeu.donneesGrille.tab[i][j]) {
					for(int k=0; k<9 && !nvTab[i][j]; k++) {
						if(tabSurvie[k]) {
							nvTab[i][j] = nbVoisin == k;	
						}
					}
				}
				else {
					for(int k=0; k<9 && !nvTab[i][j]; k++) {
						if(tabNaissance[k]) {
							nvTab[i][j] = nbVoisin == k;	
						}
					}
				}
				
			}
		}
		jeu.donneesGrille.tab = nvTab;
		jeu.provoquerUpdate();
	}

	public boolean estAllume(int i, int j) {
		boolean b = false;
		try {
			b = jeu.donneesGrille.tab[i][j];
		}
		catch (Exception e) {
		}
		return b;	
	}
	
	
	
	
	
}

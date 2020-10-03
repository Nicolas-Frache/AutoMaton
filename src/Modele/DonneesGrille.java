package Modele;

import java.awt.Color;

import Vue.PanPrincipale;

public class DonneesGrille{

	public boolean[][] tab;
	public Color couleurQuadrillage;
	public Color couleurEteint, couleurAllume;
	public int epaisseurQuadrillage;
	public boolean affichageQuadrillage;
	public int generation;
	
	public Jeu jeu;
	public PanPrincipale panPrinc;
	
	public DonneesGrille(Jeu jeu, PanPrincipale pp) {
		
		couleurQuadrillage = Color.GRAY;
		couleurAllume = Color.BLUE;
		couleurEteint = Color.LIGHT_GRAY;
		affichageQuadrillage = true;
		epaisseurQuadrillage = 1;
		generation = 0;	
		this.jeu = jeu;
		this.panPrinc = pp;

		tab = new boolean[1][8];
	}


	public boolean[][] getTab() {
		return tab;
	}

	public void appuieSurCase(int indexColonneClick, int indexLigneClick) {
		if (switcherLampe(indexColonneClick, indexLigneClick)) {
			generation = 0;
			jeu.provoquerUpdate();
		}
	}
	
	public boolean existeLampe(int indexColonne, int indexLigne) {
		return 
				indexColonne >= 0 &&
				indexLigne >= 0 &&				
				tab.length >= indexColonne+1 &&
				tab.length >= 0 && 
				tab[0].length >= indexLigne+1 &&
				tab[0].length >= 0;
	}
	
	public boolean switcherLampe(int indexColonne, int indexLigne) {
		boolean retour = existeLampe(indexColonne, indexLigne);
		if(retour) {
			tab[indexColonne][indexLigne] = !tab[indexColonne][indexLigne];
		}
		return retour;	
	}
	
	public void redimensionerColonneAuto() {
		
		int w = panPrinc.dessin.getWidth();
		int h = panPrinc.dessin.getHeight();
		
		int nbCol = tab.length;
		int nbLigne = tab[0].length;
		
		double longueurCote = h/nbLigne; 
		
		int nbCol2 = (int) Math.floor((w)/(longueurCote));
		
		int marge = (int) (w - (nbCol2 * (longueurCote)));
		int bonus = (int) Math.floor(marge /(longueurCote));
		
		if(bonus > 0){
			//nbCol2 += bonus;
		}
		//System.out.println(longueurCote-1 + " " + marge + " "+ bonus);
		
		if(nbCol2 != nbCol) {
			tab = centrerEtRedimensionner(nbCol2, nbLigne);
		}
		
	}
	
	public void redimensionnerLigne(int nbLigne) {
		tab = centrerEtRedimensionner(tab.length, nbLigne);
		redimensionerColonneAuto();
		jeu.provoquerUpdate();
	}
	
	//redimensionne et centre
	public boolean[][] centrerEtRedimensionner(int nvNbCol, int nvNbLigne) {
		
		//determination des cases les plus extremes pour pouvoir centrer
		int gauche = tab.length;
		int droite = -1;
		int haut = tab[0].length;
		int bas = -1;
		
		for(int i=0; i<tab.length; i++) {
			for(int j=0; j<tab[0].length; j++) {
				if(tab[i][j]) {
					
					if(i<gauche) {
						gauche = i;
					}
					if(i>droite) {
						droite = i;
					}
					if(j<haut) {
						haut = j;
					}
					if(j>bas) {
						bas = j;
					}
					
				}
			}
		}
		
		boolean[][] nvTab = new boolean[nvNbCol][nvNbLigne];
		
		if(droite != -1) {
			
			int longueur = droite - gauche + 1;
			int hauteur = bas - haut + 1;
			
			int idCol = (int) (Math.floor(nvNbCol/2) - Math.floor(longueur/2));
			int idLigne = (int) (Math.floor(nvNbLigne/2) - Math.floor(hauteur/2));
			
			for(int i = 0; i < longueur; i++) {
				for(int j = 0; j < hauteur; j++) {
					
					if(idCol+i >= 0 && idCol+i < nvTab.length &&
							idLigne+j >= 0 && idLigne+j < nvTab[0].length) {
						
						nvTab[idCol + i][idLigne + j] = tab[gauche+i][haut+j];					
					}
				}
			}
			
		}	
		return nvTab;
	}
	
	
	
	
	
	
	
	
	
}

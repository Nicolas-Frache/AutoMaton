package Controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Modele.DonneesGrille;
import Modele.Jeu;
import Vue.VueGrille;

public class ControleurDessin extends MouseAdapter{

	DonneesGrille data;
	VueGrille vueGrille;
	
	public ControleurDessin(Jeu d, VueGrille v) {
		data = d.donneesGrille;
		vueGrille = v;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {		
		int nbColonne = data.getTab().length;
		int nbLigne = data.getTab()[0].length;
		
		int w = vueGrille.getWidth()-data.epaisseurQuadrillage;
		int h = vueGrille.getHeight()-data.epaisseurQuadrillage;

		//double largeurCase = w/nbColonne;
		double hauteurCase = h/nbLigne;
		
		/*if(hauteurCase > hauteurCase) {
			hauteurCase = hauteurCase;
		}
		else {
			hauteurCase = hauteurCase;
		}*/
		
		int indexColonneClick = (int) Math.floor(e.getX()/hauteurCase); 
		int indexLigneClick = (int) Math.floor(e.getY()/hauteurCase); 
		
		data.appuieSurCase(indexColonneClick, indexLigneClick);
	}

}

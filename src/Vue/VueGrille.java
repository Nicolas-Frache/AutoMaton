package Vue;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import Modele.DonneesGrille;
import Modele.Jeu;

public class VueGrille extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Jeu jeu;
	
	
	public VueGrille() {
		jeu = null;
	}

	@Override
	public void update(Observable o, Object arg1) {
		jeu = (Jeu)o;
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		if(jeu != null) {
			jeu.donneesGrille.redimensionerColonneAuto();
			DonneesGrille dg = jeu.donneesGrille;
			boolean[][] tab = dg.tab;
			
			int w = getWidth()-dg.epaisseurQuadrillage;
			int h = getHeight()-dg.epaisseurQuadrillage;
			int nbCol = tab.length;
			int nbLigne = tab[0].length;
			
			double largeurCase = w/nbCol;
			double hauteurCase = h/nbLigne;
			
			//if(largeurCase > hauteurCase) {
				largeurCase = hauteurCase;
			//}
			//else {
			//	hauteurCase = largeurCase;
			//}
			
			for(int i = 0; i < nbCol; i++ ) {
				for(int j = 0; j < nbLigne; j++) {
					g.setColor(tab[i][j] ? dg.couleurAllume : dg.couleurEteint);
					g.fillRect((int)(i*largeurCase), (int)(j*largeurCase), (int)largeurCase, (int)largeurCase);
				}
			}
			
			if(dg.affichageQuadrillage) {
				g.setColor(dg.couleurQuadrillage);
				for(int i = 0; i < nbCol+1; i++ ) {		
					for(int j = 0 ; j<dg.epaisseurQuadrillage; j++) {
						g.drawLine((int)(i*largeurCase)+j, 0,
								(int)(i*largeurCase)+j, (int)(largeurCase*nbLigne)-1+dg.epaisseurQuadrillage);					
					}

				}	
				
				for(int i = 0; i < nbLigne+1; i++ ) {
					for(int j = 0 ; j<dg.epaisseurQuadrillage; j++) {
						g.drawLine(0, (int)(i*largeurCase)+j, 
								(int)(largeurCase*nbCol)-1, (int)(i*largeurCase)+j);					
					}
				}	
			}
			
		}
		
	}

	
	
	
	
	
	
	

	
	
}

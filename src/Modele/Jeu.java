package Modele;

import java.util.Observable;

import Vue.PanPrincipale;

public class Jeu  extends Observable{

	public DonneesGrille donneesGrille;
	public Automate automate;
	
	public Jeu(PanPrincipale pp) {
		donneesGrille = new DonneesGrille(this, pp);
		automate = new Automate(this, pp);
	}
	
	public void provoquerUpdate() {
		setChanged();
		notifyObservers();
	}
	
}

package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Modele.EvolutionAuto;
import Modele.Jeu;
import Vue.PanPrincipale;

public class ControleurEvolution implements ActionListener, ChangeListener {

	private Jeu jeu;
	private PanPrincipale pan;
	
	public ControleurEvolution(Jeu jeu, PanPrincipale pan) {
		this.jeu = jeu;
		this.pan = pan;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(pan.boutonOneStep)) {
			jeu.automate.generationSuivante();
		}
		if(e.getSource().equals(pan.boutonPlay)) {
			pan.boutonPlay.setEnabled(false);
			pan.boutonPause.setEnabled(true);
			pan.boutonOneStep.setEnabled(false);
			
			jeu.automate.evoAuto = new EvolutionAuto(jeu);
			jeu.automate.evoAuto.start();
		}
		if(e.getSource().equals(pan.boutonPause)) {
			pan.boutonPause.setEnabled(false);
			pan.boutonPlay.setEnabled(true);
			pan.boutonOneStep.setEnabled(true);
			
			jeu.automate.evoAuto.interrupt();
		}
		if(e.getSource().equals(pan.boutonClear)) {
			jeu.donneesGrille.clear();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource().equals(pan.spinnerEvoVitesse)) {
			JSpinner j = (JSpinner)(e.getSource());
			jeu.automate.genParSeconde = (double) j.getValue();
		}
	}

}






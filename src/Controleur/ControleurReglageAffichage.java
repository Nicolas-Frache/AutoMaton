package Controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JColorChooser;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Modele.Jeu;
import Vue.PanPrincipale;

public class ControleurReglageAffichage implements ActionListener, ChangeListener,
	ItemListener, MouseWheelListener{
	
	private PanPrincipale pan;
	private Jeu jeu;
	
	public ControleurReglageAffichage(PanPrincipale panPrincipale, Jeu jeu) {
		pan = panPrincipale;
		this.jeu = jeu;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource().equals(pan.choixEpaisseurSpinner)) {
			JSpinner j = (JSpinner)(e.getSource());
			jeu.donneesGrille.epaisseurQuadrillage = (int) j.getValue();
			jeu.provoquerUpdate();
		}
		if(e.getSource().equals(pan.zoomSlider)) {
			JSlider source = (JSlider)e.getSource();
			jeu.donneesGrille.redimensionnerLigne(104 - source.getValue());
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(pan.zoomPlus)) {
			int nbLigne = jeu.donneesGrille.tab[0].length;
			if(nbLigne>4) {
				jeu.donneesGrille.redimensionnerLigne(nbLigne-1);
				pan.zoomSlider.setValue(104 - jeu.donneesGrille.tab[0].length);
			}
		}
		if(e.getSource().equals(pan.zoomMoins)) {
			int nbLigne = jeu.donneesGrille.tab[0].length;
			jeu.donneesGrille.redimensionnerLigne(nbLigne+1);
			pan.zoomSlider.setValue(104 - jeu.donneesGrille.tab[0].length);
		}
		
		if(e.getSource().equals(pan.couleurCelluleVivante)) {
			Color newColor = JColorChooser.showDialog(
	                pan,
	                "Palette de couleur",
	                jeu.donneesGrille.couleurAllume);
			
			if(newColor != null) {
				jeu.donneesGrille.couleurAllume = newColor;
				pan.couleurCelluleVivante.setBackground(newColor);
				jeu.provoquerUpdate();
			}
		}
		
		if(e.getSource().equals(pan.couleurCelluleMorte)) {
			Color newColor = JColorChooser.showDialog(
	                pan,
	                "Palette de couleur",
	                jeu.donneesGrille.couleurEteint);
			
			if(newColor != null) {
				jeu.donneesGrille.couleurEteint = newColor;
				pan.couleurCelluleMorte.setBackground(newColor);
				jeu.provoquerUpdate();
			}
		}
		
		if(e.getSource().equals(pan.couleurQuadrillage)) {
			Color newColor = JColorChooser.showDialog(
	                pan,
	                "Palette de couleur",
	                jeu.donneesGrille.couleurQuadrillage);
			
			if(newColor != null) {
				jeu.donneesGrille.couleurQuadrillage = newColor;
				pan.couleurQuadrillage.setBackground(newColor);
				jeu.provoquerUpdate();
			}
		}

		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource().equals(pan.afficherQuadrillageCheckBox)) {
			jeu.donneesGrille.affichageQuadrillage = (e.getStateChange()==1);
			jeu.provoquerUpdate();
		}
	}	

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		//zoom
		if(e.getWheelRotation() < 0) {
			int nbLigne = jeu.donneesGrille.tab[0].length;
			if(nbLigne>5) {
				jeu.donneesGrille.redimensionnerLigne(nbLigne-2);
				pan.zoomSlider.setValue(104 - jeu.donneesGrille.tab[0].length);
			}
		}
		//dezoom
		else {
			int nbLigne = jeu.donneesGrille.tab[0].length;
			jeu.donneesGrille.redimensionnerLigne(nbLigne+2);
			pan.zoomSlider.setValue(104 - jeu.donneesGrille.tab[0].length);
		}
	}
	
	
	
	
	
	
	
	
	
	
}


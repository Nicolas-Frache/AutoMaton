package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modele.Jeu;
import Vue.PanPrincipale;

public class ControleurAutomate implements ActionListener{

	private PanPrincipale pan;
	private Jeu jeu;
	
	public ControleurAutomate(PanPrincipale panPrincipale, Jeu jeu) {
		pan = panPrincipale;
		this.jeu = jeu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(pan.listePreset)) {
			majListe();
		}
		else {
			if(e.getSource().equals(pan.presetCheckBox)) {
				cocherPresetCheckbox();
			}
			else {
				jeu.automate.changerCheckbox();
			}
		}
	}
	
	public void majListe() {
		jeu.automate.choisirPreset(pan.listePreset.getSelectedIndex());
		for(int i=0; i<9; i++) {
			pan.tabCheckBox[0][i].setSelected(jeu.automate.tabNaissance[i]);
			pan.tabCheckBox[1][i].setSelected(jeu.automate.tabSurvie[i]);
		}	
	}
	
	public void cocherPresetCheckbox() {
		for(int i=0; i<9; i++) {
			pan.tabCheckBox[0][i].setEnabled(!pan.presetCheckBox.isSelected());
			pan.tabCheckBox[1][i].setEnabled(!pan.presetCheckBox.isSelected());
		}
		pan.listePreset.setEnabled(pan.presetCheckBox.isSelected());
		majListe();	
	}
	
	
	
	
	
	
}

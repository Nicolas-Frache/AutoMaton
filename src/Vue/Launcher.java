package Vue;

import javax.swing.JFrame;
import Modele.Jeu;

public class Launcher {

	
	public static void main(String[] args) {
		/*
		DonneesGrille data = new DonneesGrille();
		
		VueGrille vg = new VueGrille();
		/*
		vg.afficherQuadrillage(true);
		vg.setEpaisseurQuadrillage(1);
		*/
		JFrame frame = new JFrame();
		PanPrincipale pp = new PanPrincipale();
		Jeu jeu = new Jeu(pp);
		pp.donnerJeu(jeu);
		
		frame.setContentPane(pp);
		
		frame.setSize(900,600);
		
		jeu.addObserver(pp.dessin);
		jeu.addObserver(pp);
		
		jeu.provoquerUpdate();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
/*		frame.add(vg);
		
		data.addObserver(vg);
		
		vg.addMouseListener(new ControleurDessin(data, vg));
		
		
		data.provoquerUpdate();
		*/
		
		frame.setVisible(true);
		
		
		
		
		
	}
	
	
}

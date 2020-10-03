package Modele;

public class EvolutionAuto extends Thread {

	Jeu jeu;
	
	public EvolutionAuto(Jeu jeu) {
		this.jeu = jeu;
	}
	
	@Override
	public void run(){	
		while(true) {
			jeu.automate.generationSuivante();
			try {
				double t=1000;
				if(jeu.automate.genParSeconde>=0.1) {
					t = (1/jeu.automate.genParSeconde);					
				}
				Thread.sleep((long) (t*1000));
			} catch (InterruptedException e) { 
				//e.printStackTrace(); 
				stop();
			}
		}			
	}
	
}
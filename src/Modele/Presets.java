package Modele;

import java.util.HashMap;

public class Presets {
	private static Presets instance = new Presets();
	private static HashMap<String, boolean[][]> presets;

	private Presets(){
		presets = new HashMap<String, boolean[][]>();
		String v = "4,3,10 01,011 0,1 00 1";
		presets.put("P1", convertStringIntoPreset(v));
		v = "36,19,"
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ ""
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ "00000 01110 00000 00000 00000 00000 00000 0,"
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ ""
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ ""
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ "00000 00000 00000 00000 00000 00000 00000 0,"
				+ "00000 00000 00000 00000 00000 00000 00000 0";
		presets.put("Lanceur de vaisseau",convertStringIntoPreset(v));
	}
	
	public static synchronized Presets getInstance() {
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public static HashMap<String, boolean[][]> getPresets(){
		return (HashMap<String, boolean[][]>) presets.clone();
	}
	
	/**
	 * Retourne un tableau de boolean à 2 dimensions 
	 * @param s
	 * @return tab[indexLigne][indexColonne]
	 */
	public boolean[][] convertStringIntoPreset(String s){
		String[] tabLigne = s.split(",");
		int nbLigne = Integer.parseInt(tabLigne[1]);
		int longueurLigne = Integer.parseInt(tabLigne[0]);
		boolean[][] tab = new boolean[nbLigne][longueurLigne];
		
		for(int i=0; i<tabLigne.length; i++) {
			String[] tmp = tabLigne[i].split(" ");
			if(tmp.length >1) {
				String stmp="";
				for(String j: tmp) {
					stmp+=j;
				}
				tabLigne[i] = stmp;
			}
			
		}
		for(int i=2; i<nbLigne+2; i++) {
			for(int j=0; j<longueurLigne; j++) {
				tab[i-2][j] = tabLigne[i].charAt(j) == '1';	
			}
		}
		
		return tab;
	}	
}
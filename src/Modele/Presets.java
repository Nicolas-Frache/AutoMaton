package Modele;

import java.util.HashMap;

public class Presets {
	private static Presets instance = new Presets();
	private static HashMap<String, boolean[][]> presets;

	private Presets(){
		presets = new HashMap<String, boolean[][]>();
		String v = "4,3,10 01,011 0,1 00 1";
		presets.put("P1", convertStringIntoPreset(v));
		v = "36,9,"
				+ "00000 00000 00000 00000 00010 00000 00000 0,"
				+ "00000 00000 00000 00000 00011 00000 00000 0,"
				+ "00000 00000 01100 00000 00001 10000 00001 1,"
				+ "00000 00000 11100 00000 00001 11000 00001 1,"
				+ "11000 00101 10000 00000 00001 10000 00000 0,"
				+ ""
				+ "11000 00100 10000 00000 00011 00000 00000 0,"
				+ "00000 00101 10000 00000 00010 00000 00000 0,"
				+ "00000 00000 11100 00000 00000 00000 00000 0,"
				+ "00000 00000 01100 00000 00000 00000 00000 0";

		presets.put("Lanceur",convertStringIntoPreset(v));
		
		v = "3,3,"
				+ "100,"
				+ "011,"
				+ "110";
		presets.put("Vaisseau 1",convertStringIntoPreset(v));
		
		
		v= "27,8,"
				+ "00000 00001 00000 00100 00000 00,"
				+ "00011 01010 11000 11010 10110 00,"
				+ "11101 01110 00000 00011 10101 11,"
				+ "10001 01000 00101 00000 10100 01,"
				+ ""
				+ "00001 10000 00101 00000 01100 00,"
				+ "01100 00000 00101 00000 00001 10,"
				+ "01101 10000 00000 00000 01101 10,"
				+ "00000 10000 00000 00000 01000 00";
		presets.put("Vaisseau 2",convertStringIntoPreset(v));		
				
		
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
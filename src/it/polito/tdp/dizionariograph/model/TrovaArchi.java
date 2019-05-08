package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;

public class TrovaArchi {

	public static List<String> getVicini(String parola, List<String> parole, int lungh) {
		List<String> vicini = new ArrayList<String>();
		for(String s : parole) {
			if(isVicine(parola,s))
				vicini.add(s);
		}
		return vicini;
	}

	private static boolean isVicine(String parola, String s) {
		int count=0;
		if(parola.length() != s.length())
			return false;
		
		for(int i=0;i<s.length();i++) {
			if(parola.charAt(i) != s.charAt(i))
				count++;
		}
		if(count == 1)
			return true;
		else
			return false;
	}
}

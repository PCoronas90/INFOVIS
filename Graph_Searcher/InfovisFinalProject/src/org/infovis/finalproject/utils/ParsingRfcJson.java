package org.infovis.finalproject.utils;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



public class ParsingRfcJson {
			


	

	public static  Map<String, String> parse(Map<String, ArrayList<ArrayList<String>>> infoWithField2)   {
			Map<String, String> infoWithField = new HashMap<String, String>();
			Set<String> titoli= infoWithField2.keySet();
			for(String titolo : titoli){    
				infoWithField.put(infoWithField2.get(titolo).get(5).get(0), titolo);
				
			}
			
			return infoWithField;
	}
	}
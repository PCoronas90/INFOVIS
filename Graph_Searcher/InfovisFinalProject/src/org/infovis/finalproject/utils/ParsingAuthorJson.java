package org.infovis.finalproject.utils;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



public class ParsingAuthorJson {
			


	

	public static  Map<String, ArrayList<String>> parse(Map<String, ArrayList<ArrayList<String>>> infoWithField2)   {
			Map<String, ArrayList<String>> infoWithField = new HashMap<String, ArrayList<String>>();
			Set<String> titoli= infoWithField2.keySet();
			for(String titolo : titoli){ 
				ArrayList<String> autori= infoWithField2.get(titolo).get(0);
				for(String autore : autori){ 
					if(infoWithField.containsKey(autore)){
						infoWithField.get(autore).add(titolo);
					}
					else{
						ArrayList<String> titles=new ArrayList<String>();
						titles.add(titolo);
				infoWithField.put(autore, titles);}}
				
			}
			
			return infoWithField;
	}
	}
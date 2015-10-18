package org.infovis.finalproject.utils;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.Map;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ParsingAuthor {
			
	private static BufferedReader br;
	private static String titolo;

	private static ArrayList<String> autori= new ArrayList<String>();

	public static Map<String, ArrayList<String>> parse(File file) throws IOException  {	
	
		Map<String, ArrayList<String>> infoWithField = new HashMap<String, ArrayList<String>>();
		FileReader fr = null;
		
		fr = new FileReader(file);
		br = new BufferedReader(fr);
		String linea=null;		
		
			while ((linea = br.readLine())!= null) {
			if(!linea.contains("</rfc-entry>")){				
			}
			Document doc = Jsoup.parse(linea, "UTF-8");			
			if(linea.contains("title") ){
			titolo=doc.getElementsByTag("title").text();}			
			
			if(linea.contains("name")){	
				autori.add(doc.getElementsByTag("name").text());
						
			}	
			
			if(linea.contains("</rfc-entry>")){
				ArrayList<String> titoli=new ArrayList<String>();
				
				
				for(int i=0;i<autori.size();i++){
					if(infoWithField.containsKey(autori.get(i))){
						if(!infoWithField.get(autori.get(i)).contains(titolo)){
						infoWithField.get(autori.get(i)).add(titolo);}
					}
					else{
						titoli.add(titolo);	
						infoWithField.put(autori.get(i), titoli);	
					}
								}
				
				
					
				
				
				
				
				
				 autori.clear();
							}
			
			
		
		
			}
			return infoWithField;
}
	}

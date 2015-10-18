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

public class ParsingKeywords {
			
	private static BufferedReader br;
	private static String titolo;
	private static boolean findKey=false;

	private static ArrayList<String> keywords= new ArrayList<String>();

	

	

	


	public static Map<String, ArrayList<String>> parseKey(File file) throws IOException  {
		
		
	
	
		//File file=new File("C:/Users/Pietro/Desktop/rfc-index.txt");	
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
			
			
			if(linea.contains("<keywords>")){	
				findKey=true;
						
			}
			
			if(linea.contains("</keywords>")){	
				findKey=false;
						
			}
			
			if(linea.contains("<kw>") & findKey==true){	
				keywords.add(doc.getElementsByTag("kw").text());
						
			}
			
			
		
			
			
			
			if(linea.contains("</rfc-entry>")){
				ArrayList<String> titoli=new ArrayList<String>();
				
				
				
				for(int i=0;i<keywords.size();i++){
					if(infoWithField.containsKey(keywords.get(i))){
						if(!infoWithField.get(keywords.get(i)).contains(titolo)){
						infoWithField.get(keywords.get(i)).add(titolo);}
					}
					else{
						titoli.add(titolo);	
						infoWithField.put(keywords.get(i), titoli);	
					}
					
				}
				
				
					
					//System.out.println(titolo+autori.get(i));
				
				
				
				 findKey=false;
				 keywords.clear();
				//if(titolo.contains("Binary Message Forms in Computer")){
				//	System.out.println(autore+""+titolo);	
				//	System.out.println(infoWithField.get(titolo).get(1));
				//}
				//autore="";
				
				//System.out.println(titolo);
				//id
				//System.out.println(values.get(0));
				//autore
				//System.out.println(values.get(1));
				//mese
				//System.out.println(values.get(2));
				//anno
				//System.out.println(values.get(3));
				//stato
				//System.out.println(values.get(4));
				//System.out.println("");
				
			}
			
			
		
		
			}
			return infoWithField;
	}
}
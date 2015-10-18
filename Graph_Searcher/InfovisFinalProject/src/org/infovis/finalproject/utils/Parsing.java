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

public class Parsing {
			
	private static BufferedReader br;
	private static String titolo;
	private static String doc_id;
	private static String autore;
	private static String mese;
	private static String anno;
	private static String stato;
	private static String page;
	private static String abstr;
	private static String streamDoc;
	private static String obsolete_by;
	private static String updated_by;
	private static String obsolete;
	private static String updated;
	private static String doi;
	private static String keywords;
	private static boolean writeDocid=false;
	private static boolean obsoleteByFind=false;
	private static boolean updatedByFind=false;
	private static boolean obsoleteFind=false;
	private static boolean updatedFind=false;
	private static boolean findKey=false;

	

	


	public static Map<String, ArrayList<String>> parse(File file) throws IOException  {
		
		
	
	
		//File file=new File("C:/Users/Pietro/Desktop/rfc-index.txt");	
			Map<String, ArrayList<String>> infoWithField = new HashMap<String, ArrayList<String>>();
		FileReader fr = null;
		
		fr = new FileReader(file);
		br = new BufferedReader(fr);
		String linea=null;
		
		
		
		     int i=1;
			while ((linea = br.readLine())!= null) {
			if(!linea.contains("</rfc-entry>")){
				
			}
			Document doc = Jsoup.parse(linea, "UTF-8");
			
			if(linea.contains("title") ){
			titolo=doc.getElementsByTag("title").text();}
			
			if(linea.contains("doc-id") && writeDocid==false){
				 doc_id=doc.getElementsByTag("doc-id").text();
				 writeDocid=true;
				}
			
			if(linea.contains("name")){	
				if(autore==" "){
				autore=doc.getElementsByTag("name").text();}
				else{
				autore=autore+", "+doc.getElementsByTag("name").text();	
				}
							
			}
			
			if(linea.contains("month")){
			 mese=doc.getElementsByTag("month").text();
			}
			
			if(linea.contains("year")){
			 anno=doc.getElementsByTag("year").text();
			}
			
			if(linea.contains("current-status")){
			stato=doc.getElementsByTag("current-status").text();
			}
			
			if(linea.contains("<obsoleted-by>")){
				obsoleteByFind=true;
				
			}
			
				if(linea.contains("</obsoleted-by>")){
					obsoleteByFind=false;
				}
				
				if(linea.contains("<obsoletes>")){
					obsoleteFind=true;
					
				}
				
				if(linea.contains("</obsoletes>")){
					obsoleteFind=false;
				}
				
				if(linea.contains("</updates>")){
					updatedFind=false;
				}
				
				if(linea.contains("<updates>")){
					updatedFind=true;
					
				}
				
					
			
			if(linea.contains("doc-id")&& obsoleteByFind==true){
				if(obsolete_by==" "){
					obsolete_by=doc.getElementsByTag("doc-id").text();}
					else{
						obsolete_by=obsolete_by+", "+doc.getElementsByTag("doc-id").text();	
					}
				
				}
			

			if(linea.contains("<keywords>")){	
				findKey=true;
						
			}
			
			if(linea.contains("</keywords>")){	
				findKey=false;
						
			}
			
			if(linea.contains("<kw>") & findKey==true){	
				if(keywords==" "){
					keywords=doc.getElementsByTag("kw").text();}
					else{
						keywords=keywords+", "+doc.getElementsByTag("kw").text();	
					}
			}
			
			if(linea.contains("doc-id")&& obsoleteFind==true){
				if(obsolete==" "){
					obsolete=doc.getElementsByTag("doc-id").text();}
					else{
						obsolete=obsolete+", "+doc.getElementsByTag("doc-id").text();	
					}
				
				}
			
			if(linea.contains("<updated-by>")){
				updatedByFind=true;
				
			}
			
				if(linea.contains("</updated-by>")){
					updatedByFind=false;
				}
			
			if(linea.contains("doc-id")&& updatedByFind==true){
				if(updated_by==" "){
					updated_by=doc.getElementsByTag("doc-id").text();}
					else{
						updated_by=updated_by+", "+doc.getElementsByTag("doc-id").text();	
					}
				
				}
			
			if(linea.contains("doc-id")&& updatedFind==true){
				if(updated==" "){
					updated=doc.getElementsByTag("doc-id").text();}
					else{
						updated=updated+", "+doc.getElementsByTag("doc-id").text();	
					}
				
				}
					
			if(linea.contains("page-count")){
				page=doc.getElementsByTag("page-count").text();
				}
			
			if(linea.contains("abstract")){
				abstr=doc.getElementsByTag("abstract").text();
				}
			
			if(linea.contains("stream")){
				streamDoc=doc.getElementsByTag("stream").text();
			}
			
			if(linea.contains("doi")){
				doi=doc.getElementsByTag("doi").text();
			}
		
			
			
			
			if(linea.contains("</rfc-entry>")){
				ArrayList<String> values=new ArrayList<String>();
				values.add(doc_id);
				values.add(autore);
				values.add(mese);
				values.add(anno);
				values.add(stato);
				values.add(page);
				values.add(abstr);
				values.add(streamDoc);
				values.add(doi);
				values.add(obsolete_by);
				values.add(updated_by);
				values.add(obsolete);
				values.add(updated);
				values.add(keywords);
				
				if(infoWithField.containsKey(titolo)){
					titolo=titolo+"("+i+")";
					i++;
				}
				
				infoWithField.put(titolo, values);
				 writeDocid=false;
				 autore=" ";
				 obsolete_by= " ";
				 updated_by= " ";
				 obsolete= " ";
				 updated= " ";
				 keywords=" ";
				//if(titolo.contains("Binary Message Forms in Computer")){
				//	System.out.println(autore+""+titolo);	
				//	System.out.println(infoWithField.get(titolo).get(1));
				//}
				//autore="";
				
				 //System.out.println(titolo);
				//id
				
				//autore
				//System.out.println("obsolete"+values.get(9));
				//System.out.println("updated"+values.get(10));}
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

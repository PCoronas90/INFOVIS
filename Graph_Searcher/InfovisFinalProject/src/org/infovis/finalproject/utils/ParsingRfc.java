package org.infovis.finalproject.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ParsingRfc {
			
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
	private static String keywords;
	
	private static String doi;
	private static boolean writeDocid=false;
	private static boolean findKey=false;

	

	public static  Map<String, ArrayList<String>> parse(File file)   {

		

		

		
		//File file=new File("C:/Users/Pietro/Desktop/rfc-index.txt");	
			Map<String, ArrayList<String>> infoWithField = new HashMap<String, ArrayList<String>>();
		FileReader fr = null;
		
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		br = new BufferedReader(fr);
		String linea=null;
		
		
		
		
			try {
				while ((linea = br.readLine())!= null) {
				if(!linea.contains("</rfc-entry>")){
					
				}
				Document doc = Jsoup.parse(linea, "UTF-8");
				
				if(linea.contains("title") ){
				titolo=doc.getElementsByTag("title").text();}
				
				if(linea.contains("<rfc-not-issued-entry>")){
					writeDocid=true;				
				}
				
				if(linea.contains("</rfc-not-issued-entry>")){
					writeDocid=false;				
				}
				
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
					values.add(titolo);
					values.add(autore);
					values.add(mese);
					values.add(anno);
					values.add(stato);
					values.add(page);
					values.add(abstr);
					values.add(streamDoc);
					values.add(doi);
					values.add(keywords);
					
					
					infoWithField.put(doc_id, values);
					 writeDocid=false;
					 autore=" ";
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return infoWithField;
}
	}
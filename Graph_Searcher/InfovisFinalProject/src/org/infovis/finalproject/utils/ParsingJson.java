package org.infovis.finalproject.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ParsingJson {
			
	private static BufferedReader br;
	private static String titolo;
	private static boolean obsoleteByFind=false;
	private static boolean updatedByFind=false;
	private static boolean obsoleteFind=false;
	private static boolean updatedFind=false;;
	private static boolean writeDocid=false;
	private static ArrayList<String> autori = new ArrayList<String>();
	private static ArrayList<String> doc_id = new ArrayList<String>();

	private static ArrayList<String> obsolete_by= new ArrayList<String>();
	private static ArrayList<String> updated_by= new ArrayList<String>();
	private static ArrayList<String> obsolete= new ArrayList<String>();
	private static ArrayList<String> updated= new ArrayList<String>();

	

	


	public static Map<String, ArrayList<ArrayList<String>>> parse(File file)   {
		
		
	
	
		//File file=new File("C:/Users/Pietro/Desktop/rfc-index.txt");	
			Map<String, ArrayList<ArrayList<String>>> infoWithField = new HashMap<String, ArrayList<ArrayList<String>>>();
		FileReader fr = null;
		
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		br = new BufferedReader(fr);
		String linea=null;
		
		
		
		     int i=1;
			try {
				while ((linea = br.readLine())!= null) {
				if(!linea.contains("</rfc-entry>")){
					
				}
				
				
				Document doc = Jsoup.parse(linea, "UTF-8");
				
				if(linea.contains("title") ){
				titolo=doc.getElementsByTag("title").text();}
				
				if(linea.contains("doc-id") && writeDocid==false){
					
					 doc_id.add(doc.getElementsByTag("doc-id").text());
					 writeDocid=true;
					}
				
				if(linea.contains("name") & doc.getElementsByTag("name").text()!=null){	
					autori.add(doc.getElementsByTag("name").text());
					
								
				}
				
				if(linea.contains("<rfc-not-issued-entry>")){
					writeDocid=true;				
				}
				
				if(linea.contains("</rfc-not-issued-entry>")){
					writeDocid=false;				
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
					if(!doc.getElementsByTag("doc-id").text().contains("IEN")&!doc.getElementsByTag("doc-id").text().contains("NIC")
							&!doc.getElementsByTag("doc-id").text().contains("RTR")){
							obsolete_by.add(doc.getElementsByTag("doc-id").text());	
						
					
					}}
				

				
				
				
				
				if(linea.contains("doc-id")&& obsoleteFind==true){
					if(!doc.getElementsByTag("doc-id").text().contains("IEN")&!doc.getElementsByTag("doc-id").text().contains("NIC")
							&!doc.getElementsByTag("doc-id").text().contains("RTR")){
							obsolete.add(doc.getElementsByTag("doc-id").text());	
						
					
					}}
				
				if(linea.contains("<updated-by>")){
					updatedByFind=true;
					
				}
				
					if(linea.contains("</updated-by>")){
						updatedByFind=false;
					}
				
				if(linea.contains("doc-id")&& updatedByFind==true){
					if(!doc.getElementsByTag("doc-id").text().contains("IEN")&!doc.getElementsByTag("doc-id").text().contains("NIC")
							&!doc.getElementsByTag("doc-id").text().contains("RTR")){
							updated_by.add(doc.getElementsByTag("doc-id").text());	
						
					}
					}
				
				if(linea.contains("doc-id")&& updatedFind==true){
					if(!doc.getElementsByTag("doc-id").text().contains("IEN")&!doc.getElementsByTag("doc-id").text().contains("NIC")
							&!doc.getElementsByTag("doc-id").text().contains("RTR")){
							updated.add(doc.getElementsByTag("doc-id").text());	
						
					}
					}
						
				
				
				if(linea.contains("</rfc-entry>")){
					for(int h=0;h<autori.size();h++){
						if(autori.get(h).equals(" ") | autori.get(h).equals("") | autori.get(h)==""|autori.get(h)==" "|autori.get(h)==null|
								autori.get(h).isEmpty()|autori.get(h).length()<2){
							autori.remove(h);
						}
					}
					ArrayList<ArrayList<String>> values=new ArrayList<ArrayList<String>>();
					values.add(autori);
					values.add(obsolete_by);
					values.add(updated_by);
					values.add(obsolete);
					values.add(updated);
					values.add(doc_id);
					
					if(infoWithField.containsKey(titolo)){
						titolo=titolo+"("+i+")";
						i++;
					}
					
					infoWithField.put(titolo, values);
					writeDocid=false;
					autori = new ArrayList<String>();
					doc_id = new ArrayList<String>();

					obsolete_by= new ArrayList<String>();
					updated_by= new ArrayList<String>();
					obsolete= new ArrayList<String>();
					updated= new ArrayList<String>();
					
					
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

package org.infovis.finalproject.utils;

import java.io.File;  
import java.io.FileWriter;  
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.json.simple.JSONArray;  
import org.json.simple.JSONObject;  
  

public class JSONcreator {  
	 static File file2=new File("C:/Users/Pietro/Desktop/rfc-index.txt");
	 static Map<String, ArrayList<ArrayList<String>>> infoWithField = ParsingJson.parse(file2);
	 static Map<String, String> infoWithField2  = ParsingRfcJson.parse(infoWithField);
	 static Map<String, ArrayList<String>> infoWithField3  = ParsingAuthorJson.parse(infoWithField);
	
	
	

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ParseException, IOException, InvalidTokenOffsetsException {
		
		int h=0;
		Set<String> titoli= infoWithField.keySet();
		for(String titolo : titoli){    
			//System.out.println(h++);
       JSONArray value = new JSONArray();
       
       
        JSONObject root = new JSONObject();
        
        root.put("name",infoWithField.get(titolo).get(5).get(0));  
        root.put("color","#FF3300");
        root.put("title",titolo);
        
       JSONObject autori = new JSONObject();
        autori.put("name", "authors");
        autori.put("color","#FFFF00");
        
      
       JSONArray authorlist = new JSONArray();
      
        for(int i=0;i<infoWithField.get(titolo).get(0).size();i++){
        	
        	if(infoWithField.get(titolo).get(0).get(i).length()>2){
        		
        	  JSONObject author = new JSONObject(); 
        author.put("name",  infoWithField.get(titolo).get(0).get(i));  
        author.put("color","#FFFF00"); 
        JSONArray recursive=recursiveAuthor(infoWithField.get(titolo).get(0).get(i));
           author.put("children",recursive);
        authorlist.add(author);}}
        autori.put("children", authorlist);
        value.add(autori); 
        
        if(infoWithField.get(titolo).get(1).size()>0){
        	JSONObject obsoleteby = new JSONObject();
            obsoleteby.put("name", "obsoleteBy");
            obsoleteby.put("color", "#FF6600");
            JSONObject obsolete = new JSONObject();
            JSONArray obsoletelist = new JSONArray();
            obsolete.put("name", "obsolete");
            obsolete.put("color", "#FF6600");
            
        JSONArray obsoletebylist = new JSONArray();
        for(int i=0;i<infoWithField.get(titolo).get(1).size();i++){
        	 JSONObject obsolete_by = new JSONObject();
        obsolete_by.put("name",infoWithField.get(titolo).get(1).get(i));
        obsolete_by.put("color","#FF3300");
        obsolete_by.put("title", infoWithField2.get(infoWithField.get(titolo).get(1).get(i)));

       
        
        
        obsoletebylist.add(obsolete_by);
        }         
        obsolete.put("children", obsoletebylist);
        obsoletelist.add(obsolete);
        obsoleteby.put("children", obsoletelist);
        value.add(obsoleteby);}
        
        if(infoWithField.get(titolo).get(3).size()>0){

            JSONObject obsolete = new JSONObject();
            obsolete.put("name", "obsolete");
            obsolete.put("color", "#00FF00");
            
            JSONObject obsoleteBY = new JSONObject();
            JSONArray obsoleteBYlist = new JSONArray();
            obsoleteBY.put("name", "obsoleteBy");
            obsoleteBY.put("color", "#FF6600");
        
        JSONArray obsoletelist = new JSONArray();
        for(int i=0;i<infoWithField.get(titolo).get(3).size();i++){
        	JSONObject obsoletes = new JSONObject();
        obsoletes.put("name", infoWithField.get(titolo).get(3).get(i));
        obsoletes.put("color", "#00FF00");
        obsoletes.put("title", infoWithField2.get(infoWithField.get(titolo).get(3).get(i)));
      
      
        
        obsoletelist.add(obsoletes);
        }
        obsoleteBY.put("children", obsoletelist);
        obsoleteBYlist.add(obsoleteBY);        
        obsolete.put("children", obsoleteBYlist);
        value.add(obsolete);}
  
        if(infoWithField.get(titolo).get(2).size()>0){
        	JSONObject updatedby = new JSONObject();
            updatedby.put("name", "updatedBy");
            updatedby.put("color", "#33CC99");

            JSONObject updated = new JSONObject();
            JSONArray updatedlist = new JSONArray();
            updated.put("name", "updated");
            updated.put("color", "#FF6600");
        
            
            JSONArray values = new JSONArray();
            for(int i=0;i<infoWithField.get(titolo).get(2).size();i++){
            	JSONObject updated_by = new JSONObject();
            updated_by.put("name",infoWithField.get(titolo).get(2).get(i));
            updated_by.put("color", "#33CC99");
            updated_by.put("title", infoWithField2.get(infoWithField.get(titolo).get(2).get(i)));
            
            values.add(updated_by);
            } 
            updated.put("children", values);
            updatedlist.add(updated);  
            
            updatedby.put("children", updatedlist);
            value.add(updatedby);
            }
        
        if(infoWithField.get(titolo).get(4).size()>0){
        	JSONObject updated = new JSONObject();
            updated.put("name", "updated");
            updated.put("color", "#CCCC99");
            JSONObject updatedBY = new JSONObject();
            JSONArray updatedlistBY = new JSONArray();
            updatedBY.put("name", "updatedBy");
            updatedBY.put("color", "#FF6600");
            
            JSONArray values = new JSONArray();
            for(int i=0;i<infoWithField.get(titolo).get(4).size();i++){
            	 
            	JSONObject updates = new JSONObject();
            updates.put("name",infoWithField.get(titolo).get(4).get(i));
            updates.put("color", "#CCCC99");
            
            updates.put("title", infoWithField2.get(infoWithField.get(titolo).get(4).get(i)));
            
            
            values.add(updates);
            } 
            updatedBY.put("children", values);
            updatedlistBY.add(updatedBY);
            
            updated.put("children",updatedlistBY);
            value.add(updated);
            }
        
       
      
      
       
       
      
       
        root.put("children",value);  
       
  
        try {  
              
        	 String path="C:/Users/Pietro/Desktop/Workspace/InfovisFinalProject/WebContent/JSON/"+infoWithField.get(titolo).get(5).get(0)+".json";
        	 File file=new File(path);  
            file.createNewFile();  
            FileWriter fileWriter = new FileWriter(file);  
  
            fileWriter.write(root.toJSONString());  
            fileWriter.flush();  
            fileWriter.close();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  }
 System.out.println("finish");
	}   

	
	
	@SuppressWarnings("unchecked")
	public static  JSONArray recursiveAuthor(String author) throws IOException{
		
		 
		 ArrayList<ArrayList<String>> titlesSplit=  new ArrayList<ArrayList<String>>();
		 ArrayList<String> groups1=new ArrayList<String>();
		 ArrayList<String> groups2=new ArrayList<String>();
		 ArrayList<String> groups3=new ArrayList<String>();
		 ArrayList<String> groups4=new ArrayList<String>();
		 ArrayList<String> groups5=new ArrayList<String>();
		 ArrayList<String> groups6=new ArrayList<String>();
		 ArrayList<String> groups7=new ArrayList<String>();
		 
		 boolean add1=false;
		 boolean add2=false;
		 boolean add3=false;
		 boolean add4=false;
		 boolean add5=false;
		 boolean add6=false;
		 boolean add7=false;
		
		 for(int i=0;i<infoWithField3.get(author).size();i++ ){
			 String titolo=infoWithField3.get(author).get(i);
			 //fallo con gli add
			 if(groups1.size()<11){
				groups1.add(titolo);
				add1=true;}
			 if(groups1.size()==10&groups2.size()<11&i<infoWithField3.get(author).size()-1){
					groups2.add(titolo);
					add2=true;}
			 if(groups2.size()==10&groups3.size()<11&i<infoWithField3.get(author).size()-1){
					groups3.add(titolo);
					add3=true;}
			 if(groups3.size()==10&groups4.size()<11&i<infoWithField3.get(author).size()-1){
					groups4.add(titolo);
					add4=true;}
			 if(groups4.size()==10&groups5.size()<11&i<infoWithField3.get(author).size()-1){
					groups5.add(titolo);
					add5=true;}
			 if(groups5.size()==10&groups6.size()<11&i<infoWithField3.get(author).size()-1){
					groups6.add(titolo);
					add6=true;}
			 if(groups6.size()>=10){
					groups7.add(titolo);
					add7=true;}
			 if(i==infoWithField3.get(author).size()-1){
				 if(add1==true){
					 titlesSplit.add(groups1);	 
				 }
				 if(add2==true){
					 titlesSplit.add(groups2);	 
				 }
				 if(add3==true){
					 titlesSplit.add(groups3);	 
				 }
				 if(add4==true){
					 titlesSplit.add(groups4);	 
				 }
				 if(add5==true){
					 titlesSplit.add(groups5);	 
				 }
				 if(add6==true){
					 titlesSplit.add(groups6);	 
				 }
				 if(add7==true){
					 titlesSplit.add(groups7);	 
				 }
			 }	
			 }
			 
		 
	        
		JSONArray result= new JSONArray(); 
		
		for(int i=0;i<titlesSplit.size();i++){
			 JSONObject group = new JSONObject();
			 JSONArray listTitle= new JSONArray();
			 group.put("name","group"+i);  
			 group.put("color","#CCCCCC");
			 //group.put("children",listTitle);
			 
			for(String titolo:titlesSplit.get(i) ){
				System.out.println(titlesSplit.get(i).size());
		
	  		
				infoWithField.get(titolo).get(5).get(0); 
       
        JSONObject root = new JSONObject();
        
        root.put("name",infoWithField.get(titolo).get(5).get(0));  
        root.put("color","#FF3300");
        root.put("title", titolo);
        
        
        
      
        listTitle.add(root);
        }
		
			group.put("children",listTitle);
			result.add(group);}	
		
		return result;
	}
}  




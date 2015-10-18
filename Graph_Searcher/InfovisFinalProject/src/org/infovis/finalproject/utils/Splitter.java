package org.infovis.finalproject.utils;

import java.util.ArrayList;
import java.util.List;

public class Splitter {

	
	public static List<String> split(String list){
		List<String> result= new ArrayList<String>();
		int j=0;
		for(int i=0;i<list.length();i++){
			
			if(list.charAt(i)==','){
			String partial=list.substring(j, i);	
			result.add(partial);
			//System.out.println(partial);
			j=i+1;
			}
			if(i==list.length()-1){
			String	partial=list.substring(j, list.length());
			result.add(partial);
			//System.out.println(partial);
			}
		}
		//System.out.println("------------");
		return result;
		
		
	}
}

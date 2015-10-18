package org.infovis.finalproject.searcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.infovis.finalproject.pages.Result;
import org.infovis.finalproject.pages.Search;
import org.infovis.finalproject.utils.Parsing;
import org.infovis.finalproject.utils.ParsingJson;

import org.infovis.finalproject.utils.ParsingRfc;
import org.infovis.finalproject.utils.ParsingRfcJson;
import org.infovis.finalproject.utils.Splitter;

public class Searcher {
	private String pathOfIndex;
	private String pathOfIndexRFC;
	


	File file=new File("C:/Users/Pietro/Desktop/rfc-index.txt");	
	Map<String, ArrayList<String>> infoWithField = Parsing.parse(file);
	Map<String, ArrayList<String>> infoWithField3 = ParsingRfc.parse(file);
	Map<String, ArrayList<String>> infoWithField6 = ParsingRfc.parse(file);
	Map<String, String> rfcList = ParsingRfcJson.parse(ParsingJson.parse(file));

	
	

	

	public Searcher() throws IOException {
		
		this.pathOfIndex = "C:/Users/Pietro/Desktop/Workspace/InfovisFinalProject/index";
		this.pathOfIndexRFC="C:/Users/Pietro/Desktop/Workspace/InfovisFinalProject/indexRFC";
		
		
	}

	public Search search(String queryString) throws ParseException, IOException, InvalidTokenOffsetsException {	
		
		
		List<Result> finalRes = new ArrayList<Result>();
		
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
		Directory indexDir = FSDirectory.open(new File(pathOfIndex));
		

		Date inizio = new Date();
		IndexReader reader = DirectoryReader.open(indexDir);
		IndexSearcher indexSearcher = new IndexSearcher(reader);
		QueryParser queryParser = new QueryParser(Version.LUCENE_47, "title", analyzer);
		//QueryParser queryParser = new QueryParser(Version.LUCENE_47, "autore", analyzer);
		Query query = queryParser.parse(queryString);

		TopDocs hits = indexSearcher.search(query, reader.maxDoc());
		
		
		

		for (int i = 0; i < hits.totalHits; i++) {
			int id = hits.scoreDocs[i].doc;
			Document doc = indexSearcher.doc(id);
			
			
			Result r = new Result();
			r.setTitle(doc.get("title").replace('"', ' '));		
			
				
			r.setAuthor(infoWithField.get(doc.get("title")).get(1));
			r.setStatus(infoWithField.get(doc.get("title")).get(4));
			r.setDate( infoWithField.get(doc.get("title")).get(2)+" " +infoWithField.get(doc.get("title")).get(3));
			r.setPage(infoWithField.get(doc.get("title")).get(5));
			r.setStreamDoc(infoWithField.get(doc.get("title")).get(7));
			r.setDOCID(infoWithField.get(doc.get("title")).get(0));
			r.setDoi(infoWithField.get(doc.get("title")).get(8));
			r.setKeyword(infoWithField.get(doc.get("title")).get(13));
			//r.setObsolete(infoWithField.get(doc.get("title")).get(9));
			r.setScore(hits.scoreDocs[i].score);
			
			String Abstract=infoWithField.get(doc.get("title")).get(6);
			if(Abstract==null || Abstract==""){
				Abstract=" Not Present.";				
			}
			r.setabstract(Abstract);
			int h=(int) Math.random();
			r.setNumber(i+h);
			
			if(infoWithField.get(doc.get("title")).get(9)!=" "){
				List<Result> obsoleteByRes= new ArrayList<Result>();
				List<String> obsoleteByList= new ArrayList<String>();
				
				
				obsoleteByList= Splitter.split(infoWithField.get(doc.get("title")).get(9));
				
				for(int k=0;k<obsoleteByList.size();k++){
				String obs=obsoleteByList.get(k).replaceAll(" ", "");
				
				
					
					
					
				obsoleteByRes.add(searchObsoleteUpdated(obs));
						
						
					
			}
				
				r.setObsoleteBy(obsoleteByRes);
				
			}
			
			if(infoWithField.get(doc.get("title")).get(10)!=" "){
				List<Result> updatedByRes= new ArrayList<Result>();
				List<String> updatedByList= new ArrayList<String>();
				
				
				updatedByList= Splitter.split(infoWithField.get(doc.get("title")).get(10));
				
				for(int k=0;k<updatedByList.size();k++){
				String obs=updatedByList.get(k).replaceAll(" ", "");
				
				
					
					
					
				updatedByRes.add(searchObsoleteUpdated(obs));
						
						
					
			}
				
				r.setUpdatedBy(updatedByRes);
				
			}
		
			if(infoWithField.get(doc.get("title")).get(11)!=" "){
				List<Result> obsoleteRes= new ArrayList<Result>();
				List<String> obsoleteList= new ArrayList<String>();
				
				
				obsoleteList= Splitter.split(infoWithField.get(doc.get("title")).get(11));
				
				for(int k=0;k<obsoleteList.size();k++){
				String obs=obsoleteList.get(k).replaceAll(" ", "");
				
				
					
					
					
						obsoleteRes.add(searchObsoleteUpdated(obs));
						
						
					
			}
				
				r.setObsolete(obsoleteRes);
				
			}
			
			if(infoWithField.get(doc.get("title")).get(12)!=" "){
				List<Result> updatedRes= new ArrayList<Result>();
				List<String> updatedList= new ArrayList<String>();
				
				
				updatedList= Splitter.split(infoWithField.get(doc.get("title")).get(12));
				
				for(int k=0;k<updatedList.size();k++){
					
				String obs=updatedList.get(k).replaceAll(" ", "");
				
				
					
					
					
						updatedRes.add(searchObsoleteUpdated(obs));
						
						
					
			}
				
				r.setUpdated(updatedRes);
				
			}
			
			
					
			
			
			String path= "../JSON/"+infoWithField.get(doc.get("title")).get(0)+".json";
		    r.setJson(path);
		
			finalRes.add(r);
			
			//Ricerca titoli dello stesso autore
			
			}
		
		
		
		
		
	
		

		reader.close();
		String suggestedQuery = null;

		
				
		Date fine = new Date();

		long time = fine.getTime() - inizio.getTime();
		
		Search searchRes = new Search(finalRes, time, queryString, suggestedQuery);
		return searchRes;

	}
	
public Search searchRFC(String queryString) throws ParseException, IOException, InvalidTokenOffsetsException {	
		
		
		List<Result> finalRes = new ArrayList<Result>();
		
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
		Directory indexDir = FSDirectory.open(new File(pathOfIndexRFC));
		

		Date inizio = new Date();
		IndexReader reader = DirectoryReader.open(indexDir);
		IndexSearcher indexSearcher = new IndexSearcher(reader);
		QueryParser queryParser = new QueryParser(Version.LUCENE_47, "rfc", analyzer);
		Query query = queryParser.parse(queryString);

		TopDocs hits = indexSearcher.search(query, reader.maxDoc());
		
		float maxscore=0;
		for (int i = 0; i < hits.totalHits; i++) {
			if (hits.scoreDocs[i].score>maxscore){
				maxscore=hits.scoreDocs[i].score;
			}
			
		}
		
        
		for (int i = 0; i < hits.totalHits; i++) {
			if(hits.scoreDocs[i].score>=maxscore){
			int id = hits.scoreDocs[i].doc;
			Document doc = indexSearcher.doc(id);
			
			Result r = new Result();
			String title=infoWithField6.get(doc.get("rfc")).get(0);
			r.setTitle(title.replace('"', ' '));		
			
				
			r.setAuthor(infoWithField.get(title).get(1));
			r.setStatus(infoWithField.get(title).get(4));
			r.setDate( infoWithField.get(title).get(2)+" " +infoWithField.get(title).get(3));
			r.setPage(infoWithField.get(title).get(5));
			r.setStreamDoc(infoWithField.get(title).get(7));
			r.setDOCID(doc.get("rfc"));
			r.setDoi(infoWithField.get(title).get(8));
			r.setKeyword(infoWithField.get(title).get(13));
			//r.setObsolete(infoWithField.get(doc.get("title")).get(9));
			r.setScore(hits.scoreDocs[i].score);
			
			String Abstract=infoWithField.get(title).get(6);
			if(Abstract==null || Abstract==""){
				Abstract=" Not Present.";				
			}
			r.setabstract(Abstract);
			int h=(int) Math.random();
			r.setNumber(i+h);
			
			if(infoWithField.get(title).get(9)!=" "){
				List<Result> obsoleteByRes= new ArrayList<Result>();
				List<String> obsoleteByList= new ArrayList<String>();
				
				
				obsoleteByList= Splitter.split(infoWithField.get(title).get(9));
				
				for(int k=0;k<obsoleteByList.size();k++){
				String obs=obsoleteByList.get(k).replaceAll(" ", "");
				
				
					
					
					
				obsoleteByRes.add(searchObsoleteUpdated(obs));
						
						
					
			}
				
				r.setObsoleteBy(obsoleteByRes);
				
			}
			
			if(infoWithField.get(title).get(10)!=" "){
				List<Result> updatedByRes= new ArrayList<Result>();
				List<String> updatedByList= new ArrayList<String>();
				
				
				updatedByList= Splitter.split(infoWithField.get(title).get(10));
				
				for(int k=0;k<updatedByList.size();k++){
				String obs=updatedByList.get(k).replaceAll(" ", "");
				
				
					
					
					
				updatedByRes.add(searchObsoleteUpdated(obs));
						
						
					
			}
				
				r.setUpdatedBy(updatedByRes);
				
			}
		
			if(infoWithField.get(title).get(11)!=" "){
				List<Result> obsoleteRes= new ArrayList<Result>();
				List<String> obsoleteList= new ArrayList<String>();
				
				
				obsoleteList= Splitter.split(infoWithField.get(title).get(11));
				
				for(int k=0;k<obsoleteList.size();k++){
				String obs=obsoleteList.get(k).replaceAll(" ", "");
				
				
					
					
					
						obsoleteRes.add(searchObsoleteUpdated(obs));
						
						
					
			}
				
				r.setObsolete(obsoleteRes);
				
			}
			
			if(infoWithField.get(title).get(12)!=" "){
				List<Result> updatedRes= new ArrayList<Result>();
				List<String> updatedList= new ArrayList<String>();
				
				
				updatedList= Splitter.split(infoWithField.get(title).get(12));
				
				for(int k=0;k<updatedList.size();k++){
					
				String obs=updatedList.get(k).replaceAll(" ", "");
				
				
					
					
					
						updatedRes.add(searchObsoleteUpdated(obs));
						
						
					
			}
				
				r.setUpdated(updatedRes);
				
			}
			
			
					
			
			//JSONcreator.creator(doc.get("rfc"));
			String path= "../JSON/"+doc.get("rfc")+".json";
		    r.setJson(path);
			
			
		
			finalRes.add(r);
			
			//Ricerca titoli dello stesso autore
			
			}}
		
		
		
		
		
	
		

		reader.close();
		String suggestedQuery = null;

		
				
		Date fine = new Date();

		long time = fine.getTime() - inizio.getTime();
		
		Search searchRes = new Search(finalRes, time, queryString, suggestedQuery);
		return searchRes;

	}
	
	

	
	
	public  Map<String, String> getTitle(){
		return rfcList;
	}

	
	public Result searchObsoleteUpdated(String queryString) throws ParseException, IOException, InvalidTokenOffsetsException {
		//Occorre un secondo indice indicizzato per RFC. molti obsolete hanno lo stesso titolo, quindi la ricerca per titolo non va bene
		
		
		
		Result finalRes=new Result();
		Result r = new Result();
		    if(infoWithField3.containsKey(queryString)){
			r.setDOCID(queryString);		
			
				
			r.setAuthor(infoWithField3.get(queryString).get(1));
			r.setStatus(infoWithField3.get(queryString).get(4));
			r.setDate( infoWithField3.get(queryString).get(2)+" " +infoWithField3.get(queryString).get(3));
			r.setPage(infoWithField3.get(queryString).get(5));
			r.setStreamDoc(infoWithField3.get(queryString).get(7));
			r.setTitle(infoWithField3.get(queryString).get(0));
			r.setDoi(infoWithField3.get(queryString).get(8));
			r.setKeyword(infoWithField3.get(queryString).get(9));
			//r.setObsolete(infoWithField.get(doc.get("title")).get(9));
			r.setScore(1);
			
			
			int h=(int) Math.random();
			r.setNumber(1+h);
			
			
			
			
			
		
			finalRes=r;}
			
			//Ricerca titoli dello stesso autore
			
		
		
		
		
		
		
	
		

		
return finalRes;
}}




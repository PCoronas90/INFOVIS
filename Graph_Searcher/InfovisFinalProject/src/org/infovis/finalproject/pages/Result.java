package org.infovis.finalproject.pages;

import java.util.List;

public class Result {

	private String title;
	
	private float score;
	private String status;
	private String author;
	private String date;
	private String page;
	private String docID;
	private int number;
	private String abstr;
	private String streamDoc;
	private String doi;
	private String keyword;
	private String Json;
	
	private List<Result> obsolete_by;
	private List<Result> updated_by;
	private List<Result> obsolete;
	private List<Result> updated;

	public Result(){ 	
	}

	public Result(String title,String author,float score,String status,String date,String page,String docID,String abstr,
			String streamDoc,String doi,List<Result> obsoleteBy,List<Result> updatedBy,List<Result> obsolete,List<Result> updated,String keyword,String JSON){
		this.title = title;
		this.author=author;
		this.keyword= keyword;
		this.score= score;
		this.status= status;
		this.date= date;
		this.page= page;
		this.docID= docID;
		this.abstr=abstr;
		this.streamDoc=streamDoc;
		this.doi=doi;
		this.obsolete_by=obsoleteBy;
		this.updated_by=updatedBy;
		this.obsolete=obsolete;
		this.updated=updated;
		this.Json=JSON;
	}

	public String getJson() {
		return Json;
	}

	public void setJson(String json) {
		Json = json;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status=status;
	}

	


	

	public float getScore() {
		return this.score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public void setAuthor(String autore) {
		this.author=autore;}
	

	public String getAuthor() {
		return this.author;}
	
	public void setDate(String date) {
		this.date=date;}
	

	public String getDate() {
		return this.date;}

	public void setNumber(int j) {
		this.number=j;
		
	}
	
	public String getDOCID() {
		return this.docID;}
	
	public void setDOCID(String f) {
		this.docID=f;}

	public String getPage() {
		return this.page;}
	
	public void setPage(String p) {
		this.page=p;}
	
	public int getNumber() {
		return this.number;
		
	}
	
	public void setabstract(String p) {
		this.abstr=p;}
	
	public String getAbstract() {
		return this.abstr;
		
	}
	
	public void setStreamDoc(String p) {
		this.streamDoc=p;}
	
	public String getStreamDoc() {
		return this.streamDoc;
		
	}
	
	public void setDoi(String p) {
		this.doi=p;}
	
	public String getDoi() {
		return this.doi;
		
	}
	
	public List<Result> getObsoleteBy() {
		return this.obsolete_by;
	}
	
	public void setObsoleteBy(List<Result> o) {
		this.obsolete_by=o;
	}
	
	public int getObsoleteByNumber() {
		if(this.obsolete_by==null){
			return 0;
		}
		else{
		return this.obsolete_by.size();}
	}
	
	public Result getObsoleteByI(int i){
		return this.obsolete_by.get(i);
	}
	
	public List<Result> getUpdatedBy() {
		return this.updated_by;
	}
	
	public int getUpdatedByNumber() {
		if(this.updated_by==null){
			return 0;
		}
		else{
		return this.updated_by.size();}
	}
	
	public void setUpdatedBy(List<Result> u) {
		this.updated_by=u;
	}
	
	public List<Result> getUpdated() {
		return this.updated;
	}
	
	public int getUpdatedNumber() {
		if(this.updated==null){
			return 0;
		}
		else{
		return this.updated.size();}
	}
	
	public void setUpdated(List<Result> u) {
		this.updated=u;
	}
	

	public void setObsolete(List<Result> o) {
		this.obsolete=o;
	}
	
	public int getObsoleteNumber() {
		if(this.obsolete==null){
			return 0;
		}
		else{
		return this.obsolete.size();}
	} 
	public List<Result> getObsolete() {
		return this.obsolete;
	}


		
		
 
}



package org.infovis.finalproject.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.*;

import org.infovis.finalproject.pages.Page;
import org.infovis.finalproject.pages.Result;
import org.infovis.finalproject.pages.Search;
import org.infovis.finalproject.searcher.Searcher;

@ManagedBean(name = "searcherBean", eager = true)
@SessionScoped
public class searcherBean {

	private List<Page> linkedPPrev;
	private Map<String,String> Map;
	private List<Page> linkedPNext;
	private String originalQuery;
	private String suggestedQuery;
	private List<Page> pages;
	private List<Integer> datesFrom;
	private List<Integer> datesTo;
	private Page currentPage;
	private Searcher searcher;
	public boolean informational;
	public boolean unknown;
	public boolean historic;
	public boolean experimental;
	public boolean bestcurrent;
	public boolean proposedstandard;
	public boolean draftstandard;
	public boolean internetstandard;
	public boolean dateSelected;
	private int selectDateFrom;
	private int selectDateTo;
	private int TotalPages;
	private int numberOfResults;
	private long searchTime;
	private Set<Search> searchresultList;

	public searcherBean() throws IOException {

		this.pages = null;
		this.searcher = null;
		this.selectDateFrom=0;
		this.selectDateTo=0;
		this.linkedPPrev = new ArrayList<Page>();
		this.linkedPNext = new ArrayList<Page>();
		this.datesFrom=new ArrayList<Integer>();
		this.datesTo=new ArrayList<Integer>();
		this.searcher = new Searcher();
		this.informational=false;
		this.unknown=false;
		this.historic=false;
		this.experimental=false;
		this.bestcurrent=false;
		this.proposedstandard=false;
		this.draftstandard=false;
		this.internetstandard=false;
		this.dateSelected=false;
		this.TotalPages=0;	
		this.Map=searcher.getTitle();
		this.searchresultList = new HashSet<Search>();
		for(Integer i=1968;i<2016;i++){
			this.datesFrom.add(i);
			this.datesTo.add(i);			
		}
	}



	public void setMap(Map<String, String> map) {
		Map = map;
	}



	public String search() throws Exception {
		int dataArrivo=this.getSelectDateTo();
		int dataInizio= this.getSelectDateFrom();
		this.pages = new ArrayList<Page>();
		this.TotalPages=0;
		int numbResInserted = 0;
		int pageNumber = 1;
		this.numberOfResults = 0;
		
		List<Result> resultsAll = new ArrayList<Result>();
			Search searchResult2 = searcher.searchRFC(originalQuery);
			List<Result> RFCres= searchResult2.getTopHits();
			if(RFCres!=null){
				resultsAll.addAll(RFCres);
			
		}
		Search searchResult = searcher.search(originalQuery);
		this.searchresultList.add(searchResult);
		resultsAll.addAll(searchResult.getTopHits());
		this.setSuggestedQuery(searchResult.getSuggestedQuery());
		
		

		if (resultsAll.isEmpty() ) {
			return "empty";
		}

		int resultNumber = searchResult.getTotalHitCount()+searchResult2.getTotalHitCount();		
		this.searchTime = searchResult.getSearchDuration()+searchResult2.getSearchDuration();
		this.originalQuery = searchResult.getOriginalQuery();
		this.setSuggestedQuery(searchResult.getSuggestedQuery());

		while (numbResInserted < resultNumber) {

			Page page = new Page(pageNumber);
			List<Result> resultsPage = new ArrayList<Result>();

			if(this.informational==true){
				for (int i = numbResInserted; i < numbResInserted + 1; i++) {
					if (i < resultNumber){
						if(resultsAll.get(i).getStatus().contains("INFORM")){
							if(dataInizio!=0 & dataArrivo==0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data>=dataInizio){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}
							if(dataArrivo!=0 & dataInizio==0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data<=dataArrivo){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}

							if(dataInizio!=0 & dataArrivo!=0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data>=dataInizio & data<=dataArrivo){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}

							if(dataInizio==0 & dataArrivo==0){
								this.numberOfResults ++;
								resultsPage.add(resultsAll.get(i));	}}}}}

			if(this.unknown==true){

				for (int i = numbResInserted; i < numbResInserted + 1; i++) {
					if (i < resultNumber){

						if(resultsAll.get(i).getStatus().contains("UNKNOWN")){
							if(dataInizio!=0 & dataArrivo==0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data>=dataInizio){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}
							if(dataArrivo!=0 & dataInizio==0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data<=dataArrivo){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}

							if(dataInizio!=0 & dataArrivo!=0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data>=dataInizio & data<=dataArrivo){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}

							if(dataInizio==0 & dataArrivo==0){
								this.numberOfResults ++;
								resultsPage.add(resultsAll.get(i));	}}}
				}}

			if(this.historic==true){

				for (int i = numbResInserted; i < numbResInserted + 1; i++) {
					if (i < resultNumber){
						if(resultsAll.get(i).getStatus().contains("HIS")){
							if(dataInizio!=0 & dataArrivo==0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data>=dataInizio){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}
							if(dataArrivo!=0 & dataInizio==0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data<=dataArrivo){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}

							if(dataInizio!=0 & dataArrivo!=0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data>=dataInizio & data<=dataArrivo){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}

							if(dataInizio==0 & dataArrivo==0){
								this.numberOfResults ++;
								resultsPage.add(resultsAll.get(i));	}}}
				}}

			if(this.experimental==true){

				for (int i = numbResInserted; i < numbResInserted + 1; i++) {
					if (i < resultNumber){
						if(resultsAll.get(i).getStatus().contains("EXPERIME")){
							if(dataInizio!=0 & dataArrivo==0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data>=dataInizio){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}
							if(dataArrivo!=0 & dataInizio==0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data<=dataArrivo){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}

							if(dataInizio!=0 & dataArrivo!=0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data>=dataInizio & data<=dataArrivo){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}

							if(dataInizio==0 & dataArrivo==0){
								this.numberOfResults ++;
								resultsPage.add(resultsAll.get(i));	}}}
				}}

			if(this.bestcurrent==true){
				for (int i = numbResInserted; i < numbResInserted + 1; i++) {
					if (i < resultNumber){

						if(resultsAll.get(i).getStatus().contains("BEST")){
							if(dataInizio!=0 & dataArrivo==0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data>=dataInizio){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}
							if(dataArrivo!=0 & dataInizio==0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data<=dataArrivo){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}

							if(dataInizio!=0 & dataArrivo!=0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data>=dataInizio & data<=dataArrivo){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}

							if(dataInizio==0 & dataArrivo==0){
								this.numberOfResults ++;
								resultsPage.add(resultsAll.get(i));	}}}
				}}

			if(this.proposedstandard==true){
				for (int i = numbResInserted; i < numbResInserted + 1; i++) {
					if (i < resultNumber){

						if(resultsAll.get(i).getStatus().contains("PROPOSED")){
							if(dataInizio!=0 & dataArrivo==0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data>=dataInizio){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}
							if(dataArrivo!=0 & dataInizio==0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data<=dataArrivo){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}

							if(dataInizio!=0 & dataArrivo!=0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data>=dataInizio & data<=dataArrivo){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}

							if(dataInizio==0 & dataArrivo==0){
								this.numberOfResults ++;
								resultsPage.add(resultsAll.get(i));	}}}
				}}

			if(this.draftstandard==true){
				for (int i = numbResInserted; i < numbResInserted + 1; i++) {
					if (i < resultNumber){
						if(resultsAll.get(i).getStatus().contains("DRAFT")){
							if(dataInizio!=0 & dataArrivo==0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data>=dataInizio){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}
							if(dataArrivo!=0 & dataInizio==0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data<=dataArrivo){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}

							if(dataInizio!=0 & dataArrivo!=0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data>=dataInizio & data<=dataArrivo){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}

							if(dataInizio==0 & dataArrivo==0){
								this.numberOfResults ++;
								resultsPage.add(resultsAll.get(i));	}}
					}}}

			if(this.internetstandard==true){
				for (int i = numbResInserted; i < numbResInserted + 1; i++) {
					if (i < resultNumber){

						if(resultsAll.get(i).getStatus().contains("INTERNET")){
							if(dataInizio!=0 & dataArrivo==0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data>=dataInizio){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}
							if(dataArrivo!=0 & dataInizio==0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data<=dataArrivo){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}

							if(dataInizio!=0 & dataArrivo!=0){
								int data= parseDate(resultsAll.get(i).getDate());
								if(data>=dataInizio & data<=dataArrivo){
									this.numberOfResults++;
									resultsPage.add(resultsAll.get(i));
								}
							}

							if(dataInizio==0 & dataArrivo==0){
								this.numberOfResults ++;
								resultsPage.add(resultsAll.get(i));	}}}
				}}

			else if(this.unknown==false & this.informational==false & this.historic==false & this.experimental==false & this.bestcurrent==false
					& this.proposedstandard==false & this.draftstandard==false & this.internetstandard==false){
				for (int i = numbResInserted; i < numbResInserted + 1; i++) {
					if (i < resultNumber){
						if(dataInizio!=0 & dataArrivo==0){
							
							int data= parseDate(resultsAll.get(i).getDate());
							if(data>=dataInizio){
								this.numberOfResults++;
								resultsPage.add(resultsAll.get(i));
							}
						}
						if(dataArrivo!=0 & dataInizio==0){
							int data= parseDate(resultsAll.get(i).getDate());
							if(data<=dataArrivo){
								this.numberOfResults++;
								resultsPage.add(resultsAll.get(i));
							}
						}

						if(dataInizio!=0 & dataArrivo!=0){
							int data= parseDate(resultsAll.get(i).getDate());
							if(data>=dataInizio & data<=dataArrivo){
								this.numberOfResults++;
								resultsPage.add(resultsAll.get(i));
							}
						}

						if(dataInizio==0 & dataArrivo==0){
							this.numberOfResults ++;
							resultsPage.add(resultsAll.get(i));	};	
					}}}




			if(resultsPage.size()!=0){
				page.setResults(resultsPage);
				this.pages.add(page);
				pageNumber++;
				if(this.TotalPages<10){
					this.TotalPages++;
				}
				numbResInserted = numbResInserted + 1;}
			else{
				numbResInserted = numbResInserted + 1;	
			}




		}

		if(pages.isEmpty()){
			return "empty";
		}
		else{
			setCurrentPage(pages.get(0));
			this.updateLinkedPages(1);
			return "search";}
	}


public  java.util.Map<String, String> getRfc(){
		return this.Map;
	}

public  Set<String> getRfcMap(){
	return this.Map.keySet();
}

public  Set<String> getRfcVal(){
	Set<String>titles = new HashSet<String>();
	for(String rfc:this.Map.keySet()){
		titles.add(this.Map.get(rfc));
	}
	return titles;
}


	public String goToPageAuthor(String selectedNumber) {
		int number = Integer.parseInt(selectedNumber);
		for (Page page : pages) {
			if (page.getNumber() == number) {
				setCurrentPage(page);
				this.updateLinkedPages(number);
			}
		}
		return "searchAuthor";
	}

	public String goToPageKeyword(String selectedNumber) {
		int number = Integer.parseInt(selectedNumber);
		for (Page page : pages) {
			if (page.getNumber() == number) {
				setCurrentPage(page);
				this.updateLinkedPages(number);
			}
		}
		return "searchKeywords";
	}




	public String goToPage(String selectedNumber) {
		int number = Integer.parseInt(selectedNumber);
		for (Page page : pages) {
			if (page.getNumber() == number) {
				setCurrentPage(page);
				this.updateLinkedPages(number);
			}
		}
		return "search";
	}

	public int getMiddle(int number) {
		if (number % 2 == 0)
			return number / 2;
		else
			return number / 2 + 1;
	}

	public void updateLinkedPages(int currentPageNumber) {

		int totalLinkedPages = this.TotalPages;	

		int middlePage = this.getMiddle(totalLinkedPages);
		// Num pag di mezzo rispetto a quelle da visualizzare
		int otherPages = totalLinkedPages - middlePage;

		List<Page> tmpPrev = null;
		List<Page> tmpNext = null;

		if (currentPageNumber <= middlePage) {
			tmpNext = new ArrayList<Page>();
			// La prima pagina non ha precedenti
			if (currentPageNumber != 1) {
				tmpPrev = new ArrayList<Page>();
				for (int i = 1; i < currentPageNumber; i++) {
					tmpPrev.add(pages.get(i - 1));
				}
			}

			int min = Math.min(totalLinkedPages, pages.size());
			for (int i = currentPageNumber + 1; i <= min; i++) {
				tmpNext.add(pages.get(i - 1));
			}
		}

		// Pagine intermedie. Link a prec e succ
		else if (currentPageNumber > middlePage && currentPageNumber <= this.pages.size() - otherPages) {
			tmpPrev = new ArrayList<Page>();
			tmpNext = new ArrayList<Page>();
			for (int i = currentPageNumber - otherPages; i < currentPageNumber; i++) {
				tmpPrev.add(pages.get(i - 1));
			}
			for (int i = currentPageNumber + 1; i <= currentPageNumber + otherPages; i++) {
				tmpNext.add(pages.get(i - 1));
			}
		}

		else if (currentPageNumber > this.pages.size() - otherPages) {
			tmpPrev = new ArrayList<Page>();
			for (int i = this.pages.size() - totalLinkedPages + 1; i < currentPageNumber; i++) {
				tmpPrev.add(pages.get(i - 1));
			}
			if (currentPageNumber != this.pages.size()) { // l'ultima pagina non
				// ha pagine
				// successive
				tmpNext = new ArrayList<Page>();
				for (int i = currentPageNumber + 1; i <= this.pages.size(); i++) {
					tmpNext.add(pages.get(i - 1));
				}
			}
		}
		setLinkedPagesPrev(tmpPrev);
		setLinkedPagesNext(tmpNext);
	}

	
	public int parseDate(String date){
		int dataReturn=0;
		int i=0;
		boolean findSplit=false;
		while(i<date.length() & findSplit==false){
			if(date.charAt(i)=='1'|date.charAt(i)=='2'){
				dataReturn=Integer.parseInt(date.substring(i, date.length()));
				findSplit=true;
				break;
			}
			i++;
		}
		return dataReturn;
	} 
	public boolean isDateSelected() {
		return dateSelected;
	}

	public void setDateSelected(boolean dateSelected) {
		this.dateSelected = dateSelected;
	}

	public int getSelectDateTo() {
		return selectDateTo;
	}

	public void setSelectDateTo(int selectDateTo) {
		this.selectDateTo = selectDateTo;
	}

	public List<Integer> getDatesFrom() {
		return datesFrom;
	}

	public void setDatesFrom(List<Integer> datesFrom) {
		this.datesFrom = datesFrom;
	}

	public List<Integer> getDatesTo() {
		return datesTo;
	}

	public void setDatesTo(List<Integer> datesTo) {
		this.datesTo = datesTo;
	}

	public int getSelectDateFrom() {
		return selectDateFrom;
	}

	public void setSelectDateFrom(int selectDateFrom) {
		this.selectDateFrom = selectDateFrom;
	}



	public boolean isDraftstandard() {
		return draftstandard;
	}

	public void setDraftstandard(boolean draftstandard) {
		this.draftstandard = draftstandard;
	}

	public boolean isInternetstandard() {
		return internetstandard;
	}

	public void setInternetstandard(boolean internetstandard) {
		this.internetstandard = internetstandard;
	}

	public boolean isProposedstandard() {
		return proposedstandard;
	}

	public void setProposedstandard(boolean proposedstandard) {
		this.proposedstandard = proposedstandard;
	}

	public boolean isBestcurrent() {
		return bestcurrent;
	}

	public void setBestcurrent(boolean bestcurrent) {
		this.bestcurrent = bestcurrent;
	}

	public boolean isExperimental() {
		return experimental;
	}

	public void setExperimental(boolean experimental) {
		this.experimental = experimental;
	}

	public boolean isHistoric() {
		return historic;
	}




	public void setHistoric(boolean historic) {
		this.historic = historic;
	}



	public boolean isUnknown() {
		return unknown;
	}

	public void setUnknown(boolean unknown) {
		this.unknown = unknown;
	}

	public boolean isInformational() {
		return informational;
	}

	public void setInformational(boolean informational) {
		this.informational = informational;
	}


	public List<Page> getLinkedPagesPrev() {
		return linkedPPrev;
	}

	public void setLinkedPagesPrev(List<Page> linkedPagesPrev) {
		this.linkedPPrev = linkedPagesPrev;
	}

	public List<Page> getLinkedPagesNext() {
		return linkedPNext;
	}

	public void setLinkedPagesNext(List<Page> linkedPagesNext) {
		this.linkedPNext = linkedPagesNext;
	}

	public Page getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(Page page) {
		this.currentPage = page;
	}

	public String getOriginalQuery() {
		return originalQuery;
	}

	public void setOriginalQuery(String query) {
		this.originalQuery = query;
	}

	public String getSuggestedQuery() {
		return suggestedQuery;
	}

	public void setSuggestedQuery(String suggestedQuery) {
		this.suggestedQuery = suggestedQuery;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public int getNumberOfResults() {
		return numberOfResults;
	}

	public void setNumberOfResults(int numberOfResults) {
		this.numberOfResults = numberOfResults;
	}

	public long getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(long searchTime) {
		this.searchTime = searchTime;
	}

}
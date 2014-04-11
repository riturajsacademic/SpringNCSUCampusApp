package com.example1.mywolfpackguide;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

public class CategoryList extends Application{
	
	public List<String> arrList = new ArrayList<String>();  

	 public CategoryList(){
		 
	 }
	 
	 
	 public CategoryList(List<String> catids){
		 this.arrList = catids;
	 }
	 
	    public List<String> getArrList() {
	        return arrList;
	    }

	    public void setArrList(List<String> arrList) {
	        this.arrList = arrList;
	    }

}

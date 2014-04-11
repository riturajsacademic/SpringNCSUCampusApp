package com.example1.mywolfpackguide;

public class CategoryClass {
	
	int catId;
    String catName;
    int userId;
    
    public CategoryClass(int catId,String catName,int userId)
    {
    	this.catId = catId;
    	this.catName = catName;
    	this.userId = userId;
    }
    
    public CategoryClass(int catId,String catName)
    {
    	this.catId = catId;
    	this.catName = catName;
    }
    
    //setter methods
    public void setCatId(int catId){
    	this.catId = catId;
    }
    
    public void setUserId(int userId){
    	this.userId = userId;
    }
    
    public void setcatName(String catName){
    	this.catName = catName;
    }
    
    //getter methods
    public int getCatId(int catId){
    	return this.catId = catId;
    }
    
    public int getUserId(int userId){
    	return this.userId = userId;
    }
    
    public String getcatName(String catName){
    	return this.catName = catName;
    }


}

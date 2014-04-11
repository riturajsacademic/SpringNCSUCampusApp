package com.example1.mywolfpackguide;

import java.io.Serializable;

public class UsersClass implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	int userId;
    String userName;
    String password;
    String emailId;
 
    // constructors
    public UsersClass() {
 
    }
 
    public UsersClass(int userId,String UserName,String password,String emailId) {
        this.userId = userId;
        this.userName = UserName;
        this.password = password;
        this.emailId = emailId;
        		
    }
    
    public UsersClass(String UserName,String password,String emailId) {
        this.userName = UserName;
        this.password = password;
        this.emailId = emailId;
        		
    }
 
    // setter
    public void setUserId(int id) {
        this.userId = id;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public void setPassword(String password){
    	this.password = password;
    }
    
    public void setEmailId(String emailId){
    	this.emailId = emailId;
    }
 
    // getter
    public int getUserId() {
        return this.userId;
    }
 
    public String getUserName() {
        return this.userName;
    }
    
    public String getPassword(){
    	return this.password;
    }
    
    public String getEmailId(){
    	return this.emailId;
    }
}

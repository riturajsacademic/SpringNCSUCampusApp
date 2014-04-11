package com.example1.mywolfpackguide;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.util.Log;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.ListView;

import android.widget.Toast;

public class CategoryActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		CustomCursorAdapter dataAdapter = null;
		
		final DBAdapter db= new DBAdapter(getBaseContext());
		db.open();
		
		
		Intent getUserDetails = getIntent();
		final UsersClass userinfo = (UsersClass)getUserDetails.getSerializableExtra("objSignup");
		
		
		
		//Toast.makeText(getBaseContext(), "Success" + userinfo.userName + userinfo.password + userinfo.emailId , Toast.LENGTH_LONG).show();
		
		
		Log.i("inserting", "category");

		Cursor cur = db.selectCat(); 
		
		Log.i("inserting", "category1");
		dataAdapter = new CustomCursorAdapter(this,
			    cur);
	 
		// this.getListView().setAdapter(myCursorAdapter);
		ListView listView = (ListView) findViewById(R.id.listCategory);
				  // Assign adapter to ListView
		listView.setAdapter(dataAdapter);
	
	
	 Button buttonsignup = (Button) findViewById(R.id.btncomplete);
		buttonsignup.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//List<String> catids = new ArrayList<String>();
				//CategoryList objLists = new CategoryList();
				//catids = objLists.getArrList();

				
			
				
				CategoryList appState = ((CategoryList)getApplicationContext());
				List<String> catids=appState.arrList;
				int length = catids.size();
				
				if(length == 0)
				{
					Toast.makeText(getBaseContext(),"Please select category!", Toast.LENGTH_LONG).show();
					return;
				}
				
				//Toast.makeText(CategoryActivity.this,"count this " + length ,Toast.LENGTH_LONG).show();
				
				long[] catid = new long[length];
				int i=0;
				for(String s:catids)
				{
					catid[i] = Long.parseLong(s);
					i +=1;	
				}
				
				/*
				for(long s:catid)
				{
					Toast.makeText(getBaseContext(), "cat selected are" + s, Toast.LENGTH_LONG).show();

				}
				*/
				
				db.addContact(userinfo, catid);
				
				//Toast.makeText(getBaseContext(), "Signed up", Toast.LENGTH_LONG).show();

				Intent newintent = new Intent(CategoryActivity.this,UserHomePage.class);
				startActivity(newintent);
				/*
				
				Cursor cur = db.selectRecords();  

				if (cur.moveToFirst())
				{
					do
					{
	//Toast.makeText(getBaseContext(), "values are" + cur.getString(cur.getColumnIndex("UserID")) 
			//+ cur.getString(cur.getColumnIndex("UserName")), Toast.LENGTH_LONG).show();
						Toast.makeText(getBaseContext(), "User table values are " + cur.getString(0) + " " +
								cur.getString(1)  + " " + cur.getString(2) + " " + cur.getString(3)   + " " + cur.getString(4), Toast.LENGTH_LONG).show();
					}while(cur.moveToNext());
				}
				
				Cursor cur1 = db.selectUserCat();  

				if (cur1.moveToFirst())
				{
					do
					{
	//Toast.makeText(getBaseContext(), "values are" + cur.getString(cur.getColumnIndex("UserID")) 
			//+ cur.getString(cur.getColumnIndex("UserName")), Toast.LENGTH_LONG).show();
						Toast.makeText(getBaseContext(), "Category and User are " + cur1.getString(0) + " " +
								 cur1.getString(1) + " " +  cur1.getString(2), Toast.LENGTH_LONG).show();
					}while(cur1.moveToNext());
				}
				*/
			
				db.close();
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category, menu);
		return true;
	}	


}

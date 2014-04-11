package com.example1.mywolfpackguide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomCursorAdapter extends CursorAdapter{
	
	
	private LayoutInflater mInflater;
	private Context mContext;
	Cursor cursor;
	final List<String> catids = new ArrayList<String>();
	

	@SuppressWarnings("deprecation")
	public CustomCursorAdapter(Context context, Cursor c) {
	super(context, c);
	// TODO Auto-generated constructor stub
	mInflater = LayoutInflater.from(context);
	mContext = context;
	cursor=c;
	

		insert();
	
	}
	


	private void insert() {
		final CategoryList appState = (CategoryList)mContext.getApplicationContext();

		// TODO Auto-generated method stub
		appState.setArrList(catids);
	}



	@Override
	public void bindView(View view, Context context, final Cursor cursor) {
	// TODO Auto-generated method stub
	ViewHolder holder=(ViewHolder)view.getTag();

	holder.setTextView((TextView)view.findViewById(R.id.cat_names));
	holder.setCheckBox((CheckBox)view.findViewById(R.id.checkBox_cat));
	
	CheckBox cb=holder.getCheckBox();

	holder.getTextView().setText(cursor.getString(1));
   
	cb.setTag(Integer.valueOf(cursor.getPosition()));
	
	//cb.setTag(new Integer(cursor.getPosition()));
	
	

	CompoundButton.OnCheckedChangeListener checkedChange= new CompoundButton.OnCheckedChangeListener() {



		
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	// TODO Auto-generated method stub
		
	
	
	
	Integer currentPosition = (Integer)buttonView.getTag();

	String currentPositionString=Double.toString(currentPosition);
	
	if(cursor.moveToPosition(currentPosition))
	{

	String rowID=cursor.getString(cursor.getColumnIndex("_id"));
	if(isChecked){
		functionadd(rowID);
		
		//Toast.makeText(mContext, "checked" + rowID + " " +
	//currentPositionString, Toast.LENGTH_LONG).show();
		

	}
	else if(!isChecked){
		functionremove(rowID);
		//Toast.makeText(mContext, "unchecked" + rowID + " " +
				//currentPositionString, Toast.LENGTH_LONG).show();
	}
	
	
	}
	}
	};
	
	cb.setOnCheckedChangeListener(checkedChange);
	
}

	public void functionadd(String rowID)
	{
		catids.add(rowID);
		
	}

	public void functionremove(String rowID)
	{
		catids.remove(rowID);
		
	}
	

	 
		
	 

	
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
	// TODO Auto-generated method stub
		
	ViewHolder holder;
	View convertView = mInflater.inflate(R.layout.category, parent,false);
	holder = new ViewHolder(convertView);
	convertView.setTag(holder);
	
	return convertView;
	
	}
	
	
	

	 
	 
		 
}
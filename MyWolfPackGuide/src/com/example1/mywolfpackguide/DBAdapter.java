package com.example1.mywolfpackguide;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

private static final String TAG = "Performing...";

//public static final String DATABASE_PATH = "/data/data/com.example1.mywolfpackguide/databases/";

private static final String DATABASE_NAME = "MyWolfPackGuide_database";
private static final int DATABASE_VERSION = 7;

//Table declarations
private static final String DATABASE_TABLE_USERS="user_information ";
private static final String DATABASE_TABLE_ROLES_MASTER="role_information ";
private static final String DATABASE_TABLE_CATEGORY_MASTER="category_information";
private static final String DATABASE_TABLE_CATEGORY_USERS="user_category_information";



//column name declarations
//DATABASE_TABLE_USERS
public static final String USER_ID = "UserID";
public static final String ROLE_ID = "RoleID";
public static final String USER_NAME = "Username";
public static final String USER_PASSWORD = "Password ";
public static final String USER_EMAIL = "Email ";

//DATABASE_TABLE_ROLES_MASTER
public static final String MASTER_ROLE_ID = "RoleID";
public static final String MASTER_ROLE_NAME = "Rolename";


//DATABASE_TABLE_CATEGORY_MASTER
public static final String CAT_ID = "_id";
public static final String CAT_NAME = "CatName";

//DATABASE_TABLE_CATEGORY_USERS
public static final String PK_USERCATEGORY_ID = "PK_ID";
public static final String USERID_USERCATEGORY_ID = "UserID";
public static final String CATID_USERCATEGORY_ID = "Cat_ID";



//QUERY CONSTRUCTIONS
private static final String TABLE_USERS_CREATE = "CREATE TABLE IF NOT EXISTS" + " " +
DATABASE_TABLE_USERS + "("
+ USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + ROLE_ID + " INTEGER," + USER_NAME + " TEXT,"
+ USER_PASSWORD + " TEXT," + USER_EMAIL + " TEXT" + ")";


private static final String TABLE_ROLES_MASTER_CREATE = " CREATE TABLE IF NOT EXISTS" + " " +
DATABASE_TABLE_ROLES_MASTER + "("
+ MASTER_ROLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + MASTER_ROLE_NAME + " TEXT" + ")";


private static final String TABLE_CATEGORY_MASTER_CREATE = " CREATE TABLE IF NOT EXISTS" + " " +
DATABASE_TABLE_CATEGORY_MASTER + "("
+ CAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + CAT_NAME + " TEXT" + ")";


private static final String TABLE_CATEGORY_USERS_CREATE = " CREATE TABLE IF NOT EXISTS" + " " +
		DATABASE_TABLE_CATEGORY_USERS + "("
+ PK_USERCATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USERID_USERCATEGORY_ID + " INTEGER," +
CATID_USERCATEGORY_ID + " INTEGER" + ")";


private final Context context;

private DatabaseHelper DBHelper;
private SQLiteDatabase db;

public DBAdapter(Context ctx)
{
this.context = ctx;
DBHelper = new DatabaseHelper(context,DATABASE_NAME,DATABASE_VERSION);
}

private static class DatabaseHelper extends SQLiteOpenHelper
{
DatabaseHelper(Context context,String DATABASE_NAME,int DATABASE_VERSION) 
{
super(context, DATABASE_NAME, null, DATABASE_VERSION);
}

@Override
public void onCreate(SQLiteDatabase db) 
{
db.execSQL(TABLE_USERS_CREATE);
db.execSQL(TABLE_ROLES_MASTER_CREATE);
db.execSQL(TABLE_CATEGORY_MASTER_CREATE);
db.execSQL(TABLE_CATEGORY_USERS_CREATE);
Log.i("onCreate", TABLE_CATEGORY_USERS_CREATE);

ContentValues cat_info = new ContentValues();
cat_info.put(CAT_NAME,"Sports"); // Category Name
db.insert(DATABASE_TABLE_CATEGORY_MASTER, null, cat_info);

cat_info.put(CAT_NAME,"Seminars"); // Category Name
db.insert(DATABASE_TABLE_CATEGORY_MASTER, null, cat_info);

cat_info.put(CAT_NAME,"Workshops"); // Category Name
db.insert(DATABASE_TABLE_CATEGORY_MASTER, null, cat_info);

cat_info.put(CAT_NAME,"Science"); // Category Name
db.insert(DATABASE_TABLE_CATEGORY_MASTER, null, cat_info);

ContentValues role_info = new ContentValues();
role_info.put(MASTER_ROLE_NAME, "Admin");
db.insert(DATABASE_TABLE_ROLES_MASTER, null, role_info);

role_info.put(MASTER_ROLE_NAME, "Users");
db.insert(DATABASE_TABLE_ROLES_MASTER, null, role_info);

ContentValues admin_info = new ContentValues();
admin_info.put(ROLE_ID, "1");
admin_info.put(USER_NAME, "admin");
admin_info.put(USER_PASSWORD, "admin");
admin_info.put(USER_EMAIL, "admin@gmail.com");
db.insert(DATABASE_TABLE_USERS, null, admin_info);

}

@Override
public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
	// TODO Auto-generated method stub
	// Drop older table if existed
    db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_USERS);
    db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ROLES_MASTER);
    db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_CATEGORY_MASTER);
    db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_CATEGORY_USERS);
    // Create tables again
    onCreate(db);
	
}
}



// ---opens the database---
public DBAdapter open() throws SQLException {
db = DBHelper.getWritableDatabase();
return this;
}

// ---closes the database---
public void close() {
DBHelper.close();
}


//Methods
public long addContact(UsersClass userinfo,long[] Cat_ids) {
  
	//db.execSQL(DATABASE_CREATE_USERS);
    //Log.i(TAG, "creating" + UserName + Password);
    ContentValues values = new ContentValues();
    values.put(ROLE_ID, "2");
    values.put(USER_NAME,userinfo.getUserName()); 
    values.put(USER_PASSWORD, userinfo.getPassword()); 
    values.put(USER_EMAIL, userinfo.getEmailId());
    
 
    // Inserting Row
    Log.i(TAG, "inserting");
   	long UserId =  db.insert(DATABASE_TABLE_USERS, null, values);
   	Log.i("addContact","user inserted" + UserId);
   	
   	for(long Cat_id:Cat_ids)
   	{
   		insertUserCategory(UserId,Cat_id);
   	    Log.i(TAG, "inserting category" + UserId + Cat_id);
   	}
    return UserId;
   
}

public void insertUserCategory(long UserId,long Cat_id){
	ContentValues values = new ContentValues();
    values.put(USERID_USERCATEGORY_ID, UserId);
    values.put(CATID_USERCATEGORY_ID,Cat_id); 
    long catid = db.insert(DATABASE_TABLE_CATEGORY_USERS, null, values);
    Log.i("insertUserCategory","cat and user inserted " + catid);
   
}

public Cursor validateLoginInfo(String UserName,String Password){
	String[] selectionArgs = new String[]{UserName, Password};
    Cursor c = null;
    c = db.rawQuery("select * from " + DATABASE_TABLE_USERS + " where username=? and password=?", selectionArgs);
    if (c != null) {  
	     c.moveToFirst();  
	   } 

    return c;
}

public Cursor selectRecords() {
	
	/*
		String q = "SELECT * FROM " + DATABASE_TABLE_USERS;
		Cursor mCursor = db.rawQuery(q, null);
		 if (mCursor != null) {  
		     mCursor.moveToFirst();  
		   } 
		   */
	   
		String[] cols = new String[] {USER_ID,ROLE_ID,USER_NAME, USER_PASSWORD,USER_EMAIL};  
	   Cursor mCursor = db.query(true, DATABASE_TABLE_USERS,cols,null  
	            , null, null, null, null, null);  
	   if (mCursor != null) {  
	     mCursor.moveToFirst();  
	   } 
	   
	   return mCursor; // iterate to get each value.
	}

public Cursor selectCat() {
	
	/*
		String q = "SELECT * FROM " + DATABASE_TABLE_USERS;
		Cursor mCursor = db.rawQuery(q, null);
		 if (mCursor != null) {  
		     mCursor.moveToFirst();  
		   } 
		   */
	   Log.i("selectcat", "error here");
	   String[] cols = new String[] {CAT_ID,CAT_NAME};  
	   Cursor mCursor = db.query(true, DATABASE_TABLE_CATEGORY_MASTER,cols,null  
	            , null, null, null, null, null);  
	   if (mCursor != null) {  
	     mCursor.moveToFirst();  
	   } 
	   
	   return mCursor; // iterate to get each value.
	}


public Cursor selectUserCat() {
	
	/*
		String q = "SELECT * FROM " + DATABASE_TABLE_USERS;
		Cursor mCursor = db.rawQuery(q, null);
		 if (mCursor != null) {  
		     mCursor.moveToFirst();  
		   } 
		   */
	   Log.i("selectUserCat", "error here");
	   String[] cols = new String[] {PK_USERCATEGORY_ID,USERID_USERCATEGORY_ID,CATID_USERCATEGORY_ID};  
	   Cursor mCursor = db.query(true, DATABASE_TABLE_CATEGORY_USERS,cols,null  
	            , null, null, null, null, null);  
	   if (mCursor != null) {  
	     mCursor.moveToFirst();  
	   } 
	   
	   return mCursor; // iterate to get each value.
	}

}

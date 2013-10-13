package com.example.dars;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "mydb.db";
	private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "login";
	private static final String TABLE_CREATE ="CREATE TABLE " + TABLE_NAME + "(" +"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
	                "username TEXT NOT NULL, password TEXT NOT NULL, email TEXT NOT NULL);";
	private static final String DB_ADMIN = "INSERT INTO "+TABLE_NAME+"values(1, admin, password, admin@gmail.com);";
	
	
	public DbAdapter(Context context) {					//create DbAdapter object to connect to database
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		System.out.println("In constructor");
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		try{
			db.execSQL(TABLE_CREATE); 				//Create Database				
			db.execSQL(DB_ADMIN);					////create admin account
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int oldVersion, int newVersion) {
		

	}

}

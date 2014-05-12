package com.example.reportproject;

import java.util.UUID;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class reportDatabaseHelper  {
	
	public static final String TAG = reportDatabaseHelper.class.getName();
	
	privateDatabaseContent databaseContent;
	Context context;
	
	public reportDatabaseHelper(Context context){
		databaseContent = new privateDatabaseContent(context);
		SQLiteDatabase db = databaseContent.getWritableDatabase();
		//databaseContent.onCreate(db);
		this.context = context;	
	}
	
	public void deleteTable(){
		SQLiteDatabase db = databaseContent.getWritableDatabase();
		//db.execSQL(privateDatabaseContent.DROP_TABLE);
		//db.execSQL(privateDatabaseContent.CREATE_QUERY_REPORT);
		db.delete(privateDatabaseContent.TABLE_NAME, null, null);
	}
	
	public long insertData(String startDate, String endDate,String content){
		
		Toast.makeText(context, "Inserting", Toast.LENGTH_SHORT).show();
		
		//Log.d(TAG, ""+id);
		Log.d(TAG, ""+startDate);
		Log.d(TAG, ""+endDate);
		Log.d(TAG, ""+content);
		
		ContentValues contentvalue = new ContentValues();
		//contentvalue.put(privateDatabaseContent.ID_REPORT, id);
		contentvalue.put(privateDatabaseContent.DATE_START_REPORT, startDate);
		contentvalue.put(privateDatabaseContent.DATE_STOP_REPORT, endDate);
		contentvalue.put(privateDatabaseContent.CONTENT_REPORT, content);
		
		SQLiteDatabase db = databaseContent.getWritableDatabase();
		if(db== null) 
			Toast.makeText(context, "database not found", Toast.LENGTH_SHORT).show();
		long RowID  = db.insert(privateDatabaseContent.TABLE_NAME, null, contentvalue);
		
		// if RowID is -1 , operation fails
		if(RowID == -1)
			Toast.makeText(context, "Fail to insert", Toast.LENGTH_SHORT).show();
		return RowID;
	}
	
	public Cursor getData(){
		SQLiteDatabase db = databaseContent.getWritableDatabase();
		
		String table = privateDatabaseContent.TABLE_NAME;
		String[] columns = {privateDatabaseContent.ID_REPORT,privateDatabaseContent.DATE_START_REPORT,
							privateDatabaseContent.DATE_STOP_REPORT,privateDatabaseContent.CONTENT_REPORT};
		
		
		Cursor cursor = db.query(table, columns, null, null, null, null, null);
		
		return cursor;
	}
	
	
	private class privateDatabaseContent extends SQLiteOpenHelper{
		private Context context;
		
		private  static final String DATABASE_NAME = "reportDatabase";
		private static final String TABLE_NAME = "ReportTable";
		private static final int DATABASE_VERSION = 1;
		
		private static final String CONTENT_REPORT= "Contents";
		private static final String DATE_START_REPORT = "StartedDate";
		private static final String DATE_STOP_REPORT = "StoppedDate";
		private static final String ID_REPORT = "ID";
		
		private static final String CREATE_QUERY_REPORT = "CREATE TABLE "+TABLE_NAME+" ("+ 
															ID_REPORT+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+ 
															DATE_START_REPORT + " VARCHAR(255) ,"+
															DATE_STOP_REPORT + " VARCHAR(255) ,"+
															CONTENT_REPORT + " VARCHAR(255));";
		
		private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;
		
		
		public privateDatabaseContent(Context context) {
			super(context, DATABASE_NAME, null	, DATABASE_VERSION);
			this.context = context;
			// context , database name, create custom cursor , version of database
			Toast.makeText(context, "Constructor is called", Toast.LENGTH_SHORT).show();
			//onCreate(getWritableDatabase());
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Toast.makeText(context, "onCreate called", Toast.LENGTH_SHORT).show();
			try { 
				Log.d(TAG, "Created database");
				db.execSQL(CREATE_QUERY_REPORT);
			} catch (SQLException e) {
				Toast.makeText(context, "Error while create table", Toast.LENGTH_SHORT).show();
			}
			
					
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Toast.makeText(context, "Error while upgrade table", Toast.LENGTH_SHORT).show();
			//db.execSQL(DROP_TABLE);
			//onCreate(db);
		}
		
		
	}// privateDatabaseContent
	

}

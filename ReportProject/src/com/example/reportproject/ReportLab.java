package com.example.reportproject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;
public class ReportLab {

		private static final String TAG = ReportLab.class.getName();
		
		//SQLITE DATBASE
		reportDatabaseHelper reportDatabase;

		private static ReportLab mReportLab;
		private Context mAppContext;
		private ArrayList<ReportModel> mReportModelList;
		
		private int id_counter = 0;
		
		public static final int ID_COLUMN = 0;
		public static final int STARTDATE_COLUMN = 1;
		public static final int ENDDATE_COLUMN = 2;
		public static final int CONTENT_COLUMN = 3;
		
		
		// constructor
		private ReportLab(Context appContext) {
			mAppContext = appContext;
			mReportModelList = new ArrayList<ReportModel>();
			reportDatabase = new reportDatabaseHelper(appContext);
			
			//reportDatabase.deleteTable();
			
			loadReportList();
			
		}
		
		private void loadReportList() {
			
			Cursor cursor= reportDatabase.getData();
			
			id_counter = cursor.getCount();
			
			while(cursor.moveToNext()){
				//loading each element from database
				int uuID = cursor.getInt(ID_COLUMN);
				String sStartDate = cursor.getString(STARTDATE_COLUMN);
				String sEndDate = cursor.getString(ENDDATE_COLUMN);
				
				
				
				// get Content
				String Content = cursor.getString(CONTENT_COLUMN);
				
				// Handle format for Date
				SimpleDateFormat dateFormat = new SimpleDateFormat(
		                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
				
				Date startDate;
				Date endDate;
				try {
					startDate = dateFormat.parse(sStartDate);
					endDate = dateFormat.parse(sEndDate);
				
				
				// add to list
				mReportModelList.add(new ReportModel(uuID,startDate,endDate,Content));
				
				} catch (ParseException e) {
					Toast.makeText(mAppContext, "error when cast Date", Toast.LENGTH_SHORT).show();
				}
				
			} // travel to database result
			
		}

		// get CrimeLab
		public static ReportLab get(Context c) {
			if(mReportLab == null){
				mReportLab = new ReportLab(c.getApplicationContext());
			}
			
			return mReportLab;
		}
		
		public ArrayList<ReportModel> getReportList(){
			return mReportModelList;
		}
		
		public ReportModel getReport(int id){
			for(ReportModel c : mReportModelList){
				if(c.getID() == id)
					return c;
			}
			return null;
		}
		
		public void addReport(){
			
			ReportModel c = new ReportModel(id_counter++);
			
			mReportModelList.add(c);
			// also add it to Database
			Log.d(TAG, " ID :" + c.getID());
			reportDatabase.insertData(getDateTime(c.getStartDate()), getDateTime(c.getStopDate()), c.getcontent());
		}
		
		private String getDateTime(Date date) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat(
	                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
	        return dateFormat.format(date);
		}

		public void deleteAll() {
			mReportModelList.clear();
			reportDatabase.deleteTable();
			
		}

}

package com.example.reportproject;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class ReportModel {
	private String mcontent = "hello";
	private Date mStartDate;
	private Date mStopDate;
	private int mID;
	
	
	public ReportModel(int id) {
		setID(id);
		setStartDate(new Date());
		// for now
		setStopDate(new Date());
	}

	public ReportModel( int ID,Date start,Date end,String content ) {
		mID = ID;
		mStartDate = start;
		mStopDate = end;
		mcontent =  content;
	}
	
	public Date getStartDate() {
		return mStartDate;
		
	}


	public void setStartDate(Date mStartDate) {
		this.mStartDate = mStartDate;
	}


	public Date getStopDate() {
		return mStopDate;
	}


	public void setStopDate(Date mStopDate) {
		this.mStopDate = mStopDate;
	}


	public int getID() {
		return mID;
	}


	public void setID(int mID) {
		this.mID = mID;
	}


	public String getcontent() {
		return mcontent;
	}


	public void setcontent(String mcontent) {
		this.mcontent = mcontent;
	}

}

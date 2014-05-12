package com.example.reportproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	Context context;
	ArrayList<ReportModel> data ;
	private static LayoutInflater inflater = null;
	
	public ListViewAdapter(Context context, ArrayList<ReportModel> in_data ) {
		this.context = context;
		data =  new ArrayList<ReportModel>(in_data);
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public ReportModel getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View v = convertView;
		if(v == null){
			v = inflater.inflate(R.layout.report_lement, null);
		}
		
		TextView startText = (TextView) v.findViewById(R.id.report_element_StartDate);
		TextView stopText = (TextView) v.findViewById(R.id.report_element_EndDate);
		TextView ContentText = (TextView) v.findViewById(R.id.report_element_content);
		
		startText.setText(getDateTime(data.get(position).getStartDate()));
		stopText.setText(getDateTime(data.get(position).getStopDate()));
		ContentText.setText(data.get(position).getcontent().toString());
		
		
		return v;
	}

	public void addItemListView(ArrayList<ReportModel> array) {
		data = array;
		notifyDataSetChanged();
		
	}
	
	private String getDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(date);
	}

}

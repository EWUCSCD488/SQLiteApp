package com.example.reportproject;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ReportProjectFragment extends Fragment {
	

	
	private Button mstartButton;
	private Button mstopButton;
	private ListView mlistView;
	private ListViewAdapter reportAdapter;
	
	// Data for ListView
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		
		ReportLab.get(getActivity());
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		View v = inflater.inflate(R.layout.fragment_report_project, container,false);
		
		mstartButton = (Button)v.findViewById(R.id.Start_Button);
		mstopButton = (Button) v.findViewById(R.id.Stop_Button);
		
		
		
		
		mlistView = (ListView) v.findViewById(R.id.ListView_Fragement_Id);
		
		reportAdapter = new ListViewAdapter(getActivity(),ReportLab.get(getActivity()).getReportList());
		mlistView.setAdapter(  reportAdapter );
		
		mstartButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ReportLab.get(getActivity()).addReport();
				reportAdapter.addItemListView(ReportLab.get(getActivity()).getReportList());
				//Toast.makeText(getActivity(), "item added", Toast.LENGTH_SHORT).show();
			}
		});
		
		mstopButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ReportLab.get(getActivity()).deleteAll();
				reportAdapter.addItemListView(ReportLab.get(getActivity()).getReportList());
				
			}
		});
		
		return v;
	}
	
}

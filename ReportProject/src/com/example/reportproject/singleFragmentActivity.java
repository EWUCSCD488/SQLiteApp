package com.example.reportproject;

import android.os.Bundle;
import android.support.v4.app.*;

public abstract class singleFragmentActivity extends FragmentActivity {


	protected abstract Fragment createFragment();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_project);
		
		FragmentManager mFm = getSupportFragmentManager();
		Fragment fragment = (Fragment) mFm.findFragmentById(R.id.container);
		if(fragment == null){
			fragment = createFragment();
			FragmentTransaction temp = mFm.beginTransaction();
			temp.add(R.id.container, fragment);
					temp.commit();
		}
	}

}


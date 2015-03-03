package com.grappes.sandwedgesadmin;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class SingleOrderActivity extends Activity {

	// Declare Variables
	TextView txtorder;
	int pos;
	//String[] orderid;
	String prodetail;
	String ptotal;
	String padrres;
	String ptime;
	String oid;
	Button s; 
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_single_order);
		s=(Button) findViewById(R.id.complete);
		s.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
							
				 ParseQuery<ParseObject> query = ParseQuery.getQuery("Orders");
			
				 
				// Retrieve the object by id
				query.getInBackground(oid, new GetCallback<ParseObject>() {
				  public void done(ParseObject gameScore, ParseException e) {
				    if (e == null) {
				    	if(gameScore.get("complete").toString().equalsIgnoreCase("false"))
				    	{
				    		gameScore.put("complete", true);
						      gameScore.saveInBackground();
						      Toast.makeText(SingleOrderActivity.this, "Your task will be completed soon.", Toast.LENGTH_SHORT).show();
						  	Intent in=new Intent(SingleOrderActivity.this,OrderActivity.class);
						  	startActivity(in);
						  	finish();
				    	}
				      
				    }
				    else
				    	Toast.makeText(SingleOrderActivity.this,e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
				  }
				});
				
			}
		});
			// Retrieve data from MainActivity on item click event
		Intent i = getIntent();
 
		// Get the name
		oid=i.getStringExtra("oid");
		//pos = i.getIntExtra("pos",0);
		prodetail=i.getStringExtra("productdetails");
		ptotal=i.getStringExtra("gtotal");
		padrres=i.getStringExtra("address");
		ptime=i.getStringExtra("time");
		// Locate the TextView in singleitemview.xml
		txtorder = (TextView) findViewById(R.id.details);
		if(i.getIntExtra("differ", 0)==2)
			 s.setVisibility(View.GONE);
		
		// Load the text into the TextView
		txtorder.setText("Order no. #"+oid+"\n\n"+ prodetail+"\n\n"+ptotal+"\n\n"+padrres+"\n\n"+"Order placed on : "+ptime);
 
	}


	@Override
	protected void onPause() {
		finish();
		super.onPause();
	}


	@Override
	protected void onStop() {
		finish();
		// TODO Auto-generated method stub
		super.onStop();
	}


	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
		super.onBackPressed();
	}
}
package com.grappes.sandwedgesadmin;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.PushService;
 
public class OrderActivity extends Activity {
	// Declare Variables
	ListView listview;
	
	List<ParseObject> ob;
	ProgressDialog mProgressDialog;
	ListViewAdapter adapter;
	
	EditText editsearch;
	private List<Order> numberlist = null;
	
	// Set the limit of objects to show
	private int limit = 100;
	
	String orid=new String();
	String productdetails=new String();
	String gtotal=new String();
	String itotal=new String();
	
	String disc=new String();
	String address=new String();
	String oplacedat=new String();
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview_main.xml
		setContentView(R.layout.activity_order);
		// Execute RemoteDataTask AsyncTask
		PushService.setDefaultPushCallback(this, MainActivity.class);
		// Locate the EditText in listview_main.xml
			editsearch = (EditText) findViewById(R.id.editTextinc);
		 
				// Capture Text in EditText
				editsearch.addTextChangedListener(new TextWatcher() {
		 
					@Override
					public void afterTextChanged(Editable arg0) {
						// TODO Auto-generated method stub
						String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
						adapter.filter(text);
					}
		 
					@Override
					public void beforeTextChanged(CharSequence arg0, int arg1,
							int arg2, int arg3) {
						// TODO Auto-generated method stub
					}
		 
					@Override
					public void onTextChanged(CharSequence arg0, int arg1, int arg2,
							int arg3) {
						// TODO Auto-generated method stub
					}
				});
				
		new RemoteDataTask().execute();
	}
 
	private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(OrderActivity.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Please wait");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			mProgressDialog.show();
		}
 
		@Override
		protected Void doInBackground(Void... params) {
			// Create the array
			numberlist = new ArrayList<Order>();
			
			try {
				// Locate the class table named "TestLimit" in Parse.com
				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
						"Orders");
				query.orderByDescending("createdAt");
				// Set the limit of objects to show
				query.whereEqualTo("complete", false);
				query.setLimit(limit);
				ob = query.find();
				for (ParseObject order : ob) {
					Order map = new Order();
					orid+=order.getObjectId().toString()+";".trim();
					productdetails+=order.get("product_det")+";".trim();
					gtotal+="Total : "+ order.get("itotal")+
							
							"\nDiscount : "+order.get("disc")+
							"\nGrand Total : "+order.get("gtotal")+"-".trim();
					/*gtotal+=(String) order.get("gtotal")+"#";
					itotal+=(String) order.get("itotal")+"#";
					vat+=(String) order.get("vat")+"#";
					disc+=(String) order.get("disc")+"#";*/
					address+=order.get("Address")+";".trim();
					oplacedat+=order.getCreatedAt()+"-".trim();
					if(!order.getBoolean("complete"))
					{
					map.setObjectId((String) order.getObjectId());
					map.setGrandtotal((String) order.get("gtotal"));
					map.setAddress((String) order.get("Address"));
					map.setUser((String) order.get("uid"));
					map.setDate((String) order.getCreatedAt().toString());
					map.setdisc((String) order.get("disc"));
					map.setiTotal((String) order.get("itotal"));
					map.setProdDet((String) order.get("product_det"));
					numberlist.add(map);
					}
					
				}
			} catch (ParseException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return null;
		}
 
		@Override
		protected void onPostExecute(Void result) {
			// Locate the ListView in listview.xml
			listview = (ListView) findViewById(R.id.listview);
			
			
			// Pass the results into ListViewAdapter.java
			adapter = new ListViewAdapter(OrderActivity.this, numberlist);
			
			// Binds the Adapter to the ListView
			listview.setAdapter(adapter);
			
			// Close the progressdialog
			mProgressDialog.dismiss();
			
			
		/*	listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent i = new Intent(OrderActivity.this,
							SingleOrderActivity.class);
					int len=gtotal.split("-", -1).length;
					// Pass data "name" followed by the position
					
					i.putExtra("oid", orid);
					i.putExtra("pos", position);
					i.putExtra("productdetails", productdetails);
					i.putExtra("gtotal", gtotal);
					i.putExtra("address", address);
					i.putExtra("time", oplacedat);
					i.putExtra("differ",1 );
				
					// Open SingleItemView.java Activity
					startActivity(i);
					
				}
			});*/
			// Create an OnScrollListener
			listview.setOnScrollListener(new OnScrollListener() {
 
				@Override
				public void onScrollStateChanged(AbsListView view,
						int scrollState) { // TODO Auto-generated method stub
					int threshold = 1;
					int count = listview.getCount();
 
					if (scrollState == SCROLL_STATE_IDLE) {
						if (listview.getLastVisiblePosition() >= count
								- threshold) {
							// Execute LoadMoreDataTask AsyncTask
							//new LoadMoreDataTask().execute();
						}
					}
				}
 
				
				
				@Override
				public void onScroll(AbsListView view, int firstVisibleItem,
						int visibleItemCount, int totalItemCount) {
					// TODO Auto-generated method stub
 
				}
 
			});
			
			
			
		}
		
		
 
		private class LoadMoreDataTask extends AsyncTask<Void, Void, Void> {
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				// Create a progressdialog
				mProgressDialog = new ProgressDialog(OrderActivity.this);
				// Set progressdialog title
				mProgressDialog.setTitle("Checking for more");
				// Set progressdialog message
				mProgressDialog.setMessage("Loading more...");
				mProgressDialog.setIndeterminate(false);
				// Show progressdialog
				mProgressDialog.show();
			}
 
			@Override
			protected Void doInBackground(Void... params) {
				// Create the array
				numberlist = new ArrayList<Order>();
				
				try {
					// Locate the class table named "TestLimit" in Parse.com
					ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
							"Orders");
					query.orderByDescending("createdAt");
					// Add 20 results to the default limit
					query.whereEqualTo("complete", false);
					query.setLimit(limit += 20);
					ob = query.find();
					for (ParseObject order : ob) {
						
						productdetails+=order.get("product_det")+";".trim();
						gtotal+="Total : "+ order.get("itotal")+
								"\nVAT : "+order.get("vat")+
								"\nDiscount : "+order.get("disc")+
								"\nGrand Total : "+order.get("gtotal")+"-".trim();
						/*gtotal+=(String) order.get("gtotal")+"#";
						itotal+=(String) order.get("itotal")+"#";
						vat+=(String) order.get("vat")+"#";
						disc+=(String) order.get("disc")+"#";*/
						address+=order.get("Address")+";".trim();
						oplacedat+=order.getCreatedAt()+"-".trim();
						Order map = new Order();
						if(!order.getBoolean("complete"))
						{
						map.setObjectId((String) order.getObjectId());
						map.setGrandtotal((String) order.get("gtotal"));
						map.setAddress((String) order.get("Address"));
						map.setUser((String) order.get("uid"));
						map.setDate((String) order.getCreatedAt().toString());
						numberlist.add(map);
						}
						
					}
				} catch (ParseException e) {
					Log.e("Error", e.getMessage());
					e.printStackTrace();
				}
				return null;
			}
 
			@Override
			protected void onPostExecute(Void result) {
				// Locate listview last item
				int position = listview.getLastVisiblePosition();
				
				// Pass the results into ListViewAdapter.java
				adapter = new ListViewAdapter(OrderActivity.this, numberlist);
				
				// Binds the Adapter to the ListView
				listview.setAdapter(adapter);
				
				// Show the latest retrived results on the top
				listview.setSelectionFromTop(position, 0);
				
				// Close the progressdialog
				mProgressDialog.dismiss();
			}
		}
 
	}
 
}
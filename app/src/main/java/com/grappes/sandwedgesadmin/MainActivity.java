package com.grappes.sandwedgesadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	Button neworder;
	Button completeorder;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		neworder=(Button) findViewById(R.id.newordersbutton);
		completeorder=(Button) findViewById(R.id.completedorderbutton);
		neworder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(MainActivity.this,OrderActivity.class);
				startActivity(i);
			}
		});
		
		completeorder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(MainActivity.this,OrderCompleteActivity.class);
				startActivity(i);
				
			}
		});
		
	}
	
	public void addProduct(View v)
	{
		Intent i=new Intent(MainActivity.this,AddProduct.class);
		startActivity(i);
	}
}

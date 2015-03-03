package com.grappes.sandwedgesadmin;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseObject;

public class AddProduct extends ActionBarActivity {

	
	EditText name;
	EditText desc;
	EditText pric;
	EditText location;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		  Parse.initialize(this, "rLDuBNajsyienDD24YTTCKUuJN2S0sdPdtSy9wIe", "P1bKXK9sdtwWT3Nd3fTVnVu2zrC0fcGLLBalyXS2");
		setContentView(R.layout.activity_add_product);
		name=(EditText) findViewById(R.id.name);
		desc=(EditText) findViewById(R.id.description);
		pric=(EditText) findViewById(R.id.price);
		location=(EditText) findViewById(R.id.location);
		
		
	}
	
	 public void saveToCloud(View v)
	 {
	  String description=name.getText().toString();
	  String name=desc.getText().toString();
	  int price=Integer.parseInt(pric.getText().toString());
	  String loc=location.getText().toString();
	  
	  ParseObject sc =new ParseObject("Menu");
	  sc.put("Product_name", name);
	  sc.put("Product_desc", description);
	  sc.put("Product_price", price);
	  sc.put("Product_location", loc);
	  sc.saveInBackground();

	   
	 }
	
	
}

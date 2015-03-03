package com.grappes.sandwedgesadmin;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	Context mContext;
	LayoutInflater inflater;
	private List<Order> orderlist = null;
	private ArrayList<Order> arraylist;
	protected int count;

	public ListViewAdapter(Context context, List<Order> numberlist) {
		mContext = context;
		this.orderlist = numberlist;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<Order>();
		this.arraylist.addAll(numberlist);
	}

	public class ViewHolder {
		TextView oid;
		TextView uname;
		TextView gtotal;
		TextView Address;
		TextView date;
		TextView itot;
		TextView disc;
		TextView prodet;
	}

	@Override
	public int getCount() {
		return orderlist.size();
	}

	@Override
	public Order getItem(int position) {
		return orderlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.listview_order, null);
			// Locate the TextView in listview_item.xml
			holder.Address = (TextView) view.findViewById(R.id.address);
			holder.oid = (TextView) view.findViewById(R.id.ordernum);
			holder.uname = (TextView) view.findViewById(R.id.username);
			holder.gtotal = (TextView) view.findViewById(R.id.gtotal);
			holder.date = (TextView) view.findViewById(R.id.date);
			holder.itot = (TextView) view.findViewById(R.id.itot);
			holder.disc = (TextView) view.findViewById(R.id.disc);
			holder.prodet = (TextView) view.findViewById(R.id.proddet);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextView
		
		holder.Address.setText(orderlist.get(position).getAddress());
		holder.oid.setText(orderlist.get(position).getObjectId());
		holder.uname.setText(orderlist.get(position).getUser());
		holder.gtotal.setText(orderlist.get(position).getGrandtotal());
		holder.date.setText(orderlist.get(position).getDate());
		holder.itot.setText(orderlist.get(position).getiTotal());
		holder.disc.setText(orderlist.get(position).getdisc()); 
		holder.prodet.setText(orderlist.get(position).getProdDet());
		// Listen for ListView Item Click
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Send single item click data to SingleItemView Class
				Intent i = new Intent(mContext, SingleOrderActivity.class);
				// Pass all data number
				i.putExtra("oid", (holder.oid.getText().toString()));
				
				i.putExtra("productdetails", holder.prodet.getText().toString());
				i.putExtra("gtotal", "Total : "+holder.itot.getText().toString()+"\nDiscount : "+holder.disc.getText().toString()+"\nGrand Total : "+holder.gtotal.getText().toString());
				i.putExtra("address", holder.Address.getText().toString());
				i.putExtra("time", holder.date.getText().toString());
				i.putExtra("differ",1 );
				// Start SingleItemView Class
				
				mContext.startActivity(i);
			}
		});

		return view;
	}
	// Filter Class
		public void filter(String charText) {
			charText = charText.toLowerCase(Locale.getDefault());
			orderlist.clear();
			if (charText.length() == 0) {
				orderlist.addAll(arraylist);
			} else {
				for (Order wp : arraylist) {
					if (wp.getObjectId().toLowerCase(Locale.getDefault())
							.contains(charText) || wp.getUser().toLowerCase(Locale.getDefault())
							.contains(charText)) {
						orderlist.add(wp);
					}
				}
			}
			notifyDataSetChanged();
		}
}

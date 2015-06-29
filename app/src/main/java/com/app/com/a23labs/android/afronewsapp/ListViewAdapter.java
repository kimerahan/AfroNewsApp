package com.app.com.a23labs.android.afronewsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<Devotional> data;
	ImageLoader imageLoader;
    Devotional resultp;
	

	public ListViewAdapter(Context context,
			ArrayList<Devotional> arraylist) {
		this.context = context;
		data = arraylist;
		imageLoader = new ImageLoader(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		TextView title;
		TextView body;
		ImageView image;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.listview_item, parent, false);
		// Get the position
		resultp =  data.get(position);

		// Locate the TextViews in listview_item.xml
		title = (TextView) itemView.findViewById(R.id.rank);
		body = (TextView) itemView.findViewById(R.id.country);
		//population = (TextView) itemView.findViewById(R.id.population);

		// Locate the ImageView in listview_item.xml
		image = (ImageView) itemView.findViewById(R.id.flag);

		// Capture position and set results to the TextViews
		title.setText(resultp.getTitle());
		body.setText(resultp.getBody());
		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class
		imageLoader.DisplayImage(resultp.getArtlink(), image);
		// Capture ListView item click

		return itemView;
	}
}

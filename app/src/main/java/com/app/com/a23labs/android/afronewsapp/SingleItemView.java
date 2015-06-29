package com.app.com.a23labs.android.afronewsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleItemView extends Activity {
	// Declare Variables
	String title;
	String body;
	String artlink;
	String position;
	ImageLoader imageLoader;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.singleitemview);
        imageLoader = new ImageLoader(this);

        //phaneroostream.cloudapp.net/phaneroo/dashboard/api/devotionals.json

		Intent i = getIntent();
		// Get the result of rank
		title = i.getStringExtra("title");
		// Get the result of country
		body = i.getStringExtra("body");
		// Get the result of population
	//	population = i.getStringExtra("population");
		// Get the result of flag
		artlink = i.getStringExtra("artlink");

		// Locate the TextViews in singleitemview.xml
		TextView newstitle = (TextView) findViewById(R.id.rank);
		TextView newsbody = (TextView) findViewById(R.id.country);

		// Locate the ImageView in singleitemview.xml
		ImageView imgflag = (ImageView) findViewById(R.id.flag);

		// Set results to the TextViews
		newstitle.setText(title);
		newsbody.setText(body);

		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class
		imageLoader.DisplayImage(artlink, imgflag);
	}
}
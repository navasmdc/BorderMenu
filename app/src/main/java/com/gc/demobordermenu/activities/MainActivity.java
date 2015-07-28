package com.gc.demobordermenu.activities;


import com.gc.bordermenu.activities.BorderMenuActivity;
import com.gc.bordermenu.views.IconItem;
import com.gc.bordermenu.views.TextItem;
import com.gc.demobordermenu.R;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends BorderMenuActivity {
	
	final int COPY = 0;
	final int PASTE = 1;
	final int CUT = 2;
	final int ORDER = 3;
	final int SHOWALL = 4;
	final int  CREATE = 5;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		IconItem iconItem = new IconItem(this, CREATE, R.drawable.ic_create_white_24dp);
		addLeftItem(iconItem);
		iconItem = new IconItem(this, COPY, R.drawable.ic_content_copy_white_24dp);
		addLeftItem(iconItem);
		iconItem = new IconItem(this, CUT, R.drawable.ic_content_cut_white_24dp);
		addLeftItem(iconItem);
		iconItem = new IconItem(this, ORDER, R.drawable.ic_sort_white_24dp);
		addTopItem(iconItem);
		TextItem textItem = new TextItem(this, SHOWALL, "show all",Color.parseColor("#1E88E5"));
		addTopItem(textItem);
		setHidedOnTouchOutside(true);
	}

	@Override
	public void onItemClick(int id) {
		switch(id){
		case COPY:
			Toast.makeText(getApplicationContext(), "Copy", 1).show();
			if(getItemById(PASTE) == null){
				IconItem iconItem = new IconItem(this, PASTE, R.drawable.ic_content_paste_white_24dp);
				addLeftItem(iconItem);
			}
			break;
		case PASTE:
			Toast.makeText(getApplicationContext(), "Paste", 1).show();
			removeLeftItem(PASTE);
			break;
		case CUT:
			if(getItemById(PASTE) == null){
				IconItem iconItem = new IconItem(this, PASTE, R.drawable.ic_content_paste_white_24dp);
				addLeftItem(iconItem);
			}
			Toast.makeText(getApplicationContext(), "Cut", 1).show();
			break;
		case ORDER:
			Toast.makeText(getApplicationContext(), "Order", 1).show();
			break;
		case SHOWALL:
			Toast.makeText(getApplicationContext(), "Show All", 1).show();
			break;
		case CREATE:
			Toast.makeText(getApplicationContext(), "Create", 1).show();
			break;
		}
	}
	

}

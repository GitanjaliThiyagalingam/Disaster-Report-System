package com.example.dars;

import java.util.ArrayList;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ExpandableListView;

public class ServiceActivity extends ExpandableListActivity{
	
	//create list objects to get all the list items
	private ArrayList<String> parentItems = new ArrayList<String>();
	private ArrayList<Object> childItems = new ArrayList<Object>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		ExpandableListView expandableList = getExpandableListView(); 		//Create Expandable LostView Object
		expandableList.setDividerHeight(2);
		expandableList.setGroupIndicator(null);
		expandableList.setClickable(true);

		setGroupParents();
		setChildData();

		MyExpandableAdapter adapter = new MyExpandableAdapter(parentItems, childItems);
		
		adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
		expandableList.setAdapter(adapter);
		expandableList.setOnChildClickListener(this);
	}

	public void setGroupParents() {
		parentItems.add("Accident");
		parentItems.add("Disaster");
		parentItems.add("Fire Accident");
	}

	public void setChildData() {
		
		// Accident
		ArrayList<String> child = new ArrayList<String>();
		child.add("Find current Location");
		child.add("Mark Location");
		child.add("See the disaster place");
		child.add("Find Path");
		child.add("Check nearby Hospitals");
		childItems.add(child);
		
		
		//Disaster
		child = new ArrayList<String>();
		child.add("Find current Location");
		child.add("Mark Location");
		child.add("See the disaster place");
		child.add("Find Path");
		child.add("Check nearby Places");
		childItems.add(child);
		
		//Fire Accident
		child = new ArrayList<String>();
		child.add("Find current Location");
		child.add("Mark Location");
		child.add("See the disaster place");
		child.add("Find Path");
		child.add("Check nearby Places");
		childItems.add(child);
		}
}

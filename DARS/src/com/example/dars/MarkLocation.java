package com.example.dars;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MarkLocation extends FragmentActivity {	
	
	GoogleMap googleMap;
	PlaceDbAdapter myDb=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mymap);
		
		SupportMapFragment supportMapFragment = (SupportMapFragment) 
							getSupportFragmentManager().findFragmentById(R.id.map);
		
		// Getting a reference to the map
		googleMap = supportMapFragment.getMap();
		//googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
		
		// Setting a click event handler for the map 
		 googleMap.setOnMapClickListener(new OnMapClickListener() {
				
				@Override
				public void onMapClick(LatLng point) {		
					
										
					// Drawing marker on the map
					drawMarker(point);			
					
					// Creating an instance of ContentValues
					ContentValues contentValues = new ContentValues();
					
					// Setting latitude in ContentValues
		            contentValues.put(PlaceDbAdapter.FIELD_LAT, point.latitude );
		            
		            // Setting longitude in ContentValues
		            contentValues.put(PlaceDbAdapter.FIELD_LNG, point.longitude);
		            
		            // Setting zoom in ContentValues
		            contentValues.put(PlaceDbAdapter.FIELD_ZOOM, googleMap.getCameraPosition().zoom);
		            
		            // Creating an instance of LocationInsertTask
					LocationInsertTask insertTask = new LocationInsertTask();
					
					// Storing the latitude, longitude and zoom level to SQLite database
					insertTask.execute(contentValues);                
			        		        
			        Toast.makeText(getBaseContext(), "Marker is added to the Map", Toast.LENGTH_SHORT).show();			        
			        
				}
			});	
		 		
	}
	private void drawMarker(LatLng point){
    	// Creating an instance of MarkerOptions
    	MarkerOptions markerOptions = new MarkerOptions();					
    		
    	// Setting latitude and longitude for the marker
    	markerOptions.position(point);
    		
    	// Adding marker on the Google Map
    	googleMap.addMarker(markerOptions);    		
    }
	public class LocationInsertTask extends AsyncTask<ContentValues, Void, Void>{
		@Override
		protected Void doInBackground(ContentValues... contentValues) {
			
			/** Setting up values to insert the clicked location into SQLite database */           
            getContentResolver().insert(LocationsContentProvider.CONTENT_URI, contentValues[0]);			
			return null;
		}		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
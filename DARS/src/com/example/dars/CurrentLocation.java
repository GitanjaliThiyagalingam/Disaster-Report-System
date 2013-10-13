package com.example.dars;
import android.app.Dialog;
import android.content.ContentValues;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class CurrentLocation extends FragmentActivity implements LocationListener {
    
GoogleMap googleMap;
public static double latitude,longitude;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymap);
        
        // Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        // Showing status
        if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available
            
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
            
        }else {    // Google Play Services are available    
        
            // Getting reference to the SupportMapFragment of activity_main.xml
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            
            // Getting GoogleMap object from the fragment
            googleMap = fm.getMap();
            
            // Enabling MyLocation Layer of Google Map
            googleMap.setMyLocationEnabled(true);                    
            
             // Getting LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    
            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();
    
            // Getting the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);
    
            // Getting Current Location
            Location location = locationManager.getLastKnownLocation(provider);
    
            if(location!=null){
                    onLocationChanged(location);
            }
    
            //locationManager.requestLocationUpdates(provider, 20000, 0, this);
        }        
    }
    

    @Override
    public void onLocationChanged(Location location) {
        
        TextView tvLocation = (TextView) findViewById(R.id.tv_location);        
        // Getting latitude of the current location
        latitude = location.getLatitude();
        
        // Getting longitude of the current location
        longitude = location.getLongitude();        
        
        // Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);
        
        // Showing the current location in Google Map
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        
        // Zoom in the Google Map
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));        
        
        
        // Setting latitude and longitude in the TextView tv_location
        tvLocation.setText("Latitude:" +  latitude  + ", Longitude:"+ longitude );    
     // Creating an instance of ContentValues
     	ContentValues contentValues = new ContentValues();
     		
     		// Setting latitude in ContentValues
        contentValues.put(PlaceDbAdapter.FIELD_LAT, latLng.latitude );
             
             // Setting longitude in ContentValues
        contentValues.put(PlaceDbAdapter.FIELD_LNG, latLng.longitude);
             
             // Setting zoom in ContentValues
        contentValues.put(PlaceDbAdapter.FIELD_ZOOM, googleMap.getCameraPosition().zoom);
             
             // Creating an instance of LocationInsertTask
     	LocationInsertTask insertTask = new LocationInsertTask();
     		
     		// Storing the latitude, longitude and zoom level to SQLite database
     	insertTask.execute(contentValues);                
             		        
        Toast.makeText(getBaseContext(), "Marker is added to the Map", Toast.LENGTH_SHORT).show();	
                
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub        
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub        
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	public class LocationInsertTask extends AsyncTask<ContentValues, Void, Void>{
		@Override
		protected Void doInBackground(ContentValues... contentValues) {
			
			/** Setting up values to insert the clicked location into SQLite database */           
            getContentResolver().insert(LocationsContentProvider.CONTENT_URI, contentValues[0]);			
			return null;
		}		
	}
	
}
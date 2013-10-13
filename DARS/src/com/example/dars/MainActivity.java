package com.example.dars;



import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	         
	        TabHost tabHost = getTabHost();
	         
	        // Tab for Home Page
	        TabSpec homespec = tabHost.newTabSpec("Home");
	        // setting Title and Icon for the Tab
	        homespec.setIndicator("Home", getResources().getDrawable(R.drawable.home_tab));
	        Intent photosIntent = new Intent(MainActivity.this, HomeActivity.class);
	        homespec.setContent(photosIntent);
	         
	        // Tab for Login Page
	        TabSpec loginspec = tabHost.newTabSpec("Login");       
	        loginspec.setIndicator("Login", getResources().getDrawable(R.drawable.login_tab));
	        Intent songsIntent = new Intent(MainActivity.this, LoginActivity.class);
	        loginspec.setContent(songsIntent);
	         
	        // Tab for Register Page
	        TabSpec regspec = tabHost.newTabSpec("Register");
	        regspec.setIndicator("Register", getResources().getDrawable(R.drawable.register_tab));
	        Intent videosIntent = new Intent(MainActivity.this, RegisterActivity.class);
	        regspec.setContent(videosIntent);
	         
	        // Adding all TabSpec to TabHost
	        tabHost.addTab(homespec); // Adding photos tab
	        tabHost.addTab(loginspec); // Adding songs tab
	        tabHost.addTab(regspec); // Adding videos tab
	    }
}

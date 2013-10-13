package com.example.dars.test;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;

import com.example.dars.LoginActivity;

public class LoginActivityFunctionalTest extends ActivityInstrumentationTestCase2<LoginActivity> {
	private LoginActivity activity;

	public LoginActivityFunctionalTest() {
			super(LoginActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(false);
		activity = getActivity();
	}
	
	public void testStartLoginActivity() throws Exception {

		// Add monitor to check for the login activity
		ActivityMonitor monitor = getInstrumentation().addMonitor(
				LoginActivity.class.getName(), null, false);

		// Find button and click it
		Button view = (Button) activity
				.findViewById(com.example.dars.R.id.btnLogin);
		TouchUtils.clickView(this, view);
	}
}

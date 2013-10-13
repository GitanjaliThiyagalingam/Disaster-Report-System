package com.example.dars.test;

import android.content.Intent;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import com.example.dars.RegisterActivity;

public class RegisterActivityTest extends android.test.ActivityUnitTestCase<RegisterActivity> {
	
	private RegisterActivity activity;
	private int btn;	
	

	public RegisterActivityTest() {
		super(com.example.dars.RegisterActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				RegisterActivity.class);
		startActivity(intent, null, null);
		activity = getActivity();
	}
	
	@SmallTest
	public void testLayout() {
		// test the sign in button layout
		btn = com.example.dars.R.id.btnRegister;
		assertNotNull(activity.findViewById(btn));
		Button view1 = (Button) activity.findViewById(btn);
		assertEquals("Incorrect label of the button", "Register New Account",
				view1.getText());

	}
	
	@SmallTest
	public void testIntentTriggerViaOnClick() {
		
		// -----------------for sign in --------------------------
		// check sign in button view
		btn = com.example.dars.R.id.btnRegister;
		Button view = (Button) activity.findViewById(btn);
		assertNotNull("Button not allowed to be null", view);

		// call the sign in method directly via view1
		getActivity().onClick(view);

		// Check the intent which was started
		Intent triggeredIntent = getStartedActivityIntent();
		assertNotNull("Intent was null", triggeredIntent);
		// Check the intent which was started
		String data = triggeredIntent.getExtras().getString("URL");
		assertEquals("Incorrect data passed via the intent",
		        "http://www.vogella.com", data);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}

package com.example.dars;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SmsSendActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendsms);
        
        
        
        OnClickListener listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/** Getting reference to et_number of main.xml */
		        EditText etNumber = (EditText) findViewById(R.id.et_number);
		        
		        /** Getting reference to et_message of main.xml */
		        EditText etMessage = (EditText) findViewById(R.id.et_message);
		        
		        String number = etNumber.getText().toString();
		        String message = etMessage.getText().toString();		        
		        		        
		        /** Creating a pending intent which will be broadcasted when an sms message is successfully sent */
		        PendingIntent piSent = PendingIntent.getBroadcast(getBaseContext(), 0, new Intent("in.wptrafficanalyzer.sent") , 0);
		        
		        /** Creating a pending intent which will be broadcasted when an sms message is successfully delivered */
		        PendingIntent piDelivered = PendingIntent.getBroadcast(getBaseContext(), 0, new Intent("in.wptrafficanalyzer.delivered"), 0);
		        
		        /** Getting an instance of SmsManager to sent sms message from the application*/
		        SmsManager smsManager = SmsManager.getDefault();		        
		        
		        /** Sending the Sms message to the intended party */
		        smsManager.sendTextMessage(number, null, message, piSent, piDelivered);
				
			}
		};
		
		/** Getting reference to btn_send of main.xml */
        Button btnSend = (Button) findViewById(R.id.btn_send);
        
        /** Setting a click event listener for the button */
        btnSend.setOnClickListener(listener);
        
    }
}
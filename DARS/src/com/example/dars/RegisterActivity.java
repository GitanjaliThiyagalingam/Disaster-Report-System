package com.example.dars;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 
public class RegisterActivity extends Activity implements OnClickListener{
	private Button mRegister;
	private EditText mUsername;
	private EditText mPassword;
	private EditText mEmail,mpword;
    TextView registerErrorMsg;
    private DbAdapter myDb = new DbAdapter(RegisterActivity.this);			//creating an object to connect to the SQLite database
     
    private static String KEY_UID = "uid";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mRegister=(Button)findViewById(R.id.btnRegister);
        mRegister.setOnClickListener(this);       
    }
         
        // Register Button Click event
    public void onClick(View v) {
        if(v.getId()==R.id.btnRegister){			  // check if clicked button is btnRegister
			mUsername = (EditText)findViewById(R.id.registerName);
			mPassword = (EditText)findViewById(R.id.registerPassword);
			mEmail = (EditText)findViewById(R.id.registerEmail);
			mpword=(EditText)findViewById(R.id.adPword);
			registerErrorMsg = (TextView) findViewById(R.id.register_error);
			
			String uname = mUsername.getText().toString();
			String pass = mPassword.getText().toString();
			String email = mEmail.getText().toString();
			String adPass=mpword.getText().toString();
			boolean invalid = false;       			//variable to check empty inputs
			
			if(uname.equals("")){      				//if username is empty
				invalid = true;
				Toast.makeText(getApplicationContext(), "Username Missing", Toast.LENGTH_SHORT).show();		//warning the user to input username
			}else if(pass.equals("")){
				invalid = true;
				Toast.makeText(getApplicationContext(), "Password Missing", Toast.LENGTH_SHORT).show();
			}else if(email.equals("")){
				invalid = true;
				Toast.makeText(getApplicationContext(), "Email ID Missing", Toast.LENGTH_SHORT).show();
			}
			
			if((invalid == false)&&(adPass.equals("admin"))){      //if all the inputs are given and check the admin password
				addEntry(uname, pass, email);						//add details to the database
				Intent i_register = new Intent(RegisterActivity.this, LoginActivity.class);	     //to start LoginActivity
				startActivity(i_register);
				finish();
			}
			
		}
}

public void onDestroy(){
	super.onDestroy();
	myDb.close();
}

public void addEntry(String uname, String pass, String email){
	
	SQLiteDatabase db = myDb.getWritableDatabase(); 		//open the database to write data
	
	ContentValues values = new ContentValues();
	values.put("username", uname);
	values.put("password", pass);
	values.put("email", email);
	
	try{
		db.insert(DbAdapter.TABLE_NAME, null, values);
		Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
	}catch(Exception e){
		e.printStackTrace();
	}
}
}

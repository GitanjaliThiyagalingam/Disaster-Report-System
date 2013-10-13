package com.example.dars;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 
public class LoginActivity extends Activity implements OnClickListener{
    Button btnLogin;
    EditText inputName;
    EditText inputPassword;
    TextView loginErrorMsg;
    DbAdapter mydb=null;
    String uname;
         
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
 
        // Importing all assets like buttons, text fields
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        loginErrorMsg = (TextView) findViewById(R.id.login_error);
        
    }
 
    // Login button Click Event
    public void onClick(View view) {
        	if(view.getId()==R.id.btnLogin){
    			inputName = (EditText)findViewById(R.id.loginName);				
    			inputPassword = (EditText)findViewById(R.id.loginPassword);
    			
    			uname = inputName.getText().toString();					//get the input username and password
    			String pass = inputPassword.getText().toString();
    			
    			if(uname.equals("") || uname == null){
    				Toast.makeText(getApplicationContext(), "Username Empty", Toast.LENGTH_SHORT).show();
    			}else if(pass.equals("") || pass == null){
    				Toast.makeText(getApplicationContext(), "Password Empty", Toast.LENGTH_SHORT).show();
    			}else{
    				boolean validLogin = validateLogin(uname, pass, LoginActivity.this);			//check username and password whether in the database or not
    				if(validLogin){
    					Intent i = new Intent(LoginActivity.this, ServiceActivity.class);
    					i.putExtra("name", uname);
    					startActivity(i);							//start the LoggegInActivity
    					finish();
    				}
    			} 
        	}        
    }
    
    public String getName(){
		return uname;
	}
    
    public boolean validateLogin(String uname, String pass, Context context) {
		
		mydb = new DbAdapter(context);
		SQLiteDatabase db = mydb.getReadableDatabase();					//create readable database
		//SELECT
		String[] columns = {"id"};
		
		//WHERE clause
		String selection = "username=? AND password=?";
		
		//WHERE clause arguments
		String[] selectionArgs = {uname,pass};
		
		Cursor cursor = null;
		try{
		//SELECT _id FROM login WHERE username=uname AND password=pass
		cursor = db.query(DbAdapter.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
		
		startManagingCursor(cursor);
		}catch(Exception e){
			e.printStackTrace();
		}
		int numberOfRows = cursor.getCount();
		
		if(numberOfRows <= 0){
		
			Toast.makeText(getApplicationContext(), "Login Failed..\nTry Again", Toast.LENGTH_SHORT).show();
			return false;
		}	
		
		return true;
	}
	
	public void onDestroy(){
		super.onDestroy();
		mydb.close();
	}

}
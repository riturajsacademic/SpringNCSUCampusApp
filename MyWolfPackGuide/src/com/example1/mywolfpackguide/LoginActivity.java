package com.example1.mywolfpackguide;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;



public class LoginActivity extends Activity
{
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	@Override
public void onCreate(Bundle savedInstanceState) 
{
super.onCreate(savedInstanceState);

setContentView(R.layout.activity_login);

final EditText txt_username = (EditText) findViewById(R.id.txtUserName);

final EditText txt_psw = (EditText) findViewById(R.id.txtpassword);


Button btn_signin=(Button) findViewById(R.id.btnlogin);

Button btn_register = (Button) findViewById(R.id.btnRegister);


btn_register.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		Intent intentRegister = new Intent(LoginActivity.this, SignUpActivity.class);
		startActivity(intentRegister);
		
	}
});



	btn_signin.setOnClickListener(new OnClickListener(){

		public void onClick(View v) {
	
			DBAdapter db= new DBAdapter(getBaseContext());
			db.open();
			
			Cursor curUserInfo = db.validateLoginInfo(txt_username.getText().toString(), txt_psw.getText().toString());
			
			if(curUserInfo.getCount() == 1)
			{
			if (curUserInfo.moveToFirst())
			{
				//Toast.makeText(getBaseContext(),"Success" + curUserInfo.getString(0) + " " + curUserInfo.getString(1) , Toast.LENGTH_SHORT).show();
				if(Integer.parseInt(curUserInfo.getString(1)) == 1){
					
					Intent newintent = new Intent(LoginActivity.this,AdminHomePage.class);
					startActivity(newintent);
				}
				else
				{
					Intent newintent = new Intent(LoginActivity.this,UserHomePage.class);
					startActivity(newintent);
				}
			}

			}
			else
			{
				Toast.makeText(getBaseContext(), "Login Failed! Please sign in to use the app", Toast.LENGTH_SHORT).show();
			}
				
			db.close();

		}
}); 

}
}


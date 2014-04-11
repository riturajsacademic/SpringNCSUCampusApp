package com.example1.mywolfpackguide;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		

		final EditText txt_username = (EditText) findViewById(R.id.txtUserName);

		final EditText txt_psw = (EditText) findViewById(R.id.txtpassword);

		final EditText txt_email = (EditText) findViewById(R.id.txtEmail);
		
		Button btn_next =(Button) findViewById(R.id.btnNext);
		
		
		
		btn_next.setOnClickListener(new OnClickListener() {
	
	@Override
		public void onClick(View v) {
		// TODO Auto-generated method stub
		
		UsersClass objUser = new UsersClass(txt_username.getText().toString(),txt_psw.getText().toString(),
				txt_email.getText().toString());
		
		if((txt_username.getText().toString().length() == 0) || (txt_psw.getText().toString().length() == 0) ||
				(txt_email.getText().toString().length() == 0)){
			Toast.makeText(getBaseContext(), "Enter all fields", Toast.LENGTH_SHORT).show();
			return;
		}

		Intent intentRegister = new Intent(SignUpActivity.this, CategoryActivity.class);
		intentRegister.putExtra("objSignup",objUser);
		//intentRegister.putExtra("Password", objUser.getPassword());
		//intentRegister.putExtra("Email", objUser.getEmailId());
		startActivity(intentRegister);
		
		
		
		
	}
});

}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

}

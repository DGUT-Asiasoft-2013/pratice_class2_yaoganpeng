package com.example.helloworld;

import com.example.helloworld.fragments.PasswordRecoverStep1Fragment;
import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class LoginActivity extends Activity {
	SimpleTextInputCellFragment fragAccount,fragPassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				goRegister();
			}
		});
		findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				goLogin();
			}
		});
		findViewById(R.id.btn_forgot_password).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goRecoverPassword();
			}

			
		});
		fragAccount=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_account);
		fragPassword=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_password);
	}
	protected void onResume(){
		super.onResume();
		fragAccount.setLabelText("’Àªß√˚");
		fragAccount.setHintText("«Î ‰»Îµ«¬º√˚");
		fragPassword.setLabelText("√‹¬Î");
		fragPassword.setHintText("«Î ‰»Î√‹¬Î");
		fragPassword.setIsPassword(true);
	}
	void goRegister(){
		Intent itnt = new Intent(this,RegisterActivity.class);
		startActivity(itnt);
	}
	void goLogin(){
		Intent itnt = new Intent(this,HelloWorldActivity.class);
		startActivity(itnt);
	}
	void goRecoverPassword() {
		Intent itnt = new Intent(this,PasswordRecoverStep1Fragment.class);
		startActivity(itnt);
	}
}
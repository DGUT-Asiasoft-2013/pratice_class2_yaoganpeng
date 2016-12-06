package com.example.helloworld;

import com.example.helloworld.fragments.PasswordRecoverStep1Fragment;
import com.example.helloworld.fragments.PasswordRecoverStep1Fragment.OnGoNextListener;

import android.app.Activity;
import android.os.Bundle;

public class PasswordRecoverActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password_recover);
		PasswordRecoverStep1Fragment frag = (PasswordRecoverStep1Fragment)getFragmentManager().findFragmentById(R.id.btn_next);
		frag.setOnGoNextListener(new OnGoNextListener() {
			
			@Override
			public void onGoNext() {
				goStep2();
			}
		});
	}
	void goStep2(){
		
	}
}

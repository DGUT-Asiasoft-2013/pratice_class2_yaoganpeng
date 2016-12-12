package com.example.helloworld;

//import com.example.helloworld.fragments.PasswordRecoverStep1Fragment;
//import com.example.helloworld.fragments.PasswordRecoverStep1Fragment.OnGoNextListener;
//import com.example.helloworld.fragments.PasswordRecoverStep2Fragment;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.ViewGroup;
//
//public class PasswordRecoverActivity extends Activity {
//	PasswordRecoverStep1Fragment step1 = new PasswordRecoverStep1Fragment();
//	PasswordRecoverStep2Fragment step2 = new PasswordRecoverStep2Fragment();
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_password_recover);
//		step1.setOnGoNextListener(new OnGoNextListener() {
//			@Override
//			public void onGoNext() {
//				goStep2();
//			}
//		});
//
//		getFragmentManager().beginTransaction().replace(R.id.container, step1).commit();
//	}
//
//	void goStep2(){
//
//		getFragmentManager()
//		.beginTransaction()	
//		.setCustomAnimations(
//				R.animator.slide_in_right,
//				R.animator.slide_out_left,
//				R.animator.slide_in_left,
//				R.animator.slide_out_right)
//		.replace(R.id.container, step2)
//		.addToBackStack(null)
//		.commit();
//	}
//}
import java.io.IOException;

import com.example.helloworld.api.Server;
import com.example.helloworld.fragments.PasswordRecoverStep1Fragment;
import com.example.helloworld.fragments.PasswordRecoverStep1Fragment.OnGoNextListener;
import com.example.helloworld.fragments.PasswordRecoverStep2Fragment;
import com.example.helloworld.fragments.PasswordRecoverStep2Fragment.OnPasswordRecoverListener;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PasswordRecoverActivity extends Activity {

	PasswordRecoverStep1Fragment step1Fragment=new PasswordRecoverStep1Fragment();
	PasswordRecoverStep2Fragment step2Fragment=new PasswordRecoverStep2Fragment();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password_recover);
		
		step1Fragment.setOnGoNextListener(new OnGoNextListener() {			
			@Override
			public void onGoNext() {
				
				goStep2();				
			}
		});
		
		step2Fragment.setOnPasswordRecoverListener(new OnPasswordRecoverListener() {
			
			@Override
			public void onPasswordRecover() {
				gorepassword();
				
			}
		});
		getFragmentManager()
		.beginTransaction()
		.replace(R.id.container, step1Fragment)
		.commit();
	}
	
	
	
	 void gorepassword() {
		 OkHttpClient client =Server.getSharedClient();
		 String password = MD5.getMD5(step2Fragment.getText());
		 MultipartBody.Builder requestBodyBulider=new MultipartBody.Builder()
					.setType(MultipartBody.FORM)
					.addFormDataPart("email",step1Fragment.getText())
					.addFormDataPart("passwordHash",password);
		 
		 
		 
		 Request request = Server.requestBuilderWithApi("passwordrecover")
					.method("post", null)
					.post(requestBodyBulider.build())
					.build();
		 
		 client.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(final Call arg0,final Response arg1) throws IOException {			
				runOnUiThread(new Runnable() {					
					
					public void run() {
						try {
							PasswordRecoverActivity.this.onResponse(arg0,arg1.body().string());							
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
				});
				
			}
			
			@Override
			public void onFailure(final Call arg0,final IOException arg1) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						PasswordRecoverActivity.this.onFailure(arg0, arg1);					
					}
				});
				
			}
		});
		
	}



	void onFailure(Call arg0, IOException arg1) {
		new AlertDialog.Builder(PasswordRecoverActivity.this)
		.setTitle("失败了")
		.setMessage(arg1.getLocalizedMessage())
		.setPositiveButton("确认",null)
		.show();
		
	}



	void onResponse(Call arg0,String string) {
		 new AlertDialog.Builder(PasswordRecoverActivity.this)
			.setMessage(string+"修改密码成功")
			.setPositiveButton("确认",null)
			.show();		
	}



	void goStep2(){
		getFragmentManager()
		.beginTransaction()
		.setCustomAnimations(R.animator.slide_in_right,
				R.animator.slide_out_left,
				R.animator.slide_in_left, 
				R.animator.slide_out_right)
		.replace(R.id.container, step2Fragment)
		.addToBackStack(null)
		.commit();
	}
	
	
}
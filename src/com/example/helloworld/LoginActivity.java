package com.example.helloworld;

import java.io.IOException;


import com.example.helloworld.api.Server;
import com.example.helloworld.entity.User;
import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

		fragAccount = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_account);
		fragPassword = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_password);
	}

	@Override
	protected void onResume() {
		super.onResume();

		fragAccount.setLabelText("账户名");
		fragAccount.setHintText("请输入账户名");
		fragPassword.setLabelText("密码");
		fragPassword.setHintText("请输入密码");
		fragPassword.setIsPassword(true);
	}

	void goRegister(){
		Intent itnt = new Intent(this,RegisterActivity.class);
		startActivity(itnt);
	}

	void goLogin(){
		OkHttpClient client = Server.getSharedClient();

		MultipartBody requestBody = new MultipartBody.Builder()
				.addFormDataPart("account", fragAccount.getText())
				.addFormDataPart("passwordHash", MD5.getMD5(fragPassword.getText()))
				.build();

		Request request = Server.requestBuilderWithApi("login")
				.method("post", null)
				.post(requestBody)
				.build();

		final ProgressDialog dlg = new ProgressDialog(this);
		dlg.setCancelable(false);
		dlg.setCanceledOnTouchOutside(false);
		dlg.setMessage("正在登陆");
		dlg.show();

		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(final Call arg0, final Response arg1) throws IOException {
				//把消息处理按user类格式化
				try{						
					ObjectMapper obMapper = new ObjectMapper();
					final User user = obMapper.readValue(arg1.body().string(), User.class);
					final String hello = "Hello "+user.getName();

					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							dlg.dismiss();
							LoginActivity.this.onResponse(arg0, hello);
						}
					});
				}catch(final Exception e){
					runOnUiThread(new Runnable() {							
						@Override
						public void run() {
							dlg.dismiss();
							LoginActivity.this.onFailure(arg0, e);
						}
					});						
				}
			}

			@Override
			public void onFailure(Call arg0, final IOException arg1) {
				runOnUiThread(new Runnable() {
					public void run() {
						dlg.dismiss();
						Toast.makeText(LoginActivity.this, arg1.getLocalizedMessage(), Toast.LENGTH_LONG).show();
					}
				});
			}
		});
	}

	void goRecoverPassword(){
		Intent itnt = new Intent(this, PasswordRecoverActivity.class);
		startActivity(itnt);
	}
	void onResponse(Call arg0, String arg1){
		new AlertDialog.Builder(this)
		.setTitle("登录成功")
		.setMessage(arg1)
		.setNegativeButton("好",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent itnt = new Intent(LoginActivity.this,HelloWorldActivity.class);
				startActivity(itnt);
				finish();
			}
		})
		.show();
	}

	void onFailure(Call arg0, Exception e){
		new AlertDialog.Builder(this)
		.setTitle("登录失败")
		.setMessage(e.getLocalizedMessage())
		.setNegativeButton("好", null)
		.show();
	}
}
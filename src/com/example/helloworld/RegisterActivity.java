package com.example.helloworld;

import java.io.IOException;

import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends Activity {

	SimpleTextInputCellFragment fragInputCellAccount;
	SimpleTextInputCellFragment fragInputCellPassword;
	SimpleTextInputCellFragment fragInputCellPasswordRepeat;
	SimpleTextInputCellFragment fragInputCellEmailAddress;
	SimpleTextInputCellFragment fragInputCellName;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_register);

		fragInputCellAccount = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_account);
		fragInputCellPassword = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_password);
		fragInputCellPasswordRepeat = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_password_repeat);
		fragInputCellEmailAddress = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_email);
		fragInputCellName = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_name);

		findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				submit();

			}
		});



	}

	void submit(){
		//--------------
		//验证密码与重复密码是否相同
		String password = fragInputCellPassword.getText();
		String passwordRepeat = fragInputCellPasswordRepeat.getText();

		if(!password.equals(passwordRepeat)){
			new AlertDialog.Builder(RegisterActivity.this)
			.setMessage("输入密码不一致哦")
			.setIcon(android.R.drawable.ic_dialog_alert)
			.setNegativeButton("好", null)
			.show();
		}else{


			//---------------
			//得到文本框内容
			String account = fragInputCellAccount.getText();
			String name = fragInputCellName.getText();
			String email = fragInputCellEmailAddress.getText();

			//---------------
			//创建OKHttp客户端

			RequestBody requestBody = new MultipartBody.Builder()
					.addFormDataPart("account", account)
					.addFormDataPart("name", name)
					.addFormDataPart("email", email)
					.addFormDataPart("passwordHash", password)
					.build();

			OkHttpClient client = new OkHttpClient();

			//创建请求，包含地址，方法("GET","POST","PUT","DELETE")
			Request request = new Request.Builder()
					.url("http://172.27.0.12:8080/membercenter/api/register")
					.method("post", null)
					.post(requestBody)
					.build();
			//---------------
			//ProgressDialog
			final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
			progressDialog.setMessage("等待中，请稍后...");
			progressDialog.setCancelable(false);
			progressDialog.setCanceledOnTouchOutside(false);



			client.newCall(request).enqueue(new Callback() {

				@Override
				public void onResponse(final Call arg0, final Response arg1) throws IOException {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {

							progressDialog.dismiss();

							try {
								RegisterActivity.this.onResponse(arg0,arg1);
							} catch (Exception e) {
								e.printStackTrace();
								RegisterActivity.this.onFailure(arg0, e);
							}

						}
					});

				}

				@Override
				public void onFailure(final Call arg0, final IOException arg1) {


					RegisterActivity.this.onFailure(arg0, arg1);

				}
			});
		}
	}


	@Override
	protected void onResume() {
		super.onResume();

		fragInputCellAccount.setLabelText("用户名");{
			fragInputCellAccount.setHintText("请输入用户名");
		}

		fragInputCellName.setLabelText("昵称");{
			fragInputCellName.setHintText("请输入昵称");
		}

		fragInputCellPassword.setLabelText("密码");{
			fragInputCellPassword.setHintText("请输入密码");
			fragInputCellPassword.setIsPassword(true);
		}

		fragInputCellPasswordRepeat.setLabelText("重复密码");{
			fragInputCellPasswordRepeat.setHintText("请重复输入密码");
			fragInputCellPasswordRepeat.setIsPassword(true);
		}

		fragInputCellEmailAddress.setLabelText("电子邮箱");{
			fragInputCellEmailAddress.setHintText("请输入电子邮箱");
		}

	}
	void onResponse(Call arg0, Response arg1) throws IOException{
		new AlertDialog.Builder(this)
		.setTitle("请求成功")
		.setMessage(arg1.body().string())
		.setNegativeButton("好", null)
		.show();


	}

	void onFailure(Call arg0, Exception e){
		new AlertDialog.Builder(this)
		.setTitle("请求失败")
		.setMessage(e.getLocalizedMessage())
		.setNegativeButton("好", null)
		.show();
	}


}

package com.example.helloworld;

import java.io.IOException;

import org.apache.http.conn.ClientConnectionManager;

import android.app.Activity;
//import android.app.DownloadManager.Request;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BootActivity extends Activity {
	//ÆðÊ¼Ò³
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_boot);
	}

	@Override
	protected void onResume() {
		super.onResume();

		//		Handler handler = new Handler();
		//		handler.postDelayed(new Runnable(){
		//			public void run() {
		//				startLoginActivity();
		//			}
		//		},1000);

		OkHttpClient client = new OkHttpClient();
		Request request = new Request
				.Builder()
				.url("http://172.27.0.12:8080/membercenter/api/hello")
				.method("GET",null)
				.build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call arg0, final Response arg1) throws IOException {
				BootActivity.this.runOnUiThread(new  Runnable() {
					public void run() {
						try {
							Toast.makeText(getApplicationContext(), arg1.body().string(),Toast.LENGTH_SHORT).show();
						} catch (IOException e) {
							e.printStackTrace();
						}
						startLoginActivity();
					}
				});
			}


				@Override
				public void onFailure(Call arg0, final IOException arg1) {
					Toast.makeText(getApplicationContext(), arg1.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

				}
			});



		}



		void startLoginActivity() {
			Intent itnt = new Intent(this,LoginActivity.class);
			startActivity(itnt);
			finish();
		}
	}

package com.example.helloworld;

import java.util.zip.Inflater;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FeedContentActivity extends Activity {
	TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String str=bundle.getString("text");
        setContentView(R.layout.fragment_feedcontent);
        textView = (TextView)findViewById(R.id.text);
        textView.setText(str);
        
	}
}

package com.example.helloworld.fragments.pages;

import com.example.helloworld.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SearchPageFragment extends Fragment {
	View view;
	Button btnSearch;
	EditText content;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view==null){
			view = inflater.inflate(R.layout.fragment_page_search_page, null);
			btnSearch = (Button)view.findViewById(R.id.search);
			content = (EditText)view.findViewById(R.id.search_content);
			btnSearch.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
				}
			});
		}
		

		return view;
	}
}
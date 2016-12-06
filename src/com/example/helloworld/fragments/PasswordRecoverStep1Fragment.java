package com.example.helloworld.fragments;

import com.example.helloworld.R;
import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PasswordRecoverStep1Fragment extends Fragment {
	View view;
	SimpleTextInputCellFragment fragEmail;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(view==null){
		view = inflater.inflate(R.layout.fragment_password_recover_step1, container);
		fragEmail = (SimpleTextInputCellFragment)getChildFragmentManager().findFragmentById(R.id.input_email);
		view.findViewById(R.id.btn_forgot_password);
		}
		return view;
		
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		fragEmail.setLabelText("◊¢≤·’À∫≈");
		fragEmail.setHintText(" ‰»Î◊¢≤·” œ‰µÿ÷∑");
	}
	public static interface OnGoNextListener{
		void onGoNext();
	}
	OnGoNextListener onGoNextListener;
	public void setOnGoNextListener(OnGoNextListener onGoNextListener){
		this.onGoNextListener = onGoNextListener;
	}
}

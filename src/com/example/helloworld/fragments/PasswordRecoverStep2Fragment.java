package com.example.helloworld.fragments;

//package com.example.helloworld.fragments;
//
//import com.example.helloworld.R;
//
//import android.app.Fragment;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//public class PasswordRecoverStep2Fragment extends Fragment {
//	View view;
//	
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		
//		if(view==null){
//			view = inflater.inflate(R.layout.fragment_password_recover_step2, null);
//		}
//		
//		return view;
//	}
//}
import com.example.helloworld.R;
import com.example.helloworld.fragments.inputcells.BaseInputCellFragment;
import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PasswordRecoverStep2Fragment extends Fragment {

	View view;
	SimpleTextInputCellFragment flagInputVerify;
	SimpleTextInputCellFragment flagInputPassword;
	SimpleTextInputCellFragment flagInputPasswordRepeat;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view==null) {
			view=inflater.inflate(R.layout.fragment_password_recover_step2, null);
		}
		flagInputVerify=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_verify);
		flagInputPassword=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_password);
		flagInputPasswordRepeat=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_password_repeat);
		
		view.findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				passwordre();
				
			}
		});
		return view;
	}
	
	

	@Override
	public void onResume() {
		
		super.onResume();
		flagInputVerify.setLabelText("验证码");
		flagInputVerify.setHintText("请输入验证码");
		flagInputPassword.setLabelText("新密码");
		flagInputPassword.setHintText("输入新密码");
		flagInputPasswordRepeat.setLabelText("重复输入");
		flagInputPasswordRepeat.setHintText("再次输入新密码");
	}
	
	
	public static interface OnPasswordRecoverListener {
		void onPasswordRecover();
	}

	OnPasswordRecoverListener onPasswordRecoverListener;

	public void setOnPasswordRecoverListener(OnPasswordRecoverListener onPasswordRecoverListener) {
		this.onPasswordRecoverListener = onPasswordRecoverListener;
	}

	 void passwordre() {
		 if (flagInputPassword.getText().equals(flagInputPasswordRepeat.getText())) {
			 if (onPasswordRecoverListener!=null) {
					onPasswordRecoverListener.onPasswordRecover();
				}
		} else {
			new AlertDialog.Builder(getActivity())
			.setTitle("失败RU啊")
			.setMessage("两次密码输入不一致")
			.setPositiveButton("Rua!",null)
			.show();
		}
	   
	}
	 
	public String getText(){
		return flagInputPassword.getText();
	}
}
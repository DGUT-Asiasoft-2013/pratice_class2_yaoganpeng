package com.example.helloworld;

import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;

import android.app.Activity;
import android.os.Bundle;

public class RegisterActivity extends Activity{
	SimpleTextInputCellFragment fragInputCellAccount;
	SimpleTextInputCellFragment fragInputCellPassword;
	SimpleTextInputCellFragment fragInputCellPasswordRepeat;
	SimpleTextInputCellFragment fragInputEmailAddress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		fragInputCellAccount=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_account);
		fragInputCellPassword=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_password);
		fragInputCellPasswordRepeat=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_password_repeat);
		fragInputEmailAddress=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_email);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		fragInputCellAccount.setLabelText("�˻���");
		fragInputCellAccount.setHintText("�������˻���");
		fragInputCellPassword.setLabelText("����");
		fragInputCellPassword.setHintText("����������");
		fragInputCellPassword.setIsPassword(true);
		fragInputCellPasswordRepeat.setLabelText("�ظ�����");
		fragInputCellPasswordRepeat.setHintText("���ظ���������");
		fragInputCellPasswordRepeat.setIsPassword(true);
		fragInputEmailAddress.setLabelText("��������");
		fragInputEmailAddress.setHintText("����������");
	}

}

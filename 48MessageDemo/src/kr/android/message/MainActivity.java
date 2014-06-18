package kr.android.message;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

	Button btnAlert, btnToast, btnProgress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnAlert = (Button)findViewById(R.id.btnAlert);
		btnToast = (Button)findViewById(R.id.btnToast);
		btnProgress = (Button)findViewById(R.id.btnProgress);
		
		//�̺�Ʈ �ҽ��� �̺�Ʈ ������ ����
		btnAlert.setOnClickListener(this);
		btnToast.setOnClickListener(this);
		btnProgress.setOnClickListener(this);	
	}

	//�̺�Ʈ �ڵ鷯
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		case R.id.btnAlert:
			//���â ����
			//ü�ΰɾ �Ӽ� ȣ��
																								//setCancelable(true)	: ���ư���� â�� ���� ������ ����
			//new AlertDialog.Builder(this).setTitle("��ȭ����").setMessage("���â�� �����Ͽ����ϴ�!").setCancelable(true).setNeutralButton("�ݱ�", new DialogInterface.OnClickListener() {
			new AlertDialog.Builder(this).setTitle("��ȭ����").setMessage("���â�� �����Ͽ����ϴ�!").setCancelable(false).setNeutralButton("�ݱ�", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// �ܼ��� â�� ���� ���� ����� �ڵ尡 ����
					// â�� ���� �� �ΰ����� �۾��� �� ����  onClick()�޼��� �ȿ� ����					
				}
			}).show();			
			break;
		case R.id.btnToast:
			Toast.makeText(this, "�佺Ʈ�޽����� ���µǾ����ϴ�!",  Toast.LENGTH_SHORT).show();
			break;			
		case R.id.btnProgress:
			final ProgressDialog progressDialog = ProgressDialog.show(this, "���α׷��� �� �۵���", "��ø� ��ٷ��ּ���!");
			
			//�͸��� Ŭ������ ������ �����Ͽ� ��׶��� �۾�
			new Thread(){
				@Override
				public void run(){
					try{
						sleep(5000);
					}catch(Exception e){
						Log.e("Message Demo", e.toString());
					}
					//â �ݱ�
					//���ó��� Ŭ�����̹Ƿ� �޼ҵ�ȿ� �ִ� ���������� ȣ���� �� �����Ƿ� progressDialog��ü final�� �ٿ��ֿ��� �� 
					progressDialog.dismiss();					
				}
			}.start();
			
			break;
		}		
	}
}

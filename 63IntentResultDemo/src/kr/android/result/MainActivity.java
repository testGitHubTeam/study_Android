package kr.android.result;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener{
	
	Button btnIntentTwo, btnAddress;
	//ȣ���� Activity �ĺ��� ���� �ĺ��� ��� ����
	private static final int SHOW_ACTIVITY_INTENTTWO = 1;
	private static final int SHOW_ACTIVITY_ADDRESS = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnIntentTwo = (Button)findViewById(R.id.btnIntentTwo);
		btnAddress = (Button)findViewById(R.id.btnAddress);
		
		//�̺�Ʈ ������ ����
		btnIntentTwo.setOnClickListener(this);
		btnAddress.setOnClickListener(this);
		
	}
	
	//�̺�Ʈ �ڵ鷯
	@Override
	public void onClick(View v) {
		Intent intent = null;
		if(v.getId()==R.id.btnIntentTwo){		//IntetnTwoȣ��
			intent = new Intent(this, IntentTow.class);
										//requestCode: �ĺ��� 
			startActivityForResult(intent, SHOW_ACTIVITY_INTENTTWO);
			
			
		}else if(v.getId()==R.id.btnAddress){	//�ּҷ� ȣ��
			intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts/people"));
			//Activityȣ��
			startActivityForResult(intent, SHOW_ACTIVITY_ADDRESS);
		}
	}
	
	// Activity ȣ��� ������� �޴� �޼ҵ� �ۼ�
	// setResult()ȣ���ϸ� onActivityResult( )���� �����͸� �ް� ��
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		
		switch(requestCode){
		case SHOW_ACTIVITY_INTENTTWO:		//IntentTwo�� ����� ó��
			if(resultCode==Activity.RESULT_OK){
				String result = data.getStringExtra("msg");
				Toast.makeText(this, "����: " + result, Toast.LENGTH_SHORT).show();
			}else if(resultCode==Activity.RESULT_CANCELED){
				Toast.makeText(this, "��ҵ�", Toast.LENGTH_SHORT).show();
			}
			
			break;
		case SHOW_ACTIVITY_ADDRESS:			//�ּҷϿ��� ������� ����� ó��
			
			if(resultCode==Activity.RESULT_OK){
				Toast.makeText(this, "����: " + data.getData().toString(), Toast.LENGTH_SHORT).show();
			}else if(resultCode==Activity.RESULT_CANCELED){
				Toast.makeText(this, "��ҵ�", Toast.LENGTH_SHORT).show();
			}
			break;
		}		
	}
}

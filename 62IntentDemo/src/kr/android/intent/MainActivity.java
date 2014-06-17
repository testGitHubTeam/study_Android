package kr.android.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements OnClickListener{

	int[] buttons ={R.id.btnCall, R.id.btnDial, R.id.btnMap, R.id.btnMessage, R.id.btnSetting, R.id.btnWeb};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//�̺�Ʈ�ҽ��� �̺�Ʈ������ ����
		for(int button: buttons){
			Button btn = (Button)findViewById(button);
			btn.setOnClickListener(this);
		}
	}

	//�̺�Ʈ �ڵ鷯
	@Override
	public void onClick(View v) {
		
		Intent intent = null;
		
		if(v.getId()==R.id.btnWeb){				//�������� ǥ��
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));			
		}else if(v.getId()==R.id.btnCall){		//��ȭ����
			//���� ��� �ʿ�: <uses-permission android:name="android.permission.CALL_PHONE"/>
			intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-3287-6767"));
			
		}else if(v.getId()==R.id.btnDial){		//���̾�ǥ��
			intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-1212-3434"));
		
		}else if(v.getId()==R.id.btnMap){		//���� ǥ��
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Seoul"));
			
		}else if(v.getId()==R.id.btnMessage){	//�޽��� ǥ��
			intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:010-3434-4545"));
			
		}else if(v.getId()==R.id.btnSetting){	//���� ȣ��
			intent = new Intent("android.settings.SETTINGS");	
			
		}
				
		startActivity(intent);
	}
}

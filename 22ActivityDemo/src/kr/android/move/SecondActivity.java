package kr.android.move;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity{
	
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȭ�鿡 ������ ������ ���� �����ϴ� �Լ�
		setContentView(R.layout.second_main);
		
		tv = (TextView)findViewById(R.id.textView1);		
		
		//������ ��������
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		
		if(bundle== null){	//�����͸� ������ �� �ִ� ���鰴ü�� null�̸� �����Ͱ� ������ �ǹ�		
			tv.append("\n\n ����� �����Ͱ� �����ϴ�!");
		}else{
			String msg = bundle.getString("msg");
			tv.append("\n\n" + msg);
		}
	}	
}

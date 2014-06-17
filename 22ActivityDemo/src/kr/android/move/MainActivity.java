/* ȭ�� �̵�

����Ʈ ����:
��Ƽ��Ƽ�� ��Ƽ��Ƽ ȣ��(�Ű�����)
� ����� ������ ���� �����ϱ� ���� ���

AndroidManifest.xml
��Ƽ��Ƽ �߰��� ���⿡ �ݵ�� ���
-android:versionCode="1"	���ۿ��� �������� ��������
-android:versionName="1.0" 	�Ϲ� ����ڿ��� �������� �������� 

*/
package kr.android.move;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;


public class MainActivity extends Activity implements View.OnClickListener{
	
	Button btnButton1, btnButton2;
	EditText etText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		
		//ID�� �ش�Ǵ� ��ü�� ������ ��ȯ
		btnButton1 = (Button)findViewById(R.id.button1);
		btnButton2 = (Button)findViewById(R.id.button2);		
		etText = (EditText)findViewById(R.id.editText1);
		
		//�̺�Ʈ �ҽ��� �̺�Ʈ ������ ����
		btnButton1.setOnClickListener(this);
		btnButton2.setOnClickListener(this);
	}

	//�̺�Ʈ �ڵ鷯
	@Override
	public void onClick(View v) {
		// ȭ�� �̵�
		// ����Ʈ ��ü ����
		Intent intent = null;
		if(v.getId()==R.id.button1){
								//���� ��Ƽ��Ƽ, �̵��� ��Ƽ��Ƽ
			intent = new Intent(this, SecondActivity.class);
		}else if(v.getId()==R.id.button2){
			intent = new Intent(this, SecondActivity.class);
			//Editable -> String
			//ȭ�� �̵��ÿ� �̵��� ȭ�鿡�� ȣ���� �����͸� ����
			intent.putExtra("msg", etText.getText().toString());
		}
		
		//����Ʈ�� �Ű������� ���� ��Ƽ��Ƽ ����
		startActivity(intent);
	}
}

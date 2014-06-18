package kr.android.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener{
	
	EditText etText;
	Button btnWrite, btnMove, btnRead;
	TextView tvView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etText = (EditText)findViewById(R.id.etText);
		btnWrite = (Button)findViewById(R.id.btnWrite);
		btnMove = (Button)findViewById(R.id.btnMove);
		btnRead = (Button)findViewById(R.id.btnRead);
		tvView = (TextView)findViewById(R.id.tvView);		
		
		btnWrite.setOnClickListener(this);
		btnMove.setOnClickListener(this);
		btnRead.setOnClickListener(this);		
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btnWrite){	//�����۷����� ������ ����
			//1.SharedPreferences��ü ȣ��
			//PreferencesEx���ϸ����� ����� ���� ���� ����
			SharedPreferences sharedPreferences = getSharedPreferences("PreferencesEx", MODE_PRIVATE);
			//2.�����۷����� �����͸� ����
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putString("text", etText.getText().toString());
			//3.������ ����
			editor.commit();
						
			//EditText�ʱ�ȭ
			etText.setText("");
			
		}else if(v.getId()==R.id.btnRead){	//�����۷��� �б�
			//1.SharedPreferences��ü ȣ��
			SharedPreferences sharedPreferences = getSharedPreferences("PreferencesEx", MODE_PRIVATE);
			//2.�����۷����� ������ �б�			
													//key, defaultValue
			tvView.setText(sharedPreferences.getString("text", ""));				
			
		}else if(v.getId()==R.id.btnMove){	//ȭ�� �̵�
			Intent intent = new Intent(this, MainTwo.class);
			startActivity(intent);			
		}
	}	
}

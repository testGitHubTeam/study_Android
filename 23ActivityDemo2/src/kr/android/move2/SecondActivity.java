package kr.android.move2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity{
	
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_main);		
		
		tv = (TextView)findViewById(R.id.tvContent);
		
		Intent intent = getIntent();
		Bundle bundle= intent.getExtras();
		
		if( bundle == null){
			tv.append("��ϵ� �����Ͱ� �����ϴ�");
		}else{	
			
			String name = bundle.getString("name");
			String korean= bundle.getString("korean");
			String english= bundle.getString("english");
			String math = bundle.getString("math");
			
			int sum = Integer.parseInt(korean) + Integer.parseInt(english) + Integer.parseInt(math);
			int avg = sum/3;
			tv.setText("�̸�: " + name + "\n");
			tv.append("����: " + korean + "\n");
			tv.append("����: " + english + "\n");
			tv.append("����: " +  math+ "\n");
			tv.append("����: " + sum  + "\n");
			tv.append("���: " + avg + "\n");			
		}	
	}
}
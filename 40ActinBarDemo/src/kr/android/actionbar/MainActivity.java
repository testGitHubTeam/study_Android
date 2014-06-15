package kr.android.actionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//ActionBar����
		//ActionBar�� API 11���� ��밡��
		//AndroidMainifest.xml�� minSDKVersion�� 11�̻����� �����ؾ� ��
		ActionBar actionBar = getActionBar();
				
		//Ÿ��Ʋ ����
		actionBar.setTitle("ActionBar");
		
		//����Ÿ��Ʋ ����
		actionBar.setSubtitle("SubTitle of ActionBar");
		
		//Ÿ��Ʋ ������ �����ϰ� �����ܸ� ���̰� �ϱ�
		actionBar.setDisplayShowTitleEnabled(false);
		
		//ActionBar �����
		actionBar.hide();
		
		//ActionBar ���̱�
		actionBar.show();		
		
	}
}
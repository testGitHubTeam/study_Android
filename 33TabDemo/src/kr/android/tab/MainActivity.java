package kr.android.tab;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Tabhost��ü ����
		TabHost myTabHost = (TabHost)findViewById(R.id.tabhost);
		//���ξ�Ƽ��Ƽ�� ��ȣ��Ʈ ���
		myTabHost.setup();

		//ù��° Tab�۾� (�ð�)
		//TabSpec��ü ����
		//tag_analogClock�̸��� ����ũ�Ѱ� �ƴϰ� �����
		TabHost.TabSpec tabSpec = myTabHost.newTabSpec("tag_analogClock");
		tabSpec.setContent(R.id.tab1_analogClock);		
		//SDK4.0�̻󿡼��� ������ ��ǥ��
		//ȣȯ���� ���ؼ� �������� �־���
		tabSpec.setIndicator("�ð�", getResources().getDrawable(R.drawable.ic_launcher));
		//TabHost�� ���
		myTabHost.addTab(tabSpec);
		
		
		//�ι�° Tab �۾� (��ư)
		//TabSpec��ü ����
		tabSpec = myTabHost.newTabSpec("tag_button");
		tabSpec.setContent(R.id.tab2_button);
		tabSpec.setIndicator("��ư", getResources().getDrawable(R.drawable.ic_launcher));
		//TabHost�� ���
		myTabHost.addTab(tabSpec);		
		//�ʱ� ������ �� ����(default: 0)
		myTabHost.setCurrentTab(0);
		//myTabHost.setCurrentTab(1);
		
	}
}

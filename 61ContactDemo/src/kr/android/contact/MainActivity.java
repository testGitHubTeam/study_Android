package kr.android.contact;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	Button btnPick, btnView;
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnPick = (Button)findViewById(R.id.btnPick);
		btnView = (Button)findViewById(R.id.btnView);		
		btnPick.setOnClickListener(this);
		btnView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btnPick){			
			
			//ȣ���� �����Ͽ� ����Ʈ ����
			//people���� �� �������� ��
			intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts/people"));
			
		}else if(v.getId()==R.id.btnView){
			//primary key�� �ش�Ǵ� 1���� people ������ ������
			//content	 : ��Ű��
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/1"));			
		}
		
		startActivity(intent);
	}	
}
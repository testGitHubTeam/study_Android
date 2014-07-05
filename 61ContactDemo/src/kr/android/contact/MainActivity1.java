
package kr.android.contact;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity1 extends Activity implements OnClickListener{

	Button btnPick, btnView;
	Intent intent;

	static final int PICK_CONTACT_REQUEST =1;
	
	String id = null;
	
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
			
			startActivityForResult(intent, PICK_CONTACT_REQUEST);
			
		}else if(v.getId()==R.id.btnView){
			//primary key�� �ش�Ǵ� 1���� people ������ ������
			//content	 : ��Ű��
			Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
			
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse(id));

			startActivity(intent);
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//startActivityForResult���� �ѱ� requestCodeȮ��
		if(requestCode == PICK_CONTACT_REQUEST){
			if( resultCode== RESULT_OK){
				// ������ ����� Uri ���ϵǸ� �ش� Uri �� �����Ͽ� ������ ������ �˴ϴ�.
				Uri contactUri = data.getData();
				
				id = contactUri.toString();
				
				Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
			}
		}
	}
}


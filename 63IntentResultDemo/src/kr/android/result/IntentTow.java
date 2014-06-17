package kr.android.result;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IntentTow extends Activity implements OnClickListener{
	
	Button btnSuccess, btnCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);
		
		btnSuccess = (Button)findViewById(R.id.btnSuccess);
		btnCancel = (Button)findViewById(R.id.btnCancel);
		
		btnSuccess.setOnClickListener(this);
		btnCancel.setOnClickListener(this);		
		
	}

	@Override
	public void onClick(View v) {
		
		if(v.getId()==R.id.btnSuccess){
			Intent intentResult = getIntent();
			intentResult.putExtra("msg", "������");
			//���� Activity���� ������ �����͸� ȣ���� Activity�� ����
			setResult(RESULT_OK, intentResult);
			//Activity ����
			finish();			
		}else if(v.getId()==R.id.btnCancel){
			//��� ��ư, ���ư ��ġ��
			setResult(RESULT_CANCELED, null);			
			finish();
		}		
	}
}

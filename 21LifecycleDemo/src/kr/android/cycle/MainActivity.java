/*
 * �����ֱ� ���º�ȭ�� ���� �� ���� �α׷� Ȯ���ϱ�
 * ---------------------------------------------------------------
 * eclipse�� �ܼ�: Console â
 * ���ȿ��� �������� �ܼ�: LogCat â
*/
package kr.android.cycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	
	public static final String TAG ="Lifecycle";

	//��Ƽ��Ƽ�� ������ ���Ŀ� ȣ��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.i(TAG, "onCreate �����");		
	}
	
	//onCreate�� ȣ��� ���� �Ǵ� onRestart�� ȣ��� ���Ŀ� ���������� ȣ��� 
	@Override
	protected void onStart(){
		//�޼ҵ� �������� �θ�޼ҵ带 ȣ���ؼ� �ʱ�ȭ�۾��� �ݵ�� �ؾ���
		super.onStart();
		Log.i(TAG, "onStart �����");
	}
	
	//��Ƽ��Ƽ�� ��׶��� ���¿��� ���׶�����·� �Ƿ��� �� ��  ȣ�� 
	@Override
	protected void onRestart(){
		super.onRestart();
		Log.i(TAG, "onRestart �����");
	}
	
	//��Ƽ��Ƽ�� ���׶��� ���°� �Ǳ� ������ ȣ��
	@Override
	protected void onResume(){
		super.onResume();
		Log.i(TAG, "onResume �����");
	}
	
	//��Ƽ��Ƽ�� ��׶��� ���°� �Ǳ� ���� ȣ��
	@Override
	protected void onPause(){
		super.onPause();
		Log.i(TAG, "onPause �����");
	}
	
	//��Ƽ��Ƽ�� ��׶��� ���°� �Ǹ� ȣ��
	@Override
	protected void onStop(){
		super.onStop();
		Log.i(TAG,  "onStop �����");
	}
	
	//��Ƽ��Ƽ�� ����� �� ȣ��
	@Override
	protected void onDestroy(){
		super.onDestroy();
		Log.i(TAG, "onDestroy �����");
	}
}

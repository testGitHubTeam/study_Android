/* Thread 
 * 
 * Thread -> Handle -> UI

*/
package kr.android.hanlder;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	
	//��ü ����
	ProgressBar progressBar;	
	//Android.os.Handler
	Handler handler = new Handler();
	boolean isRunning = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		progressBar = (ProgressBar)findViewById(R.id.pbProgress);
	}
	
	//���׶��� ������ �����
	//������� ������ ó��
	@Override
	public void  onStart(){
		super.onStart();
		
		//ProgressBar�ʱ�ȭ
		progressBar.setProgress(0);
		
		//ȭ�� �������̽��� ������ ��׶��� �۾��� ���� ������ ����
		Thread background = new Thread(new Runnable() {
			
			@Override
			public void run() {				
				//������ ������ �о���� ȿ���� �ֱ� ���� for�� ����
				try{
					for(int i=0; i<20 && isRunning; i++){
						Thread.sleep(1000);
						//������ �����͸� �о�´ٰ� ����
						//�����忡�� UI�� �����Ϸ��� Handler��ü�� �̿��ؼ� ������
						//post()��  Runnable��ü�� �μ��� �־�� ��
						handler.post(new Runnable() {
							@Override
							public void run() {
								// UI�� �����ؼ� ȭ�� ����
								progressBar.incrementProgressBy(5);							
							}
						});
					}
				}catch(Exception e){
					Log.e("�ڵ鷯 ����", e.toString());					
				}
			}
		});
		
		isRunning = true;
		background.start();
	}
	
	//������ ����
	@Override
	public void onStop(){
		super.onStop();
		
		isRunning = false;
	}
}

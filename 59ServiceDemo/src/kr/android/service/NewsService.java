package kr.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class NewsService extends Service{
	
	Handler handler = new Handler();

	//������ ����ó���� ���� ����
	boolean bQuit;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	//���� ����
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		//������ ����
		NewsThread newsThread = new NewsThread();
		newsThread.start();
		
		return super.onStartCommand(intent, flags, startId);
	}

	//���� ����
	@Override
	public void onDestroy() {
		// �θ��� onDestroy�ʱ�ȭ
		super.onDestroy();
		
		Toast.makeText(this, "���� ����", Toast.LENGTH_SHORT).show();
		
		//������ ����
		bQuit=true;
	}
	
	//������ ����: ���������� �����͸� ���۹޾Ƽ� ǥ���ϴ� ����	
	class NewsThread extends Thread{
		String[] enuNews = {
				"����� ���� �������� �������� ������ ������",
				"���� ������ ����ص� ������� �ɱ� ķ����",
				"���, ������ ������ ������ ?",
				"�̷��� ���� �Ѱ���",
				"������ ����, ȫ��, �Ϻ��� �ı�",
				"������ ���� ����",
				"������ ����� ��Ű��!"
		};

		@Override
		public void run() {

			for(int idx=0; bQuit==false; idx++){
				
				
				
				final String msg = enuNews[idx%enuNews.length];
				
				//UI�� ������ ����
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						Toast.makeText(NewsService.this, msg, Toast.LENGTH_LONG).show();
						
					}
				});
				
				//7�� ������ ������ �о����
				try{
					Thread.sleep(3000);
				}catch(Exception e){
					
				}
			}
			//super.run();
		}
	}
}

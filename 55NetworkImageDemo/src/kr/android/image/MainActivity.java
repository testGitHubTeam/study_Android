package kr.android.image;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	static final String TAG = "NetworkImageDemo";
	static final String IMAGE_URL ="http://img.naver.net/static/www/u/2013/0731/nmms_224940510.gif";
	
	ImageView imageView;
	ProgressDialog progressDialog;
	Button btnGo;	
	
	//import android.os.Handler;
	Handler handler = new Handler();	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		
		imageView = (ImageView)findViewById(R.id.ivImage);
		btnGo = (Button)findViewById(R.id.btnCall);
		btnGo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				progressDialog = ProgressDialog.show(MainActivity.this, "������ ������", "�̹��� �ҷ����� ��");		
				
				processData();
			}
		});		
	}
	
	//�����带 �����ؼ� ������ ����
	public void processData(){
		//thread��ü ����
		new Thread(){
			@Override
			public void run(){
				//�о�� �̹����� InputStream�� getRemoteImage�޼ҵ忡 �����Ͽ� Bitmap���� ����
				final Bitmap iBitmap = getRemoteImage(getStreamFromURL());
				
				//UI�۾�
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						//ImageView�� Bitmap����
						imageView.setImageBitmap(iBitmap);
						//â ����
						progressDialog.dismiss();						
					}
				});				
			}
		}.start();
	}	
		

	//�о�� InputStream�� Bitmap���� ����
	public Bitmap getRemoteImage(InputStream input){
		Bitmap bitmap = null;
		
		try{
			BufferedInputStream bufferedInputStream = new BufferedInputStream(input);
			bitmap = BitmapFactory.decodeStream(bufferedInputStream);
			bufferedInputStream.close();					
			
		}catch(IOException e){
			Log.e(TAG,  "�Ľ� ���� �߻�", e);
		}

		return bitmap;
	}
	
	//������ �����ؼ� Image�� �б�
	public InputStream getStreamFromURL(){
		InputStream input = null;

		try{
			HttpClient httpClient = new DefaultHttpClient();
			//��û���
			HttpGet getMethod = new HttpGet(IMAGE_URL);

			//������ ���� ��ü
			HttpResponse httpResponse = (HttpResponse)httpClient.execute(getMethod);

			//���� ���� ó��
			HttpEntity httpEntity = httpResponse.getEntity();
			BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(httpEntity);
			input = bufferedHttpEntity.getContent();


		}catch(Exception e){
			Log.e(TAG, "��Ʈ��ũ ���� �߻�", e);			
		}

		return input;
	}
}

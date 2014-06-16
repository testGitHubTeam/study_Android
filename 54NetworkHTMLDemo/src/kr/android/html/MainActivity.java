package kr.android.html;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	static final String TAG ="NetworkHTMLDEMO";
	static final String URL ="http://www.naver.com/index.html";
	
	EditText etView;
	Button btnGo;
	ProgressDialog progressDialog;
	
	//import android.os.Handler;
	Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etView =(EditText)findViewById(R.id.etView);
		btnGo = (Button)findViewById(R.id.btnCall);
		btnGo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ���̾�α� â ǥ��
				progressDialog = ProgressDialog.show(MainActivity.this, "����Ʈ ���� ��", "��ø� ��ٷ��ּ���");
				
				processData();
			}
		});		
	}
	
	//�����带 �����ؼ� ������ �����ؼ� HTMLȣ�� �� HTML���� ���
	public void processData(){
		//�ʱ�ȭ
		//��ư�� ������ ���� �� �ֱ� ������ ���������� ������ ����� �����ֱ� ����
		etView.setText("");
		
		//������ ����
		new Thread(){
			
			@Override
			public void run(){
				//������ �����ؼ� HTMLȣ��
				//String str = getStringFromURL();
				final String str = getStringFromURL();
				
				//������ UI�� �ݿ�
				handler.post(new Runnable() {
					
					@Override
					public void run() {	//�������� UI�۾�
						// ����Ŭ�������� �������� ���� �� �����Ƿ� ������ final�ٿ��־�� ��
						etView.setText(str);						
						//â ����
						progressDialog.dismiss();
						
					}
				});
			}
		}.start();
	}
	
	//������ ������ ���ϴ� HTML�� ȣ���� �� ������ ��ȯ ����
	public String getStringFromURL(){
		String responseBody = null;
		
		try{
			
			HttpClient httpClient = new DefaultHttpClient();
			
			//�ڵ鷯�� ���� ����
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			
			//get������� �ڵ鷯�� �����Ͽ� ������ ���� ��Ī�ؼ� responseBody�� ����
												//��ûó��, ����ó��
			responseBody = httpClient.execute(new HttpGet(URL), responseHandler);
			
		}catch(Exception e){
			Log.e(TAG, "��Ʈ��ũ ����", e);			
		}		
		return responseBody;
	}
}

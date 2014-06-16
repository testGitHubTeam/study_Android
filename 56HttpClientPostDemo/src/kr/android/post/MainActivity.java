package kr.android.post;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	static final String POST_URL = "http://192.168.0.2:8080/HellowWeb/postTest.jsp";
	static final String TAG = "HttpClientPostDemo";
	
	EditText etName, etAddress;
	Button btnSend;	
	ProgressDialog progressDialog;
	
	Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etName = (EditText)findViewById(R.id.etName);
		etAddress = (EditText)findViewById(R.id.etAddress);
		btnSend = (Button)findViewById(R.id.btnSend);
				
		btnSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String name = etName.getText().toString();
				String address = etAddress.getText().toString();
				
				if(name.length()<=0){
					Toast.makeText(MainActivity.this, "�̸��Է� �ʼ�", Toast.LENGTH_SHORT).show();
					etName.requestFocus();
					return;
				}
				if(address.length()<=0){
					Toast.makeText(MainActivity.this, "�ּ��Է� �ʼ�", Toast.LENGTH_SHORT).show();
					etAddress.requestFocus();
					return;
				}
				
				progressDialog = ProgressDialog.show(MainActivity.this, "����Ʈ ������", "������ ���� ���Դϴ�.");
								
				new Thread(){
					@Override
					public void run(){
						final InputStream input = getStreamFromURL(POST_URL);
						
						//UI�۾�
						handler.post(new Runnable() {
							
							@Override
							public void run() {
								parseXML(input);								
							}
						});						
					}
				}.start();				
			}
		});	
	}
	
		//������ �����ؼ� POST������� �����͸� �����ϰ� �������� ������ ������ ó��
		public InputStream getStreamFromURL(String url){
			
			InputStream input = null;
			
			try{			
				HttpClient httpClient = new DefaultHttpClient();
				
				//POST���
//				�ڷ������� �����ϱ� ���� ArrayList ��ü�����ϰ�
//				NameValuePair ����Ͽ� key, value ������ �ڷ������� ����			
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				
				//�̸� ���
				params.add(new BasicNameValuePair("name", etName.getText().toString()));
				//�ּ� ���
				params.add(new BasicNameValuePair("address", etAddress.getText().toString()));
				//HttpPost
				HttpPost postMethod = new HttpPost(url);
				postMethod.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
				
				//������ ���� ��ü
				HttpResponse httpResponse = (HttpResponse)httpClient.execute(postMethod);					
				
				//������� ó��
				HttpEntity httpEntity = httpResponse.getEntity();
				BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(httpEntity);
				input = bufferedHttpEntity.getContent();			
				
			}catch(Exception e){
				Log.e(TAG, "��Ʈ��ũ ���� �߻�", e);
			}
			
			return input;
		}
	
		//InputStream -> DOM tree ����
		public void parseXML(InputStream input){
			try{
				//DOM�ļ� ����
				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				
				//import org.w3c.dom.Document;
				//DOM tree����
				Document doc = builder.parse(input);
				NodeList node = doc.getElementsByTagName("code");
				
				String result = node.item(0).getFirstChild().getNodeValue();
				
				//���α׷��� â ����
				progressDialog.dismiss();
				
				if(result.equals("success")){
					Toast.makeText(this, "���ۼ���", Toast.LENGTH_LONG).show();
					
					//EditText�ʱ�ȭ
					etName.setText("");;
					etAddress.setText("");
				}else{
					Toast.makeText(this, "���۽���", Toast.LENGTH_LONG).show();
				}
			}
			catch(Exception e){
				Log.e(TAG, "�Ľ� ����", e);
				progressDialog.dismiss();
				Toast.makeText(this, "�����߻�" + e, Toast.LENGTH_LONG).show();
			}		
			Log.i(TAG,"����");		
		}
}
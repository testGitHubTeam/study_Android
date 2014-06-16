package kr.android.browser2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	
	WebView wvWeb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		wvWeb = (WebView)findViewById(R.id.wvWeb);
		
		//�̺�Ʈ �����ϱ� ���� ���
		wvWeb.setWebViewClient(new MyWebClient());
		
		//�ڹٽ�ũ��Ʈ ���
		WebSettings webSettings = wvWeb.getSettings();
		webSettings.setJavaScriptEnabled(true);
		//ZOOM��� ���
		webSettings.setBuiltInZoomControls(true);
		
		//�⺻URL����
		//wvWeb.loadUrl("http://m.naver.com");		
		wvWeb.loadUrl("http://m.daum.net");
		
		//ZOOM��� Ȯ���ϱ����� PC������ URL����
		//wvWeb.loadUrl("http://www.korea.com");
	}
	
	//Web�� �̺�Ʈ ó�� �����ʿ����� �ϴ� Ŭ���� ����
	//private: ����Ŭ������ ���� ��쿡�� ��밡��
	private class MyWebClient extends WebViewClient{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url){
			//Ŭ���� ��ũ�ּҸ� WebView�� ����
			view.loadUrl(url);
			return true;
		}
	}
	
	//BackKey�� ���� �޼ҵ� ������
	//public: �Ϲ������� ����
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		//ȭ�� �����丮�� �о�鿩 ���� �������� �����ϴ��� Ȯ��
		if(keyCode==KeyEvent.KEYCODE_BACK && wvWeb.canGoBack()){
			//���� �������� �̵�
			wvWeb.goBack();
		}else if(keyCode==KeyEvent.KEYCODE_BACK && !wvWeb.canGoBack()){
			//���� �������� ���� ���
			new AlertDialog.Builder(this).setTitle("���� Ȯ��").setMessage("�����Ͻðڽ��ϱ�?").setCancelable(false).setPositiveButton("��", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					//Activity ����
					finish();					
				}				
			
			}).setNegativeButton("�ƴϿ�",new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//â �ݱ�
				}
			}).show();			
			
		}		
		return true;
	}
}

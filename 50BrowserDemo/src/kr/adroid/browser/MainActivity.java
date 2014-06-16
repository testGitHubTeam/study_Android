/* WebView

1) URL ��ũ
2) HTML ���� ���� (�������� ���)
3) HTML ���� ��� (asset������ �������� ���)
4) �ڹ� ��ũ��Ʈ ���
- �ڹ� ��ũ���� alert ��ü �ڵ� �ۼ�

*/

package kr.adroid.browser;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MainActivity extends Activity {
	
	WebView browser;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		browser =(WebView)findViewById(R.id.wvWeb);
		
		//URL ��ũ
		//browser.loadUrl("http://m.naver.com");
		
		
		//HTML ���� ����
/*		String msg ="<html><body>Hellow Android World!!!</body></html>";		
												//mimeType	:����Ÿ��
		browser.loadDataWithBaseURL(null, msg, "text/html", "UTF-8", null);*/
		
		
		//assets�� HTML���� �̿�
		browser.loadUrl("file:///android_asset/hello.html");
		
		//�ڹ� ��ũ��Ʈ ���
		browser.getSettings().setJavaScriptEnabled(true);		
		
		MyWebChromeClient myWebChromeClient = new MyWebChromeClient();		
		//�������� ���
		browser.setWebChromeClient(myWebChromeClient);
	}
	//�ڹ� ��ũ��Ʈ�� alert ��ü �ڵ� �ۼ�
	//�ڹ� ��ũ��Ʈ�� alert ���â�� ��ü�ϴ� �ȵ���̵�  ���â ����
	private class MyWebChromeClient extends WebChromeClient{
		@Override
		public boolean onJsAlert(WebView view, String url, String message, JsResult result){
			//message	: '���â'
			new AlertDialog.Builder(MainActivity.this).setTitle("���").setCancelable(false).setNeutralButton("Ȯ��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			}).show();
			
			result.confirm();
			
			return true;
		}
	}
}

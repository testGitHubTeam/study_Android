package kr.android.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class NewsRSSDetail extends Activity{
	
	String title, description, pubDate;
	WebView wvWeb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_detail);
		
		//�Ѿ�� ������ ����
		Intent intent = getIntent();
		
		title = intent.getExtras().getString("title");
		pubDate = intent.getExtras().getString("pubDate");
		description = intent.getExtras().getString("description");
		
		//img�±׿��� https:�� �����Ǿ� �־ �̹����� ȣ������ ���ϴ� �κ� ó��
		//img �±׸� �˻��ؼ� https: ���� �ϵ��� ó��
		description = description.replaceAll("img src=\"//", "img src=\"https://");
		
		//HTML���� �ۼ�
		StringBuffer text = new StringBuffer();
		text.append("<html><body><font size=4>");
		text.append(title);
		text.append("</font><hr size=2 width=100% noshade>");
		text.append(description);
		text.append("<br>�Է³�¥: ");
		text.append(pubDate);		
		text.append("</body></html>");
		
		//WebView�� ���
		wvWeb = (WebView)findViewById(R.id.wvWeb);
		WebSettings webSettings = wvWeb.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setBuiltInZoomControls(true);
		
		wvWeb.loadDataWithBaseURL(null, text.toString(), "text/html", "UTF-8", null);
	}
}

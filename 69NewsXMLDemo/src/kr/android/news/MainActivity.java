package kr.android.news;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends ListActivity {
	
	//XML���� ���
	public static final String  ARTICLE_URL = "https://news.google.co.kr/news/feeds?pz=1&cf=all&ned=kr&h1=ko&topic=e&output=rss";

	//��Ͽ� �ʿ��� ������ ���� ��ü ����
	ArrayList<MyNews> myRSS = new ArrayList<MyNews>();
	
	TextView tvTitle, tvDate, tvDescription;	
	
	Handler handler = new Handler();
	MyListAdapter adapter;
	ProgressBar progressBar;
	
	//Sun, 15 Jun 2014 10:50:18 GMT -> 2014-06-15 10:50:18
	//���� ������ ��¥�� String�����͸� -> Date �����ͷ� ����
	SimpleDateFormat origin_Format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
	SimpleDateFormat new_Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		
		progressBar = (ProgressBar)findViewById(R.id.progressBar);		
		
		adapter = new MyListAdapter(this, R.layout.news_list);
		
		//ListView�� MyListAdapter���
		setListAdapter(adapter);
		
		//������ �б�
		progressBar.setVisibility(View.VISIBLE);
		
		new Thread(){
			@Override
			public void run() {
				final InputStream inputStream = getStreamFromURL();
				
				//UI ����
				handler.post(new Runnable() {
					
					@Override
					public void run() {
							parseXML(inputStream);
							//ProgressBar ����
							progressBar.setVisibility(View.GONE);
						}
				});
				super.run();
			}			
		}.start();
	}
	
	/** 
	 * XML�Ľ�
	 * 
	 */
	private void parseXML(InputStream in){
		
		try{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			
			//XML �±׻����� ������ �����ϴ� ���� (JDK 6.0�̻���� ���)
			documentBuilderFactory.setIgnoringElementContentWhitespace(true);
			
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			//DOM Ʈ�� ����
			//import org.w3c.dom.Document;
			Document document = documentBuilder.parse(in);
			
			//��� ����
			NodeList articles = document.getElementsByTagName("item");
			for(int i=0; i<articles.getLength(); i++){
				MyNews myNews = new MyNews();
				
				//<item> �±��� �ڽ� �±� ȣ��
				NodeList article = articles.item(i).getChildNodes();
				for(int j=0; j<article.getLength(); j++){
					Node node = article.item(j);
					if(node.getNodeName().equals("title")){
						myNews.title = node.getFirstChild().getNodeValue();
					}else if(node.getNodeName().equals("pubDate")){		
						
//						myNews.pubDate = node.getFirstChild().getNodeValue();
						
						//��¥ ǥ���� ���ϴ� �������� �����ϱ�
										//String -> Date
						//import java.util.Date;						
						Date date = origin_Format.parse(node.getFirstChild().getNodeValue());
										//Date -> String
						myNews.pubDate = new_Format.format(date);
						
					}else if(node.getNodeName().equals("description")){
						myNews.description = node.getFirstChild().getNodeValue();
					}
				}
				//������ ���
				myRSS.add(myNews);
			}
			
			//�����Ϳ� ���ε� �����͸� �о�鿩 ListView ����
			adapter.notifyDataSetChanged();
			
		}catch(Exception e){
			Log.e("NewsXMLDemo", "Parsing Error", e);
		}finally{
			if( in!=null){ try{in.close();}catch(IOException e){e.printStackTrace();} }
		}
		
	}
	
	/** 
	 * �������� XML �б�
	*/
	private InputStream getStreamFromURL(){
		
		InputStream input = null;
		
		try{
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet getMethod = new HttpGet(ARTICLE_URL);
			
			//������ ���� ��ü
			HttpResponse httpResponse = (HttpResponse)httpClient.execute(getMethod);
			
			//���� ����ó��
			HttpEntity httpEntity = httpResponse.getEntity();
			BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(httpEntity);
			input = bufferedHttpEntity.getContent();
			
		}catch(Exception e){
			Log.e("NewsXMLDemo", "Network Error", e);
		}
		
		return input;
	}
	
	
	//�̺�Ʈ �ڵ鷯
	@Override
	protected void onListItemClick(ListView list, View v, int position, long id){
		Intent intent = new Intent(this, NewsRSSDetail.class);
		//������ ����
		intent.putExtra("title", myRSS.get(position).title);
		intent.putExtra("pubDate", myRSS.get(position).pubDate);
		intent.putExtra("description", myRSS.get(position).description);
		
		startActivity(intent);	
	}	
	
	
	/**
	 * Ŀ���� ������
	 *
	 */
	class MyListAdapter extends BaseAdapter{
		
		Context context;
		LayoutInflater inflater;
		//����Ʈ�� ���� XML����
		int layout;
		
		public MyListAdapter(Context context, int layout){
			this.context = context;
			this.layout = layout;
			
			//LayoutInflater����
			inflater = LayoutInflater.from(context);
		}

		//��ü �������� ���� ��ȯ
		@Override
		public int getCount() {
			
			return myRSS.size();
		}

		//���޵� �����ǰ��� �ش��ϴ� ������ ��ȯ 
		@Override
		public Object getItem(int position) {
			return myRSS.get(position).title;
		}

		//���üǰ� ��ȯ
		@Override
		public long getItemId(int position) {
			return position;
		}

		//���� ������ ��ȯ (���������� ������ �� ��ü ����)
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(convertView==null){
				//���� ��Ī ��ų���� ���̱� ������ parent, false����
				convertView = inflater.inflate(layout, parent, false);
			}
			tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
			tvTitle.setText(myRSS.get(position).title);
			
			
			tvDate =(TextView)convertView.findViewById(R.id.tvDate);
			tvDate.setText(myRSS.get(position).pubDate);
			
			return convertView;
		}
	}}

/**
 * ������ ����
 *
 */
class MyNews{
	String title;
	String pubDate;
	String description;
	
}
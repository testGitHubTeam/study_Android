package kr.android.xmlpullparser;

import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

	//����Ʈ ��ü ����
	ArrayList<String> items = new ArrayList<String>();
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try{
			//XML������ �о�鿩 XmlPullParser ��ü ����
			XmlPullParser xpp = getResources().getXml(R.xml.words);
			
			while(xpp.getEventType()!=XmlPullParser.END_DOCUMENT){
				if(xpp.getEventType()==XmlPullParser.START_TAG){
					if(xpp.getName().equals("word")){
						items.add(xpp.getAttributeValue(0));
					}
				}
				//���� �׸�(element, text��)���� �̵�
				xpp.next();				
			}			
		}catch(Exception e){
			Log.e("XMLPullParserDemo", e.toString());			
		}
		
		//Adapter ����
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		
		//ListView�� ArrayAdapter���
		setListAdapter(adapter);		
	}
}

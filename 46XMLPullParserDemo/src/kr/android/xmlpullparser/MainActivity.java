package kr.android.xmlpullparser;

import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.os.Build;

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

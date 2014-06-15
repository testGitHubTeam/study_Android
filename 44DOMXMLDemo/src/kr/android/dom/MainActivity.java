package kr.android.dom;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;


public class MainActivity extends ListActivity {
	
	//�����۵��� ���� ����Ʈ ��ü ����
	ArrayList<String> items = new ArrayList<String>();
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		// XML�о ArrayList�� ���
		InputStream in = null;				
		try{
			//XML�� �о InputStream����
			in = getResources().openRawResource(R.raw.words);
			
			//DOM �ļ� ����
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			//InputStream�� �о�鿩 DOMƮ�� ����
			Document doc = builder.parse(in);
			
			//NodeList words = doc.getDocumentElement().getElementsByTagName("word");
			//<word>�±׸� ���� ��� ����Ʈ Ȯ��
			NodeList words = doc.getElementsByTagName("word");
			
			for(int i=0; i<words.getLength(); i++){
				Element e = (Element)words.item(i);
				items.add( e.getAttribute("value") );				
			}
			
			//����� ����
			adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
			//ListView�� ����� ���
			setListAdapter(adapter);
			
			
		}catch(Exception e){
			Toast.makeText(this, "����: "+e.toString(), Toast.LENGTH_SHORT).show();
			
		}finally{
			
			if(in!=null){ try{in.close();}catch(IOException e){e.printStackTrace();} }
			
		}		
	}
}

package kr.android.dom2;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

	EditText etResult;
	Button btnParseXML;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		etResult = (EditText)findViewById(R.id.etResult);
		btnParseXML = (Button)findViewById(R.id.btnParseXML);
		btnParseXML.setOnClickListener(this);	
	}

	@Override
	public void onClick(View v) {

		InputStream in = null;
		
		try{
			in = getResources().openRawResource(R.raw.product);
			
			//DOM�ļ� ����
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			//DOMƮ�� ����
			Document doc = builder.parse(in);
			
			//StringBuffer����
			StringBuffer sb = new StringBuffer();
			
			//<item>�±׸� ���� ��� ����Ʈ Ȯ��
			NodeList orders = doc.getElementsByTagName("item");
			sb.append("�ֹ��׸�\n");			 
			
			for(int i=0; i<orders.getLength(); i++){
//				Node n1 = (Node)orders.item(i);						
//				Node n2 = n1.getFirstChild();
//				String s = n2.getNodeValue();				
				
//				Node n1 = (Node)orders.item(i);
//				String s = n1.getFirstChild().getNodeValue();
				
				String s = orders.item(i).getFirstChild().getNodeValue();				
				
				sb.append((i+1)+": " + s +"\n");
				
			}
			
			etResult.setText(sb.toString());			
			
		}catch(Exception e){
			Toast.makeText(this, "����: " + e.toString(), Toast.LENGTH_LONG).show();
			
		}finally{
			if(in!=null){ try{in.close();}catch(IOException e){e.printStackTrace();} }
		}		
	}	
}

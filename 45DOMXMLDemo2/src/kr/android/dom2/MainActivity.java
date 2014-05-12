package kr.android.dom2;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.animation.AnimatorSet.Builder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
		// TODO Auto-generated method stub
		
		InputStream in = null;
		
		//ArrayList<String> items;
		
		try{
			in = getResources().openRawResource(R.raw.product);
			
			//DOM颇辑 积己
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			//DOM飘府 积己
			Document doc = builder.parse(in);
			
			
			//StringBuffer积己
			StringBuffer sb = new StringBuffer();
			
			NodeList orders = doc.getElementsByTagName("item");
			sb.append("林巩亲格\n");			 
			
			for(int i=0; i<orders.getLength(); i++){
//				Node n1 = (Node)oders.item(i);						
//				Node n2 = n1.getFirstChild();
//				String s = n2.getNodeValue();				
				
//				Node n1 = (Node)oders.item(i);
//				String s = n1.getFirstChild().getNodeValue();
				
//				Element e = (Element)words.item(i);
//				items.add( e.getAttribute("value") );	
				
				String s = orders.item(i).getFirstChild().getNodeValue();
				
				sb.append((i+1)+": " + s +"\n");
			}
			
			etResult.setText(sb.toString());			
			
		}catch(Exception e){
			Toast.makeText(this, "抗寇: " + e.toString(), Toast.LENGTH_LONG).show();
			
		}finally{
			if(in!=null){ try{in.close();}catch(IOException e){e.printStackTrace();} }
		}		
	}	
}

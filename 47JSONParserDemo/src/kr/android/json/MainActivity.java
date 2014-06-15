package kr.android.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.AssetManager.AssetInputStream;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		text = (TextView)findViewById(R.id.tvText);
		
		//assets������ ���� ó���� ���� AssetManager ����
		AssetManager assetManager = getResources().getAssets();
		
		try{
			//JSON���� �б�
			AssetInputStream ais = (AssetInputStream)assetManager.open("sample.json");
			
			//byte��Ʈ���� ���ڽ�Ʈ������ ��ȯ
			BufferedReader br = new BufferedReader(new InputStreamReader(ais, "UTF-8"));
			
			StringBuffer sb = new StringBuffer();
			String result = null;
			
			while((result=br.readLine()) != null){
				sb.append(result);
			}
			
			//JSON������
			String msg = sb.toString();
			
			//JSONObject  ����
			JSONObject jsonObject = new JSONObject(msg);
			
			String menu=jsonObject.getString("menu");
			text.setText(menu+"\n");
			
			//JSONArray ����
			JSONArray jsonArray = new JSONArray(jsonObject.getString("member"));
			
			for( int i=0; i<jsonArray.length(); i++){
				text.append("===================\n");
				text.append(jsonArray.getJSONObject(i).getString("id")+"\n");
				text.append(jsonArray.getJSONObject(i).getString("name")+"\n");
				text.append(jsonArray.getJSONObject(i).getString("address")+"\n");
				text.append(jsonArray.getJSONObject(i).getString("job")+"\n");			
			}			
			
		}catch(Exception e){
			Log.e("JSON Deomo", e.toString());
			
		}		
	}	
}

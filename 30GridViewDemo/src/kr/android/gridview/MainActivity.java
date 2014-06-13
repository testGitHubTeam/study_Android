package kr.android.gridview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

	//�迭 ����
	String[] items={"ȣ��", "���", "������", "����", "����", "�޷�", "�ٳ���", "����", "������", "����", "�Ǹ�", "���ξ���", "�ڵ�", "�챸", "�޵�", "���ھ�", "ȣ��", "�丶��", "Ű��", "��"};
	TextView myTextView;
	GridView myGridView;
	ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myTextView = (TextView)findViewById(R.id.tvView);
		myGridView = (GridView)findViewById(R.id.gvView);
		
		//�迭�� �̿��ϴ� adapter��ü ����
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		
		//GridView�� ArrayAdapter���
		myGridView.setAdapter(adapter);
		
		//�̺�Ʈ�ҽ��� �̺�Ʈ ������ ����
		myGridView.setOnItemClickListener(this);
		
	}

	//�̺�Ʈ �ڵ鷯 ������
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// 
		myTextView.setText(items[position]);
		
	}
	
}

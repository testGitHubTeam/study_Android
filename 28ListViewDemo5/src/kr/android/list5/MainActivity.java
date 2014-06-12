
/*
 * ListActivity
ListView���� ������ ����
�̺�Ʈ �ڵ鷯�� �����Ǿ� �־� �̺�Ʈ�� ������ �ؼ� ����ϸ� ��

*/

package kr.android.list5;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {
	
	//�迭 ����
	String[] items={"�̱�", "����", "�Ϻ�", "������ī", "������", "����", "��������", "�߱�", "�Ϻ�", "����", "ȫ��", "��۶󵥽�", "�ε�", "�˷���ī"};
	TextView myTextView;
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//View����� adapter��ü ����
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		
		//ListView�� ArrayAdapter�� ���, 
		//ListActivity�� �����Ǵ� Listview�� ���� ������ ������ ������ listView�� ListActivity�� ����ID�� ��õǾ�� �ϸ�
		//����ID�� adapter�� ��ϵ�
		setListAdapter(adapter);
		
		myTextView = (TextView)findViewById(R.id.tvView);
		
	}
	
	//�̺�Ʈ �ڵ鷯 ������
	@Override
	public void onListItemClick(ListView parent, View v, int position, long id){
		myTextView.setText(items[position]);
		
	}

}

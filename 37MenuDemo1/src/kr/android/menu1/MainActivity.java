/*
 * ������ ID�� ����� ��� ListActivityŬ������ ��ӹ޾Ƽ� ���
*/
package kr.android.menu1;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {
	
	//�迭 ����
	String[] items ={"�ٳ���", "����", "��", "���", "���", "����", "��", "����", "ȣ��", "�а�", "��", "������", "����", "���ξ���", "���ھ�"};
	
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//����� ����
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		
		//ListView�� Adapter��ü ���
		setListAdapter(adapter);		
	}

	//�޴� ����
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/* Menu�� ����
		 * groupid	: �޴� ������ �׷� ����, �������� Menu.NONE=0
		 * itemid	: �޴� ������ �ο��� ID
		 * order	: �޴� �������� ǥ�õ� ����, �������� Menu.NONE=0
		 * title	: �޴� �������� ǥ�õ� �ؽ�Ʈ		
		*/
		menu.add(Menu.NONE, 1, Menu.NONE, "16px");
		menu.add(Menu.NONE, 2, Menu.NONE, "24px");
		menu.add(Menu.NONE, 3, Menu.NONE, "32px");
		menu.add(Menu.NONE, 4, Menu.NONE, "40px");
		return true;
	}

	//�̺�Ʈ �ڵ鷯
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//�޴��� Ŭ������ �� �̺�Ʈ ó��
		switch(item.getItemId()){	//����Ʈ�� �� ������ ������ ���� ������
		case 1:
			getListView().setDividerHeight(16);
			break;
		case 2:
			getListView().setDividerHeight(24);
			break;
		case 3:
			getListView().setDividerHeight(32);
			break;
		case 4:
			getListView().setDividerHeight(40);
			break;
		}
		
		return true;
	}
}

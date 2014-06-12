/* ��� �۾� */
package kr.android.list;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener{
	
	//������ ����
	String[] items = {"����", "����", "�뱸", "�λ�", "����", "��õ", "����", "����", "����", "�뿵", "����", "����", "����"};
	
	TextView text;
	ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		text = (TextView)findViewById(R.id.textView1);
		//�ܼ��� �����͸� ǥ��
		list = (ListView)findViewById(R.id.listView1);
		
		//��� �۾� �ܰ�
		//1)�迭�� �����͸� ������ �����Ƿ� �� �迭�� listview�� ��Ī�� �־�� ��
		//  1��°���� this�� ������ ���� Acitivity�� ǥ��
		//2)3��° ������ �迭�� �о�ͼ�
		//3)2��° ������ android.R.layout.simple_list_item_1 : �̹� ������� �ؽ�Ʈ�並 ����
		//4)��� �����͸� �������� (�迭�� �����͸�  ListView�� �����ϴ� �����ϴ� ������ ����)
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		//5)����Ʈ�信 ArrayAdapter ���, �����͸� ��Ī��Ŵ
		list.setAdapter(adapter);
		
		//�̺�Ʈ �ҽ��� �̺�Ʈ ������ ����
		list.setOnItemClickListener(this);
	}
										
	//AdapterView<?> parent	: ���������� ó���Ǵ� �����͸� ǥ���ϱ� ���� ?�� ���ϵ�ī���������� ǥ��(������Ʈ Ÿ���� �ǹ�)
	//						  �̺�Ʈ�� �߻��� ����Ʈ���� ����
	//View view: ����Ʈ�� �����ۺ� �������� view
	//			 ListView���� �̺�Ʈ�� �߻��� �����͸� ǥ���ϴ� View
	//int position: �ε��� ��, �����͸� ǥ���� ���� ��ġ��
	//long id: �����ǰ��� ��ġ, �����ͺ��̽� ������ primary key�� id�� ��õ�
	//�̺�Ʈ �ڵ鷯
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		text.setText(items[position]);
		
	}
}

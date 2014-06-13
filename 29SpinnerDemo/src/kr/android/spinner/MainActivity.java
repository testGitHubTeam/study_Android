/* Spinner
 * 
 * Spinner�� �θ� AdapterView
 * 
*/
package kr.android.spinner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {
	
	//�迭 ����
	String[] items = {"���ѹα�", "�̱�", "����", "�Ϻ�", "������ī", "������", "����Ʈ���ϸ���", "ĥ��", "������", "����", "��������", "�߱�", "�Ϻ�", "����", "ȫ��", "��۶󵥽�", "�ε�", "�˷���ī"};
	Spinner spinner;
	TextView myTextView;
	ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myTextView = (TextView)findViewById(R.id.tvView);
		spinner = (Spinner)findViewById(R.id.spContry);
		
		//adapter ����
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
		
		//��Ӵٿ� ȭ�鿡 ǥ���� ���ҽ� ����
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		//Spinner�� ArrayAdapter�� ���
		spinner.setAdapter(adapter);
		
		//�̺�Ʈ �ҽ��� �̺�Ʈ ������ ����
		spinner.setOnItemSelectedListener(this);
	}

	//AdapterView<?> parent: �̺�Ʈ�� �߻��� spinner��ü
	//View view: �̺�Ʈ�� �߻��� View(������ ����)
	//int position: ��ġ��
	//long id: position ���� ��ġ, DB���� ����ɼ� ����(primary key��)
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		myTextView.setText(items[position]);
	}
	
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		myTextView.setText("");
	}
}

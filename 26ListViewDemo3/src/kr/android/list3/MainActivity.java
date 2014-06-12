package kr.android.list3;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener{
	
	ArrayList<String> items;
	ArrayAdapter<String> adapter;
	ListView myListView;
	EditText myEditText;
	Button btnAdd, btnDel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//������ ����� ���� �� ������ �߰�
		items = new ArrayList<String>();
		items.add("First");
		items.add("Second");
		items.add("Third");
		
		//������ư���� ������ ��  ListView����� adapter��ü ���� 
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, items);
		
		myListView = (ListView)findViewById(R.id.lvList);
		//ListView�� ArrayAdapter���
		myListView.setAdapter(adapter);
		
		//���ø�� ����(���� ����)
		myListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		myEditText = (EditText)findViewById(R.id.etText);
		btnAdd = (Button)findViewById(R.id.btnAdd);
		btnDel = (Button)findViewById(R.id.btnDel);		
		
		//�̺�Ʈ�ҽ��� �̺�Ʈ ������ ����
		btnAdd.setOnClickListener(this);
		btnDel.setOnClickListener(this);
	}

	//�̺�Ʈ �ڵ鷯
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.btnAdd:	//������ �߰�
				String text = myEditText.getText().toString();
				if(text.length()>0){	//������ �Է½�
					//������ ����
					items.add(0, text);
					//ListView����
					adapter.notifyDataSetChanged();
					//EditText �ʱ�ȭ
					myEditText.setText("");;
				}
				//�ʼ�, ������ �Ʒ��� case������ ����Ǿ� ���� �߻�
				break;	
				
			case R.id.btnDel:	//������ ����
				//������ ������ ��������
				int id = myListView.getCheckedItemPosition();
				
				if(id !=ListView.INVALID_POSITION){	//������ ���ýÿ��� ����
					items.remove(id);										
					//RadioButton�� Ŭ���ؼ� ������ �� RadioButton�� ������ �������� �ʰ� �׸� ���� �� �ʱ�ȭ
					myListView.clearChoices();					
					adapter.notifyDataSetChanged();
				}
				
				break;
		}
	}
}

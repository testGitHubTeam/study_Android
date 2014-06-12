package kr.android.list4;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
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
	Button btnAdd, btnDel;
	EditText myEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//������ ����� ���� �� �߰�
		items = new ArrayList<String>();
		items.add("One");
		items.add("Two");
		items.add("Three");
		
		//üũ�ڽ��� ���� ������ ��  ListView����� adapter��ü ���� 
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, items);
		
		myListView = (ListView)findViewById(R.id.lvList);
		//ListView�� ArrayAdapter���
		myListView.setAdapter(adapter);
		//���ø�� ����(��Ƽ ���)
		myListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
		btnAdd = (Button)findViewById(R.id.btnAdd);
		btnDel = (Button)findViewById(R.id.btnDel);
		myEditText = (EditText)findViewById(R.id.etText);
		
		//�̺�Ʈ �ҽ��� �̺�Ʈ ������ ����
		btnAdd.setOnClickListener(this);
		btnDel.setOnClickListener(this);
	}

	//�̺�Ʈ �ڵ鷯
	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
		case R.id.btnAdd:
			String text = myEditText.getText().toString();
			
			if(text.length()>0){
				items.add(0, text);
				adapter.notifyDataSetChanged();
				myEditText.setText("");
			}
			break;			
			
		case R.id.btnDel:
			//ListView ���� ���ý� ������ position�� ���� ������ �����ϴ� ��ü ����
			SparseBooleanArray sba = myListView.getCheckedItemPositions();
			if(sba.size()>0){	//����� ���õ� ���
				//����Ʈ�� ������ �ε������� ���� �ݺ��� ����
				for(int i=myListView.getCount()-1; i>=0; i--){
					//get()�� position���� �����ϸ� true��ȯ
					if(sba.get(i)){	//������ position�� ���ϱ�
						items.remove(i);						
					}
				}				
			}
			//CheckBox �ʱ�ȭ
			myListView.clearChoices();
			//ListView �ʱ�ȭ
			adapter.notifyDataSetChanged();
			
			break;			
		}		
	}
}

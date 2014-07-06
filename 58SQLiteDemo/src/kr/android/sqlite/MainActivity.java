package kr.android.sqlite;


import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
												//import android.view.View.OnClickListener;
public class MainActivity extends ListActivity implements OnClickListener {
	
	//�����ͺ��̽� ������ ���� ��ü ����
	DatabaseAdapter databaseAdapter;
	static final String TAG = "SQLiteDemo";
	TextView tv_mCurrentID;
	EditText etEditMemo;
	//import android.widget.SimpleCursorAdapter;
	SimpleCursorAdapter simpleCursorAdapter;
	Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv_mCurrentID= (TextView)findViewById(R.id.tvId);
		etEditMemo =(EditText)findViewById(R.id.etEditMemo);

		//��ư ���
		int buttons[] = {R.id.btnAdd, R.id.btnDelete, R.id.btnModify};
		
		//Button ��ü ȣ�� �� �̺�Ʈ ����
		for(int id:buttons){
			Button button = (Button)findViewById(id);
			button.setOnClickListener(this);
		}
		
		//����, ������ư ��Ȱ��ȭ
		setEnabled(false);
	}

	
	//�����ͺ��̽� ��� �Ϸ�
	@Override
	protected void onPause(){
		super.onPause();
		//Cursor ����
		cursor.close();
		//SQLiteDatabase ����
		databaseAdapter.close();
	}
	
	
	//������ �о����
	@Override
	protected void onResume(){
		super.onResume();
		
		//DatabaseAdapter ȣ��
		databaseAdapter = new DatabaseAdapter(this);
		//SQLite Database��ü ����
		databaseAdapter.open();
		
		//��� �����͸� ���� �ִ� Ŀ�� ��ü ȣ��
		cursor = databaseAdapter.fetchAllMemo();
		
		//���̺��� �÷��� �並 ����
		String[] from = DatabaseAdapter.PROJECTION;
		int[] to = new int[]{R.id.tv_ID, R.id.tv_Cotent};
		
		//Table�� �ִ� �����͸� ��Ī��ų�� �ֵ��� �ϱ� ���� simpleCursorAdapter��ü ����
		//min SDK version�� 11�̻��̾�� ��													// SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER: �����ͺ����� ������ �� �ֵ��� ����(�ǹ�����)
		//simpleCursorAdapter = new SimpleCursorAdapter(context, layout, c, from, to, flags)
		simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.memo_row, cursor, from, to, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		
		//ListView�� SimpleCursorAdapter���
		setListAdapter(simpleCursorAdapter);
	}

	
	//�̺�Ʈ ó��
	@Override
	public void onClick(View v) {			
		
		// �Է��� ID���� DB�� INSERT
		String content = etEditMemo.getText().toString();
		String id = tv_mCurrentID.getText().toString();
		
		if(v.getId()==R.id.btnAdd){	//����
			if(content.length()!=0){
				databaseAdapter.addMemo(content);
				toastMemo(content); 	//��ġ
			}
		}else if(v.getId()==R.id.btnModify){	//����
			if(content.length()!=0){
				databaseAdapter.setMemo(id, content);
				toastMemo(content); 	//��ġ
			}			
		}else if(v.getId()==R.id.btnDelete){	//����
			databaseAdapter.deleteMemo(id);
		}
		//EditText �ʱ�ȭ
		etEditMemo.setText("");
		//TextView �ʱ�ȭ
		tv_mCurrentID.setText("ID");
		
		//����, ������ư ��Ȱ��ȭ
		setEnabled(false);
		
		//�߰��� �����͸� �о��
		cursor = databaseAdapter.fetchAllMemo();
		//SimpleCursorAdapter�� Cursor��ü�ϰ� �Ǹ� ��������� ListView �� �����ϴ� ȿ��
		simpleCursorAdapter.changeCursor(cursor);
	}
	
	
	//ListView �̺�Ʈ �ڵ鷯
	@Override
	protected void onListItemClick(ListView parent, View view, int postion, long id){		
		
		
		//TAP�� ���� ID�� Content������ ���� �ִ� ���̾ƿ� ȣ��
		LinearLayout layout   =(LinearLayout)view;
		TextView tv_Content = (TextView)layout.findViewById(R.id.tv_Cotent);
		etEditMemo.setText(tv_Content.getText());
		tv_mCurrentID.setText(Long.toString(id));
		
		//����, ������ư Ȱ��ȭ
		setEnabled(true);
		toastMemo(tv_Content.getText().toString()); 	//��ġ
		
	}
	
	
	//����, ������ư ���� ����
	private void setEnabled(boolean enabled){
		int buttons[] = {R.id.btnDelete, R.id.btnModify};
		
		for(int id: buttons){
			Button button = (Button)findViewById(id);
			button.setEnabled(enabled);
		}
	}
	
	
	//�˻��� ���� ǥ��
	private void toastMemo(String str){
		if(str.length() ==0){
			return;
		}
		String memo = databaseAdapter.searchMemo(str);
		Toast.makeText(this, memo, Toast.LENGTH_LONG).show();
	}
}
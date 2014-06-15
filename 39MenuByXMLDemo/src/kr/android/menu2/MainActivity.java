package kr.android.menu2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	}

	//�޴� ���� �� ���	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		//MenuInflater�� ������: XML���·� �Ǿ� �ִ� �޴������� �о�� ��ü�� �����ϰ� �޴��� �����
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//�޴��� �������� Ŭ�� �� �� �̺�Ʈ �߻��� ���� ó��
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
		case R.id.itSeoul:
			Toast.makeText(this, "����", Toast.LENGTH_SHORT).show();;
			return true;
		case R.id.itBusan:
			Toast.makeText(this, "�λ�", Toast.LENGTH_SHORT).show();;
			return true;			
		case R.id.itSuwon:
			Toast.makeText(this, "����", Toast.LENGTH_SHORT).show();;
			return true;			
		case R.id.itJeju:
			Toast.makeText(this, "����", Toast.LENGTH_SHORT).show();;
			return true;			
		}		
		
		return super.onOptionsItemSelected(item);
	}
}

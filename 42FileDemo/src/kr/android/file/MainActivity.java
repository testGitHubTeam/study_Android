/* ������ ���� - ���念���� ������ ����
 * 
 * Activity�� ������ �ִ� ���� �޼ҵ带 ����Ͽ� ������ ����
 * �����ֱ⿡ ���� ���� �����ϵ��� ����
 * ���念���̹Ƿ� ��� ���� ���� ȣ�Ⱑ��
 * 
*/
package kr.android.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//���ϸ� ����
	static final String NOTES = "notes.txt";
	
	EditText etEditor;
	Button btnClose;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etEditor = (EditText)findViewById(R.id.etEditor);
		btnClose = (Button)findViewById(R.id.btnClose);
		
		//�̺�Ʈ ó��
		btnClose.setOnClickListener(new OnClickListener() {
			
			//�̺�Ʈ �ڵ鷯
			@Override
			public void onClick(View v) {
				//Activity ����
				finish();
			}
		});		
	}
	
	//���Ϸ� ���� Data�� �о��
	@Override
	public void onResume(){
		//�ʱ�ȭ ��Ų������ �ݵ�� �۾��� �־�� ��
		super.onResume();
		
		InputStream in = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		
		//���� ������ ���� ���� ó��
		try{
			//���Ϸ� ���� �����͸� �о InputStream ���� ��ȯ
			//������ �������� �ʴ� ��� null��ȯ �� FileNotFoundException�߻�
			in = openFileInput(NOTES);			
			
			if(in !=null){	//������ �����ϴ� ���
				//���� ��Ʈ������ ��ȯ
				inputStreamReader = new InputStreamReader(in);
				reader = new BufferedReader(inputStreamReader);

				String str;
				StringBuffer buf = new StringBuffer();				
				 while( (str=reader.readLine()) != null){
					 buf.append(str + '\n');
				 }				 
				 etEditor.setText(buf.toString());				
			}			
		}catch(FileNotFoundException e){
			//���ʾ� ����ÿ��� ������ �������� �ʱ� ������ 1ȸ ���ܰ� �߻���
			//�ƹ�ó�� ����
		}catch(Exception e){
			//�׿� ���ܽ� ���ܳ��� �˷���
			Toast.makeText(this, "����: " + e.toString(), Toast.LENGTH_SHORT).show();
		}finally{
			if( reader!=null ){ try{reader.close();}catch(IOException e){e.printStackTrace();} }
			if( inputStreamReader!=null ){ try{inputStreamReader.close();}catch(IOException e){e.printStackTrace();} }
			if( in!=null ){ try{in.close();}catch(IOException e){e.printStackTrace();} }
		}
	}
	
	//���Ͽ� Data�� ������
	@Override
	public void onPause(){
		super.onPause();
		
		OutputStreamWriter out = null;
		
		try{
			//���� ����(ByteStream�� ���ڽ�Ʈ������ ��ȯ), 
			//���� ������ ������� �Ӽ� ����(
			//	MODE_PRIVATE: �����
			//	MODE_APPEND	: �̾��)			
			out = new OutputStreamWriter(openFileOutput(NOTES, MODE_PRIVATE));
			out.write(etEditor.getText().toString());
			
			Toast.makeText(this, "�����͸� �����մϴ�!",  Toast.LENGTH_SHORT).show();
			
		}catch(Exception e){
			e.printStackTrace();			
		}finally{
			if( out!=null){ try{out.close();}catch(IOException e){e.printStackTrace();} }
			
		}
	}	
}

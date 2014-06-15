package kr.android.file2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//���ϸ� ����
	String filename = "test" + System.currentTimeMillis() +".txt";
	
	//SDī�� ��� ����
	File sdcard_path = Environment.getExternalStorageDirectory();
	
	TextView tvOutput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvOutput =(TextView)findViewById(R.id.tvOutput);
		
		//���� ���� ȣ��
		writeFileToSDCard();
		
		//���� �б� ȣ��
		readFileFromSDCard();		
		
	}
	
	//���� ����
	private void writeFileToSDCard(){
		tvOutput.setText("[���Ͼ���]\n");
		
		//SDCard�� �����ϰ� ������ ������ �� �ִ��� ���� üũ
		if(sdcard_path.exists() && sdcard_path.canWrite()){
			//���� ����
			//���� ������ ���� ��� ����
			File uadDir = new File(sdcard_path.getAbsolutePath() + "/filetest");
			//����ϰ��� �ϴ� ����(���丮) ����
			uadDir.mkdir();
			
			//���� ����
			FileOutputStream fos = null;
			try{
				fos = new FileOutputStream(uadDir.getAbsolutePath()+"/"+ filename);
				
				String msg="SDī���� ���� ����";
				//String -> byte[]
				fos.write(msg.getBytes());
				tvOutput.append("������ �����Ǿ����ϴ�.\n");
			}catch(Exception e){
				Toast.makeText(this, "����: "+ e.toString(), Toast.LENGTH_SHORT).show();
				
			}finally{
				if(fos!=null){ try{fos.close();}catch(Exception e){e.printStackTrace();} }
			}			
		}
	}
	
	//���Ϸκ��� ������ �б�
	private void readFileFromSDCard(){
		
		tvOutput.append("=====================\n");
		tvOutput.append("[�����б�]\n");
		File rFile = new File(sdcard_path.getAbsolutePath() + "/filetest/"+filename);
		
		if(rFile.canRead()){
			FileInputStream fis = null;
			
			try{
				fis = new FileInputStream(rFile);
				byte[] reader = new byte[fis.available()];
				fis.read(reader);
				
				//byte[] -> String
				tvOutput.append(new String(reader));				
				
			}catch(IOException e){
				Toast.makeText(this, "����: "+e.toString(), Toast.LENGTH_SHORT).show();
			}finally{
				if(fis!=null){ try{fis.close();}catch(IOException e){e.printStackTrace();} }
			}
		}		
	}
}

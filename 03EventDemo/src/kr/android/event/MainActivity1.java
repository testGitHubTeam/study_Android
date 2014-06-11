/*
 * 
 * layout�� ������ ��ư�� �о�ͼ� 
 * �����Ͻð����ʸ� ǥ���ϱ�
 *   
 * ��¥ ǥ�� ���� ���� SimpleDateFormat("yyyy-MM-dd a hh:mm:ss")
 *  
*/
package kr.android.event;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity1 extends Activity {
	
	Button btn;
	//��¥ ǥ�� ���� ����
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btn = (Button) findViewById(R.id.button1);  
        updateTime();
    }
    
    //���� ��¥�� �ð�
    private void updateTime(){
    	//Date -> String
    	//�⺻ ��¥ ǥ���������� ���
    	//btn.setText(new Date().toString());
    	//��¥ ǥ�� ���� �����Ͽ� ���
    	btn.setText(sf.format(new Date()));
    }

}

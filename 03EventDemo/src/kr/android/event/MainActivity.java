/* Button, �̺�Ʈ ó�� - ���� �������̽�
 * 
 * layout�� ������ ��ư�� �о�ͼ� 
 * �����Ͻð����ʸ� ǥ���ϱ�
 *   
 * ��¥ ǥ�� ���� ���� SimpleDateFormat("yyyy-MM-dd a hh:mm:ss")
 * 
 * �̺�Ʈ ����
 * - ���� �������̽� ���
 * import android.view.view; view.view�ȿ� �ִ� �������̽� ���
 * implement
 * 
*/
package kr.android.event;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import android.view.View;

//implements View.OnClickListener�Ͽ� �̺�Ʈ ó�� ��ü ����
public class MainActivity extends Activity implements View.OnClickListener{
	
	
	Button btn;
	//��¥ ǥ�� ���� ����
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //findViewById()�޼ҵ带 �̿��� �ش� ID�� ��ü ����
        btn = (Button) findViewById(R.id.button1);
        
        //��ư �̺�Ʈ �ҽ��� �̺�Ʈ ������ ����
        btn.setOnClickListener(this);
        
        updateTime();
    }
    
    //���� ��¥�� �ð�
    private void updateTime(){
    	//Date -> String
    	//btn.setText(new Date().toString());
    	btn.setText(sf.format(new Date()));
    }

    //�̺�Ʈ �ڵ鷯
	@Override			//�̺�Ʈ�� �߻��� �̺�Ʈ �ҽ�	(cf. JAVA:�̺�Ʈ ��ü)
	public void onClick(View v) {
		//Ŭ���� �� ���� ���Ӱ� �ð��� �о Button�� text�� �����Ͽ� ǥ��
		updateTime();		
	}
}

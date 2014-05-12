/*
 * 
 * layout에 생성한 버튼을 읽어와서 
 * 연월일시간분초를 표시하기
 *   
 * 날짜 표현 형식 설정 SimpleDateFormat("yyyy-MM-dd a hh:mm:ss")
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
	//날짜 표현 형식 설정
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btn = (Button) findViewById(R.id.button1);  
        updateTime();
    }
    
    private void updateTime(){
    	//Date -> String
    	//btn.setText(new Date().toString());
    	btn.setText(sf.format(new Date()));
    }

}

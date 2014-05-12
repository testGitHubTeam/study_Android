/* 이벤트 처리 - 익명 내부 클래스 사용

버튼 좌우 크기 늘리기
-wrap_content: 글을 중심으로 상대적으로 감싸게 됨
-match_parent: 모니터와 매치

익명 내부 클래스로 클릭 이벤트 처리
*/
package kr.android.event2;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends Activity {
	
	Button btn;
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.button1);
        
        //이벤트 소스와 이벤트 리스너 연결하면서 익명내부 클래스 구현
        //OnClickListener인터페이스를 구현한 기본 View생성
        btn.setOnClickListener(new View.OnClickListener() {
        	//이벤트 핸들러
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateTime();
			}
		});
    }
    
    private void updateTime(){
    	btn.setText(sf.format(new Date()));
    }
}

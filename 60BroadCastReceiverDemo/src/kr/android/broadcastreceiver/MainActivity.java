/*
 * 
 * 테스트 방법:
 * 1) 최초 실행 후 백버튼으로 꺼버림(프로그램 중지시킴)
 * 
*/
package kr.android.broadcastreceiver;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}
}

/* ImageView

- 자동으로 종,회비율 맞춰줌

pride.jpg
영문과 숫자만 사용, 소문자만 사용(구분을 용이하게 하기 위해)

pride.jpg
pride.png
안드로이드는 pride로만 상수로 등록하게 되어 jpg와 png를 구분하지 못해 충돌하게됨
pride1.jpg
pride2.png
형태로 파일명을 구분해 주어야 함

영역별 R class 호출 방법:
java: 	R.drawable.pride
xml:	@drawable/pride

*/
package kr.android.image;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        
    }
}
/* ImageView

- �ڵ����� ��,ȸ���� ������

southkorea.jpg
������ ���ڸ� ���, �ҹ��ڸ� ���(������ �����ϰ� �ϱ� ����)

southkorea.jpg
southkorea.png
�ȵ���̵�� southkorea�θ� ����� ����ϰ� �Ǿ� jpg�� png�� �������� ���� �浹�ϰԵ�
southkorea1.jpg
southkorea2.png
���·� ���ϸ��� ������ �־�� ��

������ R class ȣ�� ���:
java: 	R.drawable.southkorea
xml:	@drawable/southkorea

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
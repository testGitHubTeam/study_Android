package kr.android.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView1 extends View{
	//Activity���� Ŀ���� �並 ����ϱ� ����
	//Context��ü �ϳ��� �Ķ���ͷ� ������ ������ ����
	public MyView1(Context context){
		super(context);
	}
	
	//XML�� element�� ������ �ݵ�� �Ʒ� ������ ����
	public MyView1(Context context, AttributeSet attributeSet){
		super(context, attributeSet);
	}
	
	//�׸� �׸���
	public void onDraw(Canvas canvas){
		//��׶��� ����
		canvas.drawColor(Color.BLACK);
		
	}

}

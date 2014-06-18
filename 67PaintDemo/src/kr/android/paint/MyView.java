package kr.android.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View{
	//Activity���� Ŀ���� �並 ����ϱ� ����
	//Context��ü �ϳ��� �Ķ���ͷ� ������ ������ ����
	public MyView(Context context){
		super(context);
	}
	
	//XML�� element�� ������ �ݵ�� �Ʒ� ������ ����
	public MyView(Context context, AttributeSet attributeSet){
		super(context, attributeSet);
	}
	
	//�׸� �׸���
	//onDraw()�޼ҵ�� �䰡 ȭ�鿡 ���÷��̵� �� �ڵ����� ȣ��˴ϴ�.
	public void onDraw(Canvas canvas){
		//��׶��� ���� ����
		canvas.drawColor(Color.BLACK);
		//�׸��� �ɼ� ����
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		
		//drawLine
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(10);
					//����x, ����y, ��x, ��y, Paint��ü
		canvas.drawLine(50, 0, 50, 100, paint);
		
		paint.setColor(Color.RED);
		paint.setStrokeWidth(5);
		for(int y=30, alpha=255; alpha>2; alpha-=50, y+=10){
			//���İ� ����
			paint.setAlpha(alpha);
			//�׸���
			canvas.drawLine(0, y, 100, y, paint);			
		}
		
		//drawRect
		paint.setColor(Color.WHITE);
		//�׵θ� ���� �׸���
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(1);
				//����, ���, ������, �ϴ�, Paint
		canvas.drawRect(120, 10, 120+80, 10+80, paint);
		
		paint.setColor(Color.MAGENTA);
		paint.setStyle(Paint.Style.FILL);
		canvas.drawRect(220, 10, 220+80, 10+80, paint);
		
		//drawArc
		paint.setColor(Color.MAGENTA);
					//
		canvas.drawArc(new RectF(150,120,150+100, 120+100), 	//���� ����
				0, 			//���۰���
				50, 		//������
				true, 		//�߽ɻ�뿩��
				paint);
		
		//drawOval
		paint.setColor(Color.YELLOW);
		canvas.drawOval(new RectF(20, 250, 20+100, 250+50), paint);
		
		//drawRoundRect
		paint.setColor(Color.GREEN);
		canvas.drawRoundRect(new RectF(150, 250, 150+100, 250+50), 
				10, 10,	//�𼭸��� �ձ� ���� 
				paint);
		
		//drawPath
		paint.setColor(Color.BLUE);
		//�׵θ��� �׸���
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(2);		
		//Path����
		Path path = new Path();
		path.moveTo(0, 0);
		path.lineTo(30, 60);
		path.lineTo(-30, 60);
		//�׸� ��ǥ������ �Ϸ�Ǹ� close( )
		path.close();
		//���� ������ ����� ���� ��ġ
		path.offset(150, 340);
		canvas.drawPath(path, paint);
		
	}

}

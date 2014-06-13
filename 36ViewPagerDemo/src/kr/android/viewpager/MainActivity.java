package kr.android.viewpager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//���� ����
	Button btnYellow, btnPink, btnRed;
	ViewPager myViewPager;
	int[] btn ={R.id.btnP, R.id.btnR, R.id.btnY};
	
	//�̺�Ʈ ó��
	private View.OnClickListener myListener = new View.OnClickListener() {
		//�̺�Ʈ �ڵ鷯
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.btnY:			
				myViewPager.setCurrentItem(0);
				break;
			case R.id.btnP:
				myViewPager.setCurrentItem(1);
				break;
			case R.id.btnR:
				myViewPager.setCurrentItem(2);
				break;
			
			case R.id.btnYellow:				
			case R.id.btnPink:				
			case R.id.btnRed:
				String text = ((Button)v).getText().toString();
				Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();					
			}			
		}
	};	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//��ư�� �о�ͼ� �ٷ� �̺�Ʈ�� ����
		for(int id:btn){
			findViewById(id).setOnClickListener(myListener);			
		}
		
		//ViewPager ����
		myViewPager = (ViewPager)findViewById(R.id.vpView);
		
		//ViewPager�� ����� Ŭ������ ���
		myViewPager.setAdapter(new MyPagerAdapter(this));
	}
	
	//�߻�Ŭ������ PagerAdapter����Ͽ� Ŀ��������������� ����
	private class MyPagerAdapter extends PagerAdapter{		
		//XML�� �о�鿩�� XML�� ǥ�õ� Ŭ���� ������ ���� ��ü�� �������ִ� ��ü ����
		LayoutInflater myLayoutInflater;
		
		public MyPagerAdapter(Context context){
			myLayoutInflater = LayoutInflater.from(context);
		}
		
		//ViewPager���� ����� View��ü ����/��� �ϵ��� instantiateItem() ������		
		@Override
		public Object instantiateItem(View pager, int position){			
			View v = null;
			switch(position){
			case 0:
				
				//������ XML������ �о�鿩 ��ü�� �����ϰ� XML�� ������ �ִ� View�� ��ȯ
				v = myLayoutInflater.inflate(R.layout.page_yellow, null);
				//RelativeLayout�� ������ü�� Button�� ȣ���Ͽ� �̺�Ʈ ����				
				v.findViewById(R.id.btnYellow).setOnClickListener(myListener);
				break;
			case 1:
				v = myLayoutInflater.inflate(R.layout.page_pink,  null);
				v.findViewById(R.id.btnPink).setOnClickListener(myListener);
				break;
			case 2:
				v= myLayoutInflater.inflate(R.layout.page_red,  null);
				v.findViewById(R.id.btnRed).setOnClickListener(myListener);
				break;			
			}
			
			//������ View��ü ���
			((ViewPager)pager).addView(v, 0);	//XML���� ������ view, index
			
			return v;
		}
		
		//View ��ü ����
		@Override
		public void destroyItem(View pager, int position, Object object){
			
			((ViewPager)pager).removeView((View)object);
			
		}
		
		//���� PagerAdapter���� ������ ������ ������ ��ȯ
		@Override
		public int getCount() {			
			return btn.length;
		}

		//instantiateItem�޼��忡�� ������ ��ü�� �̿��� �������� üũ
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view==object;
		}		
	}	
}

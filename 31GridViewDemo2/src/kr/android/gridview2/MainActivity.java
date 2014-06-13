package kr.android.gridview2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	//�̹��� ������ �޴� �迭
	Integer[] images = {R.drawable.austria, R.drawable.belgium, R.drawable.bulgaria, R.drawable.cyprus, R.drawable.czech_republic, R.drawable.denmark, R.drawable.estonia, R.drawable.finland, R.drawable.france, R.drawable.germany,
			R.drawable.greece, R.drawable.hungary, R.drawable.ireland, R.drawable.italy, R.drawable.latvia, R.drawable.lithuania, R.drawable.luxembourg,
			R.drawable.malta, R.drawable.netherlands, R.drawable.poland, R.drawable.portugal, R.drawable.romania, R.drawable.slovakia,
			R.drawable.slovenia, R.drawable.spain, R.drawable.sweden, R.drawable.united_kingdom, R.drawable.austria, R.drawable.belgium, R.drawable.bulgaria, R.drawable.cyprus, R.drawable.czech_republic, R.drawable.denmark, R.drawable.estonia, R.drawable.finland, R.drawable.france, R.drawable.germany,
			R.drawable.greece, R.drawable.hungary, R.drawable.ireland, R.drawable.italy, R.drawable.latvia, R.drawable.lithuania, R.drawable.luxembourg,
			R.drawable.malta, R.drawable.netherlands, R.drawable.poland, R.drawable.portugal, R.drawable.romania, R.drawable.slovakia,
			R.drawable.slovenia, R.drawable.spain, R.drawable.sweden, R.drawable.united_kingdom, R.drawable.austria, R.drawable.belgium, R.drawable.bulgaria, R.drawable.cyprus, R.drawable.czech_republic, R.drawable.denmark, R.drawable.estonia, R.drawable.finland, R.drawable.france, R.drawable.germany,
			R.drawable.greece, R.drawable.hungary, R.drawable.ireland, R.drawable.italy, R.drawable.latvia, R.drawable.lithuania, R.drawable.luxembourg,
			R.drawable.malta, R.drawable.netherlands, R.drawable.poland, R.drawable.portugal, R.drawable.romania, R.drawable.slovakia,
			R.drawable.slovenia, R.drawable.spain, R.drawable.sweden, R.drawable.united_kingdom};

	GridView myGridView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
		myGridView = (GridView)findViewById(R.id.gvView);
		
		//GridView�� �̹����� �����ֱ� ���� ImageAdapter��ü�� ���
		myGridView.setAdapter(new ImageAdapter(this));		

		
	}
	//�߻� Ŭ���� BaseAdapter�� ����Ͽ� GridView�� �̹����� ������ �� �ִ� ��� �����ϴ� ���� Ŭ���� ����
	public class ImageAdapter extends BaseAdapter{

		//���� ����
		//Activity�� �θ� Context
		private Context context;
		
		//���� �����ڿ��� �׻� Context��ü�� ���޵Ǿ�� �մϴ�.
		public ImageAdapter(Context context){
			this.context = context;
		}		
		
		//��ü �������� ����
		@Override
		public int getCount() {						
			return images.length;
		}

		//���޵� �����ǰ��� �ش��ϴ� ������ ��ȯ 
		@Override
		public Object getItem(int position) {
			return images[position];
		}
		
		@Override
		public long getItemId(int position) {
			//���üǰ� ��ȯ
			return position;
		}

		//���� ������ ��ȯ (���������� ������ �� ��ü ����)
		//int position: ��ġ��
		//View convertView: ���� �̹����� ���� �� �ִ� ��(ǥ�� �� ��)
		//ViewGroup parent: �θ�׷�, GridView��ü �ǹ�		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//������ �� �ִ� ImageView�� �ִٸ� ImageView�� ��Ȱ���ϰ� 
			//������ ImageView�� ������ ��ü �����Ͽ� �۾� ����  
			ImageView myImageView = (ImageView)convertView;			
			if(convertView == null){	//������ ImageView�� ������, ������ ���
				myImageView = new ImageView(context);
			}
			
			//ImageView�� ũ��� �̹��� ũ�� �׸��� ���� ����
			myImageView.setLayoutParams(new GridView.LayoutParams(80,80));			
			myImageView.setPadding(10,  10, 10, 10);
			//�̹����� ���ڷ� �Ѿ�� position�� �°� ����
			myImageView.setImageResource(images[position]);			
			
			return myImageView;
		}		
	}
}

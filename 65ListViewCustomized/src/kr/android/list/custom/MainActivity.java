package kr.android.list.custom;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	ArrayList<MyItem> myItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//������ ���� �� ����
		myItems = new ArrayList<MyItem>();
		myItems.add(new MyItem(R.drawable.ic_launcher, "Sony VAIO"));
		myItems.add(new MyItem(R.drawable.ic_launcher, "IMB ThinkPad"));
		myItems.add(new MyItem(R.drawable.ic_launcher, "Apple MacBookpro"));
		
		//
		MyListAdapter myListAdapter = new MyListAdapter(this, R.layout.widget_icontext, myItems);
		
		//ListVIew�� ��Ī
		ListView lvList = (ListView)findViewById(R.id.lvList);
		lvList.setAdapter(myListAdapter);
	}
	
	//Ŀ���� ������ Ŭ����
	class MyListAdapter extends BaseAdapter{
		
		//����Ʈ ������ ó���ϱ� ���� ����Ʈ ���� ����
		Context context;
		ArrayList<MyItem> myItems;
		int layout;
		LayoutInflater inflater;
		
		public MyListAdapter(Context context, int layout, ArrayList<MyItem> myItems){
			this.context = context;
			this.layout = layout;
			this.myItems = myItems;
			
			//ListView���� ����� View�� ������ XML�� �о���� ����
			inflater = LayoutInflater.from(context);
		}		

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return myItems.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return myItems.get(position).name;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			final int pos = position;
			
			if(convertView==null){
				convertView = inflater.inflate(layout, null);
			}
			
			//
			ImageView ivIcon = (ImageView)convertView.findViewById(R.id.ivIcon);
			ivIcon.setImageResource(myItems.get(position).icon);
			TextView tvName =(TextView)convertView.findViewById(R.id.tvName);
			tvName.setText(myItems.get(position).name);
			Button btnSelect = (Button)convertView.findViewById(R.id.btnSelect);
			btnSelect.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//��ǰ���� �о����
					String str = myItems.get(pos).name + "�� �ֹ��մϴ�";
					
					Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
				}
			});
			
			return convertView;
		}		
	}

	//����Ʈ�信 ����� �׸� 
	class MyItem{
		int icon;
		String name;
		public MyItem(int icon, String name){
			this.icon = icon;
			this.name = name;
		}
	}
}
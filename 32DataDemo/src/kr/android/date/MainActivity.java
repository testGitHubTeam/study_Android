package kr.android.date;

import java.text.DateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends Activity implements OnClickListener{
	
	//Calendar��ü����
	Calendar dateAndTime = Calendar.getInstance();
	
	//�⺻ ���� ����
	DateFormat dfDateNadTime = DateFormat.getDateTimeInstance();
	TextView tvDateAndTime;
	Button btnDateSelect, btnTimeSelect;	
	
	//DatePickerDiaglog ���� �߻��ϴ� �̺�Ʈ�� ó���ϴ� ��ü ����
	DatePickerDialog.OnDateSetListener datepickerListener = new DatePickerDialog.OnDateSetListener() {

		//�̺�Ʈ �ڵ鷯
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			// DatePickerDialog���� ������ ��,��,���� Calendar��ü�� ����
			dateAndTime.set(Calendar.YEAR, year);
			dateAndTime.set(Calendar.MONTH, monthOfYear);
			dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			
			//������ ������ �ݿ��ǵ��� �޼ҵ� ȣ��
			updateDateAndTime();
		}
	};
	
	//TimePickerDialog ���� �߻��ϴ� �̺�Ʈ�� ó���ϴ� ��ü ����
	TimePickerDialog.OnTimeSetListener timepickerListener = new TimePickerDialog.OnTimeSetListener() {
		
		//�̺�Ʈ �ڵ鷯
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TimePickerDialog���� ������ ��,���� Calendar��ü�� ����
			dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
			dateAndTime.set(Calendar.MINUTE, minute);
			
			//������ ������ �ݿ��ǵ��� �޼ҵ� ȣ��
			updateDateAndTime();			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvDateAndTime = (TextView)findViewById(R.id.tvText);
		btnDateSelect = (Button)findViewById(R.id.btnDateSelect);
		btnTimeSelect = (Button)findViewById(R.id.btnTimeSelect);		
		
		//�̺�Ʈ�ҽ��� �̺�Ʈ ������ ����
		btnDateSelect.setOnClickListener(this);
		btnTimeSelect.setOnClickListener(this);
		
		//�ʱⵥ���� ����
		updateDateAndTime();
	}
	
	//��¥�� �ð� ǥ�� �޼ҵ�
	private void updateDateAndTime(){
		//���� �����ؼ�, TextView�� ǥ��
		tvDateAndTime.setText(dfDateNadTime.format(dateAndTime.getTime()));
	}
	
	//�̺�Ʈ �ڵ鷯
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btnDateSelect){	//��¥ ����
			new DatePickerDialog(this, datepickerListener, dateAndTime.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH), dateAndTime.get(Calendar.DAY_OF_MONTH)).show();		
			
		}else if(v.getId()==R.id.btnTimeSelect){	//�ð� ����
			new TimePickerDialog(this, timepickerListener, dateAndTime.get(Calendar.HOUR_OF_DAY), dateAndTime.get(Calendar.MINUTE), true).show();
		}
	}
}

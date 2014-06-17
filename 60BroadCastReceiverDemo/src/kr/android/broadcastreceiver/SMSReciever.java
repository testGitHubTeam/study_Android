package kr.android.broadcastreceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Note;
import android.telephony.SmsMessage;
import android.widget.Toast;


public class SMSReciever extends BroadcastReceiver{

	//�˸�â�� ��������� �˸�â�� �����Ҽ� �ִ� ID����
	private static final int NOTIFY_ID =1234;
	
	//��ε�ĳ��Ʈ �޽��� ���Ž� �ڵ� ȣ��Ǵ� onReceive() ������
	@Override
	public void onReceive(Context context, Intent intent) {
		//context	: �������̼��� ������ ���� 
		//intent	: �޽����� ������ ����
		
		Bundle bundle = intent.getExtras();
		
		//import android.telephony.SmsMessage;
		SmsMessage[] smsMessages = null;
		String address ="";
		String content ="";
		
		if(bundle!=null){
			//PDU: SMS�޼����� �������
			Object[] pdus =(Object[])bundle.get("pdus");
			
			//������ Object(pdu)��ŭ SmsMessage��ü ����
			smsMessages = new SmsMessage[pdus.length];
			
			for(int i=0;i< smsMessages.length;i++){
				//Object �迭(pdu)�� ����ִ� �޽����� byte[]�� ĳ�����Ͽ� SmsMesage��ü�� ���
				smsMessages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
				//��ȭ��ȣ ����
				address += smsMessages[i].getOriginatingAddress();
				//�޽��� ����
				content += smsMessages[i].getMessageBody();
				content += "\n";				
			}
			Toast.makeText(context, address + ":"+content, Toast.LENGTH_SHORT).show();
			//Notification Status bar �� ���
			addNotificationStatusBar(context, content, content);
		}		
	}	
	
	//Notification Status Bar�� ǥ�õ� Notification ����
	//Context: ���ø����̼��� ������ ����
	private void addNotificationStatusBar(Context context, String address, String message){
		//1. NotificationManager ���
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		//2. Notification.Builder ��ü ����
		Notification.Builder builder = new Notification.Builder(context);
		
		//�˸� �޽��� Ŭ���� �̵��� Activity����
		Intent intent = new Intent(context, NotificationMessage.class);
		
		//Activity ������ �ִ� ���� �ƴϹǷ�  Intent�� ���� ������ �� ���� �빮�� PendingIntent�� �����ؾ� ��
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
		/* ��밡���� flags:
		 * FLAG_CANCEL_CURRENT	: ������ ������ PendingIntent�� ����ϰ�, ���Ӱ� �ϳ��� ����
		 * FLAG_NO_CREATE		: ���� ������ PendingIntent�� ��ȯ
		 * FLAG_ONE_SHOT		: �� �÷��׸� �̿��� ������ PendingIntent�� �� �ѹ� �ۿ� ���� �� ����
		 * FLAG_UPDATE_CURRENT	: ���� �̹� ������ PendingIntent�� �����Ѵٸ� �ش� intent�� ������ ����	
		*/
		//�˸�â �ֻ���� ������ ���� �������� �˸��޽��� ���� ��ܿ� ǥ��		
		builder.setTicker(address + " : " + message);
		//�˸��޽����� Ŭ���ؼ� Activity�� ȣ���ϸ� �ڵ����� �˸��޽��� ����(true:����, false:������)
		builder.setAutoCancel(true);
		//�˸��޽��� ������ ����
		builder.setSmallIcon(R.drawable.ic_launcher);
		//�˸��޽��� ����Ʈ �׸��� �ּ� ǥ��
		builder.setContentTitle(address);
		//�˸��޽��� ����Ʈ�� ���� ����
		builder.setContentText(message);
		//�̵��� Activity������ ���� �մ� PendingIntent��� 
		builder.setContentIntent(pendingIntent);
		
		//������ �˸��޽�����  ID�� �ο��ؼ� NotificationManager�� ���
													//minSDK version 16���� ����
		notificationManager.notify(NOTIFY_ID, builder.build());
	}
}

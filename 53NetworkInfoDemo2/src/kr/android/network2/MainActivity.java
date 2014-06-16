package kr.android.network2;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView tvInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvInfo = (TextView)findViewById(R.id.tvInfo);
		
		//��Ʈ��ũ ������ ������ؼ� ConnectivityManagerȣ��
		ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		
		//WiFi���� ȣ��
		NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		boolean isWiFiAvailable = networkInfo.isAvailable();
		boolean isWiFiConn = networkInfo.isConnected();
		
		tvInfo.append("WiFi ���� ����: " + isWiFiAvailable + "\n");
		tvInfo.append("WiFi ���� ����: " + isWiFiConn + "\n");
		tvInfo.append("\n");
		
		//����� ���� ȣ��
		networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		boolean isMobileAvailable = networkInfo.isAvailable();
		boolean isMobileconn = networkInfo.isConnected();
		tvInfo.append("����� ���� ����: " + isMobileAvailable + "\n");
		tvInfo.append("����� ���� ����: " + isMobileconn + "\n");		
	}
}

package kr.android.video;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends ActionBarActivity {
	
	VideoView vwVideo;
	File sd_path = Environment.getExternalStorageDirectory();
	final String VIDEO_path = "http://192.168.0.2:8080/HellowWeb/movie02.mp4";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		vwVideo = (VideoView)findViewById(R.id.vwVideo);
		vwVideo.requestFocus();
		//�̵�� ���ѷ� ���� �� ���
		vwVideo.setMediaController(new MediaController(this));
		
		try{
			
			//raw�� ������ ���念���� ����
			//rawToFile(this, R.raw.movie01, "movie01.mp4");
			
			//���念���� ������ ȣ��
			//�ܺο��� ȣ���� �� �ֵ��� ��� ����
			String path = getFilesDir().getAbsolutePath()+"/movie01.mp4";
			//VideoView��ü���� ����� ������ ����
			vwVideo.setVideoPath(path);
			Toast.makeText(MainActivity.this, getFilesDir().getAbsolutePath().toString(), Toast.LENGTH_LONG).show();
						
			
			/*
			//SDī�忡�� ������ ȣ��
			vwVideo.setVideoPath(sd_path.getAbsolutePath()+ "/movie03.mp4");
			Toast.makeText(MainActivity.this, sd_path.getAbsolutePath().toString(), Toast.LENGTH_LONG).show();
			*/
			
			
			/*
			//�������� ������ ȣ��			
			//VideoView��ü���� ����� ������ ����
			vwVideo.setVideoURI(Uri.parse(VIDEO_path));
			Toast.makeText(MainActivity.this, VIDEO_path, Toast.LENGTH_LONG).show();
			*/
			
		}catch(Exception e){
			Log.e("VideoDemo", "Play Error", e);
		}		
	}
	
	//raw �������� ������ ȣ���ؼ� InputStream���� ����
	private void rawToFile(Context context, int resID, String fileName) throws Exception{
		InputStream inputStream = context.getResources().openRawResource(resID);
		
		intoFile(getApplication(), inputStream, fileName);
	}
	
	//InputStream�� ���念���� ���Ϸ� ����
	private void intoFile(Context context, InputStream inputStream, String fileName) throws Exception{
		int count;
		byte[] size = new byte[1024];
		OutputStream outputStream = null;
		
		try{
			//MODE_WORLD_READABLE: ��θ� �����ؼ� ���� ���� ����
			outputStream = openFileOutput(fileName, MODE_WORLD_READABLE);
			
			while(true){
				count = inputStream.read(size);
				if(count<=0) break;
				outputStream.write(size, 0, count);
			}
		}catch(Exception e){
			Log.e("VideoDemo", "IO Error", e);
		}finally{
			if(outputStream!=null){ try{outputStream.close();}catch(IOException e){e.printStackTrace();} }
			if(inputStream!=null){ try{inputStream.close();}catch(IOException e){e.printStackTrace();} }
		}
	}
}

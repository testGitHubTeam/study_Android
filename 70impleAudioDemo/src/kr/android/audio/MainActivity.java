package kr.android.audio;

import java.io.File;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements OnClickListener{

	MediaPlayer mediaPlayer;
	Button btnPlayLocal, btnPlayServer, btnPlaySDCard, btnPause, btnReplay;
	int playbackPosition = 0;
	
	File sd_path = Environment.getExternalStorageDirectory();
	final String AUDIO_PATH ="http://192.168.0.20:8080/web/girlgeneration.mp3";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnPlayLocal =(Button)findViewById(R.id.btnPlayLocal);
		btnPlayServer =(Button)findViewById(R.id.btnPlayServer);
		btnPlaySDCard =(Button)findViewById(R.id.btnPlaySDCard);		
		btnPause =(Button)findViewById(R.id.btnPause);
		btnReplay =(Button)findViewById(R.id.btnReplay);
		
		btnPlayLocal.setOnClickListener(this);
		btnPlayServer.setOnClickListener(this);
		btnPlaySDCard.setOnClickListener(this);
		btnPause.setOnClickListener(this);
		btnReplay.setOnClickListener(this);	
	}
	
	//서버에서 데이터 받기
	private void playAudioServer(String url) throws Exception{
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setDataSource(url);
		mediaPlayer.prepare();
		mediaPlayer.start();		
	}
	
	//내장되어 있는 데이터 호출
	private void playAudioLocal() throws Exception{
		mediaPlayer = MediaPlayer.create(this, R.raw.girlgeneration);
		mediaPlayer.start();
	}
	
	//SDCard에서 데이터 호출
	private void playAudioSD() throws Exception{
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setDataSource(sd_path.getAbsolutePath()+"/"+ "2n1.mp3");
//		mediaPlayer.setDataSource(sd_path.getAbsolutePath()+"/Music/"+ "girlgeneration.mp3");
		mediaPlayer.prepare();
		mediaPlayer.start();		
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		killMediaPlayer();
	}

	//MediaPlayer 자원 정리
	private void killMediaPlayer(){
		if(mediaPlayer!=null){
			try{
				mediaPlayer.release();
			}catch(Exception e){
				Log.e("SimpleAudio", "Release Error", e);
			}
		}
	}



	@Override
	public void onClick(View v) {

		if(v.getId()==R.id.btnPlayLocal){			//재생시작
			//내장된 데이터 호출
			try {
				mediaPlayer.stop();				
				playAudioLocal();		
				
			} catch (Exception e) {
				Log.e("SimpleAudio", "Play Error", e);
			}
		}else if(v.getId()==R.id.btnPlaySDCard){
			//SDCard의 데이터 호출
			try {
				mediaPlayer.stop();				
				playAudioSD();				
			} catch (Exception e) {
				Log.e("SimpleAudio", "Play Error", e);
			}
		
		}else if(v.getId()==R.id.btnPlayServer){			
			//서버에서 데이터 호출			
			try {
				mediaPlayer.stop();
				playAudioServer(AUDIO_PATH);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(v.getId()==R.id.btnReplay){	//재생 재개
			if(mediaPlayer!=null && !mediaPlayer.isPlaying()){
				//중지시점의 재생 위치로 이동
				mediaPlayer.seekTo(playbackPosition);
				//재생
				mediaPlayer.start();				
			}			
			
		}else if(v.getId()==R.id.btnPause){		//재생 일시중지
			
			if(mediaPlayer!=null){
				//일시정지 시점의 재생 위치 저장
				playbackPosition = mediaPlayer.getCurrentPosition();
				//일시정지
				mediaPlayer.pause();				
			}			
		}
	}
}

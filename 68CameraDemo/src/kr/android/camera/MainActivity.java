package kr.android.camera;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	MyCameraSurface myCameraSurface;
	Button btnShutter;
	ImageView ivRawImage;
	File sd_path = Environment.getExternalStorageDirectory();
	
	//���� ���� ����
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myCameraSurface = (MyCameraSurface)findViewById(R.id.svPreview);		
		ivRawImage = (ImageView)findViewById(R.id.ivRawImage);
		
		btnShutter = (Button)findViewById(R.id.btnShutter);
		btnShutter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ���� �Կ��� Camera��ü�� takePickture�޼ҵ� ȣ���� ���� �̷���
				// takePicture�� ShutterCallback ���� �ϳ��� PictureCallback���� �ΰ�
				// (Raw �̹����� �ϳ��� JPEG���� ���ڵ� �̹��� �� �ϳ�)�� �Ű������� ����
				myCameraSurface.mCamera.takePicture(shutterCallback, rawCallback, picture);				
			}
		});
	}
	
	ShutterCallback shutterCallback = new ShutterCallback() {
		
		@Override
		public void onShutter() {
			// Shutter�� ���� �� �ʿ��� �۾��� ����
			// ����Ʈ�� �⺻ Shutter �� �����			
		}
	};
	
	PictureCallback rawCallback = new PictureCallback() {
		
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// Raw�̹��� �����͸� ������ �ʿ��� �۾��� ����
			// ���� ��� ���ϰ� ���� nulló����			
		}
	};
	
	
	//���� ����
	PictureCallback picture = new PictureCallback() {
		
								//byte[] data	: ��������
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			if(data!=null){
				//�Կ��� �̹��� ǥ��
				BitmapFactory.Options opts = new BitmapFactory.Options();
				
				//������ �ǵ帮�� �ʰ�, �̹��� ũ�⸸ '1/�Է¼���'�� �ٿ��� �ε���
				//��Ʈ���� �޸𸮸� �ʹ� ���� �����ϴ� ������ �ذ��ϱ� ���� �ɼ�
				//1/16�� �������ν� �ػ󵵸� ����
				opts.inSampleSize = 16;
				
				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, opts);
				ivRawImage.setImageBitmap(bitmap);
				
				//�Կ��� ������ ���Ͽ� ����
				//���ϸ� ����
															//import java.util.Date;
				String filename = simpleDateFormat.format(new Date());
				String path = sd_path.getAbsolutePath()+"/"+filename+".jpg";
				FileOutputStream fos = null;
				try{
					fos = new FileOutputStream(path);
					fos.write(data);
				}catch(Exception e){
					Toast.makeText(MainActivity.this, "���� ���� �� ���� �߻�", Toast.LENGTH_SHORT).show();
					Log.e("CameraDemo", "File Error", e);
					
					//(�߿�) ���� �߻��� �޼ҵ� �����ϱ� ���� return;
					return;
				}finally{
					
					if( fos!=null){ try{fos.close();}catch(IOException e){e.printStackTrace();} }
					
				}
				
				//�̵�� ������ �߰��� ��
				//MediaScanner�� �̿��Ͽ� �ش� ������ �̵�� DB�� �߰�
				Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
				
				Uri uri = Uri.parse("file://"+path);
				intent.setData(uri);
				
				//��ε�ĳ��Ʈ ����Ʈ�� �̿��� �ش� �̵�� ������ ���ԵǾ� ��� �������� �˷��� ��(�̵�� DB�� ���)
				sendBroadcast(intent);
				
				Toast.makeText(MainActivity.this, "���� ���� �Ϸ�", Toast.LENGTH_SHORT).show();
				myCameraSurface.mCamera.startPreview();
				
			}
		}
	};	

	
	//�޴� ���
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuItem menuItem = menu.add(0, 1, 0, "������ ����");
			
		//�޴� �̺�Ʈ ó��
		menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem menuItem) {
				
				Intent intent = new Intent(Intent.ACTION_VIEW, Media.EXTERNAL_CONTENT_URI);
				startActivity(intent);
				
				return true;
			}
		});
		return true;
	}
}	//End of MainActivity


/*
 * View�� ������ �Ǵ� ī�޶� ������� ���� ���� ȭ�� ��ȭ �Ǵ� �׷����� ���� ���� ��� SurfaceView�� ����� ó��
 * 
 * Surface�� �׷��ȹ��� (������ ����)
 * SurfaceView���� �������� ȭ�� ��� �ϱ� ���ؼ� SurfaceHolder����
 * 
 * 
 * SurfaceView			: ȭ�� ����
 * -------------------
 * Surface				: ������ ����
 * -------------------
 * SurfaceHolder		: ȭ�� ����
 * -> SurfaceHolder�� �̿��ؼ� Surface(����)�� �׸��� �׸��� SurfaceView�� �ݿ�
 * 
 * 
 * �̺�Ʈ ó��:
 * Callback Interface�� 
 * SurfaceHolder�� ���ؼ� �ۼ��� Surface�� SurfaceView�� �����ϱ� ���ؼ� 
 * Surface�� ����, ����, ���ῡ ���� �̺�Ʈ ó�� 
 * 
*/
//��ӿ����� �����ϴ� �� ����
class MyCameraSurface extends SurfaceView implements SurfaceHolder.Callback{
	
	//��ü ����
	//���ۿ� �ִ� �����͸� SurfaceView�� �����ϴ� ��ü����
	SurfaceHolder mholder;
	//import android.hardware.Camera;
	Camera mCamera;

	//�����ڸ� ���� XML�� ���
	public MyCameraSurface(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		//SurfaceHolder ��ü ���� �� SurfaceHolder�ʱ�ȭ
		mholder = getHolder();
		//SurfaceHolder�� Callback����
		mholder.addCallback(this);
		
	}
	
	//ǥ�� ������ ī�޶� �����ϰ� �̸����� ����
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//ī�޶� �ʱ�ȭ
		mCamera = Camera.open();

		//SurfaceView�� ������� �� ī�޶� ��ü�� ������ �� �̸����� ȭ������ Ȧ�� ��ü ����
		try{
			//ī�޶� ��ü Ȧ���� ����
			mCamera.setPreviewDisplay(mholder);

		}catch(IOException e){
			mCamera.release();
			mCamera = null;			
		}
	}

	//SurfaceView�� ȭ�� ũ�Ⱑ �ٲ�� ���� ���� ������ �̸����� ����
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		//ī�޶�� �̸����� �����ϴ� �̺�Ʈ ó��
		mCamera.startPreview();		
	}	

	//Surface�ı��� ī�޶� �ڿ�����
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//ī�޶�� ���� �����ϴ� �̺�Ʈ ���� ó��
		if(mCamera!=null){ 
			//View����
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;			
		} 
	}	
}


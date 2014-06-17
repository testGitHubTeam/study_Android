package kr.android.gallery;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener{	
	
	ImageView ivPhoto;
	TextView tvFileInfo;
	Button btnPhotoSelect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ivPhoto = (ImageView)findViewById(R.id.ivPhoto);
		tvFileInfo = (TextView)findViewById(R.id.tvFileInfo);
		btnPhotoSelect = (Button)findViewById(R.id.btnPhotoSelect);
		//��ư�� �̺�Ʈ �����ʿ� ����
		btnPhotoSelect.setOnClickListener(this);
		
	}

	//�̺�Ʈ �ڵ鷯
	@Override
	public void onClick(View v) {
		// �������� ȣ���ؼ� �޴����� ����� �̹����� �о����
		Intent intent = new Intent();
		//���� ���ҽ����� ��ϵ��� ACTION_GET_CONTENT �� ������ �� �ֵ��� ����
		intent.setAction(Intent.ACTION_GET_CONTENT);
		//image/jpeg, image/png, image/gif�� �̹��������� Ÿ������ ����
		intent.setType("image/*");
		startActivityForResult(intent, 0);		
	}
	
	//Activityȣ���ؼ� ������� �����͸� ���޹޴� �޼ҵ�
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data){
			super.onActivityResult(requestCode, resultCode, data);
			
			if(data!=null){
				FileOutputStream fileOutputStream =null;
				try{
					Uri uri = data.getData();
					//�̹��� �信 �̹��� �����ֱ�
														//getContentResolver()�� �Ű������� �Ͽ� uri�� ���� �����͸� ȣ���� �� �ֵ��� ���ִ� ��ü
					Bitmap image = Images.Media.getBitmap(getContentResolver(), uri);
					ivPhoto.setImageBitmap(image);
					ivPhoto.setVisibility(View.VISIBLE);
					
					//�̹��� ���� ���� �� ǥ��
					tvFileInfo.setText("========�̹��� ����===========\n");
					tvFileInfo.append("URI: " + uri.toString()+"\n");
					tvFileInfo.append("Last Path Segemnt: "+uri.getLastPathSegment()+"\n");
					
					//Cursor �����͸� �����Ͽ� �����ö����
//					uri			:'content://' scheme�� ������ ������ Content Provider�� �����մϴ�.
//					projection	: ���� �޾ƾ� �ϴ� ������ column�� �̸�����Դϴ�.
//								  null ���� �� ��� column�� �����ɴϴ�.
//					selection	:SQLite�� WHERE ������ ������ �����մϴ�.
//					selectionArgs	:selection�� arguments ���� �����մϴ�.
//					sortOrder		:SQLite�� ORDER BY�� ���� ���Ĺ���� �����մϴ�.
					Cursor cursor = getContentResolver().query(Images.Media.EXTERNAL_CONTENT_URI, null, Images.Media._ID+"=?", new String[]{uri.getLastPathSegment()}, null);
					if(cursor.moveToFirst()){
						String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(Images.Media.DATA));
						tvFileInfo.append("�����̹������: "+imagePath + "\n");
						File file = new File(imagePath);
						tvFileInfo.append("�̹��� �뷮: " + file.length()+"\n");
					}
					tvFileInfo.append("ũ��: " + image.getWidth() + "*" + image.getHeight());
					
					tvFileInfo.setVisibility(View.VISIBLE);
					
					//���õ� SD Card�� �����(\storage\sdcard\) ������ �о�鿩 ���� ���ø����̼� ���� ����(\data\data\kr.android.gallery\files\test.jpg)�� �����ϴ� ��
					fileOutputStream = openFileOutput("test.jpg",  MODE_PRIVATE);
									//����, �ø�Ƽ					
					image.compress(CompressFormat.JPEG, 100, fileOutputStream);
					
				}catch(Exception e){
					Log.e("FileSearchByGallery", "IO Error", e);					
					
				}finally{
					if(fileOutputStream!=null){ try{fileOutputStream.close();}catch(IOException e){e.printStackTrace();} };					
				}
			}
		}
}

package kr.android.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter {
	//============================상수정의 시작
	//Log 처리를 위한 TAG지정
	static final String TAG="DatabaseAdapter";
	//데이터베이스 파일 이름
	static final String DB_NAME ="daily_memo.db";
	//테이블 이름
	static final String TABLE_NAME="memo";
	//각 열(칼럼)의 이름
	static final String MEMO_ID = "_id";
	static final String MEMO_CONTENT ="content";
	//컬럼 인덱스
	static final int ID_INDEX =0;
	static final int CONTENT_INDEX =1;
	//컬럼 명세
	static final String[] PROJECTION = new String[]{MEMO_ID, MEMO_CONTENT};
	//테이블 생성 SQL
	//(주의)구문에 띄어쓰기 정확하게 작성할것
	static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("+MEMO_ID+" integer PRIMARY KEY AUTOINCREMENT, " + MEMO_CONTENT + " text NOT NULL)";
	                    
	//테이블 삭제 SQL
	static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
	//============================상수정의 끝
	
	
	//데이터베이스 연동 객체 생성
	//Connection객체과 동일한 기능을 수행
	private SQLiteDatabase db;
	
	//데이터베이스를 이용한 어플리케이션의 컨텍스트
	private Context context;
	
	public DatabaseAdapter(Context context){
		this.context = context;
	}
	
	//SQLiteDatabase 생성
	//import android.database.SQLException;
	public void open() throws SQLException{
		try{
			//getWritableDatabase() 메서드를 호출하여 SQLiteDatabase를 읽고 쓸수 있도록 처리
			db = (new DatabaseHelper(context)).getWritableDatabase();
		}catch(SQLException e){
			//getWritableDatabase() 메서드를 호출하여 SQLiteDatabase를 쓸수 없을 경우 읽기만 가능하도록 처리
			db = (new DatabaseHelper(context)).getReadableDatabase();
		}
	}
	//SQLiteDatabase 자원정리
	public void close(){
		if(db!=null){db.close();}
	}
	
	
	//목록(_id 내림차순)
	public Cursor fetchAllMemo(){
		
		return db.query(TABLE_NAME,	//테이블병 
				PROJECTION,			//컬럼 명세 
				null,			 	//WHERE 절
				null, 				//WHERE절에 전달될 데이터
				null, 				//groupBy절
				null, 				//having절
									//(주의)공백 표시
				MEMO_ID + " DESC"	//ORDER BY
				);
	}
	
	//데이터를 추가하고 추가된 데이터의 PRIMARY KEY를 반환
	public String addMemo(String content){
		ContentValues values = new ContentValues();
		values.put(MEMO_CONTENT, content);
		//PRIMARY KEY
		long id =db.insert(TABLE_NAME, null, values);
		
		if(id<0){
			return "";
		}
		return Long.toString(id);
	}
	
	//지정된 ID의 행 삭제
	public void deleteMemo(String id){
		db.delete(TABLE_NAME, 		//테이블 명
				MEMO_ID + "= ?", 	//WHERE
				new String[]{id});	//WHERE절 ?에 전달할 데이터(배열)
		
	}
	
	
	//업데이트
	public void setMemo(String id, String content){
		//업데이트 값 설정
		ContentValues values = new ContentValues();
		values.put(MEMO_CONTENT, content);
		
		//레코드 업데이트
		db.update(TABLE_NAME, 			//테이블명
				values, 				//수정할 데이터
				MEMO_ID + "=?", 		//WHERE절
				new String[]{id});		//WHERE절 ?에 전달될 PRIMARY KEY
	}
	
	//검색
	public String searchMemo(String str){
		//읽을 데이터의 조건
									//(주의)공백 주의
		String where = MEMO_CONTENT + " LIKE ?";
		//WHERE절의 ?을 대체할 매개변수
		String param = str.substring(0, 1) + "%";
		
		//검색
		Cursor cursor = db.query(TABLE_NAME, 
				PROJECTION, 
				where, 
				new String[]{param},				
				null, 
				null, 
				MEMO_ID + " DESC",	//ORDER BY, (주의)빈칸 
				"10");				//limit 최대 10개 설정
		
		StringBuffer stringBuffer = new StringBuffer();
		if(cursor.moveToFirst()){
			do{
				long id = cursor.getLong(ID_INDEX);
				String memo = cursor.getString(CONTENT_INDEX);
				
				stringBuffer.append("ID(");
				stringBuffer.append(id);
				stringBuffer.append(")");
				stringBuffer.append(memo);
				stringBuffer.append("\n");				
						
			}while(cursor.moveToNext());
		}
		//Cursor 자원정리
		cursor.close();
		
		return stringBuffer.toString();		
	}
	
	
	//SQLiteOpenHelper를 상속받아 DB생성 및 테이블 생성
	//테이블 수정, 삭제 클래스
	public class DatabaseHelper extends SQLiteOpenHelper{
		//생성자 구현해 줘고 추상메소드 구현해 주어야 함
		//DB생성해 줌
		//onCreate() 실행 후 계속 호출됨
		public DatabaseHelper(Context context){
			super(context, DB_NAME, null, 1);
	        //context	: Activity등의 Context 인스턴스 
	        //DB_NAME	: 데이터베이스의 이름 
	        //null		: 커서 팩토리(보통 null지정) 
	        //1			: 데이터베이스 스키마 버전 (디비 업데이트시 스키마 버전 업데이트 함)
		}
		
		//처음 일회 동작
		@Override
		public void onCreate(SQLiteDatabase db) {
			// 테이블 생성
			db.execSQL(CREATE_TABLE);			
		}
		
		//데이터베이스 스키마 버전 변경시 호출
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// 기본가이드, 원하는 코드로 변경
			db.execSQL(DROP_TABLE);
			onCreate(db);			
		}
	}	
}
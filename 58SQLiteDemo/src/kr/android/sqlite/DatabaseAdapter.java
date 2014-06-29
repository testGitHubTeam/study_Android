package kr.android.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter {
	//============================������� ����
	//Log ó���� ���� TAG����
	static final String TAG="DatabaseAdapter";
	//�����ͺ��̽� ���� �̸�
	static final String DB_NAME ="daily_memo.db";
	//���̺� �̸�
	static final String TABLE_NAME="memo";
	//�� ��(Į��)�� �̸�
	static final String MEMO_ID = "_id";
	static final String MEMO_CONTENT ="content";
	//�÷� �ε���
	static final int ID_INDEX =0;
	static final int CONTENT_INDEX =1;
	//�÷� ��
	static final String[] PROJECTION = new String[]{MEMO_ID, MEMO_CONTENT};
	//���̺� ���� SQL
	//(����)������ ���� ��Ȯ�ϰ� �ۼ��Ұ�
	static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("+MEMO_ID+" integer PRIMARY KEY AUTOINCREMENT, " + MEMO_CONTENT + " text NOT NULL)";
	                    
	//���̺� ���� SQL
	static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
	//============================������� ��
	
	
	//�����ͺ��̽� ���� ��ü ����
	//Connection��ü�� ������ ����� ����
	private SQLiteDatabase db;
	
	//�����ͺ��̽��� �̿��� ���ø����̼��� ���ؽ�Ʈ
	private Context context;
	
	public DatabaseAdapter(Context context){
		this.context = context;
	}
	
	//SQLiteDatabase ����
	//import android.database.SQLException;
	public void open() throws SQLException{
		try{
			//getWritableDatabase() �޼��带 ȣ���Ͽ� SQLiteDatabase�� �а� ���� �ֵ��� ó��
			db = (new DatabaseHelper(context)).getWritableDatabase();
		}catch(SQLException e){
			//getWritableDatabase() �޼��带 ȣ���Ͽ� SQLiteDatabase�� ���� ���� ��� �б⸸ �����ϵ��� ó��
			db = (new DatabaseHelper(context)).getReadableDatabase();
		}
	}
	//SQLiteDatabase �ڿ�����
	public void close(){
		if(db!=null){db.close();}
	}
	
	
	//���(_id ��������)
	public Cursor fetchAllMemo(){
		
		return db.query(TABLE_NAME,	//���̺� 
				PROJECTION,			//�÷� �� 
				null,			 	//WHERE ��
				null, 				//WHERE���� ���޵� ������
				null, 				//groupBy��
				null, 				//having��
									//(����)���� ǥ��
				MEMO_ID + " DESC"	//ORDER BY
				);
	}
	
	//�����͸� �߰��ϰ� �߰��� �������� PRIMARY KEY�� ��ȯ
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
	
	//������ ID�� �� ����
	public void deleteMemo(String id){
		db.delete(TABLE_NAME, 		//���̺� ��
				MEMO_ID + "= ?", 	//WHERE
				new String[]{id});	//WHERE�� ?�� ������ ������(�迭)
		
	}
	
	
	//������Ʈ
	public void setMemo(String id, String content){
		//������Ʈ �� ����
		ContentValues values = new ContentValues();
		values.put(MEMO_CONTENT, content);
		
		//���ڵ� ������Ʈ
		db.update(TABLE_NAME, 			//���̺��
				values, 				//������ ������
				MEMO_ID + "=?", 		//WHERE��
				new String[]{id});		//WHERE�� ?�� ���޵� PRIMARY KEY
	}
	
	//�˻�
	public String searchMemo(String str){
		//���� �������� ����
									//(����)���� ����
		String where = MEMO_CONTENT + " LIKE ?";
		//WHERE���� ?�� ��ü�� �Ű�����
		String param = str.substring(0, 1) + "%";
		
		//�˻�
		Cursor cursor = db.query(TABLE_NAME, 
				PROJECTION, 
				where, 
				new String[]{param},				
				null, 
				null, 
				MEMO_ID + " DESC",	//ORDER BY, (����)��ĭ 
				"10");				//limit �ִ� 10�� ����
		
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
		//Cursor �ڿ�����
		cursor.close();
		
		return stringBuffer.toString();		
	}
	
	
	//SQLiteOpenHelper�� ��ӹ޾� DB���� �� ���̺� ����
	//���̺� ����, ���� Ŭ����
	public class DatabaseHelper extends SQLiteOpenHelper{
		//������ ������ ��� �߻�޼ҵ� ������ �־�� ��
		//DB������ ��
		//onCreate() ���� �� ��� ȣ���
		public DatabaseHelper(Context context){
			super(context, DB_NAME, null, 1);
	        //context	: Activity���� Context �ν��Ͻ� 
	        //DB_NAME	: �����ͺ��̽��� �̸� 
	        //null		: Ŀ�� ���丮(���� null����) 
	        //1			: �����ͺ��̽� ��Ű�� ���� (��� ������Ʈ�� ��Ű�� ���� ������Ʈ ��)
		}
		
		//ó�� ��ȸ ����
		@Override
		public void onCreate(SQLiteDatabase db) {
			// ���̺� ����
			db.execSQL(CREATE_TABLE);			
		}
		
		//�����ͺ��̽� ��Ű�� ���� ����� ȣ��
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// �⺻���̵�, ���ϴ� �ڵ�� ����
			db.execSQL(DROP_TABLE);
			onCreate(db);			
		}
	}	
}
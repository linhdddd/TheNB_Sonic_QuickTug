package com.InnuendoGameLabs.QuickTug.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import com.InnuendoGameLabs.QuickTug.model.User;


public class DatabaseHandler extends SQLiteOpenHelper {



	public DatabaseHandler(Context context) {
		super(context, GlobalVariables.DATABASE_NAME, null, GlobalVariables.DATABASE_VERSION);
	} 

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_LOGIN_TABLE = "CREATE TABLE " + GlobalVariables.TABLE_LOGIN + "("
				+ GlobalVariables.KEY_ID + " TEXT PRIMARY KEY,"  //0
				+ GlobalVariables.KEY_EMAIL	+ " TEXT," 				//3
				+ GlobalVariables.KEY_CREATED_AT + " TEXT," 		//8
				+ GlobalVariables.KEY_COINS + " TEXT,"			//10
				+ GlobalVariables.KEY_GAID + " TEXT" + ")";		//17
		db.execSQL(CREATE_LOGIN_TABLE);
	}

	// Cập nhật database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Xóa table cũ
		db.execSQL("DROP TABLE IF EXISTS " + GlobalVariables.TABLE_LOGIN);
		// Khởi tạo lại database
		onCreate(db);
	}

	// Lưu trữ thông tin chi tiết ngư�?i dùng vào database

	public void addUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(GlobalVariables.KEY_ID, user.getId());
		values.put(GlobalVariables.KEY_EMAIL, user.getEmail());
		values.put(GlobalVariables.KEY_CREATED_AT, user.getCreated_at());
		values.put(GlobalVariables.KEY_COINS, user.getCoins());
		values.put(GlobalVariables.KEY_GAID, user.getGaid());
		db.insert(GlobalVariables.TABLE_LOGIN, null, values);
		db.close();
	}

	

	public User getUser() {
		User user = new User();
		String selectQuery = "SELECT * FROM " + GlobalVariables.TABLE_LOGIN;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			user.setId(cursor.getString(0));
			user.setEmail(cursor.getString(1));
			user.setCreated_at(cursor.getString(2));
			user.setCoins(cursor.getString(3));
			user.setGaid(cursor.getString(4));
		}
		db.close();
		return user;
	} 

	// Xem trạng thái đăng nhập, trả v�? số row trong table
	public int getRowCount() {
		String countQuery = "SELECT  * FROM " + GlobalVariables.TABLE_LOGIN;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int rowCount = cursor.getCount();
		db.close();
		cursor.close();
		return rowCount;
	} 

	// Thực hiện xóa tất cả row trong table
	public void resetTables() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(GlobalVariables.TABLE_LOGIN, null, null);
		db.close();
	}

}

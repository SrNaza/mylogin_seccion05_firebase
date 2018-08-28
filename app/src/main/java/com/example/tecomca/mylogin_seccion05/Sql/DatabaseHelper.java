package com.example.tecomca.mylogin_seccion05.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tecomca.mylogin_seccion05.Model.Category;
import com.example.tecomca.mylogin_seccion05.Model.User;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteAssetHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "MundoSonrisa.db";

    // Table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_CATEGORIES = "categories";

    //Table users columns
    // User
    private static final String COLUM_USER_ID = "id_user";
    private static final String COLUM_USER_NAME = "name";
    private static final String COLUM_USER_EMAIL = "email";
    private static final String COLUM_USER_PASSWORD = "password";

    // Categories
    private static final String COLUM_CATEGORY_ID = "id_category";
    private static final String COLUM_CATEGORY_NAME = "name";
    private static final String COLUM_CATEGORY_IMAGE = "image";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER
            + "("
            + COLUM_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUM_USER_NAME + " TEXT,"
            + COLUM_USER_EMAIL + " TEXT,"
            + COLUM_USER_PASSWORD + " TEXT"
            + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    @Override
//    public void onCreate(SQLiteDatabase db){
//        db.execSQL(CREATE_USER_TABLE);
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUM_USER_NAME, user.getName());
        values.put(COLUM_USER_EMAIL, user.getEmail());
        values.put(COLUM_USER_PASSWORD, user.getPassword());

        // insertar los valores de values en la base de datos
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public boolean checkUser(String email) {


        String[] columns = {
                COLUM_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUM_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password) {
        String[] columns = {
                COLUM_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUM_USER_EMAIL + " = ?" + " AND " + COLUM_USER_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public List<Category> checkImageCategories() {
        String[] columns = {
                COLUM_CATEGORY_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUM_CATEGORY_IMAGE + " = ?" + " AND " + COLUM_CATEGORY_NAME + " = ?";

        Cursor cursor = db.query(TABLE_CATEGORIES,
                columns,
                selection,
                null,
                null,
                null,
                null);
        List<Category> categories = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Category jefe = new Category();
                jefe.setName(cursor.getString(cursor.getColumnIndex("name")));
                jefe.setImagen(cursor.getBlob(cursor.getColumnIndex("imagen")));
                categories.add(jefe);
            } while (cursor.moveToNext());
        }


        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        return categories;
//        if (cursorCount > 0) {
//            return true;
//        }
//        return false;
    }


}

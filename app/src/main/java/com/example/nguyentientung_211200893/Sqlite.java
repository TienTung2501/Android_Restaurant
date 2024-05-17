package com.example.nguyentientung_211200893;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Sqlite extends SQLiteOpenHelper {
    private static final String DBName="NguyenTienTung.db";
    private static final int  VERSION=1;
    private static final String TABLENAME="NhaHang";
    private  static  String Id="_id";
    private static String Ten="ten";
    private static  String DiaChi="diachi";
    private static  String SoPhieu="sophieu";
    private static  String Diem="diem";

    private SQLiteDatabase myDB;

    public Sqlite(@Nullable Context context) {
        super(context, DBName,null, VERSION);
    }

    public static String getId() {
        return Id;
    }

    public static String getTen() {
        return Ten;
    }

    public static String getDiaChi() {
        return DiaChi;
    }

    public static String getSoPhieu() {
        return SoPhieu;
    }

    public static String getDiem() {
        return Diem;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTable = "CREATE TABLE " + TABLENAME +
                "( " + Id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Ten + " TEXT NOT NULL, " +
                DiaChi + " REAL NOT NULL, " +
                SoPhieu + " REAL NOT NULL, " +
                Diem + " INTEGER NOT NULL " + ")";
        db.execSQL(queryTable);
        ArrayList<NhaHang> list = new ArrayList<>();
        list.add(new NhaHang(1, "Sen Ho Tay", " 614 Lac Long Quan",101,8.6));
        list.add(new NhaHang(2, "Non La", " 614 Phan Boi Chau",112,8.4));
        list.add(new NhaHang(3, "Quan Ngon Ha Noi", " Nguyen DInh Chieu",101,8.2));
        list.add(new NhaHang(4, "Luc Thuy", " Cau GIay",200,7.9));
        list.add(new NhaHang(5, "Ly Club", " 614 Dong Da",107,7.6));
        list.add(new NhaHang(6, "Ly Club", " 614 Truong Trinh",121,7.6));

        for (NhaHang nhahang : list) {
            ContentValues values = new ContentValues();
            values.clear();
            values.put(Ten,nhahang.getTen());
            values.put(DiaChi, nhahang.getDiaChi());
            values.put(SoPhieu, nhahang.getSoPhieu());
            values.put(Diem, nhahang.getDiem());
            db.insert(TABLENAME, null, values);
        }



    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void openDb(){
        myDB= getWritableDatabase();
    }
    public void closeDb(){
        if(myDB !=null && myDB.isOpen()){
            myDB.close();
        }
    }
    public long Insert(String ten, String diachi,int  sophieu,double diem){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Ten,ten);
        values.put(DiaChi, diachi);
        values.put(SoPhieu, sophieu);
        values.put(Diem, diem);
        return db.insert(TABLENAME,null,values);
    }
    public Cursor DisplayAll(){
        SQLiteDatabase db = getReadableDatabase(); // Use getReadableDatabase() instead of myDB
        String query = "SELECT * FROM " + TABLENAME;
        return db.rawQuery(query, null);
    }
    public long Update(int id,String ten, String diachi,int  sophieu,double diem) {
        ContentValues values = new ContentValues();
        values.put(Ten,ten);
        values.put(DiaChi, diachi);
        values.put(SoPhieu, sophieu);
        values.put(Diem, diem);

        String where=Id+" = "+id;
        return  myDB.update(TABLENAME,values,where,null);
    }

    public long Delete(int id){
        String where=Id+ " = "+ id;
        return  myDB.delete(TABLENAME,where,null);
    }

}

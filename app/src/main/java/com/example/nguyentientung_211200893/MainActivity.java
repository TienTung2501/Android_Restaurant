package com.example.nguyentientung_211200893;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_UPDATE_CONTACT = 1;
    private static final int REQUEST_ADD_CONTACT = 2;

    public int position,idNhaHang;
    private Sqlite myDb;
    private ListView listViewNhaHang;
    private ArrayList<NhaHang> nhahangs;
    private ArrayAdapter<NhaHang> adapter;
    String titleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        eventListener();
        registerForContextMenu(listViewNhaHang);
    }
    @Override
    protected void onStart() {
        super.onStart();
        myDb.openDb();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myDb.closeDb();
    }

    private void eventListener() {
        listViewNhaHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                idNhaHang=nhahangs.get(i).getId();
                position=i;
                return false;
            }
        });
//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this, AddTaxiActivity.class);
//                startActivityForResult(intent, REQUEST_ADD_CONTACT);
//            }
//        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() ==  R.id.mnEdit) {
            Intent intent=new Intent(MainActivity.this, DanhGiaActivity.class);
            intent.putExtra("object", (Serializable) nhahangs.get(position));
            //startActivity(intent);
            startActivityForResult(intent, REQUEST_UPDATE_CONTACT);
            return true;
        }
        else if (item.getItemId() == R.id.mnDelete){

            return true;
        }
        else {
            return super.onContextItemSelected(item);
        }
    }

    private void initComponent() {
        myDb=new Sqlite(this);
        nhahangs=new ArrayList<>();
        listViewNhaHang=findViewById(R.id.ListViewNhaHang);
        displayData();

    }
    private void sortContactsByName() {
        Collections.sort(nhahangs, new Comparator<NhaHang>() {
            @Override
            public int compare(NhaHang nh1, NhaHang nh2) {
                return Double.compare(nh2.getDiem(), nh2.getDiem());
            }
        });
    }
    private void displayData(){
        nhahangs.clear();
        fetchData();
        sortContactsByName();
        adapter=new NhaHangAdapter(MainActivity.this, R.layout.nhahang_item, nhahangs);
        listViewNhaHang.setAdapter(adapter);
        adapter.notifyDataSetChanged(); // Cập nhật dữ liệu trong Adapter
    }
    private  void fetchData(){
        Cursor cursor= myDb.DisplayAll();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndexOrThrow(Sqlite.getId()));
            String ten=cursor.getString(cursor.getColumnIndexOrThrow(Sqlite.getTen()));
            String diachi=cursor.getString(cursor.getColumnIndexOrThrow(Sqlite.getDiaChi()));
            int sophieu=cursor.getInt(cursor.getColumnIndexOrThrow(Sqlite.getSoPhieu()));
            double diem=cursor.getDouble(cursor.getColumnIndexOrThrow(Sqlite.getDiem()));
            NhaHang c=new NhaHang(id,ten,diachi,sophieu,diem);
            nhahangs.add(c);
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_UPDATE_CONTACT && resultCode == RESULT_OK) {
            // Nếu dữ liệu đã được cập nhật trong InformationDetailActivity, làm mới danh sách và hiển thị lại
            displayData();
        }
        if(requestCode == REQUEST_ADD_CONTACT && resultCode == RESULT_OK){
            // If a new contact is added successfully, refresh the contact list
            displayData();
        }
    }
}
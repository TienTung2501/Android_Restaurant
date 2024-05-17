package com.example.nguyentientung_211200893;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class DanhGiaActivity extends AppCompatActivity {

    private NhaHang object;
    TextInputEditText tenTxt,diachiTxt,sophieu,diem;
    Button btnUpdate,btnBack;
    private static final int REQUEST_UPDATE_CONTACT = 1;
    Sqlite mydb;
    @Override
    protected void onStart() {
        super.onStart();
        mydb.openDb();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mydb.closeDb();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danh_gia);
        getIntentExtra();
        initComponent();
        eventListener();
    }

    private void eventListener() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sophieucu=object.getSoPhieu();
                double diemcu=object.getDiem();
                int _id=object.getId();
                String _ten= tenTxt.getText().toString();
                String _diachi= diachiTxt.getText().toString();
                double diemmoi=Double.parseDouble(diem.getText().toString());
                int _sophieu=sophieucu+1;
                double _diem= (sophieucu*diemcu+diemmoi)/(sophieucu+1);
                mydb.Update(_id,_ten,_diachi,_sophieu,_diem);
                Toast.makeText(DanhGiaActivity.this,"Update Suceess", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initComponent() {
        mydb=new Sqlite(this);
        btnUpdate=findViewById(R.id.UpdateBtn);
        btnBack=findViewById(R.id.BackBtn);
        tenTxt=findViewById(R.id.TenTxt);
        diachiTxt=findViewById(R.id.DiaChiTxt);
        sophieu=findViewById(R.id.SophieuTxt);
        diem=findViewById(R.id.DiemTxt);
        tenTxt.setClickable(false);
        diachiTxt.setClickable(false);
        sophieu.setClickable(false);
        diachiTxt.setTextInputLayoutFocusedRectEnabled(false);
        sophieu.setTextInputLayoutFocusedRectEnabled(false);
        tenTxt.setTextInputLayoutFocusedRectEnabled(false);
        tenTxt.setCursorVisible(false);
        sophieu.setCursorVisible(false);
        diachiTxt.setCursorVisible(false);
        tenTxt.setText(object.getTen());
        diachiTxt.setText(object.getDiaChi());
        sophieu.setText(String.valueOf(object.getSoPhieu()));
        diem.setText(String.valueOf(object.getDiem()));
    }

    private void getIntentExtra() {
        Intent intent = getIntent();// cần khai báo biến object ở trên
        object=(NhaHang) intent.getSerializableExtra("object");
    }
}
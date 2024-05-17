package com.example.nguyentientung_211200893;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NhaHangAdapter extends ArrayAdapter<NhaHang> {
    public NhaHangAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public NhaHangAdapter(@NonNull Context context, int resource, @NonNull List<NhaHang> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater vi=LayoutInflater.from(getContext());
            v=vi.inflate(R.layout.nhahang_item,null);// lấy từ layout ta thiết kế

        }
        NhaHang sv=getItem(position);
        if(sv!= null){

            TextView ten= v.findViewById(R.id.TenTxt);
            TextView diachi=v.findViewById(R.id.DiaChiTxt);
            TextView diem=v.findViewById(R.id.DiemTxt);

            ten.setText(String.valueOf(sv.getTen()));
            diachi.setText(String.valueOf(sv.getDiaChi()));
            diem.setText(String.valueOf(sv.getDiem()));
        }
        return v;
    }
}

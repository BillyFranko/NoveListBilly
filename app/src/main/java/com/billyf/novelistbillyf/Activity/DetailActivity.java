package com.billyf.novelistbillyf.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.billyf.novelistbillyf.R;

public class DetailActivity extends AppCompatActivity {

    private TextView tvNama, tvPenulis, tvHalaman, tvTahun, tvPenerbit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNama = findViewById(R.id.tv_nama);
        tvPenulis = findViewById(R.id.tv_penulis);
        tvHalaman = findViewById(R.id.tv_halaman);
        tvTahun = findViewById(R.id.tv_tahun);
        tvPenerbit = findViewById(R.id.tv_penerbit);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("varnama");
        String penulis = intent.getStringExtra("varpenulis");
        String halaman = intent.getStringExtra("varhalaman");
        String tahun = intent.getStringExtra("vartahun");
        String penerbit = intent.getStringExtra("varpenerbit");

        setTitle(nama);
        tvNama.setText(nama);
        tvPenulis.setText(penulis);
        tvHalaman.setText(halaman);
        tvTahun.setText(tahun);
        tvPenerbit.setText(penerbit);

    }
}
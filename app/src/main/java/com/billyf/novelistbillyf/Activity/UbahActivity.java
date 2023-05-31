package com.billyf.novelistbillyf.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.billyf.novelistbillyf.R;

public class UbahActivity extends AppCompatActivity {

    private String yId, yNama, yPenulis, yHalaman, yTahun, yPenerbit;
    private Button btnubah;
    private EditText etNama, etPenulis, etHalaman, etTahun, etPenerbit;
    private String nama,asal,halaman,tahun,penerbit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent ambil = getIntent();
        yId = ambil.getStringExtra("xId");
        yNama = ambil.getStringExtra("xNama");
        yPenulis = ambil.getStringExtra("xPenulis");
        yHalaman = ambil.getStringExtra("xHalaman");
        yTahun = ambil.getStringExtra("xTahun");
        yPenerbit = ambil.getStringExtra("xPenerbit");

        etNama = findViewById(R.id.et_nama);
        etPenulis = findViewById(R.id.et_penulis);
        etHalaman = findViewById(R.id.et_halaman);
        etTahun = findViewById(R.id.et_tahun);
        etPenerbit = findViewById(R.id.et_penerbit);

        etNama.setText(yNama);
        etPenulis.setText(yPenulis);
        etHalaman.setText(yHalaman);
        etTahun.setText(yTahun);
        etPenerbit.setText(yPenerbit);
    }
}
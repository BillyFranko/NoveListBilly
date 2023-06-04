package com.billyf.novelistbillyf.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.billyf.novelistbillyf.R;

public class DetailActivity extends AppCompatActivity {

    private TextView tvNama, tvPenulis, tvHalaman, tvTahun, tvPenerbit, tvSinopsis;
    private Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnback = findViewById(R.id.btn_back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
            }
        });

        tvNama = findViewById(R.id.tv_nama);
        tvPenulis = findViewById(R.id.tv_penulis);
        tvHalaman = findViewById(R.id.tv_halaman);
        tvTahun = findViewById(R.id.tv_tahun);
        tvPenerbit = findViewById(R.id.tv_penerbit);
        tvSinopsis =findViewById(R.id.tv_sinopsis);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("varnama");
        String penulis = intent.getStringExtra("varpenulis");
        String halaman = intent.getStringExtra("varhalaman");
        String tahun = intent.getStringExtra("vartahun");
        String penerbit = intent.getStringExtra("varpenerbit");
        String sinopsis = intent.getStringExtra("varsinopsis");

        setTitle(nama);
        tvNama.setText("Judul Novel             : " + nama);
        tvPenulis.setText("Penulis                    : " +penulis);
        tvHalaman.setText("Jumlah Halaman   : " +halaman);
        tvTahun.setText("Tahun Terbit           : " +tahun);
        tvPenerbit.setText("Penerbit                  : " +penerbit);
        tvSinopsis.setText(sinopsis);

    }
}
package com.billyf.novelistbillyf.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.billyf.novelistbillyf.API.APIRequestData;
import com.billyf.novelistbillyf.API.RetroServer;
import com.billyf.novelistbillyf.Model.ModelResponse;
import com.billyf.novelistbillyf.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {

    private String yId, yNama, yPenulis, yHalaman, yTahun, yPenerbit;
    private Button btnubah;
    private EditText etNama, etPenulis, etHalaman, etTahun, etPenerbit;
    private String nama,penulis,halaman,tahun,penerbit;

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

        btnubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                penulis = etPenulis.getText().toString();
                halaman = etHalaman.getText().toString();
                tahun = etTahun.getText().toString();
                penerbit = etPenerbit.getText().toString();

                if(nama.trim().isEmpty()){
                    etNama.setError("Nama tak boleh kosong");
                }
                else if (penulis.trim().isEmpty()){
                    etPenulis.setError("Asal tidak boleh kosong");
                }
                else if (halaman.trim().isEmpty()){
                    etHalaman.setError("Deskripsi singkat tidak boleh kosong");
                }
                else if (tahun.trim().isEmpty()){
                    etTahun.setError("Asal tidak boleh kosong");
                }
                else if (penerbit.trim().isEmpty()){
                    etPenerbit.setError("Deskripsi singkat tidak boleh kosong");
                }
                else {
                    ubahNovel();
                }

            }
        });
    }

    private void ubahNovel(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardUpdate(yId,nama,penulis,halaman,tahun,penerbit);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this, "Kode : "+kode+"Pesan : " + pesan
                        , Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server : "
                        +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
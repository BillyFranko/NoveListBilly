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

public class TambahActivity extends AppCompatActivity {

    private EditText etNama, etPenulis, etHalaman, etTahun, etPenerbit, etSinopsis;
    private Button btnSimpan, btnback;
    private String nama,penulis,penerbit,halaman, tahun, sinopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        btnback = findViewById(R.id.btn_back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TambahActivity.this, MainActivity.class));
            }
        });

        etNama = findViewById(R.id.et_nama);
        etPenulis = findViewById(R.id.et_penulis);
        etHalaman = findViewById(R.id.et_halaman);
        etTahun = findViewById(R.id.et_tahun);
        etPenerbit = findViewById(R.id.et_penerbit);
        etSinopsis = findViewById(R.id.et_sinopsis);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                penulis = etPenulis.getText().toString();
                halaman = etHalaman.getText().toString();
                tahun = etTahun.getText().toString();
                penerbit = etPenerbit.getText().toString();
                sinopsis = etSinopsis.getText().toString();

                if(nama.trim().isEmpty()){
                    etNama.setError("Nama tak boleh kosong");
                }
                else if (penulis.trim().isEmpty()){
                    etPenulis.setError("Penulis tidak boleh kosong");
                }
                else if (halaman.trim().isEmpty()){
                    etHalaman.setError("Halaman tidak boleh kosong");
                }
                else if (tahun.trim().isEmpty()){
                    etTahun.setError("Tahun tidak boleh kosong");
                }
                else if (penerbit.trim().isEmpty()){
                    etPenerbit.setError("Penerbit tidak boleh kosong");
                }
                else if (sinopsis.trim().isEmpty()){
                    etSinopsis.setError("Penerbit tidak boleh kosong");
                }
                else {
                    tambahnovel();
                }
            }
        });

    }

    private void tambahnovel(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardCreate(nama,penulis,halaman,tahun,penerbit,sinopsis);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : "+kode+"Pesan : " + pesan
                        , Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server : "
                        +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
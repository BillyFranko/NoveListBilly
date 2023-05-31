package com.billyf.novelistbillyf.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.billyf.novelistbillyf.API.APIRequestData;
import com.billyf.novelistbillyf.API.RetroServer;
import com.billyf.novelistbillyf.Adapter.AdapterNovel;
import com.billyf.novelistbillyf.Model.ModelNovel;
import com.billyf.novelistbillyf.Model.ModelResponse;
import com.billyf.novelistbillyf.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_novel;
    private FloatingActionButton fab_tambah;
    private RecyclerView.Adapter adNovel;
    private RecyclerView.LayoutManager lmNovel;
    private List<ModelNovel> ListNovel = new ArrayList<>();
    private ProgressBar pbnovel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_novel = findViewById(R.id.rv_novel);
        fab_tambah = findViewById(R.id.fab_tambah);
        pbnovel = findViewById(R.id.pb_novel);

        lmNovel = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_novel.setLayoutManager(lmNovel);

        fab_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveNovel();
    }

    public void retrieveNovel(){
        pbnovel.setVisibility(View.VISIBLE);
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardRetrieve();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                ListNovel = response.body().getData();

                adNovel = new AdapterNovel(MainActivity.this,ListNovel);
                rv_novel.setAdapter(adNovel);

                adNovel.notifyDataSetChanged();
                pbnovel.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                pbnovel.setVisibility(View.GONE);
            }
        });

        }
    }

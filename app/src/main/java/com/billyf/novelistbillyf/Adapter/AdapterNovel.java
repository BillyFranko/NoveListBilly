package com.billyf.novelistbillyf.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.billyf.novelistbillyf.API.APIRequestData;
import com.billyf.novelistbillyf.API.RetroServer;
import com.billyf.novelistbillyf.Activity.DetailActivity;
import com.billyf.novelistbillyf.Activity.MainActivity;
import com.billyf.novelistbillyf.Activity.UbahActivity;
import com.billyf.novelistbillyf.Model.ModelNovel;
import com.billyf.novelistbillyf.Model.ModelResponse;
import com.billyf.novelistbillyf.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterNovel extends RecyclerView.Adapter<AdapterNovel.VHNovel>{

    private Context ctx;
    private List<ModelNovel> ListNovel;

    public AdapterNovel(Context ctx, List<ModelNovel> listNovel) {
        this.ctx = ctx;
        ListNovel = listNovel;
    }

    @NonNull
    @Override
    public VHNovel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_novel, parent, false);
        return new VHNovel(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHNovel holder, int position) {
        ModelNovel MN = ListNovel.get(position);

        holder.tvId.setText( MN.getId());
        holder.tvNama.setText( MN.getNama());
        holder.tvPenulis.setText(MN.getPenulis());
        holder.tvHalaman.setText(MN.getHalaman());
        holder.tvTahun.setText(MN.getTahun());
        holder.tvPenerbit.setText(MN.getPenerbit());
        holder.tvSinopsis.setText(MN.getSinopsis());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = MN.getNama();
                String penulis = MN.getPenulis();
                String halaman = MN.getHalaman();
                String tahun = MN.getTahun();
                String penerbit = MN.getPenerbit();
                String sinopsis = MN.getSinopsis();

                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("varnama", nama);
                intent.putExtra("varpenulis", penulis);
                intent.putExtra("varhalaman", halaman);
                intent.putExtra("vartahun", tahun);
                intent.putExtra("varpenerbit", penerbit);
                intent.putExtra("varsinopsis", sinopsis);

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListNovel.size();
    }

    public class VHNovel extends RecyclerView.ViewHolder {
        TextView tvId, tvNama, tvPenulis, tvHalaman, tvTahun, tvPenerbit, tvSinopsis;

        public VHNovel(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvPenulis = itemView.findViewById(R.id.tv_penulis);
            tvHalaman = itemView.findViewById(R.id.tv_halaman);
            tvTahun = itemView.findViewById(R.id.tv_tahun);
            tvPenerbit = itemView.findViewById(R.id.tv_penerbit);
            tvSinopsis = itemView.findViewById(R.id.tv_sinopsis);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Kegiatan apa yang akan dilakukan?");
                    pesan.setCancelable(true);

                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            hapusNovel(tvId.getText().toString());
                            dialog.dismiss();
                        }
                    });

                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent pindah = new Intent(ctx, UbahActivity.class);
                            pindah.putExtra("xId", tvId.getText().toString());
                            pindah.putExtra("xNama", tvNama.getText().toString());
                            pindah.putExtra("xPenulis", tvPenulis.getText().toString());
                            pindah.putExtra("xHalaman", tvHalaman.getText().toString());
                            pindah.putExtra("xTahun", tvTahun.getText().toString());
                            pindah.putExtra("xPenerbit", tvPenerbit.getText().toString());
                            pindah.putExtra("xSinopsis", tvSinopsis.getText().toString());
                            ctx.startActivity(pindah);
                        }
                    });

                    pesan.show();
                    return false;
                }
            });
        }

        private void hapusNovel(String idNovel){
            APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ModelResponse> proses = ARD.ardDelete(idNovel);
            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "Kode : " + kode+ "Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                    ((MainActivity) ctx).retrieveNovel();
                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
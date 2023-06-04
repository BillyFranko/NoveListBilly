package com.billyf.novelistbillyf.API;

import com.billyf.novelistbillyf.Model.ModelNovel;
import com.billyf.novelistbillyf.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {

    @GET("retrieve.php")
    Call<ModelResponse> ardRetrieve();

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelResponse> ardCreate(
            @Field("nama") String nama,
            @Field("penulis") String penulis,
            @Field("halaman") String halaman,
            @Field("tahun") String tahun,
            @Field("penerbit") String penerbit,
            @Field("sinopsis") String sinopsis
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ModelResponse> ardUpdate(
            @Field("id") String id,
            @Field("nama") String nama,
            @Field("penulis") String penulis,
            @Field("halaman") String halaman,
            @Field("tahun") String tahun,
            @Field("penerbit") String penerbit,
            @Field("sinopsis") String sinopsis
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ModelResponse> ardDelete(
            @Field("id") String id
    );

}

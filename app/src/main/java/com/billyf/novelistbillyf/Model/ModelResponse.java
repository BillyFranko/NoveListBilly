package com.billyf.novelistbillyf.Model;

import java.util.List;

public class ModelResponse {

    private String kode,pesan;
    private List<ModelNovel> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelNovel> getData() {
        return data;
    }

}

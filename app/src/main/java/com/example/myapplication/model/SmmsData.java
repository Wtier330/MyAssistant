package com.example.myapplication.model;

import lombok.Data;
/*
*  "data": [
        {
            "file_id": 0,
            "width": 4677,
            "height": 3307,
            "filename": "luo.jpg",
            "storename": "D5VpWCKFElUsPcR.jpg",
            "size": 801933,
            "path": "/2019/12/16/D5VpWCKFElUsPcR.jpg",
            "hash": "Q6vLIbCGZojrMhO2e7BmgFuXRV",
            "created_at": 1564844329,
            "url": "https://i.loli.net/2019/12/16/D5VpWCKFElUsPcR.jpg",
            "delete": "https://sm.ms/delete/Q6vLIbCGZojrMhO2e7BmgFuXRV",
            "page": "https://sm.ms/image/D5VpWCKFElUsPcR"
        }
* */
@Data
public class SmmsData {
    public int fileId;
    public int width;
    public int height;
    public String filename;
    public String storename;
    public long size;
    public String path;
    public String hash;
    public long createdAt;
    public String url;
    public String delete;
    public String page;
}

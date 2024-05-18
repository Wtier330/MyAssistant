package com.example.myapplication.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.bean.Goods;

import java.util.List;

@Dao
public interface GoodsDao {
    @Query("SELECT * FROM tb_goods ")
    List<Goods> getAll();

    @Query("SELECT * FROM tb_goods where id = :id")
    Goods getById(int id);


    @Insert
    void insert(Goods goods);

    @Delete
    void delete(Goods goods);
}

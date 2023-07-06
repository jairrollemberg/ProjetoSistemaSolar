package com.example.escolasistemasolarnovo.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.escolasistemasolarnovo.entidades.Custos;


import java.util.List;

@Dao
public interface CustosDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insert(Custos custos);

    @Query("Select * from custos")
    public List<Custos> getAll();

    @Update
    int update(Custos custos);

    @Delete
    void delete(Custos custos);
    }

package com.example.escolasistemasolarnovo.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.escolasistemasolarnovo.entidades.Receitas;


import java.util.List;

@Dao
public interface ReceitasDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insert(Receitas receitas);

    @Query("Select * from receitas")
    public List<Receitas> getAll();

    @Update
    int update(Receitas receitas);

    @Delete
    void delete(Receitas receitas);
}
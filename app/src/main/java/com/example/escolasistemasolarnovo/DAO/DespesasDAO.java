package com.example.escolasistemasolarnovo.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.escolasistemasolarnovo.entidades.Despesas;

import java.util.List;

@Dao
public interface DespesasDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insert(Despesas despesas);

    @Query("Select * from despesas")
    public List<Despesas> getAll();

    @Update
    int update(Despesas despesas);

    @Delete
    void delete(Despesas despesas);
}
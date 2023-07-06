package com.example.escolasistemasolarnovo.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.escolasistemasolarnovo.entidades.Outras_Entradas;
import com.example.escolasistemasolarnovo.entidades.Outras_Saidas;

import java.util.List;

@Dao
public interface Outras_SaidasDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insert(Outras_Saidas outras_saidas);

    @Query("Select * from outras_saidas")
    public List<Outras_Saidas> getAll();

    @Update
    int update(Outras_Saidas outras_saidas);

    @Delete
    void delete(Outras_Saidas outras_saidas);
}

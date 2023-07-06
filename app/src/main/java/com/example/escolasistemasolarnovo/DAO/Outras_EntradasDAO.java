package com.example.escolasistemasolarnovo.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.escolasistemasolarnovo.entidades.Custos;
import com.example.escolasistemasolarnovo.entidades.Outras_Entradas;

import java.util.List;

@Dao
public interface Outras_EntradasDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insert(Outras_Entradas outras_entradas);

    @Query("Select * from outras_entradas")
    public List<Outras_Entradas> getAll();

    @Update
    int update(Outras_Entradas outras_entradas);

    @Delete
    void delete(Outras_Entradas outras_entradas);

}

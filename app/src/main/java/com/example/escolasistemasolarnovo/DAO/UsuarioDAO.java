package com.example.escolasistemasolarnovo.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.escolasistemasolarnovo.entidades.Usuario;

import java.util.List;



@Dao
public interface UsuarioDAO {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        public  long insert(Usuario usuario);

        @Query("Select * from Usuario WHERE nome=:nome AND senha=:senha")
         List<Usuario> getUser(String nome, String senha);

        @Query("Select * from usuario")
        public List<Usuario> getAll();

        @Update
         int update(Usuario usuario);

        @Delete
         void delete(Usuario usuario);
}

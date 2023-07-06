package com.example.escolasistemasolarnovo.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "usuario")
public class Usuario {

    public Usuario() {
    }
    public Usuario(int id, String nome, String cpf, String telefone, String email, String perfil, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.perfil = perfil;
        this.senha = senha;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "nome")
    public String nome;

    @ColumnInfo(name = "cpf")
    public String cpf;

    @ColumnInfo(name = "telefone")
    public String telefone;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "perfil")
    public String perfil;

    @ColumnInfo(name = "senha")
    public String senha;

    @NonNull
    public static ArrayList<Usuario> fonteDadosOriginal(int n){
        ArrayList<Usuario> lista = new ArrayList<>();
        for (int i=0; i<n; i++){
            Usuario novo = new Usuario();
            lista.add(novo);
        }
        return lista;
    }


}

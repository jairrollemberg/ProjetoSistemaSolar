package com.example.escolasistemasolarnovo.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "receitas")
public class Receitas {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "data_receita")
    public String dataReceita;

    @ColumnInfo(name = "origem_receita")
    public String origemReceita;

    @ColumnInfo(name = "valor_receita")
    public String valorReceita;

    @ColumnInfo(name = "detalhamento_receita")
    public String detalhamento_receita;

    public Receitas() {
    }
    public Receitas(int id, String dataReceita, String origemReceita, String valorReceita, String detalhamento_receita) {
        this.id = id;
        this.dataReceita = dataReceita;
        this.origemReceita = origemReceita;
        this.valorReceita = valorReceita;
        this.detalhamento_receita = detalhamento_receita;
    }

    @NonNull
    public static ArrayList<Receitas> fonteDadosOriginal(int n){
        ArrayList<Receitas> lista = new ArrayList<>();
        for (int i=0; i<n; i++){
            Receitas novo = new Receitas();
            novo.dataReceita = String.valueOf(i);
            novo.origemReceita = String.valueOf(i);
            novo.valorReceita = String.valueOf(i);
            lista.add(novo);
        }
        return lista;
    }


}

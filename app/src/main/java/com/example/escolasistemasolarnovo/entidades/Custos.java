package com.example.escolasistemasolarnovo.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "custos")
public class Custos {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "data_custo")
    public String dataCusto;

    @ColumnInfo(name = "origem_custo")
    public String origemCusto;

    @ColumnInfo(name = "valor_custo")
    public String valorCusto;

    @ColumnInfo(name = "detalhamento_custo")
    public String detalhamento_custo;

    public Custos() {
    }
    public Custos(int id, String dataCusto, String origemCusto, String valorCusto, String detalhamento_custo) {
        this.id = id;
        this.dataCusto = dataCusto;
        this.origemCusto = origemCusto;
        this.valorCusto = valorCusto;
        this.detalhamento_custo = detalhamento_custo;
    }
    @NonNull
    public static ArrayList<Custos> fonteDadosOriginal(int n){
       ArrayList<Custos> lista = new ArrayList<>();
        for (int i=0; i<n; i++){
            Custos novo = new Custos();
            novo.dataCusto = String.valueOf(i);
            novo.origemCusto = String.valueOf(i);
            novo.valorCusto = String.valueOf(i);
            lista.add(novo);
        }
        return lista;
    }


}

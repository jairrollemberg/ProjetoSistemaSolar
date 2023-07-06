package com.example.escolasistemasolarnovo.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "despesas")
public class Despesas {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "data_despesas")
    public String dataDespesas;

    @ColumnInfo(name = "origem_despesas")
    public String origemDespesas;

    @ColumnInfo(name = "valor_despesas")
    public String valorDespesas;

    @ColumnInfo(name = "detalhamento_despesas")
    public String detalhamento_despesas;

    public Despesas() {
    }
    public Despesas(int id, String dataDespesas, String origemDespesas, String valorDespesas, String detalhamento_despesas) {
        this.id = id;
        this.dataDespesas = dataDespesas;
        this.origemDespesas = origemDespesas;
        this.valorDespesas = valorDespesas;
        this.detalhamento_despesas = detalhamento_despesas;
    }

    @NonNull
    public static ArrayList<Despesas> fonteDadosOriginal(int n){
        ArrayList<Despesas> lista = new ArrayList<>();
        for (int i=0; i<n; i++){
            Despesas novo = new Despesas();
            novo.dataDespesas = String.valueOf(i);
            novo.origemDespesas = String.valueOf(i);
            novo.valorDespesas = String.valueOf(i);
            lista.add(novo);
        }
        return lista;
    }


}

package com.example.escolasistemasolarnovo.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "outras_entradas")
public class Outras_Entradas {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "data_outras_entradas")
    public String dataOutrasEntradas;

    @ColumnInfo(name = "origem_outras_entradas")
    public String origemOutrasEntradas;

    @ColumnInfo(name = "valor_outras_entradas")
    public String valorOutrasEntradas;

    @ColumnInfo(name = "detalhamento_outras_entradas")
    public String detalhamentoOutrasEntradas;

    public Outras_Entradas() {
    }

    public Outras_Entradas(int id, String dataOutrasEntradas, String origemOutrasEntradas, String valorOutrasEntradas, String detalhamentoOutrasEntradas) {
        this.id = id;
        this.dataOutrasEntradas = dataOutrasEntradas;
        this.origemOutrasEntradas = origemOutrasEntradas;
        this.valorOutrasEntradas = valorOutrasEntradas;
        this.detalhamentoOutrasEntradas = detalhamentoOutrasEntradas;
    }
    @NonNull
    public static ArrayList<Outras_Entradas> fonteDadosOriginal(int n){
        ArrayList<Outras_Entradas> lista = new ArrayList<>();
        for (int i=0; i<n; i++){
            Outras_Entradas novo = new Outras_Entradas();
            novo.dataOutrasEntradas = String.valueOf(i);
            novo.origemOutrasEntradas = String.valueOf(i);
            novo.valorOutrasEntradas = String.valueOf(i);
            lista.add(novo);
        }
        return lista;
    }


}
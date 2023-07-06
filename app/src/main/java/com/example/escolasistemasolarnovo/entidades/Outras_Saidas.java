package com.example.escolasistemasolarnovo.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "outras_saidas")
public class Outras_Saidas {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "data_outras_saidas")
    public String dataOutrasSaidas;

    @ColumnInfo(name = "origem_outras_saidas")
    public String origemOutrasSaidas;

    @ColumnInfo(name = "valor_outras_saidas")
    public String valorOutrasSaidas;

    @ColumnInfo(name = "detalhamento_outras_saidas")
    public String detalhamentoOutrasSaidas;

    public Outras_Saidas(int id, String dataOutrasSaidas, String origemOutrasSaidas, String valorOutrasSaidas, String detalhamentoOutrasSaidas) {
        this.id = id;
        this.dataOutrasSaidas = dataOutrasSaidas;
        this.origemOutrasSaidas = origemOutrasSaidas;
        this.valorOutrasSaidas = valorOutrasSaidas;
        this.detalhamentoOutrasSaidas = detalhamentoOutrasSaidas;
    }

    public Outras_Saidas() {}

    @NonNull
    public static ArrayList<Outras_Saidas> fonteDadosOriginal(int n){
        ArrayList<Outras_Saidas> lista = new ArrayList<>();
        for (int i=0; i<n; i++){
            Outras_Saidas novo = new Outras_Saidas();
            novo.dataOutrasSaidas = String.valueOf(i);
            novo.origemOutrasSaidas = String.valueOf(i);
            novo.valorOutrasSaidas = String.valueOf(i);
            lista.add(novo);
        }
        return lista;
    }

}
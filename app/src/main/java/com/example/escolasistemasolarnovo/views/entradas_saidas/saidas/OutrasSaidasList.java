package com.example.escolasistemasolarnovo.views.entradas_saidas.saidas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Outras_Entradas;
import com.example.escolasistemasolarnovo.entidades.Outras_Saidas;
import com.example.escolasistemasolarnovo.views.entradas_saidas.entradas.OutrasEntradasList;
import com.example.escolasistemasolarnovo.views.entradas_saidas.entradas.OutrasEntradasListADD;
import com.example.escolasistemasolarnovo.views.entradas_saidas.entradas.OutrasEntradasListAdapter;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialADM;

import java.util.ArrayList;

public class OutrasSaidasList extends AppCompatActivity implements TextWatcher {
    // atributos --------------------------
    private ImageButton ib_add1,ib_voltar_inicio;
    RecyclerView rvOutrasSaidas;
    EditText editPesquisa;
    OutrasSaidasListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outras_saidas_list);

        //********************* captura de componentes ***************
        editPesquisa = findViewById(R.id.editPesquisa);
        editPesquisa.addTextChangedListener(this); //define um evento de mudança de texto
        rvOutrasSaidas = findViewById(R.id.rvOutrasSaidas);
        rvOutrasSaidas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OutrasSaidasListAdapter(this);
        rvOutrasSaidas.setAdapter(adapter);

        //Eventos de botao
        ib_add1 = findViewById(R.id.ib_add1);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
    }
    public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {}
    public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {}
    public void afterTextChanged(Editable text) {
        String novoTexto = text.toString();//captura do novo texto
        ArrayList<Outras_Saidas> dadosOriginais = adapter.fonteDadosOriginal;
        ArrayList<Outras_Saidas> dadosFiltrados = new ArrayList<Outras_Saidas>();
        for (int i=0; i<dadosOriginais.size();i++){
            Outras_Saidas outras_saidas = dadosOriginais.get(i);
            if ((outras_saidas.origemOutrasSaidas.contains(novoTexto)) || (outras_saidas.dataOutrasSaidas.contains(novoTexto))) {
                dadosFiltrados.add(outras_saidas);
            }
        }
        adapter.mudarFonteDados(dadosFiltrados);

    }
    public void cliqueListner(View view){
        ib_add1.setOnClickListener(v -> {
            Intent intent = new Intent(OutrasSaidasList.this, OutrasSaidasListADD.class);
            startActivity(intent);
            finish();
        });
        ib_voltar_inicio.setOnClickListener(v -> {
            Intent intent = new Intent(OutrasSaidasList.this, MenuInicialADM.class);
            startActivity(intent);
            finish();
        });

    }

}
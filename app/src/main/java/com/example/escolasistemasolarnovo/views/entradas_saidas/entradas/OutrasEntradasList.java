package com.example.escolasistemasolarnovo.views.entradas_saidas.entradas;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Outras_Entradas;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialADM;

import java.util.ArrayList;

public class OutrasEntradasList extends AppCompatActivity implements TextWatcher {
    // atributos --------------------------
    private ImageButton ib_add1,ib_voltar_inicio;
    RecyclerView rvOutrasEntradas;
    EditText editPesquisa;
    OutrasEntradasListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outras_entradas_list);

        //********************* captura de componentes ***************
        editPesquisa = findViewById(R.id.editPesquisa);
        editPesquisa.addTextChangedListener(this); //define um evento de mudança de texto
        rvOutrasEntradas = findViewById(R.id.rvOutrasEntradas);
        rvOutrasEntradas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OutrasEntradasListAdapter(this);
        rvOutrasEntradas.setAdapter(adapter);

        //Eventos de botao
        ib_add1 = findViewById(R.id.ib_add1);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
    }
    public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {}
    public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {}
    public void afterTextChanged(Editable text) {
        String novoTexto = text.toString();//captura do novo texto
        ArrayList<Outras_Entradas> dadosOriginais = adapter.fonteDadosOriginal;
        ArrayList<Outras_Entradas> dadosFiltrados = new ArrayList<Outras_Entradas>();
        for (int i=0; i<dadosOriginais.size();i++){
            Outras_Entradas outras_entradas = dadosOriginais.get(i);
            if ((outras_entradas.origemOutrasEntradas.contains(novoTexto)) || (outras_entradas.dataOutrasEntradas.contains(novoTexto))) {
                dadosFiltrados.add(outras_entradas);
            }
        }
        adapter.mudarFonteDados(dadosFiltrados);

    }
    public void cliqueListner(View view){
        ib_add1.setOnClickListener(v -> {
            Intent intent = new Intent(OutrasEntradasList.this, OutrasEntradasListADD.class);
            startActivity(intent);
            finish();
        });
        ib_voltar_inicio.setOnClickListener(v -> {
            Intent intent = new Intent(OutrasEntradasList.this, MenuInicialADM.class);
            startActivity(intent);
            finish();
        });

    }

}

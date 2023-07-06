package com.example.escolasistemasolarnovo.views.receitas;

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
import com.example.escolasistemasolarnovo.entidades.Receitas;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialADM;

import java.util.ArrayList;

public class ReceitasList extends AppCompatActivity implements TextWatcher {
    // atributos --------------------------
    private ImageButton ib_add1,ib_voltar_inicio;
    RecyclerView rvReceita;
    EditText editPesquisa;
    ReceitasListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita_list);

        //********************* captura de componentes ***************
        editPesquisa = findViewById(R.id.editPesquisa);
        editPesquisa.addTextChangedListener(this); //define um evento de mudança de texto
        rvReceita = findViewById(R.id.rvReceita);
        rvReceita.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReceitasListAdapter(this);
        rvReceita.setAdapter(adapter);

        //Eventos de botao
        ib_add1 = findViewById(R.id.ib_add1);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
    }

    public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {}
    public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {}
    public void afterTextChanged(Editable text) {
        String novoTexto = text.toString();//captura do novo texto
        ArrayList<Receitas> dadosOriginais = adapter.fonteDadosOriginal;
        ArrayList<Receitas> dadosFiltrados = new ArrayList<Receitas>();
        for (int i=0; i<dadosOriginais.size();i++){
            Receitas receitas = dadosOriginais.get(i);
            if ((receitas.origemReceita.contains(novoTexto)) || (receitas.dataReceita.contains(novoTexto)) || (receitas.valorReceita.contains(novoTexto))) {
                dadosFiltrados.add(receitas);
            }
        }
        adapter.mudarFonteDados(dadosFiltrados);

    }

    public void cliqueListner(View view){
        ib_add1.setOnClickListener(v -> {
            Intent intent = new Intent(ReceitasList.this, ReceitasListADD.class);
            startActivity(intent);
        });
        ib_voltar_inicio.setOnClickListener(v -> {
            Intent intent = new Intent(ReceitasList.this, MenuInicialADM.class);
            startActivity(intent);
            finish();
        });

    }



}
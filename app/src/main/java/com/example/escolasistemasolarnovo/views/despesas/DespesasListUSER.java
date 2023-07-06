package com.example.escolasistemasolarnovo.views.despesas;

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
import com.example.escolasistemasolarnovo.entidades.Despesas;
import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.utils.SessionData;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialADM;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialUSER;

import java.util.ArrayList;

public class DespesasListUSER extends AppCompatActivity implements TextWatcher {
    // atributos --------------------------
    private ImageButton ib_voltar_inicio;
    RecyclerView rvDespesas;
    EditText editPesquisa;
    DespesasListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas_list_user);

        //********************* captura de componentes ***************
        editPesquisa = findViewById(R.id.editPesquisa);
        editPesquisa.addTextChangedListener(this); //define um evento de mudança de texto
        rvDespesas = findViewById(R.id.rvDespesas);
        rvDespesas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DespesasListAdapter(this);
        rvDespesas.setAdapter(adapter);

        //Eventos de botao
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
    }
    public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {}
    public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {}
    public void afterTextChanged(Editable text) {
        String novoTexto = text.toString();//captura do novo texto
        ArrayList<Despesas> dadosOriginais = adapter.fonteDadosOriginal;
        ArrayList<Despesas> dadosFiltrados = new ArrayList<Despesas>();
        for (int i=0; i<dadosOriginais.size();i++){
            Despesas despesas = dadosOriginais.get(i);
            if ((despesas.origemDespesas.contains(novoTexto)) || (despesas.dataDespesas.contains(novoTexto))) {
                dadosFiltrados.add(despesas);
            }
        }
        adapter.mudarFonteDados(dadosFiltrados);
    }
    public void cliqueListner(View view){
        findViewById(R.id.ib_voltar_inicio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuarioLogado = SessionData.getInstance().getUserLogado();
                if (usuarioLogado.perfil.equals("Usuário")){
                    startActivity(new Intent(DespesasListUSER.this, MenuInicialUSER.class));
                }else {
                    startActivity(new Intent(DespesasListUSER.this, MenuInicialADM.class));
                }
            }
        });
    }
}
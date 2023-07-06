package com.example.escolasistemasolarnovo.views.custos;

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
import com.example.escolasistemasolarnovo.entidades.Custos;
import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.utils.SessionData;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialADM;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialUSER;

import java.util.ArrayList;

public class CustoListUSER extends AppCompatActivity implements TextWatcher {
    // atributos --------------------------
    private ImageButton ib_voltar_inicio;
    RecyclerView rvCusto;
    EditText editPesquisa;
    CustoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custo_list_user);

        //********************* captura de componentes ***************
        editPesquisa = findViewById(R.id.editPesquisa);
        editPesquisa.addTextChangedListener(this); //define um evento de mudança de texto
        rvCusto = findViewById(R.id.rvCusto);
        rvCusto.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustoListAdapter(this);
        rvCusto.setAdapter(adapter);

        //Eventos de botao
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
    }
    public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {}
    public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {}
    public void afterTextChanged(Editable text) {
        String novoTexto = text.toString();//captura do novo texto
        ArrayList<Custos> dadosOriginais = adapter.fonteDadosOriginal;
        ArrayList<Custos> dadosFiltrados = new ArrayList<Custos>();
        for (int i=0; i<dadosOriginais.size();i++){
            Custos custos = dadosOriginais.get(i);
            if ((custos.origemCusto.contains(novoTexto)) || (custos.dataCusto.contains(novoTexto))) {
                dadosFiltrados.add(custos);
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
                    startActivity(new Intent(CustoListUSER.this, MenuInicialUSER.class));
                }else {
                    startActivity(new Intent(CustoListUSER.this, MenuInicialADM.class));
                }
            }
        });
    }
}

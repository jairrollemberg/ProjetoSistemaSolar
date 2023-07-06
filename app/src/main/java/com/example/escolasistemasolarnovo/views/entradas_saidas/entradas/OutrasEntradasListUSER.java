package com.example.escolasistemasolarnovo.views.entradas_saidas.entradas;

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
import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.utils.SessionData;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialADM;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialUSER;

import java.util.ArrayList;

public class OutrasEntradasListUSER extends AppCompatActivity implements TextWatcher {
    // atributos --------------------------
    private ImageButton ib_voltar_inicio;
    RecyclerView rvOutrasEntradas;
    EditText editPesquisa;
    OutrasEntradasListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outras_entradas_list_user);

        //********************* captura de componentes ***************
        editPesquisa = findViewById(R.id.editPesquisa);
        editPesquisa.addTextChangedListener(this); //define um evento de mudança de texto
        rvOutrasEntradas = findViewById(R.id.rvOutrasEntradas);
        rvOutrasEntradas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OutrasEntradasListAdapter(this);
        rvOutrasEntradas.setAdapter(adapter);

        //Eventos de botao
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
        findViewById(R.id.ib_voltar_inicio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuarioLogado = SessionData.getInstance().getUserLogado();
                if (usuarioLogado.perfil.equals("Usuário")){
                    startActivity(new Intent(OutrasEntradasListUSER.this, MenuInicialUSER.class));
                }else {
                    startActivity(new Intent(OutrasEntradasListUSER.this, MenuInicialADM.class));
                }
            }
        });
    }
}

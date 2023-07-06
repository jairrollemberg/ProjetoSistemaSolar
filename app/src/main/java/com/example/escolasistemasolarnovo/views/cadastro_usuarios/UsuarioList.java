package com.example.escolasistemasolarnovo.views.cadastro_usuarios;

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

import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialADM;

import java.util.ArrayList;

public class UsuarioList extends AppCompatActivity implements TextWatcher {
    // atributos ----------------
    private ImageButton ib_add1,ib_voltar_inicio;
    RecyclerView rvUsuario;
    EditText editPesquisa;
    UsuarioListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_list);

        //********************* captura de componentes ***************
        editPesquisa = findViewById(R.id.editPesquisa);
        editPesquisa.addTextChangedListener(this); //define um evento de mudança de texto
        rvUsuario = findViewById(R.id.rvUsuario);
        rvUsuario.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UsuarioListAdapter(this);
        rvUsuario.setAdapter(adapter);


        //Eventos de botao
        ib_add1 = findViewById(R.id.ib_add1);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
    }

        public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {}
        public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {}
        public void afterTextChanged(Editable text) {
            String novoTexto = text.toString();//captura do novo texto
            ArrayList<Usuario> dadosOriginais = adapter.fonteDadosOriginal;
            ArrayList<Usuario> dadosFiltrados = new ArrayList<Usuario>();
            for (int i=0; i<dadosOriginais.size();i++){
                Usuario usuario = dadosOriginais.get(i);
                if ((usuario.nome.contains(novoTexto)) || (usuario.email.contains(novoTexto))) {
                    dadosFiltrados.add(usuario);
                }
            }
            adapter.mudarFonteDados(dadosFiltrados);

        }

    public void cliqueListner(View view){
        ib_add1.setOnClickListener(v -> {
            Intent intent = new Intent(UsuarioList.this, UsuarioCadastro.class);
            startActivity(intent);
            finish();
        });
        ib_voltar_inicio.setOnClickListener(v -> {
            Intent intent = new Intent(UsuarioList.this, MenuInicialADM.class);
            startActivity(intent);
            finish();
        });

    }

}

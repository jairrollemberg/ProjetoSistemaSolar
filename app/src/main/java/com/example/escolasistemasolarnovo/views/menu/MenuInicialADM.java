package com.example.escolasistemasolarnovo.views.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.views.TelaLogin;
import com.example.escolasistemasolarnovo.views.cadastro_usuarios.UsuarioList;
import com.example.escolasistemasolarnovo.views.custos.CustoList;
import com.example.escolasistemasolarnovo.views.despesas.DespesasList;
import com.example.escolasistemasolarnovo.views.entradas_saidas.entradas.OutrasEntradasList;
import com.example.escolasistemasolarnovo.views.entradas_saidas.saidas.OutrasSaidasList;
import com.example.escolasistemasolarnovo.views.receitas.ReceitasList;

public class MenuInicialADM extends AppCompatActivity implements View.OnClickListener {
    // -----------atributos -----------------------
    ImageView ImageViewCustos, ImageViewReceitas, ImageViewDespesas, ImageViewOutrasEntradas, ImageViewOutrasSaidas, ImageViewUsuarios;
    Button btnSairTelaInicial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial_adm);

        //********************* captura de componentes ***************
        ImageViewCustos = findViewById(R.id.ImageViewCustos);
        ImageViewReceitas = findViewById(R.id.ImageViewReceitas);
        ImageViewDespesas = findViewById(R.id.ImageViewDespesas);
        ImageViewOutrasEntradas = findViewById(R.id.ImageViewOutrasEntradas);
        ImageViewOutrasSaidas = findViewById(R.id.ImageViewOutrasSaidas);
        ImageViewUsuarios = findViewById(R.id.ImageViewUsuarios);
        btnSairTelaInicial = findViewById(R.id.btnSairTelaInicial);

        //***************eventos de componentes*****

        ImageViewCustos.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInicialADM.this, CustoList.class);
            startActivity(intent);
        });
        ImageViewReceitas.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInicialADM.this, ReceitasList.class);
            startActivity(intent);
        });
        ImageViewDespesas.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInicialADM.this, DespesasList.class);
            startActivity(intent);
        });
        ImageViewOutrasSaidas.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInicialADM.this, OutrasSaidasList.class);
            startActivity(intent);
        });
        ImageViewOutrasEntradas.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInicialADM.this, OutrasEntradasList.class);
            startActivity(intent);
        });
        ImageViewUsuarios.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInicialADM.this, UsuarioList.class);
            startActivity(intent);
        });

        btnSairTelaInicial.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInicialADM.this, TelaLogin.class);
            startActivity(intent);
            finish();
        });
    }
    //***************metodos interface View.OnClickListener*****
    @Override
    public void onClick(View v) {

    }
}

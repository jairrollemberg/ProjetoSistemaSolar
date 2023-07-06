package com.example.escolasistemasolarnovo.views.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.views.TelaLogin;
import com.example.escolasistemasolarnovo.views.custos.CustoListUSER;
import com.example.escolasistemasolarnovo.views.despesas.DespesasListUSER;
import com.example.escolasistemasolarnovo.views.entradas_saidas.entradas.OutrasEntradasListUSER;
import com.example.escolasistemasolarnovo.views.entradas_saidas.saidas.OutrasSaidasListUSER;
import com.example.escolasistemasolarnovo.views.receitas.ReceitasListUSER;

public class MenuInicialUSER extends AppCompatActivity implements View.OnClickListener {
        // -----------atributos -----------------------
        ImageView ImageViewCustos, ImageViewReceitas, ImageViewDespesas, ImageViewOutrasEntradas, ImageViewOutrasSaidas;
        Button btnSairTelaInicial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial_user);

        //********************* captura de componentes ***************
        ImageViewCustos = findViewById(R.id.ImageViewCustos);
        ImageViewReceitas = findViewById(R.id.ImageViewReceitas);
        ImageViewDespesas = findViewById(R.id.ImageViewDespesas);
        ImageViewOutrasEntradas = findViewById(R.id.ImageViewOutrasEntradas);
        ImageViewOutrasSaidas = findViewById(R.id.ImageViewOutrasSaidas);
        btnSairTelaInicial = findViewById(R.id.btnSairTelaInicial);

        //***************eventos de componentes*****

        ImageViewCustos.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInicialUSER.this, CustoListUSER.class);
            startActivity(intent);
        });
        ImageViewReceitas.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInicialUSER.this, ReceitasListUSER.class);
            startActivity(intent);
        });
        ImageViewDespesas.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInicialUSER.this, DespesasListUSER.class);
            startActivity(intent);
        });
        ImageViewOutrasSaidas.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInicialUSER.this, OutrasSaidasListUSER.class);
            startActivity(intent);
        });
        ImageViewOutrasEntradas.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInicialUSER.this, OutrasEntradasListUSER.class);
            startActivity(intent);
        });

        btnSairTelaInicial.setOnClickListener(v -> {
            Intent intent = new Intent(MenuInicialUSER.this, TelaLogin.class);
            startActivity(intent);
            finish();
        });
    }
    //***************metodos interface View.OnClickListener*****
    @Override
    public void onClick(View v) {

    }
}
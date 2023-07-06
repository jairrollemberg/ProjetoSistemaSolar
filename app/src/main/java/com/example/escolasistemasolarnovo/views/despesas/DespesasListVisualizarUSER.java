package com.example.escolasistemasolarnovo.views.despesas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.utils.SessionData;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialADM;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialUSER;


public class DespesasListVisualizarUSER extends AppCompatActivity implements View.OnClickListener{

    //Atributos*****
    TextView dataDespesasVisualizar,origemDespesasVisualizar, detalhamentoDespesasVisualizar, valorDespesasVisualizar;
    int id;
    ImageButton ib_voltar_inicio;
    private TextView dataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas_list_visualizar_user);

        //******captura dos componentes******
        dataDespesasVisualizar = findViewById(R.id.dataDespesasVisualizar);
        origemDespesasVisualizar = findViewById(R.id.origemDespesasVisualizar);
        valorDespesasVisualizar = findViewById(R.id.valorDespesasVisualizar);
        detalhamentoDespesasVisualizar = findViewById(R.id.detalhamentoDespesasVisualizar);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        dataBtn = findViewById(R.id.dataPickerBtn);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            dataBtn.setText(extras.getString("data"));
            origemDespesasVisualizar.setText(extras.getString("origem"));
            valorDespesasVisualizar.setText(extras.getString("valor"));
            detalhamentoDespesasVisualizar.setText(extras.getString("obs"));
            id = extras.getInt("id");
        }
    }
    public void cliqueListner(View view){
        ib_voltar_inicio.setOnClickListener(v -> {
            Intent intent = new Intent(DespesasListVisualizarUSER.this, DespesasListUSER.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View v) {
        Usuario userLogado = SessionData.getInstance().getUserLogado();
        if (userLogado.perfil.equals("Usuário")){
            startActivity(new Intent(DespesasListVisualizarUSER.this, MenuInicialUSER.class));
        }else {
            startActivity(new Intent(DespesasListVisualizarUSER.this, MenuInicialADM.class));
        }
    }
}

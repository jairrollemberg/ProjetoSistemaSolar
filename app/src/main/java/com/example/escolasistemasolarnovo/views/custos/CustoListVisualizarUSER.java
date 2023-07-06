package com.example.escolasistemasolarnovo.views.custos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.utils.SessionData;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialADM;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialUSER;


public class CustoListVisualizarUSER extends AppCompatActivity implements View.OnClickListener{

    //Atributos*****
    TextView dataCustoVisualizar ,origemCustoVisualizar, detalhamentoCustoVisualizar, valorCustoVisualizar;
    int id;
    ImageButton ib_voltar_inicio;
    private TextView dataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custo_list_visualizar_user);

        //******captura dos componentes******
        dataCustoVisualizar = findViewById(R.id.dataCustoVisualizar);
        origemCustoVisualizar = findViewById(R.id.origemCustoVisualizar);
        valorCustoVisualizar = findViewById(R.id.valorCustoVisualizar);
        detalhamentoCustoVisualizar = findViewById(R.id.detalhamentoCustoVisualizar);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        dataBtn = findViewById(R.id.dataPickerBtn);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            dataBtn.setText(extras.getString("data"));
            origemCustoVisualizar.setText(extras.getString("origem"));
            valorCustoVisualizar.setText(extras.getString("valor"));
            detalhamentoCustoVisualizar.setText(extras.getString("obs"));
            id = extras.getInt("id");
        }
    }
    public void cliqueListner(View view){
        ib_voltar_inicio.setOnClickListener(v -> {
            Intent intent = new Intent(CustoListVisualizarUSER.this, CustoListUSER.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View v) {
        Usuario userLogado = SessionData.getInstance().getUserLogado();
        if (userLogado.perfil.equals("Usuário")){
            startActivity(new Intent(CustoListVisualizarUSER.this, MenuInicialUSER.class));
        }else {
            startActivity(new Intent(CustoListVisualizarUSER.this, MenuInicialADM.class));
        }
    }
}

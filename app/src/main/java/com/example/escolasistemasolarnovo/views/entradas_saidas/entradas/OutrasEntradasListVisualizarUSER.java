package com.example.escolasistemasolarnovo.views.entradas_saidas.entradas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.utils.SessionData;
import com.example.escolasistemasolarnovo.views.custos.CustoListUSER;
import com.example.escolasistemasolarnovo.views.custos.CustoListVisualizarUSER;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialADM;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialUSER;

public class OutrasEntradasListVisualizarUSER extends AppCompatActivity implements View.OnClickListener{

    //Atributos*****
    TextView dataOutrasEntradasVisualizar ,origemOutrasEntradasVisualizar, detalhamentoOutrasEntradasVisualizar, valorOutrasEntradasVisualizar;
    int id;
    ImageButton ib_voltar_inicio;
    private TextView dataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outras_entradas_list_visualizar_user);

        //******captura dos componentes******
        dataOutrasEntradasVisualizar = findViewById(R.id.dataOutrasEntradasVisualizar);
        origemOutrasEntradasVisualizar = findViewById(R.id.origemOutrasEntradasVisualizar);
        valorOutrasEntradasVisualizar = findViewById(R.id.valorOutrasEntradasVisualizar);
        detalhamentoOutrasEntradasVisualizar = findViewById(R.id.detalhamentoOutrasEntradasVisualizar);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        dataBtn = findViewById(R.id.dataPickerBtn);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            dataBtn.setText(extras.getString("data"));
            origemOutrasEntradasVisualizar.setText(extras.getString("origem"));
            valorOutrasEntradasVisualizar.setText(extras.getString("valor"));
            detalhamentoOutrasEntradasVisualizar.setText(extras.getString("obs"));
            id = extras.getInt("id");
        }
    }
    public void cliqueListner(View view){
        ib_voltar_inicio.setOnClickListener(v -> {
            Intent intent = new Intent(OutrasEntradasListVisualizarUSER.this, OutrasEntradasListUSER.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View v) {
        Usuario userLogado = SessionData.getInstance().getUserLogado();
        if (userLogado.perfil.equals("Usuário")){
            startActivity(new Intent(OutrasEntradasListVisualizarUSER.this, MenuInicialUSER.class));
        }else {
            startActivity(new Intent(OutrasEntradasListVisualizarUSER.this, MenuInicialADM.class));
        }
    }
}
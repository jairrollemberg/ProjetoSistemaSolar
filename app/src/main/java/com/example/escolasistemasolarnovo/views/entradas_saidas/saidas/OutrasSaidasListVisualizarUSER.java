package com.example.escolasistemasolarnovo.views.entradas_saidas.saidas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.utils.SessionData;
import com.example.escolasistemasolarnovo.views.entradas_saidas.entradas.OutrasEntradasListUSER;
import com.example.escolasistemasolarnovo.views.entradas_saidas.entradas.OutrasEntradasListVisualizarUSER;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialADM;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialUSER;

public class OutrasSaidasListVisualizarUSER extends AppCompatActivity implements View.OnClickListener{

    //Atributos*****
    TextView dataOutrasSaidasVisualizar ,origemOutrasSaidasVisualizar, detalhamentoOutrasSaidasVisualizar, valorOutrasSaidasVisualizar;
    int id;
    ImageButton ib_voltar_inicio;
    private TextView dataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outras_saidas_list_visualizar_user);

        //******captura dos componentes******
        dataOutrasSaidasVisualizar = findViewById(R.id.dataOutrasSaidasVisualizar);
        origemOutrasSaidasVisualizar = findViewById(R.id.origemOutrasSaidasVisualizar);
        valorOutrasSaidasVisualizar = findViewById(R.id.valorOutrasSaidasVisualizar);
        detalhamentoOutrasSaidasVisualizar = findViewById(R.id.detalhamentoOutrasSaidasVisualizar);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        dataBtn = findViewById(R.id.dataPickerBtn);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            dataBtn.setText(extras.getString("data"));
            origemOutrasSaidasVisualizar.setText(extras.getString("origem"));
            valorOutrasSaidasVisualizar.setText(extras.getString("valor"));
            detalhamentoOutrasSaidasVisualizar.setText(extras.getString("obs"));
            id = extras.getInt("id");
        }
    }
    public void cliqueListner(View view){
        ib_voltar_inicio.setOnClickListener(v -> {
            Intent intent = new Intent(OutrasSaidasListVisualizarUSER.this, OutrasSaidasListUSER.class);
            startActivity(intent);
        });
    }
    @Override
    public void onClick(View v) {
        Usuario userLogado = SessionData.getInstance().getUserLogado();
        if (userLogado.perfil.equals("Usuário")){
            startActivity(new Intent(OutrasSaidasListVisualizarUSER.this, MenuInicialUSER.class));
        }else {
            startActivity(new Intent(OutrasSaidasListVisualizarUSER.this, MenuInicialADM.class));
        }
    }
}
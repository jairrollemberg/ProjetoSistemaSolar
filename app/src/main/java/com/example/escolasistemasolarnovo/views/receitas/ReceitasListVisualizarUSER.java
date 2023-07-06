package com.example.escolasistemasolarnovo.views.receitas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Usuario;
import com.example.escolasistemasolarnovo.utils.SessionData;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialADM;
import com.example.escolasistemasolarnovo.views.menu.MenuInicialUSER;

import java.util.Calendar;

public class ReceitasListVisualizarUSER extends AppCompatActivity implements View.OnClickListener{

    //Atributos*****
    int id;
    ImageButton ib_voltar_inicio;
    TextView dataReceitaVisualizar, valorReceitaVisualizar,origemReceitaVisualizar, detalhamentoReceitaVisualizar;
    private TextView dataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas_list_visualizar_user);


        //******captura dos componentes******
        dataReceitaVisualizar = findViewById(R.id.dataReceitaVisualizar);
        origemReceitaVisualizar = findViewById(R.id.origemReceitaVisualizar);
        valorReceitaVisualizar = findViewById(R.id.valorReceitaVisualizar);
        detalhamentoReceitaVisualizar = findViewById(R.id.detalhamentoReceitaVisualizar);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        dataBtn = findViewById(R.id.dataPickerBtn);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            dataBtn.setText(extras.getString("data"));
            origemReceitaVisualizar.setText(extras.getString("origem"));
            valorReceitaVisualizar.setText(extras.getString("valor"));
            detalhamentoReceitaVisualizar.setText(extras.getString("obs"));
            id = extras.getInt("id");
        }
    }
    public void cliqueListner(View view){
        ib_voltar_inicio.setOnClickListener(v -> {
            Intent intent = new Intent(ReceitasListVisualizarUSER.this, ReceitasListUSER.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View v) {
        Usuario userLogado = SessionData.getInstance().getUserLogado();
        if (userLogado.perfil.equals("Usuário")){
            startActivity(new Intent(ReceitasListVisualizarUSER.this, MenuInicialUSER.class));
        }else {
            startActivity(new Intent(ReceitasListVisualizarUSER.this, MenuInicialADM.class));
        }
    }
}
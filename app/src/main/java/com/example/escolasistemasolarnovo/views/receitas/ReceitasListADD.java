package com.example.escolasistemasolarnovo.views.receitas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.escolasistemasolarnovo.DAO.ReceitasDAO;
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Receitas;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;
import com.example.escolasistemasolarnovo.views.entradas_saidas.entradas.OutrasEntradasList;
import com.example.escolasistemasolarnovo.views.entradas_saidas.entradas.OutrasEntradasListADD;

import java.util.Calendar;

public class ReceitasListADD extends AppCompatActivity implements View.OnClickListener {

    //Atributos*****
    EditText origemReceitaAdd, mlTextReceitaAdd, valorReceitaAdd;
    ImageButton ib_voltar_inicio;
    Button btnsalvarReceita;
    TextView dataReceitaAdd;
    BancoDeDados bd;

    private DatePickerDialog datePickerDialog;
    private Button dataBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita_list_add);
        initDatePicker();

        //******captura dos componentes******
        dataReceitaAdd = findViewById(R.id.dataReceitaAdd);
        origemReceitaAdd = findViewById(R.id.origemReceitaAdd);
        valorReceitaAdd = findViewById(R.id.valorReceitaAdd);
        mlTextReceitaAdd = findViewById(R.id.detalhamentoReceitaAdd);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        btnsalvarReceita = findViewById(R.id.btnsalvarReceita);
        dataBtn = findViewById(R.id.dataPickerBtn);
        dataBtn.setText(getTodaysDate());
        dataReceitaAdd.setOnClickListener(this);
        btnsalvarReceita.setOnClickListener(this);
    }
    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        mes = mes +1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(dia, mes, ano);
    }

    public void cliqueListner(View view){
        ib_voltar_inicio.setOnClickListener(v -> {
            Intent intent = new Intent(ReceitasListADD.this, ReceitasList.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View origem) {
        String dataReceita = dataBtn.getText().toString();
        String origemReceita = origemReceitaAdd.getText().toString();
        String valorReceita = valorReceitaAdd.getText().toString();


        if (origem.getId() == R.id.btnsalvarReceita) {
            if (dataReceita.isEmpty() || origemReceita.isEmpty() || valorReceita.isEmpty()) {//***verifica se os campos nao estao vazios***
                Toast.makeText(this, "Dados Incompletos!!",Toast.LENGTH_LONG).show();
            }else{
                capturarDados();
            }
        }
    }
    private void capturarDados() { //insere informaçoes no banco
        String dataReceita = dataBtn.getText().toString();
        String origemReceita = origemReceitaAdd.getText().toString();
        String valorReceita = valorReceitaAdd.getText().toString();
        String detalhamentoReceita = mlTextReceitaAdd.getText().toString();

        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        ReceitasDAO receitasDAO = bd.getReceitasDAO();

        Receitas receitas = new Receitas();
        receitas.dataReceita = dataReceita;
        receitas.origemReceita = origemReceita;
        receitas.valorReceita = valorReceita;
        receitas.detalhamento_receita = detalhamentoReceita;

        receitasDAO.insert(receitas);
        Toast.makeText(this, "Cadastrado Realizado!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ReceitasListADD.this, ReceitasList.class);
        startActivity(intent);
        finish();
    }
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, dia, mes, ano) -> {
            mes = mes + 1;
            String date = makeDateString(ano,mes,dia);
            dataBtn.setText(date);
        };
        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener,ano,mes,dia );
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int dia, int mes, int ano) {
        return dia+" / " + getMonthFormat(mes) + " / " + ano;
    }

    private String getMonthFormat(int mes) {
        if (mes == 1)
            return "JAN";
        if (mes == 2)
            return "FEV";
        if (mes == 3)
            return "MAR";
        if (mes == 4)
            return "ABR";
        if (mes == 5)
            return "MAI";
        if (mes == 6)
            return "JUN";
        if (mes == 7)
            return "JUL";
        if (mes == 8)
            return "AGO";
        if (mes == 9)
            return "SET";
        if (mes == 10)
            return "OUT";
        if (mes == 11)
            return "NOV";
        if (mes == 12)
            return "DEZ";
        return "JAN";
    }
    public void openDataPicker(View view) {
        datePickerDialog.show();
    }

    }
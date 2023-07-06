package com.example.escolasistemasolarnovo.views.despesas;

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

import com.example.escolasistemasolarnovo.DAO.DespesasDAO;
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Despesas;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;
import com.example.escolasistemasolarnovo.views.custos.CustoList;
import com.example.escolasistemasolarnovo.views.custos.CustoListADD;

import java.util.Calendar;

public class DespesasListADD extends AppCompatActivity implements View.OnClickListener {

    //Atributos*****
    EditText mlTextDespesasAdd, origemDespesasAdd, valorDespesasAdd;
    ImageButton ib_voltar_inicio;
    Button btnsalvarDespesas;
    TextView dataDespesasAdd;
    BancoDeDados bd;

    private DatePickerDialog datePickerDialog;
    private Button dataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas_list_add);
        initDatePicker();

        //******captura dos componentes******
        dataDespesasAdd = findViewById(R.id.dataDespesasAdd);
        origemDespesasAdd = findViewById(R.id.origemDespesasAdd);
        valorDespesasAdd = findViewById(R.id.valorDespesasAdd);
        mlTextDespesasAdd = findViewById(R.id.detalhamentoDespesasAdd);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        btnsalvarDespesas = findViewById(R.id.btnsalvarDespesas);
        dataBtn = findViewById(R.id.dataPickerBtn);
        dataBtn.setText(getTodaysDate());
        dataDespesasAdd.setOnClickListener(this);
        btnsalvarDespesas.setOnClickListener(this);
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
            Intent intent = new Intent(DespesasListADD.this, DespesasList.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View origem) {
        String dataDespesas = dataBtn.getText().toString();
        String origemDespesas = origemDespesasAdd.getText().toString();
        String valorDespesas = valorDespesasAdd.getText().toString();


        if (origem.getId() == R.id.btnsalvarDespesas) {
            if (dataDespesas.isEmpty() || origemDespesas.isEmpty() || valorDespesas.isEmpty()) {//***verifica se os campos nao estao vazios***
                Toast.makeText(this, "Dados Incompletos!!",Toast.LENGTH_LONG).show();
            }else{
                capturarDados();
            }
        }
    }
    private void capturarDados() { //insere informaçoes no banco
        String dataDespesas = dataBtn.getText().toString();
        String origemDespesas = origemDespesasAdd.getText().toString();
        String valorDespesas = valorDespesasAdd.getText().toString();
        String detalhamentoDespesas = mlTextDespesasAdd.getText().toString();

        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        DespesasDAO despesasDAO = bd.getDespesasDAO();

        Despesas despesas = new Despesas();
        despesas.dataDespesas = dataDespesas;
        despesas.origemDespesas = origemDespesas;
        despesas.valorDespesas = valorDespesas;
        despesas.detalhamento_despesas = detalhamentoDespesas;

        despesasDAO.insert(despesas);
        Toast.makeText(this, "Cadastrado Realizado!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(DespesasListADD.this, DespesasList.class);
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

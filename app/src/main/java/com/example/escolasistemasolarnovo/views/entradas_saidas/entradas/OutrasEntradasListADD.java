package com.example.escolasistemasolarnovo.views.entradas_saidas.entradas;

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

import com.example.escolasistemasolarnovo.DAO.CustosDAO;
import com.example.escolasistemasolarnovo.DAO.Outras_EntradasDAO;
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Custos;
import com.example.escolasistemasolarnovo.entidades.Outras_Entradas;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;
import com.example.escolasistemasolarnovo.views.custos.CustoList;
import com.example.escolasistemasolarnovo.views.custos.CustoListADD;

import java.util.Calendar;

public class OutrasEntradasListADD extends AppCompatActivity implements View.OnClickListener{

    //Atributos*****
    EditText origemOutrasEntradasAdd, mlTextOutrasEntradasAdd, valorOutrasEntradasAdd;
    ImageButton ib_voltar_inicio;
    Button btnsalvarOutrasEntradas;
    TextView dataOutrasEntradasAdd;
    BancoDeDados bd;


    private DatePickerDialog datePickerDialog;
    private Button dataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outras_entradas_add);
        initDatePicker();

        //******captura dos componentes******
        dataOutrasEntradasAdd = findViewById(R.id.dataOutrasEntradasAdd);
        origemOutrasEntradasAdd = findViewById(R.id.origemOutrasEntradasAdd);
        valorOutrasEntradasAdd = findViewById(R.id.valorOutrasEntradasAdd);
        mlTextOutrasEntradasAdd = findViewById(R.id.detalhamentoOutrasEntradasAdd);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        btnsalvarOutrasEntradas = findViewById(R.id.btnsalvarOutrasEntradas);
        dataBtn = findViewById(R.id.dataPickerBtn);
        dataBtn.setText(getTodaysDate());
        dataOutrasEntradasAdd.setOnClickListener(this);
        btnsalvarOutrasEntradas.setOnClickListener(this);
    }
    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        mes = mes +1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(dia, mes, ano);
    }
    public void cliqueListner(View view) {
        ib_voltar_inicio.setOnClickListener(v -> {
            Intent intent = new Intent(OutrasEntradasListADD.this, OutrasEntradasList.class);
            startActivity(intent);
            finish();
        });
    }
    @Override
    public void onClick(View origem) {
        String dataOutrasEntradas = dataBtn.getText().toString();
        String origemOutrasEntradas = origemOutrasEntradasAdd.getText().toString();
        String valorOutrasEntradas = valorOutrasEntradasAdd.getText().toString();


        if (origem.getId() == R.id.btnsalvarOutrasEntradas) {
            if (dataOutrasEntradas.isEmpty() || origemOutrasEntradas.isEmpty() || valorOutrasEntradas.isEmpty()) {//***verifica se os campos nao estao vazios***
                Toast.makeText(this, "Dados Incompletos!!", Toast.LENGTH_LONG).show();
            } else {
                capturarDados();
            }
        }
    }
    private void capturarDados() { //insere informaçoes no banco
        String dataOutrasEntradas = dataBtn.getText().toString();
        String origemOutrasEntradas = origemOutrasEntradasAdd.getText().toString();
        String valorOutrasEntradas = valorOutrasEntradasAdd.getText().toString();
        String detalhamentoOutrasEntradas = mlTextOutrasEntradasAdd.getText().toString();

        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        Outras_EntradasDAO outras_entradasDAO = bd.getOutras_EntradasDAO();

        Outras_Entradas outras_entradas = new Outras_Entradas();
        outras_entradas.dataOutrasEntradas = dataOutrasEntradas;
        outras_entradas.origemOutrasEntradas = origemOutrasEntradas;
        outras_entradas.valorOutrasEntradas = valorOutrasEntradas;
        outras_entradas.detalhamentoOutrasEntradas = detalhamentoOutrasEntradas;

        outras_entradasDAO.insert(outras_entradas);
        Toast.makeText(this, "Cadastrado Realizado!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(OutrasEntradasListADD.this, OutrasEntradasList.class);
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







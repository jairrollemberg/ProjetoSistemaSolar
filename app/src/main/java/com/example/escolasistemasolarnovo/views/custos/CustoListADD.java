package com.example.escolasistemasolarnovo.views.custos;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.escolasistemasolarnovo.DAO.CustosDAO;
import com.example.escolasistemasolarnovo.R;
import com.example.escolasistemasolarnovo.entidades.Custos;
import com.example.escolasistemasolarnovo.utils.BancoDeDados;

import java.util.Calendar;


public class CustoListADD extends AppCompatActivity implements View.OnClickListener {

    //Atributos*****
    EditText origemCustoAdd, mlTextCustoAdd, valorCustoAdd;
    ImageButton ib_voltar_inicio;
    Button btnsalvarCusto;
    TextView dataCustoAdd;
    BancoDeDados bd;

    private DatePickerDialog datePickerDialog;
    private Button dataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custos_add);
        initDatePicker();

        //******captura dos componentes******
        dataCustoAdd = findViewById(R.id.dataCustoAdd);
        origemCustoAdd = findViewById(R.id.origemCustoAdd);
        valorCustoAdd = findViewById(R.id.valorCustoAdd);
        mlTextCustoAdd = findViewById(R.id.detalhamentoCustoAdd);
        ib_voltar_inicio = findViewById(R.id.ib_voltar_inicio);
        btnsalvarCusto = findViewById(R.id.btnsalvarCusto);
        dataBtn = findViewById(R.id.dataPickerBtn);
        dataBtn.setText(getTodaysDate());
        dataCustoAdd.setOnClickListener(this);
        btnsalvarCusto.setOnClickListener(this);
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
            Intent intent = new Intent(CustoListADD.this, CustoList.class);
            startActivity(intent);
        });
    }
    @Override
    public void onClick(View origem) {
        String dataCusto = dataBtn.getText().toString();
        String origemCusto = origemCustoAdd.getText().toString();
        String valorCusto = valorCustoAdd.getText().toString();


        if (origem.getId() == R.id.btnsalvarCusto) {
            if (dataCusto.isEmpty() || origemCusto.isEmpty() || valorCusto.isEmpty()) {//***verifica se os campos nao estao vazios***
                Toast.makeText(this, "Dados Incompletos!!", Toast.LENGTH_LONG).show();
            } else {
                capturarDados();
            }
        }
    }
    private void capturarDados() { //insere informaçoes no banco
        String dataCusto = dataBtn.getText().toString();
        String origemCusto = origemCustoAdd.getText().toString();
        String valorCusto = valorCustoAdd.getText().toString();
        String detalhamentoCusto = mlTextCustoAdd.getText().toString();

        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "BancoApp").allowMainThreadQueries().build();
        CustosDAO custosDAO = bd.getCustosDAO();

        Custos custos = new Custos();
        custos.dataCusto = dataCusto;
        custos.origemCusto = origemCusto;
        custos.valorCusto = valorCusto;
        custos.detalhamento_custo = detalhamentoCusto;

        custosDAO.insert(custos);
        Toast.makeText(this, "Cadastrado Realizado!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CustoListADD.this, CustoList.class);
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